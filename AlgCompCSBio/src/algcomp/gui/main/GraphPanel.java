package algcomp.gui.main;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import algcomp.util.Graph;

// main canvas class where points of the graph are drawn
public class GraphPanel extends JPanel {
	
	private Graph graph;
	private int[] path;
	private boolean hasPath;
	JLabel distL;
	double dist;
	
	
	public GraphPanel(String gf) throws IOException{
		super();
		graph = new Graph(gf);
		hasPath = false;
		distL = new JLabel("Tour distance: ");
		distL.setHorizontalAlignment(JLabel.RIGHT);
		add(distL, BorderLayout.SOUTH);
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

	public void setPath(int[] _path, double distance){
		path = _path;
		hasPath = true;
		dist = distance;
		//printArray(path);
		//removeAll();
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

		super.paintComponent(g);
		distL.setText("Tour distance: " + dist);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLUE);
		for(int i=0;i<graph.points.size();i++){
			g2d.fillOval(graph.points.get(i).getX(), graph.points.get(i).getY(),7, 7);
		}
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(2));
		if(hasPath == true){
			for(int i=0; i<path.length-1;i++){
				g2d.drawLine(graph.getPoint(path[i]).getX(), graph.getPoint(path[i]).getY(), graph.getPoint(path[i+1]).getX(), graph.getPoint(path[i+1]).getY());
			}
		}
    }
}
