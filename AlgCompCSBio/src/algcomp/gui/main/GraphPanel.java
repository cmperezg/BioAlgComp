package algcomp.gui.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JPanel;

import algcomp.util.Graph;

// main canvas class where points of the graph are drawn
public class GraphPanel extends JPanel {
	
	private Graph graph;
	private int[] path;
	private boolean hasPath;
	
	
	public GraphPanel(String gf) throws IOException{
		super();
		graph = new Graph(gf);
		hasPath = false;
		repaint();
		
	}
	
	//preferred size of panel
	public Dimension getPreferredSize(){
		
        return (new Dimension(500, 400));
    }
	
	public void setGraph(Graph gr){
		graph = gr;
		repaint();
	}
	
	public Graph getGraph() {
		return graph;
	}

	public void setPath(int[] _path){
		path = _path;
		hasPath = true;
		//printArray(path);
		removeAll();
		revalidate();
		repaint();
		
	}
	
	//for testing
	private void printArray(int[] arr){
		for(int i = 0;i<arr.length;i++){
			System.out.print(arr[i]+",");
		}
	}
	
	public void paintComponent(Graphics g)
    {
		
		//System.out.println("amieventrying");
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		for(int i=0;i<graph.points.size();i++){
			g2d.fillOval(graph.points.get(i).getX(), graph.points.get(i).getY(),5, 5);
		}
		g2d.setColor(Color.RED);
		if(hasPath == true){
			for(int i=0; i<path.length-1;i++){
				g2d.drawLine(graph.getPoint(path[i]).getX(), graph.getPoint(path[i]).getY(), graph.getPoint(path[i+1]).getX(), graph.getPoint(path[i+1]).getY());
			}
		}
    }
}
