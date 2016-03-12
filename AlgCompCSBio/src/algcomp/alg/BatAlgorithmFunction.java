package algcomp.alg;

import java.util.Random;

import algcomp.util.Dpoint;
import algcomp.util.Function;

public class BatAlgorithmFunction {

	Function f;
	double min_freq;
	double max_freq;
	double max_loudness;
	int numbats;
	
	Bat[] bats;
	
	Bat bestbat;
	
	double tol;
	Random rand;
	double iteration; //gencounter
	
	
	public BatAlgorithmFunction(Function _f,int _numbats, double _tol){
		rand = new Random();
		f = _f;
		numbats = _numbats;
		bats = new Bat[numbats];
		tol = _tol;
		
		min_freq = 0;
		max_freq = 10;
		max_loudness = 100;
		
		iteration = 0;
		
		bestbat = new Bat();
		initBat(bestbat);
		
		for(int i = 0; i<numbats; i++){
			bats[i] = new Bat();
			initBat(bats[i]);
			
		}
	}
	
	void initBat(Bat b){
		b.setBestposition(new Dpoint(pom()*rand.nextInt(f.getRangex()),pom()*rand.nextInt(f.getRangey())));
		b.setNewposition(new Dpoint(pom()*rand.nextInt(f.getRangex()),pom()*rand.nextInt(f.getRangey())));
		b.setPosition(new Dpoint(pom()*rand.nextInt(f.getRangex()),pom()*rand.nextInt(f.getRangey())));
		b.setVelocity(new Dpoint(pom()*rand.nextInt(f.getRangex()),pom()*rand.nextInt(f.getRangey())));

		b.setF(rand.nextDouble()*((max_freq - min_freq)+min_freq));
		b.setR(rand.nextDouble());
		b.setA(rand.nextDouble()*max_loudness);
		
	}
	
	void updatePosition(Bat b){
		b.setNewposition(new Dpoint(b.getPosition().getX()+b.getVelocity().getX(),b.getPosition().getY()+b.getVelocity().getY()));
		b.setPosition(b.getNewposition()); // ???
	}
	
	void updateLoudness(Bat b){
		double alpha = 0.5;
		b.setA(b.getA()*alpha);
	}
	
	void updatePulseRate(Bat b){
		double gamma = 0.3;
		
		b.setR(1-Math.exp(-gamma*iteration)*b.getR());
	}
	
	//plus or minus, random.
	int pom(){
		double r = rand.nextDouble();
		if(r<0.5)
			return -1;
		return 1;
	}
}
