package algcomp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	public List<Point> points;
	
	//Read graph from file. 
	//file format should be the coordinates of points (x,y) separated by line breaks.
	public Graph(String filename) throws IOException{
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		points = new LinkedList<Point>();
		 
		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		//counter to keep track of ids
		int idcount = 0;
		String line = null;
		//Read line by line, save input into points
		while ((line = br.readLine()) != null) {
			String[] sep = line.split(",");
			//id will also be the location of the point in the point list
			Point p = new Point(idcount,Integer.parseInt(sep[0]),Integer.parseInt(sep[1]));
			idcount++;
			points.add(p);
		}
	 
		br.close();
	}// end constructor

	// get list of points
	public List<Point> getPoints() {
		return points;
	}
	
	public int size(){
		return points.size();
	}
	
	public Point getPoint(int index){
		return points.get(index);
	}
	
}
