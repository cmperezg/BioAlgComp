package algcomp.alg;

import java.util.Random;

public class BatTSP{

	int[] position;
	int[] newposition;
	int velocity; //x = velocity for x
	int numpoints;
	double eval;
	double neweval;
	int id;
	
	public BatTSP(int _num){
		numpoints = _num;
		eval = 0.0;
		position = new int[numpoints];
		newposition = new int[numpoints];
		for(int i = 0; i<numpoints;i++){
			position[i] = i;
			newposition[i] = i;
		}
		shuffleArray(position);
		shuffleArray(newposition);
	}
	
	public int[] getPosition() {
		return position;
	}
	public void setPosition(int[] position) {
		this.position = position;
	}
	public int[] getNewposition() {
		return newposition;
	}
	public void setNewposition(int[] newposition) {
		this.newposition = newposition;
	}
	public int getVelocity() {
		return velocity;
	}
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	public double getEval() {
		return eval;
	}
	public void setEval(double eval) {
		this.eval = eval;
	}
	public double getNeweval() {
		return neweval;
	}
	public void setNeweval(double neweval) {
		this.neweval = neweval;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String toString(){
		return "path: " + printArray(position) + " ev: "+ eval;
	}
	
	private String printArray(int[] arr){
		String s = "";
		for(int i = 0;i<arr.length;i++){
			s = s+(arr[i]+" ");
		}
		
		return s;
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
