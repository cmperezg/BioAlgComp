package algcomp.alg;

import algcomp.util.Dpoint;

public class Bat {

	Dpoint position;
	Dpoint bestposition;
	Dpoint newposition;
	Dpoint velocity; //x = velocity for x
	
	
	public Dpoint getBestposition() {
		return bestposition;
	}
	public void setBestposition(Dpoint bestposition) {
		this.bestposition = bestposition;
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
	double f; //pulse frequency
	double r; //pulse rates
	double a; //loudness
	
	
	
	
	public Dpoint getPosition() {
		return position;
	}
	public void setPosition(Dpoint position) {
		this.position = position;
	}
	public double getF() {
		return f;
	}
	public void setF(double f) {
		this.f = f;
	}
	public double getR() {
		return r;
	}
	public void setR(double r) {
		this.r = r;
	}
	public double getA() {
		return a;
	}
	public void setA(double a) {
		this.a = a;
	}
	
	
	
}
