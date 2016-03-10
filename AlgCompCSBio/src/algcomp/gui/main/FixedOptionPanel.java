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
	private final String[] funcs = {"Eggholder","Bohachevsky","Booth","Six Hump Camel","Easom"};
	private final String[] graphs = {"g1"};
	
	private JLabel problemL;
	private JLabel algL;
	private JLabel funcsL;
	private JLabel graphsL;
	private JComboBox<String> problemCB;
	private JComboBox<String> algCB;
	private JComboBox<String> funcsCB;
	private JComboBox<String> graphsCB;
	
	private boolean tspselected;
	
	MainFrame mf;
	
	public FixedOptionPanel(MainFrame m){
		problemL = new JLabel("Problem type: ", SwingConstants.RIGHT);
		algL = new JLabel("Algorithm: ", SwingConstants.RIGHT);
		funcsL = new JLabel("Function: ", SwingConstants.RIGHT);
		graphsL = new JLabel("Graph: ", SwingConstants.RIGHT);
		
		problemCB = new JComboBox<String>(probs);
		algCB = new JComboBox<String>(algs);
		funcsCB = new JComboBox<String>(funcs);
		graphsCB = new JComboBox<String>(graphs);
		
		setLayout(new GridLayout(3,2));
		add(problemL);
		add(problemCB);
		add(algL);
		add(algCB);
		add(graphsL);
		add(graphsCB);
	}
	
	//preferred size of panel
		public Dimension getPreferredSize(){
				
		    return (new Dimension(250,150));
		}

}
