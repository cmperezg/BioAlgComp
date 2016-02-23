package algcomp.alg;

import java.util.Random;

public class FireflyAlg {
	
	int gridsize;
	int EvolutionTime;
	int numofflies;
	double AbsCoeff;
	Firefly bestsofar;
	Firefly[] current_generation;
	
	public FireflyAlg(int _gridsize, int _EvolutionTime,int _numofflies, double _AbsCoeff)
	{
		gridsize=_gridsize;
		EvolutionTime=_EvolutionTime;
		numofflies=_numofflies;
		AbsCoeff=_AbsCoeff;
		Initfireflies(current_generation,numofflies);
		
	}
	
	public void Evaluate (Firefly fly){
		fly.Intensity=Function((double)fly.pos.getX(),(double)fly.pos.getY());
		}
	
	private double Function(double x, double y){
		return x*x+y*y; 
		//This should be the intensity function
		}
	private void Initfireflies(Firefly[] firefliesArray,int numofflies){
		Random r = new Random();
		int i;
		for (i=1;i<=numofflies; i++){
			firefliesArray[i].pos.setx(r.nextInt(gridsize));
			firefliesArray[i].pos.sety(r.nextInt(gridsize));
			Evaluate(firefliesArray[i]);
		}
			
		
	}
}
