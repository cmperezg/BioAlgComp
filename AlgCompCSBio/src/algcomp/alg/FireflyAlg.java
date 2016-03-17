package algcomp.alg;
import algcomp.util.Function;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FireflyAlg {
	
	int gridsizeX;
	int gridsizeY;
	double stepcoeff;
	double Tol;
	int numofflies;
	double AbsCoeff;
	double Attcoeff;
	Firefly[] current_generation;
	double[][] Attractive;
	Function f;
	Firefly BestsoFar;
	
	public FireflyAlg(Function func, double _Tol,int _numofflies, double _AbsCoeff,double _stepcoeff, double _Attcoeff)
	{
		gridsizeX=func.getRangex();
		gridsizeY=func.getRangey();
		Tol=_Tol;
		numofflies=_numofflies;
		AbsCoeff=_AbsCoeff;
		Attcoeff=_Attcoeff;
		stepcoeff=_stepcoeff;
		f = func;
		BestsoFar= new Firefly();
		Attractive = new double[numofflies][numofflies];
		current_generation = new Firefly[numofflies];
		InitAttractive(numofflies);
		Initfireflies();
		
	
		
		
	}
	

	
	public void Evaluate (Firefly fly, Firefly[] Fireflies, int numofflies, double abscoeff, double Attcoeff){
		int j;
		int id=fly.getId();
		fly.Intensity=1/(f.eval(fly.pos.getX(),fly.pos.getY())-f.getOptev());
		for (j=0;j<numofflies;j++){
			if (j==id){
				Attractive[id][j]=0;
				}
			else
			{
				Attractive[id][j]=Attcoeff*Math.exp(-1*abscoeff*fly.pos.distanceTo(Fireflies[j].pos));
			}
				
			}
		
		}
	
	private void Initfireflies(){
		Random r = new Random();
		int i;
		for (i=0;i<numofflies;i++){
			current_generation[i]= new Firefly();
		}
		for (i=0;i<numofflies; i++){

			current_generation[i].pos.setx(r.nextInt(gridsizeX));
			current_generation[i].pos.sety(r.nextInt(gridsizeY));
			current_generation[i].setId(i);
			Evaluate(current_generation[i],current_generation,numofflies,AbsCoeff,Attcoeff);
			FindBestSofar(current_generation);
		}
	}
	private void InitAttractive(int numofflies){
		int i,j;
		for (i=0;i<numofflies; i++){
			for(j=0;j<numofflies;j++){
				Attractive[i][j]=0;
			}	
		}
	}
	private Firefly FindBestSofar(Firefly[] FlyArray){
		int i;
		//Firefly Best=FlyArray[1];
		for (i=0;i<numofflies; i++){
			if (FlyArray[i].Intensity > BestsoFar.getIntensity())
				BestsoFar=FlyArray[i];
		}
		System.out.println(BestsoFar.toString());
		return BestsoFar;
		
	}
	private void Move(Firefly fly1, Firefly fly2, double stepcoeff,double Abscoeff,double Attcoeff){
		double r=fly1.pos.distanceTo(fly2.pos);
		Random rand=new Random();
		double LastX= fly1.pos.getX();
		double LastY= fly1.pos.getY();
		fly1.pos.setx(fly1.pos.getX()+Attcoeff*Math.exp(-Abscoeff*r*r)*(fly2.pos.getX()-fly1.pos.getX())+stepcoeff*(rand.nextFloat()-0.5));
		if (fly1.pos.getX()>gridsizeX){
			fly1.pos.setx(LastX);
		}
		fly1.pos.sety(fly1.pos.getY()+Attcoeff*Math.exp(-Abscoeff*r*r)*(fly2.pos.getY()-fly1.pos.getY())+stepcoeff*(rand.nextFloat()-0.5));
		if (fly1.pos.getY()>gridsizeY){
			fly1.pos.sety(LastY);
		}
	}
	public Firefly Step(){
		int i,j;
		for (i=0;i< numofflies;i++){
			for(j=0;j<numofflies;j++){
				if (current_generation[j].Intensity>current_generation[i].Intensity){
					Move(current_generation[i],current_generation[j],stepcoeff,AbsCoeff,Attcoeff);
				}
			}
		}
		for(i=0;i<numofflies;i++){
			Evaluate(current_generation[i],current_generation,numofflies,AbsCoeff,Attcoeff);
		}
		
		return FindBestSofar(current_generation);
	}
	
	public void FullRun(){
		long startTime = System.nanoTime();    
		while(Math.abs(f.eval(BestsoFar.pos.getX(),BestsoFar.pos.getY())-f.getOptev()) > Tol) {
			//System.out.println(bestsofar.eval-f.getOptev());
			BestsoFar=Step();
		}
		FindBestSofar(current_generation);
	long estimatedTime = System.nanoTime() - startTime;
	double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS) / 1000.0;
	System.out.println("Function: " + f.getType() + ", Time (s): " +elapsedTimeInSeconds+", Tolerance: " + Tol+", Ev: " + f.eval(BestsoFar.pos.getX(),BestsoFar.pos.getY()));
					
		return ;//Best;
	}
}
