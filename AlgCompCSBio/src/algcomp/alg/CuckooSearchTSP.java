package algcomp.alg;

import java.util.Random;

import org.apache.commons.math3.distribution.WeibullDistribution;


import algcomp.util.Graph;

public class CuckooSearchTSP {
	

	
	Graph g;
	static Random r;
	int numpoints;
	//will use weibull distribution for step length
	static WeibullDistribution weibull;
	int numnests; //number of nests
	double abrate; //abandonment rate
	int timer; //timer
	EggTSP[] nests;
	EggTSP bestegg;
	
	public CuckooSearchTSP(Graph _g, int _numnests, double _abrate, int _timer){
		
		g = _g;
		numnests = _numnests;
		abrate = _abrate;
		timer = _timer;
		nests = new EggTSP[numnests];
		numpoints = g.size();
		
		r = new Random();
		weibull = new WeibullDistribution(1,0.1);
		
		bestegg = new EggTSP(numpoints);
		evaluate(bestegg);
		
		for(int i = 0;i<numnests;i++){
			nests[i] = new EggTSP(numpoints);
			evaluate(nests[i]);
			if(nests[i].getEval()<bestegg.getEval()){
				bestegg = nests[i];
			}
			
		}

	}
	
	public EggTSP step(){
		
		//generate cuckoos
		for(int i = 0; i<nests.length; i++){
			EggTSP cuckoo = new EggTSP(nests[i]);
			//System.out.println("1"+cuckoo.toString());
			
			eggLevyStep(cuckoo);
			evaluate(cuckoo);
			//System.out.println("2"+cuckoo.toString());
			//pick a random nest to dump it and carry out comparisons
			int ind = r.nextInt(nests.length);
			evaluate(nests[ind]);
			//System.out.println("3"+nests[ind].toString());
			if(cuckoo.getEval()<nests[ind].getEval()){
				//System.out.println("yay");
				nests[ind]=new EggTSP(cuckoo);
				evaluate(nests[ind]);
				if(cuckoo.getEval()<bestegg.getEval()){
					bestegg = new EggTSP(cuckoo);
					evaluate(bestegg);
				}
			}else{
				double rn = r.nextDouble();
				//abandon it?
				if(rn < abrate){
					nests[ind] = new EggTSP(numpoints);
				}
			}
		}
		System.out.println(bestegg.toString());
		return bestegg;
	}
	
	public EggTSP fullrun(){
		System.out.println("TSP Cuckoo run");
		
		
		final Thread thisThread = Thread.currentThread();
		final int timeToRun = timer; // 1200 = 2 minutes;

		new Thread(new Runnable() {
		    public void run() {
		        try {
					thisThread.sleep(timeToRun);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Problem with putting thread to sleep");
				}
		        thisThread.interrupt();
		    }
		}).start();

		while (!Thread.interrupted()) {
		    step();
		}
		
		return bestegg;
	}
	
	public double evaluate(EggTSP pc) {
		double ev = 0.0;
		int[] path = pc.getPath();
		//printArray(path);
		for(int i=0; i<path.length-1;i++){
			ev = ev + g.getPoint(path[i]).distanceTo(g.getPoint(path[i+1]));
		}
		
		pc.setEval(ev);
		return ev;
	}
	
	public void eggLevyStep(EggTSP e){
		double rand = r.nextDouble();
		double weib = weibull.density(rand);
		int nummuts = (int)Math.ceil(weib);

		
		for(int i = 0; i<nummuts; i++){
			e.mutate();
		}
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
