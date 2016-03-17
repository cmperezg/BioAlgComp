package algcomp.alg;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import algcomp.util.Dpoint;
import algcomp.util.Function;

public class PSO {

	Function f;
	Particle[] particles;
	Particle gbest;
	double c1,c2,m,wmin,wmax,w;
	int numparticles;
	double tol;
	Random r;
	
	public PSO(Function _f, int _numparticles, double _tol){
		f = _f;
		numparticles = _numparticles;
		tol = _tol;
		c1=1.4;
		c2=1.4;
		wmin = 0.4;
		wmax = 0.9;
		w = 0.7;
		//m = 3;
		r = new Random();
		particles = new Particle[numparticles];
		
		gbest = new Particle();
		gbest.setPosition(new Dpoint(r.nextInt(f.getRangex()),r.nextInt(f.getRangey())));
		gbest.setVelocity(new Dpoint(0.0,0.0));
		gbest.setEval(f.eval(gbest.getPosition().getX(), gbest.getPosition().getY()));
		
		//init particles
		for(int i = 0; i<numparticles; i++){
			particles[i] = new Particle();
			particles[i].setPosition(new Dpoint(r.nextInt(f.getRangex()),r.nextInt(f.getRangey())));
			particles[i].setEval(f.eval(particles[i].getPosition().getX(), particles[i].getPosition().getY()));
			particles[i].setPbest(new Dpoint(r.nextInt(f.getRangex()),r.nextInt(f.getRangey())));
			particles[i].setBesteval(f.eval(particles[i].getPosition().getX(), particles[i].getPosition().getY()));
			particles[i].setVelocity(new Dpoint(0.0,0.0));
			
			if(particles[i].getEval()<gbest.getEval()){
				gbest.setPosition(new Dpoint(particles[i].getPosition().getX(),particles[i].getPosition().getY()));
				gbest.setEval(particles[i].getEval());
			}
		}
		
		
	}
	public Particle step(){


		for(int i = 0; i<numparticles; i++){
			boolean ir = isInRange(particles[i].getPosition());
			particles[i].setEval(f.eval(particles[i].getPosition().getX(), particles[i].getPosition().getY()));
			if(!ir){
				particles[i].setEval(particles[i].getEval()+1000);
			}
			//System.out.println(particles[i].toString());
			
			if(particles[i].getEval()<gbest.getEval()&ir){
				//System.out.println("inhere");
				gbest.setPosition(new Dpoint(particles[i].getPosition().getX(),particles[i].getPosition().getY()));
				gbest.setEval(particles[i].getEval());
			}
			if(particles[i].getEval()<particles[i].getBesteval()&ir){
				particles[i].setBesteval(particles[i].getEval()); 
				particles[i].setPbest(particles[i].getPosition());
			}
		}
		for(int i = 0; i<numparticles; i++){
			

			//update velocity
			//v[] = v[] + c1 * rand() * (pbest[] - present[]) + c2 * rand() * (gbest[] - present[]) 
			double tpx = (particles[i].getPbest().getX()-particles[i].getPosition().getX());
			double tgx = (gbest.getPosition().getX()-particles[i].getPosition().getX());
			double tpy = (particles[i].getPbest().getY()-particles[i].getPosition().getY());
			double tgy = (gbest.getPosition().getY()-particles[i].getPosition().getY());
			particles[i].setVelocity(new Dpoint(
					w*particles[i].getVelocity().getX()+c1*r.nextDouble()*tpx + c2*r.nextDouble()*tgx,
					w*particles[i].getVelocity().getY()+c1*r.nextDouble()*tpy + c2*r.nextDouble()*tgy
					));
			//System.out.println(particles[i].getVelocity().getX());
			//update positions
			//present[] = persent[] + v[] 
			particles[i].setPosition(new Dpoint(particles[i].getPosition().getX()+particles[i].getVelocity().getX(),
					particles[i].getPosition().getY()+particles[i].getVelocity().getY()));
		}
		System.out.println("BEST: " +gbest.toString());
		return gbest;
	}
	
	public Particle fullrun(){
		System.out.println("PSO run");
		long startTime = System.nanoTime();    
			while(Math.abs(gbest.eval-f.getOptev()) > tol) {
				//System.out.println(bestsofar.eval-f.getOptev());
				step();
			}
		long estimatedTime = System.nanoTime() - startTime;
		double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS) / 1000.0;
		System.out.println("Function: " + f.getType() + ", Time (s): " +elapsedTimeInSeconds+", Tolerance: " + tol+", Ev: " + gbest.getEval());
		return gbest;
	}
	
	boolean isInRange(Dpoint b){
		if(Math.abs(b.getX())<= f.getRangex() & Math.abs(b.getY())<= f.getRangey()){
			return true;
		}
		return false;
	}
}
