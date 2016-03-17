package algcomp.maintest;

import java.io.IOException;

import algcomp.alg.Bat;
import algcomp.alg.BatAlgorithmFunction;
import algcomp.alg.CuckooSearchFunction;
import algcomp.alg.CuckooSearchTSP;
import algcomp.alg.Egg;
import algcomp.alg.EggTSP;
import algcomp.alg.FireflyAlg;
import algcomp.alg.GeneticFunc;
import algcomp.alg.GeneticTSP;
//import algcomp.alg.PSO;
import algcomp.alg.TSPFireflyAlg;
import algcomp.util.Function;
import algcomp.util.Graph;

public class MainTester {
	
	
	public static void main(String[] args){
		
		//Function f = new Function("Bohachevsky");
		//Function f = new Function("Eggholder");
		//Function f= new Function("Booth");
		//Function f= new Function("Six Hump Camel");
		Function f= new Function("Easom");
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
////		
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
		
//		BatAlgorithmFunction baf = new BatAlgorithmFunction(f,300,.001);
//		Bat b = baf.fullrun();
		
//		PSO pso = new PSO(f, 100,.01);
//		pso.fullrun();
		
//		TSPFireflyAlg fa = new TSPFireflyAlg(g, 50,60000,0.5,0.5);
//		fa.fullrun();
		
		FireflyAlg fa = new FireflyAlg(f,0.001,100,0.5,0.8,1);
		fa.FullRun();
	}

	
}
