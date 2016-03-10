package algcomp.maintest;

import algcomp.alg.GeneticFunc;
import algcomp.alg.GeneticTSP;
import algcomp.util.Function;

public class MainTester {
	
	
	public static void main(String[] args){
		
		Function f = new Function("Bohachevsky");
		GeneticFunc gen = new GeneticFunc(f,100,0.6,10,0.001);
		System.out.println(gen.fullrun().getEval());
	}

}
