package algcomp.alg;

import algcomp.util.Dpoint;

public class Particle {
	Dpoint position;
	Dpoint pbest;
	Dpoint velocity;
	double eval;
	double besteval;
	
	
	public double getBesteval() {
		return besteval;
	}
	public void setBesteval(double besteval) {
		this.besteval = besteval;
	}
	public Dpoint getPosition() {
		return position;
	}
	public void setPosition(Dpoint position) {
		this.position = position;
	}
	public Dpoint getVelocity() {
		return velocity;
	}
	public void setVelocity(Dpoint velocity) {
		this.velocity = velocity;
	}
	public double getEval() {
		return eval;
	}
	public void setEval(double eval) {
		this.eval = eval;
	}
	public String toString(){
		return "x: " + position.getX() + " y: " + position.getY()+ " ev: " + eval +" xvel: " + velocity.getX()+" yvel: " + velocity.getY();
	}
	public Dpoint getPbest() {
		return pbest;
	}
	public void setPbest(Dpoint pbest) {
		this.pbest = pbest;
	}
	

}
