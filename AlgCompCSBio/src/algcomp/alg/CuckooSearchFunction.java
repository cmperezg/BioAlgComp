package algcomp.alg;

import java.util.Random;

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
	
	public CuckooSearchFunction(Function _f, int _numnests, double _abrate, double _tol){
		
		f = _f;
		numnests = _numnests;
		abrate = _abrate;
		tol = _tol;
		
		r = new Random();
		weibull = new WeibullDistribution(1,0.1);

	}
	
	public double levyStep(double d){
		double rand = r.nextDouble();
		double weib = weibull.density(rand);
		double plusorminus = r.nextDouble();
		if(plusorminus < 0.5){
			return d-weib;
		}return d+weib;
	}
	
	public static void main(String[] args){
		
		for(int i =0; i<100; i++){
			r = new Random();
			weibull = new WeibullDistribution(1,0.1);
			
			double d = r.nextDouble();
			System.out.println(d + "," + weibull.density(d));
		}
		
	}
}
