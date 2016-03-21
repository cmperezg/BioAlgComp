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
	
	double tol;
	Random rand;
	double iteration; //gencounter
	
	double average_loudness;
	
	
//	public BatAlgorithmFunction(Graph _g,int _numbats, double _tol){
//		rand = new Random();
//		g = _g;
//		min_freq = 0;
//		max_freq = 10;
//		max_loudness = 100;
//		numbats = _numbats;
//		bats_list = new BatTSP[numbats];
//		tol = _tol;
//		
//		bats_pulse_frequency = new double[numbats];
//		bats_pulse_rate = new double[numbats];
//		initial_bats_pulse_rate = new double[numbats];
//		bats_loudness = new double[numbats];
//		for(int i = 0; i<numbats;i++){
//			bats_list[i] = new BatTSP();
//			bats_list[i].setId(i);
//			initBat(bats_list[i]);
//			bats_pulse_frequency[i] = rand.nextDouble()*((max_freq - min_freq)+min_freq);
//			initial_bats_pulse_rate[i] = rand.nextDouble();
//			bats_loudness[i] = rand.nextDouble()*max_loudness;
//		}
//				
//		bats_pulse_rate = initial_bats_pulse_rate;
//		iteration = 0;
//		
//		bestbat = new Bat();
//		initBat(bestbat);
//		
//		average_loudness = mean(bats_loudness);
//		
//
//	}
//	
//	public BatTSP step(){
//		
//		bestbat = getBest();
//		average_loudness = mean(bats_loudness);
//		updateBats();
//		for(int i = 0; i<bats_list.length; i++){
//			double random = (rand.nextDouble()*2)-1;
//			double random2 = (rand.nextDouble()*2)-1;
//			if(rand.nextDouble() > bats_pulse_rate[i]){
//
//				bats_list[i].setNewposition(new Dpoint(bestbat.getPosition().getX()+(random*average_loudness),bestbat.getPosition().getY()+(random2*average_loudness)));
//			}else{
//				bats_list[i].setNewposition(new Dpoint(bats_list[i].getPosition().getX()+random,bats_list[i].getPosition().getY()+random2));
//			}
//			
//			eval(bats_list[i]);	
//			if(rand.nextDouble()*max_loudness < bats_loudness[i]
//					& (bats_list[i].getNeweval()
//					<= bats_list[i].getEval())){
//				bats_list[i].setPosition(bats_list[i].getNewposition());
//				bats_list[i].setEval(f.eval(bats_list[i].getPosition().getX(), bats_list[i].getPosition().getY()));	
//				bats_loudness[i] = updateLoudness(bats_loudness[i]);
//				bats_pulse_rate[i] = updatePulseRate(bats_list[i]);
//				
//			}
//					
//			if(bats_list[i].getEval()
//					< f.eval(bestbat.getPosition().getX(), bestbat.getPosition().getY())
//					& isInRange(bats_list[i].getPosition())){
//				bestbat = bats_list[i];
//			}
//		}
//		iteration++;
//		System.out.println("bestbat" + bestbat.toString());
//		return bestbat;
//	}
//	
//	public BatTSP fullrun(){
//		System.out.println("Bat run");
//		long startTime = System.nanoTime();    
//			while(Math.abs(bestbat.eval-f.getOptev()) > tol) {
//				//System.out.println(bestsofar.eval-f.getOptev());
//				step();
//			}
//		long estimatedTime = System.nanoTime() - startTime;
//		double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS) / 1000.0;
//		System.out.println("Function: " + f.getType() + ", Time (s): " +elapsedTimeInSeconds+", Tolerance: " + tol+", Ev: " + bestbat.getEval());
//		return bestbat;
//	}
//	
//	void eval(BatTSP b){
//		double ev = 0.0;
//		double nev = 0.0;
//		int[] path = b.getPosition();
//		int[] npath = b.getNewposition();
//		//printArray(path);
//		for(int i=0; i<path.length-1;i++){
//			ev = ev + g.getPoint(path[i]).distanceTo(g.getPoint(path[i+1]));
//			nev = nev + g.getPoint(npath[i]).distanceTo(g.getPoint(npath[i+1]));
//		}
//		
//		b.setEval(ev);
//		b.setNeweval(nev);
//
//	}
//	
//	void initBat(BatTSP b){
//		b.setNewposition(new Dpoint(rand.nextInt(f.getRangex()),rand.nextInt(f.getRangey())));
//		b.setPosition(new Dpoint(rand.nextInt(f.getRangex()),rand.nextInt(f.getRangey())));
//		b.setVelocity(new Dpoint(rand.nextInt(f.getRangex()),rand.nextInt(f.getRangey())));
//		
//		b.setEval(f.eval(b.getPosition().getX(),b.getPosition().getY()));
//
//		
//	}
//	void updateFrequency(){
//		for(int i =0; i<numbats; i++){
//			double value = min_freq + rand.nextDouble()*(max_freq-min_freq);
//			bats_pulse_frequency[i] = value;
//		}
//	}
//	
//	void updateVelocity(Bat b){
//		double velx = b.getVelocity().getX()+(b.getPosition().getX()-bestbat.getPosition().getX()*bats_pulse_frequency[b.getId()]);
//		double vely = b.getVelocity().getY()+(b.getPosition().getY()-bestbat.getPosition().getY()*bats_pulse_frequency[b.getId()]);
//		
//		b.setVelocity(new Dpoint(velx,vely));
//	}
//	
//	void updatePosition(Bat b){
//		b.setNewposition(new Dpoint(b.getPosition().getX()+b.getVelocity().getX(),b.getPosition().getY()+b.getVelocity().getY()));
//		b.setPosition(b.getNewposition()); // ???
//	}
//	
//	double updateLoudness(double oldloudness){
//		double alpha = 0.99;
//		return oldloudness*alpha;
//	}
//	
//	double updatePulseRate(Bat b){
//		
//		//double genv = Math.atan2(b.getVelocity().getX(), b.getVelocity().getY());
//		//double gamma = genv/bats_pulse_frequency[b.getId()];
//		double gamma = 0.99;
//		double ret = (1-Math.exp(-gamma*iteration)*bats_pulse_rate[b.getId()]);
//		//System.out.println(ret);
//		return ret;
//	}
//	
//	//plus or minus, random.
//	int pom(){
//		double r = rand.nextDouble();
//		if(r<0.5)
//			return -1;
//		return 1;
//	}
//	
//	Bat getBest(){
//		Bat b = new Bat();
//		
//		for(int i =0; i<numbats; i++){
//			if(bats_list[i].getEval() < bestbat.getEval() && isInRange(bats_list[i].getNewposition())){
//				bestbat = bats_list[i];
//				b = bats_list[i];
//			}
//			return bestbat;
//		}
//		
//		return b;
//	}
//	boolean isInRange(Dpoint b){
//		if(Math.abs(b.getX())<= f.getRangex() & Math.abs(b.getY())<= f.getRangey()){
//			return true;
//		}
//		return false;
//	}
//	
//	public static double mean(double[] m) {
//	    double sum = 0;
//	    for (int i = 0; i < m.length; i++) {
//	        sum += m[i];
//	    }
//	    return sum / m.length;
//	}
//	
//	void updateBats(){
//		updateFrequency();
//		for(int i = 0; i<numbats; i++){
//			updateVelocity(bats_list[i]);
//			//updatePosition(bats_list[i]);
//			//System.out.println(bats_list[i].toString());
//		}
//	}
}
