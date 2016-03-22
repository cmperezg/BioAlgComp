package algcomp.alg;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import algcomp.util.Dpoint;
import algcomp.util.Function;
import algcomp.util.Graph;

public class BatAlgorithmTSP {
	Graph g;
	double min_freq;
	double max_freq;
	double max_loudness;
	
	int numbats;
	
	
	BatTSP[] bats_list;
	double[] bats_pulse_frequency;
	double[] bats_pulse_rate;
	double[] initial_bats_pulse_rate;
	double[] bats_loudness;
	
	BatTSP bestbat;
	
	int timer;
	Random rand;
	double iteration; //gencounter
	
	double average_loudness;
	
	
	public BatAlgorithmTSP(Graph _g,int _numbats, int _tol){
		rand = new Random();
		g = _g;
		min_freq = 0;
		max_freq = 10;
		max_loudness = 50;
		numbats = _numbats;
		bats_list = new BatTSP[numbats];
		timer = _tol;
		
		bats_pulse_frequency = new double[numbats];
		bats_pulse_rate = new double[numbats];
		initial_bats_pulse_rate = new double[numbats];
		bats_loudness = new double[numbats];
		for(int i = 0; i<numbats;i++){
			bats_list[i] = new BatTSP(g.size());
			bats_list[i].setId(i);
			initBat(bats_list[i]);
			bats_pulse_frequency[i] = rand.nextDouble()*((max_freq - min_freq)+min_freq);
			initial_bats_pulse_rate[i] = rand.nextDouble();
			bats_loudness[i] = rand.nextDouble()*max_loudness;
		}
				
		bats_pulse_rate = initial_bats_pulse_rate;
		iteration = 0;
		
		bestbat = new BatTSP(g.size());
		initBat(bestbat);
		
		average_loudness = mean(bats_loudness);
		

	}
	
	public BatTSP step(){
		
		bestbat = getBest();
		average_loudness = mean(bats_loudness);
		updateBats();
		for(int i = 0; i<bats_list.length; i++){
			//System.out.println(bats_pulse_rate[i]);
			double random = rand.nextDouble();
			if(rand.nextDouble() > bats_pulse_rate[i]){
				int moves = (int)Math.ceil(random*average_loudness);
				
				bats_list[i].setNewposition(mutate(bestbat,moves));
			}else{
				bats_list[i].setNewposition(mutate(bats_list[i],1));
			}
			
			eval(bats_list[i]);	
			if(rand.nextDouble()*max_loudness < bats_loudness[i]
					& (bats_list[i].getNeweval()
					<= bats_list[i].getEval())){
				bats_list[i].setPosition(bats_list[i].getNewposition());
				eval(bats_list[i]);
				bats_loudness[i] = updateLoudness(bats_loudness[i]);
				bats_pulse_rate[i] = updatePulseRate(bats_list[i]);
				
			}
					
			if(bats_list[i].getEval()
					< bestbat.getEval()){
				bestbat = bats_list[i];
			}
		}
		iteration++;
		System.out.println("bestbat" + bestbat.toString());
		return bestbat;
	}
	
	private int[] mutate(BatTSP bat, int moves) {
		Random random =  new Random();
		int mutpoint;
		int temp = 0;
		
		int[] path = new int[g.size()];
		for(int i = 0; i<g.size();i++){
			path[i] = bat.getPosition()[i];
		}
		
		for(int i = 0; i<moves; i++){
			mutpoint  = random.nextInt(g.size()-1);
			if(mutpoint == g.size()-1){
				temp = path[g.size()-1];
				path[g.size()-1] = path[0];
				path[0] = temp;
			}else{
				temp = path[mutpoint];
				path[mutpoint] = path[mutpoint+1];
				path[mutpoint+1] = temp;
			}
		}
		
		return path;
	}

	public BatTSP fullrun(){
		System.out.println("TSP Bat run");
		
		
		final Thread thisThread = Thread.currentThread();
		final int timeToRun = timer; // 1200 = 2 minutes;

		new Thread(new Runnable() {
		    public void run() {
		        try {
					thisThread.sleep(timeToRun);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Problem with putting thread to sleep");
				}
		        thisThread.interrupt();
		    }
		}).start();

		while (!Thread.interrupted()) {
		    step();
		}
		
		return bestbat;
	}
	
	void eval(BatTSP b){
		double ev = 0.0;
		double nev = 0.0;
		int[] path = b.getPosition();
		int[] npath = b.getNewposition();
		//printArray(path);
		for(int i=0; i<path.length-1;i++){
			ev = ev + g.getPoint(path[i]).distanceTo(g.getPoint(path[i+1]));
			nev = nev + g.getPoint(npath[i]).distanceTo(g.getPoint(npath[i+1]));
		}
		
		b.setEval(ev);
		b.setNeweval(nev);

	}
	
	void initBat(BatTSP b){
		b.setVelocity(rand.nextInt(g.size()));
		
		eval(b);

		
	}
	void updateFrequency(){
		for(int i =0; i<numbats; i++){
			double value = min_freq + rand.nextDouble()*(max_freq-min_freq);
			bats_pulse_frequency[i] = value;
		}
	}
	
	void updateVelocity(BatTSP b){
		double velx = b.getVelocity()+(SinglePathDist(b,bestbat));
		
		b.setVelocity((int)velx);
	}
	
	void updatePosition(Bat b){
		b.setNewposition(new Dpoint(b.getPosition().getX()+b.getVelocity().getX(),b.getPosition().getY()+b.getVelocity().getY()));
		b.setPosition(b.getNewposition()); // ???
	}
	
	double updateLoudness(double oldloudness){
		double alpha = 0.99;
		return oldloudness*alpha;
	}
	
	double updatePulseRate(BatTSP b){
		
		//double genv = Math.atan2(b.getVelocity().getX(), b.getVelocity().getY());
		//double gamma = genv/bats_pulse_frequency[b.getId()];
		double gamma = 0.01;
		double ret = (1-Math.exp(-gamma*(iteration/1000))*bats_pulse_rate[b.getId()]);
		//System.out.println(ret);
		return ret;
	}
	
	//plus or minus, random.
	int pom(){
		double r = rand.nextDouble();
		if(r<0.5)
			return -1;
		return 1;
	}
	
	BatTSP getBest(){
		//BatTSP b = new BatTSP(g.size());
		
		for(int i =0; i<numbats; i++){
			if(bats_list[i].getEval() < bestbat.getEval()){
				bestbat = bats_list[i];
				//b = bats_list[i];
			}
			//return bestbat;
		}
		
		return bestbat;
	}
	
	
	public static double mean(double[] m) {
	    double sum = 0;
	    for (int i = 0; i < m.length; i++) {
	        sum += m[i];
	    }
	    return sum / m.length;
	}
	
	void updateBats(){
		updateFrequency();
		for(int i = 0; i<numbats; i++){
			updateVelocity(bats_list[i]);
			//updatePosition(bats_list[i]);
			//System.out.println(bats_list[i].toString());
		}
		
		
	}
	private int SinglePathDist(BatTSP bA,BatTSP bB){
		int i,j;
		int Dist=0;
		for(i=0;i<bA.getPosition().length-1;i++){
			for (j=0;j<bA.getPosition().length-1;j++){
				if(bA.getPosition()[i]!=bB.getPosition()[j] || bA.getPosition()[i+1]!=bB.getPosition()[j+1] ){
					Dist=Dist+1;
				}
					
			}
		} 
		return 2*g.size()-Dist;
	}
}
