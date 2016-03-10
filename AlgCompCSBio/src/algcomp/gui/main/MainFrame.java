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
	private FixedOptionPanel fixedop;
	private JPanel allop;
	
	public void displayGUI() throws IOException{
		JFrame frame = new JFrame("Algorithm Comparison");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        fixedop = new FixedOptionPanel(this);
        allop = new JPanel();
        
        JPanel mainpanel = new JPanel();
        
        mainpanel.setLayout(new GridLayout(1,2));
        
        canvas = new GraphPanel("/u1/cmperezgavilantorres/workspacejava/graphs/g1");
        options = new OptionPanel("Genetic",canvas);
        
        
        allop.setLayout(new GridLayout(2,1));
        allop.add(fixedop);
        allop.add(options);
        mainpanel.add(canvas);
        mainpanel.add(allop);
        mainpanel.setBackground(Color.WHITE); 
        frame.setContentPane(mainpanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
	}
}
