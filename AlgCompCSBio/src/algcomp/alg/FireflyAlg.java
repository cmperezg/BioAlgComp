package algcomp.alg;

import java.util.Random;

public class FireflyAlg {
	
	int gridsize;
	int EvolutionTime;
	int numofflies;
	double AbsCoeff;
	Firefly bestsofar;
	Firefly[] current_generation;
	double[][] Attractive;
	
	public FireflyAlg(int _gridsize, int _EvolutionTime,int _numofflies, double _AbsCoeff)
	{
		gridsize=_gridsize;
		EvolutionTime=_EvolutionTime;
		numofflies=_numofflies;
		AbsCoeff=_AbsCoeff;
		Initfireflies(current_generation,numofflies,AbsCoeff);
		InitAttractive(numofflies);
		
	}
	
	public void Evaluate (Firefly fly, Firefly[] Fireflies, int numofflies, double abscoeff){
		int j;
		int id=fly.pos.getId();
		fly.Intensity=Function(fly.pos.getX(),fly.pos.getY());
		for (j=1;j<=numofflies;j++){
			if (j==id){
				Attractive[id][j]=0;
				}
			Attractive[id][j]=Math.exp(-1*abscoeff*fly.pos.distanceTo(Fireflies[j].pos));
				
			}
		
		}
	
	private double Function(double x, double y){
		return x*x+y*y; 
		//This should be the intensity function
		}
	private void Initfireflies(Firefly[] firefliesArray,int numofflies,double abscoef){
		Random r = new Random();
		int i;
		for (i=1;i<=numofflies; i++){
			firefliesArray[i].pos.setx(r.nextInt(gridsize));
			firefliesArray[i].pos.sety(r.nextInt(gridsize));
			firefliesArray[i].pos.setId(i);
			Evaluate(firefliesArray[i],firefliesArray,numofflies,abscoef);
		}
	}
	private void InitAttractive(int numofflies){
		int i,j;
		for (i=1;i<=numofflies; i++){
			for(j=1;j<=numofflies;j++){
				Attractive[i][j]=0;
			}	
		}
	}
	private void Step(Firefly[] FirefliesArray, double AbsCoeff, int gridsize, int numofflies){
		
		
	}
}
