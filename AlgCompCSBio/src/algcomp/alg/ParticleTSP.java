package algcomp.alg;



public class ParticleTSP {
	int[] position;
	int[] pbest;
	int velocity;
	double eval;
	double besteval;
	public int[] getPosition() {
		return position;
	}
	public void setPosition(int[] position) {
		this.position = position;
	}
	public int[] getPbest() {
		return pbest;
	}
	public void setPbest(int[] pbest) {
		this.pbest = pbest;
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
	public double getBesteval() {
		return besteval;
	}
	public void setBesteval(double besteval) {
		this.besteval = besteval;
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
	
}
