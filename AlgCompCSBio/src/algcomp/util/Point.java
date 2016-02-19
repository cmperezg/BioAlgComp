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
	
	//returns distance from this point to another.
	public double distanceTo(Point b){
		//calculate the distance
		double distance = Math.sqrt(Math.pow((x-b.getX()), 2) + Math.pow((y-b.getY()), 2));
		return distance;
	}
}
