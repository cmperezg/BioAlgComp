package algcomp.alg; 

import algcomp.util.Dpoint;

public class Bat {

	Dpoint position;
	Dpoint newposition;
	Dpoint velocity; //x = velocity for x
	
	double eval;
	int id;
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String toString(){
		return "x: " + position.getX() + " y: " + position.getY()+ " ev: " + eval;
	}
	
	
	public double getEval() {
		return eval;
	}
	public void setEval(double eval) {
		this.eval = eval;
	}

	public Dpoint getNewposition() {
		return newposition;
	}
	public void setNewposition(Dpoint newposition) {
		this.newposition = newposition;
	}
	public Dpoint getVelocity() {
		return velocity;
	}
	public void setVelocity(Dpoint velocity) {
		this.velocity = velocity;
	}

	
	
	public Dpoint getPosition() {
		return position;
	}
	public void setPosition(Dpoint position) {
		this.position = position;
	}


	
	
	
}
