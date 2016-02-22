package algcomp.gui.main;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FixedOptionPanel extends JPanel {
	
	private final String[] algs = { "Genetic","Particle Swarm","Firefly","Ant Colony","Bat","Cuckoo Search"};
	private final String[] probs = {"Travelling Salesman","Function"};
	
	private JLabel problemL;
	private JLabel algL;
	private JComboBox problemCB;
	private JComboBox algCB;
	
	public FixedOptionPanel(){
		problemL = new JLabel("Problem type: ", SwingConstants.RIGHT);
		algL = new JLabel("Algorithm: ", SwingConstants.RIGHT);
		
		problemCB = new JComboBox<String>(probs);
		algCB = new JComboBox<String>(algs);
		
		setLayout(new GridLayout(2,2));
		add(problemL);
		add(problemCB);
		add(algL);
		add(algCB);
	}
	
	//preferred size of panel
		public Dimension getPreferredSize(){
				
		    return (new Dimension(250,150));
		}

}
