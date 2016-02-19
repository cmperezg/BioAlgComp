package algcomp.gui.main;

import java.awt.Color;
import java.awt.GridLayout;
//import java.awt.event.MouseAdapter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame {
	private GraphPanel canvas;
	private OptionPanel options;
	
	public void displayGUI() throws IOException{
		JFrame frame = new JFrame("Drawing Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new GridLayout(1,2));
        
        canvas = new GraphPanel("/u1/cmperezgavilantorres/workspacejava/graphs/g1");
        options = new OptionPanel("Genetic");
        mainpanel.add(canvas);
        mainpanel.add(options);
        mainpanel.setBackground(Color.WHITE); 
        frame.setContentPane(mainpanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
	}
}
