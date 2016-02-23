package algcomp.util;

public class Point {
	
	private int x;
	private int y;
	private int id;
	
	
	public Point(int nid,int nx, int ny){
		id = nid;
		x = nx;
		y = ny;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setx(int x) {
		this.x = x;
	}
	public void sety(int y) {
		this.y = y;
	}
	
	//returns distance from this point to another.
	public double distanceTo(Point b){
		//calculate the distance
		double distance = Math.sqrt(Math.pow((x-b.getX()), 2) + Math.pow((y-b.getY()), 2));
		return distance;
	}
}
