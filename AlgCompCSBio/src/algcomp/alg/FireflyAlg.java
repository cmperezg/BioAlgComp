package algcomp.alg;

import java.util.Random;

public class FireflyAlg {
	
	int gridsize;
	double stepcoeff;
	int EvolutionTime;
	int numofflies;
	double AbsCoeff;
	double Attcoeff;
	Firefly[] current_generation;
	double[][] Attractive;
	
	public FireflyAlg(int _gridsize, int _EvolutionTime,int _numofflies, double _AbsCoeff,double _stepcoeff, double _Attcoeff)
	{
		gridsize=_gridsize;
		EvolutionTime=_EvolutionTime;
		numofflies=_numofflies;
		AbsCoeff=_AbsCoeff;
		Attcoeff=_Attcoeff;
		stepcoeff=_stepcoeff;
		Initfireflies(current_generation,numofflies,AbsCoeff,Attcoeff);
		InitAttractive(numofflies);
	}
	
	public void Evaluate (Firefly fly, Firefly[] Fireflies, int numofflies, double abscoeff, double Attcoeff){
		int j;
		int id=fly.pos.getId();
		fly.Intensity=Function(fly.pos.getX(),fly.pos.getY());
		for (j=1;j<=numofflies;j++){
			if (j==id){
				Attractive[id][j]=0;
				}
			Attractive[id][j]=Attcoeff*Math.exp(-1*abscoeff*fly.pos.distanceTo(Fireflies[j].pos));
				
			}
		
		}
	
	private double Function(double x, double y){
		return x*x+y*y; 
		//This should be the intensity function
		}
	private void Initfireflies(Firefly[] firefliesArray,int numofflies,double abscoef, double Attcoeff){
		Random r = new Random();
		int i;
		for (i=1;i<=numofflies; i++){
			firefliesArray[i].pos.setx(r.nextInt(gridsize));
			firefliesArray[i].pos.sety(r.nextInt(gridsize));
			firefliesArray[i].pos.setId(i);
			Evaluate(firefliesArray[i],firefliesArray,numofflies,abscoef,Attcoeff);
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
	private Firefly FindBestSofar(Firefly[] FlyArray){
		int i;
		Firefly Best=FlyArray[1];
		for (i=1;i<=numofflies; i++){
			if (FlyArray[i].Intensity > Best.Intensity)
				Best=FlyArray[i];
		}
		return Best;
		
	}
	private void Move(Firefly fly1, Firefly fly2, double stepcoeff,double Abscoeff,double Attcoeff){
		double r=fly1.pos.distanceTo(fly2.pos);
		Random rand=new Random();
		fly1.pos.setx(fly1.pos.getX()+Attcoeff*Math.exp(-Abscoeff*r*r)*(fly2.pos.getX()-fly1.pos.getX())+stepcoeff*(rand.nextFloat()-0.5));
		fly1.pos.sety(fly1.pos.getY()+Attcoeff*Math.exp(-Abscoeff*r*r)*(fly2.pos.getY()-fly1.pos.getY())+stepcoeff*(rand.nextFloat()-0.5));
	}
	private void Step(Firefly[] Fly, double AbsCoeff, int gridsize, int numofflies, double stepcoeff,double Attcoeff){
		int i,j;
		for (i=1;i<= numofflies;i++){
			for(j=1;j<=numofflies;j++){
				if (Fly[j].Intensity>Fly[i].Intensity){
					Move(Fly[i],Fly[j],stepcoeff,AbsCoeff,Attcoeff);
				}
			}
		}
		for(i=1;i<=numofflies;i++){
			Evaluate(Fly[i],Fly,numofflies,AbsCoeff,Attcoeff);
		}
		
	}
	
	private Firefly FullRun(Firefly[] Flies, double Abscoeff,int gridsize, int numofflies,double stepcoeff, double Attcoeff, int EvolutionTime){
		int t=1;
		Firefly Best;
			for (t=1;t<=EvolutionTime;t++){
				Step (Flies,Abscoeff,gridsize,numofflies,stepcoeff, Attcoeff);
			}
			Best=FindBestSofar(Flies);
		return Best;
	}
}
