package algcomp.alg;

import java.util.ArrayList;
import java.util.Random;

public class PathChromosome extends Chromosome {
	//representation of the solution: a tour.
		
		int numpoints;
		int[] path;

		
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
		
		public double getEval() {
			return eval;
		}

		public void setEval(double eval) {
			this.eval = eval;
		}

		public int[] getPath() {
			return path;
		}
		
		@Override
		public Chromosome crossover(Chromosome c) {
			Random r = new Random();
			int xpoint = r.nextInt(numpoints);
			int[] newpath = new int[numpoints];
			ArrayList<Integer> cpath = ((PathChromosome) c).listPath();
			for(int i = 0; i<xpoint;i++){
				newpath[i] = path[i];
				int del = cpath.indexOf(path[i]);
				cpath.remove(del);
			}
			for(int i=0;i<cpath.size();i++){
				newpath[xpoint] = cpath.remove(0);
				xpoint++;
			}
			Chromosome ret = new PathChromosome(numpoints,newpath);
			return ret;
		}
		
		@Override
		public void mutate() {
			Random random =  new Random();
			int mutpoint = random.nextInt(numpoints-1);
			int temp = 0;
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
		private void printArray(int[] arr){
			for(int i = 0;i<arr.length;i++){
				System.out.print(arr[i]+",");
			}
		}


		


		

		

}
