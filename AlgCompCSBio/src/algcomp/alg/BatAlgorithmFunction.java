package algcomp.alg;
//reference: https://github.com/AhmedHani/PySwarmOptimization/blob/master/bat_algorithm_package/bat_algorithm.py

import java.util.Random;
import java.util.concurrent.TimeUnit;

import algcomp.util.Dpoint;
import algcomp.util.Function;

public class BatAlgorithmFunction {

	Function f;
	double min_freq;
	double max_freq;
	double max_loudness;
	
	int numbats;
	
	
	Bat[] bats_list;
	double[] bats_pulse_frequency;
	double[] bats_pulse_rate;
	double[] initial_bats_pulse_rate;
	double[] bats_loudness;
	
	Bat bestbat;
	
	double tol;
	Random rand;
	double iteration; //gencounter
	
	double average_loudness;
	
	
	public BatAlgorithmFunction(Function _f,int _numbats, double _tol){
		rand = new Random();
		f = _f;
		min_freq = 0;
		max_freq = 1;
		max_loudness = 100;
		numbats = _numbats;
		bats_list = new Bat[numbats];
		tol = _tol;
		
		bats_pulse_frequency = new double[numbats];
		bats_pulse_rate = new double[numbats];
		initial_bats_pulse_rate = new double[numbats];
		bats_loudness = new double[numbats];
		for(int i = 0; i<numbats;i++){
			bats_list[i] = new Bat();
			bats_list[i].setId(i);
			initBat(bats_list[i]);
			bats_pulse_frequency[i] = rand.nextDouble()*((max_freq - min_freq)+min_freq);
			initial_bats_pulse_rate[i] = rand.nextDouble();
			bats_loudness[i] = rand.nextDouble()*max_loudness;
		}
		for(int i = 0;i<numbats; i++){	
			bats_pulse_rate[i] = initial_bats_pulse_rate[i];
		}
		
		iteration = 0;
		
		bestbat = new Bat();
		initBat(bestbat);
		System.out.println(bestbat.getEval());
		//average_loudness = mean(bats_loudness);
		

	}
	
	public Bat step(){
		
		//bestbat = getBest();
		average_loudness = mean(bats_loudness);
		updateBats();
		//System.out.println(bats_pulse_rate[0]+","+bats_pulse_frequency[0]);
		for(int i = 0; i<bats_list.length; i++){
			double random = (rand.nextDouble()*2)-1;
			double random2 = (rand.nextDouble()*2)-1;
			//System.out.println(bats_list[i].getVelocity().getX()+","+bats_list[i].getVelocity().getY());
			//System.out.println(bats_pulse_rate[i]+","+bats_pulse_frequency[i]);
			//System.out.println(bats_loudness[i]);

			if(rand.nextDouble() > bats_pulse_rate[i]){
					//System.out.println("here");
				bats_list[i].setNewposition(new Dpoint(bestbat.getPosition().getX()+(random*average_loudness),bestbat.getPosition().getY()+(random2*average_loudness)));
			}else{
				bats_list[i].setNewposition(new Dpoint(bats_list[i].getPosition().getX()+bats_list[i].getVelocity().getX(),bats_list[i].getPosition().getY()+bats_list[i].getVelocity().getY()));
			}
			
			eval(bats_list[i]);	
			if(rand.nextDouble()*max_loudness < bats_loudness[i]
					& (bats_list[i].getNeweval()
					<= bats_list[i].getEval())){
				//System.out.println("here");
				bats_list[i].setPosition(bats_list[i].getNewposition());
				bats_list[i].setEval(bats_list[i].getNeweval());	
				bats_loudness[i] = updateLoudness(bats_loudness[i]);
				bats_pulse_rate[i] = updatePulseRate(bats_list[i]);
				
			}
					
			if(bats_list[i].getEval()
					< bestbat.getEval()
					& isInRange(bats_list[i].getPosition())){
				bestbat = new Bat(bats_list[i]);
			}
		}
		iteration++;
		System.out.println("bestbat" + bestbat.toString());
		return bestbat;
	}
	
	public Bat fullrun(){
		System.out.println("Bat run");
		long startTime = System.nanoTime();    
			while(Math.abs(bestbat.eval-f.getOptev()) > tol) {
				//System.out.println(bestsofar.eval-f.getOptev());
				step();
			}
		long estimatedTime = System.nanoTime() - startTime;
		double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS) / 1000.0;
		System.out.println("Function: " + f.getType() + ", Time (s): " +elapsedTimeInSeconds+", Tolerance: " + tol+", Ev: " + bestbat.getEval());
		return bestbat;
	}
	
	void eval(Bat b){
		double penalty = 0;
		if(!isInRange(b.getNewposition())){
			penalty = 1000;
		}
		double penalty2 = 0;
		if(!isInRange(b.getPosition())){
			penalty2 = 1000;
		}
		b.setEval(f.eval(b.getPosition().getX(), b.getPosition().getY())+penalty2);
		b.setNeweval(f.eval(b.getNewposition().getX(), b.getNewposition().getY())+penalty);
		

	}
	
	void initBat(Bat b){
		b.setNewposition(new Dpoint(rand.nextInt(f.getRangex()),rand.nextInt(f.getRangey())));
		b.setPosition(new Dpoint(rand.nextInt(f.getRangex()),rand.nextInt(f.getRangey())));
		//.b.setVelocity(new Dpoint(rand.nextInt(f.getRangex()),rand.nextInt(f.getRangey())));
		b.setVelocity(new Dpoint(rand.nextDouble(),rand.nextDouble()));

		//b.setEval(f.eval(b.getPosition().getX(),b.getPosition().getY()));

		eval(b);
	}
	void updateFrequency(){
		for(int i =0; i<numbats; i++){
			double value = min_freq + rand.nextDouble()*(max_freq-min_freq);
			bats_pulse_frequency[i] = value;
		}
	}
	
	void updateVelocity(Bat b){
		double velx = b.getVelocity().getX()+(b.getPosition().getX()-bestbat.getPosition().getX()/1000)*bats_pulse_frequency[b.getId()];
		double vely = b.getVelocity().getY()+(b.getPosition().getY()-bestbat.getPosition().getY())*bats_pulse_frequency[b.getId()];
		
		b.setVelocity(new Dpoint(velx,vely));
	}
	
	void updatePosition(Bat b){
		b.setNewposition(new Dpoint(b.getPosition().getX()+b.getVelocity().getX(),b.getPosition().getY()+b.getVelocity().getY()));
		b.setPosition(b.getNewposition()); // ???
	}
	
	double updateLoudness(double oldloudness){
		double alpha = 0.99;
		return oldloudness*alpha;
	}
	
	double updatePulseRate(Bat b){
		
		//double genv = Math.atan2(b.getVelocity().getX(), b.getVelocity().getY());
		//double gamma = genv/bats_pulse_frequency[b.getId()];
		double gamma = 0.9;
		double ret = (1-Math.exp(-gamma*iteration))*initial_bats_pulse_rate[b.getId()];
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
	
	Bat getBest(){
		Bat b = new Bat();
		
		for(int i =0; i<numbats; i++){
			if(bats_list[i].getEval() < bestbat.getEval() && isInRange(bats_list[i].getNewposition())){
				bestbat = bats_list[i];
				b = bats_list[i];
			}
			return bestbat;
		}
		
		return b;
	}
	boolean isInRange(Dpoint b){
		if(Math.abs(b.getX())<= f.getRangex() & Math.abs(b.getY())<= f.getRangey()){
			return true;
		}
		return false;
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
}
