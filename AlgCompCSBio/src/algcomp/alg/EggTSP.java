package algcomp.alg;

import java.util.Random;

public class EggTSP {
	int[] path;
	double eval;
	int numpoints;
	
	public EggTSP(int _num){
		numpoints = _num;
		eval = 0.0;
		path = new int[numpoints];
		for(int i = 0; i<numpoints;i++){
			path[i] = i;
		}
		shuffleArray(path);
	}
	
	public EggTSP(EggTSP egg2) {
		numpoints = egg2.numpoints;
		eval = 0.0;
		path = egg2.getPath();
	}

	public String toString(){
		return "path: " + printArray(path) + " ev: "+ eval;
	}
	
	private String printArray(int[] arr){
		String s = "";
		for(int i = 0;i<arr.length;i++){
			s = s+(arr[i]+" ");
		}
		
		return s;
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
	public void mutate() {
		Random random =  new Random();
		int mutpoint = random.nextInt(numpoints-1);
		int temp = 0;
		
//		temp = path[mutpoint];
//		path[mutpoint] = path[mutpoint2];
//		path[0] = temp;
		
		
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
	
	// Fisher Yates shuffle array function
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
	
}
