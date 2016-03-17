package algcomp.alg;

import java.util.Random;

public class TSPfly {
	int numofpoints;
	int [] path;
	double Intensity;


	
	public TSPfly(int num){
		numofpoints = num;

		path = new int[numofpoints];
		for(int i = 0; i<numofpoints;i++){
			path[i] = i;
		}
		shuffleArray(path);   
		//printArray(path);
	}
	


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
	
	public String toString(){
		String ret = "";
		for(int i = 0;i<path.length;i++){
			System.out.print(path[i]+",");
		}
		
		return ret+ " eval: " + Intensity;
	}



	public int[] getPath() {
		return path;
	}
	
}
