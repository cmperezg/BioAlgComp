package algcomp.util;

public class Dpoint {
	
	private double x;
	private double y;
	private int id;
	
	
	public Dpoint(int nid,double nx, double ny){
		id = nid;
		x = nx;
		y = ny;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setx(double x) {
		this.x = x;
	}
	public void sety(double y) {
		this.y = y;
	}
	
	//returns distance from this point to another.
	public double distanceTo(Dpoint b){
		//calculate the distance
		double distance = Math.sqrt(Math.pow((x-b.getX()), 2) + Math.pow((y-b.getY()), 2));
		return distance;
	}
}
