package algcomp.alg;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.math3.distribution.WeibullDistribution;

import algcomp.util.Function;;

public class CuckooSearchFunction {
	
	Function f;
	static Random r;
	//will use weibull distribution for step length
	static WeibullDistribution weibull;
	int numnests; //number of nests
	double abrate; //abandonment rate
	double tol; //tolerance
	Egg[] nests;
	Egg bestegg;
	
	public CuckooSearchFunction(Function _f, int _numnests, double _abrate, double _tol){
		
		f = _f;
		numnests = _numnests;
		abrate = _abrate;
		tol = _tol;
		nests = new Egg[numnests];
		
		r = new Random();
		weibull = new WeibullDistribution(1,0.1);
		
		bestegg = new Egg(r.nextInt(f.getRangex()),r.nextInt(f.getRangey()));
		
		for(int i = 0;i<numnests;i++){
			nests[i] = new Egg(r.nextInt(f.getRangex()),r.nextInt(f.getRangey()));
			nests[i].setEval(f.eval(nests[i].getX(),nests[i].getY()));
			if(nests[i].getEval()<bestegg.getEval()){
				bestegg = nests[i];
			}
			
		}

	}
	
	public Egg step(){
		
		//generate cuckoos
		for(int i = 0; i<nests.length; i++){
			Egg cuckoo = new Egg(levyStep(nests[i].getX()),levyStep(nests[i].getY()));
			cuckoo.setEval(f.eval(cuckoo.getX(), cuckoo.getY()));
			
			//pick a random nest to dump it and carry out comparisons
			int ind = r.nextInt(nests.length);
			if(cuckoo.getEval()<nests[ind].getEval() & isInRange(cuckoo)){
				nests[ind]=cuckoo;
				if(cuckoo.getEval()<bestegg.getEval()){
					bestegg = cuckoo;
				}
			}else{
				double rn = r.nextDouble();
				//abandon it?
				if(rn < abrate){
					nests[ind] = new Egg(r.nextInt(f.getRangex()),r.nextInt(f.getRangey()));
				}
			}
		}
		System.out.println(bestegg.toString());
		return bestegg;
	}
	
	public Egg fullrun(){
		System.out.println("Cuckoo run");
		long startTime = System.nanoTime();    
			while(Math.abs(bestegg.eval-f.getOptev()) > tol) {
				//System.out.println(bestsofar.eval-f.getOptev());
				step();
			}
		long estimatedTime = System.nanoTime() - startTime;
		double elapsedTimeInSeconds = TimeUnit.MILLISECONDS.convert(estimatedTime, TimeUnit.NANOSECONDS) / 1000.0;
		System.out.println("Function: " + f.getType() + ", Time (s): " +elapsedTimeInSeconds+", Tolerance: " + tol+", Ev: " + bestegg.getEval());
		return bestegg;
	}
	
	double levyStep(double d){
		double rand = r.nextDouble();
		double weib = weibull.density(rand);
		double plusorminus = r.nextDouble();
		if(plusorminus < 0.5){
			return d-weib;
		}return d+weib;
	}
	
	boolean isInRange(Egg e){
		if(e.getX()>f.getRangex() || e.getX()<(-f.getRangex())  ){
			return false;
		}
		if(e.getY()>f.getRangey() || e.getY()<(-f.getRangey())  ){
			return false;
		}else return true;
	}
	
	
//	public static void main(String[] args){
//		
//		for(int i =0; i<100; i++){
//			r = new Random();
//			weibull = new WeibullDistribution(1,0.1);
//			
//			double d = r.nextInt(2);
//			System.out.println(d + "," + weibull.density(d));
//		}
//		
//	}
}
