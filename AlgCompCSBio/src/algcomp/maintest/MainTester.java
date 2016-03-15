package algcomp.maintest;

import java.io.IOException;

import algcomp.alg.Bat;
import algcomp.alg.BatAlgorithmFunction;
import algcomp.alg.CuckooSearchFunction;
import algcomp.alg.CuckooSearchTSP;
import algcomp.alg.Egg;
import algcomp.alg.EggTSP;
import algcomp.alg.GeneticFunc;
import algcomp.alg.GeneticTSP;
import algcomp.util.Function;
import algcomp.util.Graph;

public class MainTester {
	
	
	public static void main(String[] args){
		
		Function f = new Function("Eggholder");
//		System.out.println("rangex: " + f.getRangex() + " rangey: " + f.getRangey()
//		+ " opt: " + f.getOptev());
//		GeneticFunc gen = new GeneticFunc(f,100,0.6,10,0.001);
//		System.out.println(gen.fullrun().getEval());
		
//		CuckooSearchFunction cs = new CuckooSearchFunction(f,15,0.25,0.0001);
//		Egg e = cs.fullrun();
//		System.out.println(e.getX()+","+e.getY()+","+e.getEval());
		//System.out.println(f.eval(7.8, 3.14));
//		Graph g = null;
//		try {
//			g = new Graph("/u1/cmperezgavilantorres/workspacejava/graphs/g1");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		CuckooSearchTSP sct = new CuckooSearchTSP(g,15,0.25,60000);
//		EggTSP e = sct.fullrun();
//		System.out.println(e.toString());
		
//		EggTSP e = new EggTSP(10);
//		sct.evaluate(e);
//		System.out.println(e.toString());
//		sct.eggLevyStep(e);
//		
//		sct.evaluate(e);
//		System.out.println(e.toString());
		
		BatAlgorithmFunction baf = new BatAlgorithmFunction(f,15,.001);
		Bat b = baf.fullrun();
	}

	
}
