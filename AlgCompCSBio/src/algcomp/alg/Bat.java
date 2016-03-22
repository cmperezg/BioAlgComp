package algcomp.alg; 

import java.util.Random;

import algcomp.util.Dpoint;

public class Bat{

	Dpoint position;
	Dpoint newposition;
	Dpoint velocity; //x = velocity for x
	
	double eval;
	double neweval;
	int id;
	Random r;
	public Bat(int rangex, int rangey, int _id){
		r = new Random();
		id = _id;
		position = new Dpoint(r.nextInt(rangex),r.nextInt(rangey));
		newposition = new Dpoint(r.nextInt(rangex),r.nextInt(rangey));
		velocity = new Dpoint(0,0);
		
	}
	public Bat(Bat b){
		position = b.getPosition();
		newposition = b.getNewposition();
		velocity = b.getVelocity();
		eval = b.getEval();
		neweval = b.getNeweval();
		id = b.getId();
	}
	public Bat(){
		
	}
	public int getId() {
		return id;
	}


	public double getNeweval() {
		return neweval;
	}


	public void setNeweval(double neweval) {
		this.neweval = neweval;
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
