package algcomp.alg;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import algcomp.util.Dpoint;
import algcomp.util.Function;
import algcomp.util.Graph;

public class PSOTSP {
	Graph g;
	ParticleTSP[] particles;
	ParticleTSP gbest;
	double c1,c2,m,wmin,wmax,w;
	int numparticles;
	double timer;
	Random r;
	
	public PSOTSP(Graph _g, int _numparticles, double _timer){
		g = _g;
		numparticles = _numparticles;
		timer = _timer;
		c1=1.4;
		c2=1.4;
		wmin = 0.4;
		wmax = 0.9;
		w = 0.7;
		//m = 3;
		r = new Random();
		particles = new ParticleTSP[numparticles];
		
		gbest = new ParticleTSP();
		gbest.setPosition(randomGraphsizeArray());
		gbest.setVelocity(0);
		gbest.setEval(eval(gbest.getPosition()));
		
		//init particles
		for(int i = 0; i<numparticles; i++){
			particles[i] = new ParticleTSP();
			particles[i].setPosition(randomGraphsizeArray());
			particles[i].setEval(eval(particles[i].getPosition()));
			particles[i].setPbest(randomGraphsizeArray());
			particles[i].setBesteval(eval(particles[i].getPbest()));
			particles[i].setVelocity(0);
			
			if(particles[i].getEval()<gbest.getEval()){
				int[] temp = new int[numparticles];
				System.arraycopy(particles[i].getPosition(), 0, temp, 0, temp.length);
				gbest.setPosition(temp);
				gbest.setEval(particles[i].getEval());
			}
		}
		
		
	}
	public ParticleTSP step(){


		for(int i = 0; i<numparticles; i++){
			
			particles[i].setEval(eval(particles[i].getPosition()));
			
			//System.out.println(particles[i].toString());
			int[] temp = new int[numparticles];
			if(particles[i].getEval()<gbest.getEval()){
				//System.out.println("inhere");
				
				System.arraycopy(particles[i].getPosition(), 0, temp, 0, temp.length);
				gbest.setPosition(temp);
				gbest.setEval(particles[i].getEval());
			}
			if(particles[i].getEval()<particles[i].getBesteval()){
				particles[i].setBesteval(particles[i].getEval()); 
				System.arraycopy(particles[i].getPosition(), 0, temp, 0, temp.length);
				particles[i].setPbest(temp);
			}
		}
		for(int i = 0; i<numparticles; i++){
			

			//update velocity
			//v[] = v[] + c1 * rand() * (pbest[] - present[]) + c2 * rand() * (gbest[] - present[]) 
			double tpx = SinglePathDist(particles[i].getPbest(),particles[i].getPosition());
			double tgx = SinglePathDist(gbest.getPosition(),particles[i].getPosition());
			
			particles[i].setVelocity(
					(int)Math.ceil(w*particles[i].getVelocity()+c1*r.nextDouble()*tpx + c2*r.nextDouble()*tgx)

					);
			//System.out.println(particles[i].getVelocity().getX());
			//update positions
			//present[] = persent[] + v[] 
			
			mutate(particles[i].position,particles[i].getVelocity());
			
		}
		System.out.println("BEST: " +gbest.toString());
		return gbest;
	}
	
	

	
	double eval(int[] path){
		double ev = 0.0;
		
		//printArray(path);
		for(int i=0; i<path.length-1;i++){
			ev = ev + g.getPoint(path[i]).distanceTo(g.getPoint(path[i+1]));
		}
		
		return ev;
		
	}

	
	int[] randomGraphsizeArray(){
		int[] ret = new int [numparticles];
		for(int i = 0; i<numparticles; i++){
			ret[i] = i;
		}
		shuffleArray(ret);
		return ret;
	}
	// Fisher Yates shuffle array function
	private void shuffleArray(int[] array)
	{
				 int index;
				 Random random = new Random();
				 for (int i = array.length - 1; i > 0; i--)
				 {
				        index = random.nextInt(i + 1);
				        if (index != i)
				        {
				            array[index] ^= array[i];
				            array[i] ^= array[index];
				            array[index] ^= array[i];
				        }
				    }
	}
	
	private static int SinglePathDist(int[] bA,int[] bB){
		int i,j;
		int Dist=0;
		for(i=0;i<bA.length-1;i++){
			for (j=0;j<bA.length-1;j++){
				if(bA[i]!=bB[j] || bA[i+1]!=bB[j+1] ){
					Dist=Dist+1;
				}
					
			}
		} 
		int size = bA.length-1;//numpairs
		
		return Dist -(size*(size-1));
	}
	
	public void mutate(int[] path, int times) {
		Random random =  new Random();
		
		for(int i = 0; i<times; i++){
			int mutpoint = random.nextInt(numparticles-1);

			int temp = 0;
			
//			temp = path[mutpoint];
//			path[mutpoint] = path[mutpoint2];
//			path[0] = temp;
			
			
			if(mutpoint == numparticles-1){
				temp = path[numparticles-1];
				path[numparticles-1] = path[0];
				path[0] = temp;
			}else{
				temp = path[mutpoint];
				path[mutpoint] = path[mutpoint+1];
				path[mutpoint+1] = temp;
			}
		}
		
		
	}
}
