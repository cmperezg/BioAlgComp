package algcomp.alg;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import algcomp.util.Dpoint;
import algcomp.util.Function;

public class BatFunc {
	Function func;
	double fmin;
	double fmax;
	double Amax;
	
	int numbats;
	
	
	Bat[] bats_list;
	double[] f;
	double[] r;
	double[] r0;
	double[] A;
	
	double alpha;
	double gamma;
	
	Bat bestbat;
	
	double tol;
	Random rand;
	double iteration; //gencounter
	
	
	
	public BatFunc(Function _f,int _numbats, double _tol){
		rand = new Random();
		func = _f;
		numbats = _numbats;
		tol = _tol;
		
		fmin = 0;
		fmax = 1;
		Amax = 100;
		
		alpha = 0.9;
		gamma = 0.9;
		
		bats_list = new Bat[numbats];
		f = new double[numbats];
		r = new double[numbats];
		r0 = new double[numbats];
		A = new double[numbats];
		
		for(int i =0; i<numbats; i++){
			bats_list[i] = new Bat(func.getRangex(),func.getRangey(),i);
			f[i] = rand.nextDouble()*((fmax - fmin)+fmin);
			r0[i] = rand.nextDouble()/100;
			r[i] = r0[i];
			A[i] = 100; 
		}
		
		iteration = 0;
		bestbat = new Bat(func.getRangex(),func.getRangey(),numbats);
		eval(bestbat);
	}

	public Bat step(){
		
		double avA = mean(A);
		for(int i = 0;i<numbats; i++){
			/*
			 * Generate new solutions by adjusting frequency,velocities,locations/solutions
			 */
			f[i] = fmin-(fmax-fmin)*rand.nextDouble();
			
			double vx = bats_list[i].getVelocity().getX() + (bats_list[i].getPosition().getX()-bestbat.getPosition().getX())*f[i];
			double vy = bats_list[i].getVelocity().getY() + (bats_list[i].getPosition().getY()-bestbat.getPosition().getY())*f[i];
			
			bats_list[i].setVelocity(new Dpoint(vx,vy));
			
			double xx = bats_list[i].getPosition().getX()+bats_list[i].getVelocity().getX();
			double xy = bats_list[i].getPosition().getY()+bats_list[i].getVelocity().getY();
			
			bats_list[i].setPosition(new Dpoint(xx,xy));
			
			/*
			 * if(rand>ri)
			 * generate local solution around best
			 * else
			 * generate solution by flying randomly
			 */
			double random = (rand.nextDouble()*2)-1;
			double random2 = (rand.nextDouble()*2)-1;
			if(rand.nextDouble()>r[i]){
				xx = bestbat.getPosition().getX()+random*avA;
				xy = bestbat.getPosition().getY()+random2*avA;
				bats_list[i].setNewposition(new Dpoint(xx,xy));
			}else{
				xx = bestbat.getPosition().getX()+random;
				xy = bestbat.getPosition().getY()+random2;
				bats_list[i].setNewposition(new Dpoint(xx,xy));
			}
			eval(bats_list[i]);
			/*
			 * if(rand<Ai & f(xi)<f(x*))
			 * Accept new solution
			 * Increase ri, reduce Ai
			 */
			if(rand.nextDouble()*Amax < A[i] & bats_list[i].getNeweval()<bats_list[i].getEval()&isInRange(bats_list[i].getNewposition())){
				bats_list[i].setPosition(bats_list[i].getNewposition());
				A[i] = A[i]*alpha;
				r[i] = r0[i]*(1-Math.exp(-gamma*iteration));
			}
		
			eval(bats_list[i]);
		}
		/*
		 * Rank and find best
		 */
		for(int i =0;i<numbats;i++){
			if(bats_list[i].getEval()<bestbat.getEval()){
				bestbat = new Bat(bats_list[i]);
			}
		}
		System.out.println("bestbat" + bestbat.toString());
		return bestbat;
	}
	
	public Bat fullrun(){
		System.out.println("Bat run");
		long startTime = System.nanoTime();    
			while(Math.abs(bestbat.eval-func.getOptev()) > tol) {
				//System.out.println(bestsofar.eval-f.getOptev());
				step();
			}
		long estimatedTime = System.nanoTime() - startTime;
		double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS) / 1000.0;
		System.out.println("Function: " + func.getType() + ", Time (s): " +elapsedTimeInSeconds+", Tolerance: " + tol+", Ev: " + bestbat.getEval());
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
		b.setEval(func.eval(b.getPosition().getX(), b.getPosition().getY())+penalty2);
		b.setNeweval(func.eval(b.getNewposition().getX(), b.getNewposition().getY())+penalty);
	}
	
	boolean isInRange(Dpoint b){
		if(Math.abs(b.getX())<= func.getRangex() & Math.abs(b.getY())<= func.getRangey()){
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
}
