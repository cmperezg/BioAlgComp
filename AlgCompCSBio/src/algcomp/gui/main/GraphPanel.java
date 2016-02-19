package algcomp.gui.main;

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
		
        return (new Dimension(500, 500));
    }
	
	public void setGraph(Graph gr){
		graph = gr;
		repaint();
	}
	
	public void paintComponent(Graphics g)
    {
		//this.setBackground(Color.white);
		for(int i=0;i<graph.points.size();i++){
			Graphics2D g2d = (Graphics2D)g;
			g2d.fillOval(graph.points.get(i).getX(), graph.points.get(i).getY(),5, 5);
		}
		
    }
}
