package algcomp.alg;

import java.util.ArrayList;
import java.util.Random;

public class PathChromosome{
	//representation of the solution: a tour.
		
		int numpoints;
		int[] path;
		double eval;
		
		//for probability of selection (roulette method)
		double from;
		double to;
		double prob;
		
		// first generation constructor. random.
		public PathChromosome(int num){
			numpoints = num;
			eval = 0.0;
			path = new int[numpoints];
			for(int i = 0; i<numpoints;i++){
				path[i] = i;
			}
			shuffleArray(path);
			//printArray(path);
		}
		
		private PathChromosome(int _nump, int[] _path){
			numpoints = _nump;
			eval = 0.0;
			path = _path;
		}
		
	

		public int[] getPath() {
			return path;
		}
		

		public PathChromosome crossover(PathChromosome c) {
			//System.out.println("NEW CROSSOVER");
			Random r = new Random();
			int xpoint = r.nextInt(numpoints);
			int[] newpath = new int[numpoints];
			ArrayList<Integer> cpath = ((PathChromosome) c).listPath();
			//System.out.println(cpath.size());
			//System.out.println(this.path.length);
			for(int i = 0; i<xpoint;i++){
				newpath[i] = path[i];
				//System.out.println(cpath.size());
				int del = cpath.indexOf(path[i]);
				//System.out.println(del);
				cpath.remove(del);
			}
			int x = cpath.size();
			for(int i=0;i<x;i++){
				//System.out.println(cpath.size());
				newpath[xpoint] = cpath.remove(0);
				//System.out.println(newpath[xpoint]);
				xpoint++;
			}
			//printArray(newpath);
			PathChromosome ret = new PathChromosome(numpoints,newpath);
			return ret;
		}

		public void mutate() {
			Random random =  new Random();
			int mutpoint = random.nextInt(numpoints-1);
			int mutpoint2 = random.nextInt(numpoints-1);
			int temp = 0;
			
//			temp = path[mutpoint];
//			path[mutpoint] = path[mutpoint2];
//			path[0] = temp;
			
			
			if(mutpoint == numpoints-1){
				temp = path[numpoints-1];
				path[numpoints-1] = path[0];
				path[0] = temp;
			}else{
				temp = path[mutpoint];
				path[mutpoint] = path[mutpoint+1];
				path[mutpoint+1] = temp;
			}
			
		}
	

		// Fisher–Yates shuffle array function
		private void shuffleArray(int[] array)
		{
		    int index;
		    Random random = new Random();
		    for (int i = array.length - 1; i > 0; i--)
		    {
		        index = random.nextInt(i + 1);
		        if (index != i)
		        {
		            array[index] ^= array[i];
		            array[i] ^= array[index];
		            array[index] ^= array[i];
		        }
		    }
		}
		
		private ArrayList<Integer> listPath(){
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i = 0;i<numpoints;i++){
				list.add(path[i]);
			}
			return list;
		}
		//for testing
		private String printArray(int[] arr){
			String s = "";
			for(int i = 0;i<arr.length;i++){
				s = s +(arr[i]+",");
			}
			
			return s;
		}


		public String toString(){
			String s = "Ev: " + this.eval
					+" Prob: " + this.prob
					+ " F: " + this.from
					+ " T: " + this.to
					+ " CH: " + printArray(path);
			
			return s;
		}


		
		
		public double getFrom() {
			return from;
		}
		public void setFrom(double from) {
			this.from = from;
		}
		public double getTo() {
			return to;
		}
		public void setTo(double to) {
			this.to = to;
		}
		public double getProb() {
			return prob;
		}
		public void setProb(double prob) {
			this.prob = prob;
		}
		public double getEval() {
			return eval;
		}
		public void setEval(double eval) {
			this.eval = eval;
		}
		

}
