package algcomp.gui.main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import algcomp.alg.Genetic;
import algcomp.alg.PathChromosome;


public class OptionPanel extends JPanel{
	String type;
	GraphPanel gp;
	
	
	Genetic genalg;
	boolean alg_initialized;
	
	//genetic
	JLabel gensizeL;
	JLabel mutprobL;
	JLabel timerL;
	JLabel immigrantsL;
	JTextField gensizeTF;
	JTextField mutprobTF;
	JTextField timerTF;
	JTextField immigrantsTF;
	
	JButton runstepB;
	JButton runallB; 
	
	public OptionPanel(String _type, GraphPanel _gp){
		type = _type;
		gp = _gp;
		
		runstepB = new JButton("Run Step");
		runallB = new JButton("Full Run"); 
		
		
		if(type.equals("Genetic")){
			genPan();
			alg_initialized = false;
		}
		
	}

	//preferred size of panel
	public Dimension getPreferredSize(){
			
	    return (new Dimension(250,150));
	}
	
	//
	private void genPan(){
		gensizeL = new JLabel("Generation size: ", SwingConstants.RIGHT);
		mutprobL = new JLabel("Mutation probability: ", SwingConstants.RIGHT);
		immigrantsL = new JLabel("Random immigrants: ", SwingConstants.RIGHT);
		timerL = new JLabel("Timer for full run (ms): ", SwingConstants.RIGHT);

		gensizeTF = new JTextField("200");
		mutprobTF = new JTextField("0.5");
		immigrantsTF = new JTextField("10");
		timerTF = new JTextField("60000");
		

		this.setLayout(new GridLayout(5,2));
		
		this.add(gensizeL);
		this.add(gensizeTF);
		this.add(mutprobL);
		this.add(mutprobTF);
		this.add(immigrantsL);
		this.add(immigrantsTF);
		this.add(timerL);
		this.add(timerTF);
		this.add(runstepB);
		this.add(runallB);
		
		//This function runs when runstep button is clicked
		runstepB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!alg_initialized){
					if(type.equals("Genetic")){
						genalg = new Genetic(gp.getGraph(),Integer.parseInt(gensizeTF.getText()),
								Double.parseDouble(mutprobTF.getText()),Integer.parseInt(immigrantsTF.getText()),Integer.parseInt(timerTF.getText()));
						alg_initialized = true;
					}
				}
					
			if(type.equals("Genetic")){
				gp.setPath(((PathChromosome) genalg.step()).getPath());	
			}

			}
	    });
		
		//This function runs when runall button is clicked
		runallB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(type.equals("Genetic")){
					genalg = new Genetic(gp.getGraph(),Integer.parseInt(gensizeTF.getText()),
							Double.parseDouble(mutprobTF.getText()),Integer.parseInt(immigrantsTF.getText()),Integer.parseInt(timerTF.getText()));
					alg_initialized = true;
					gp.setPath(((PathChromosome) genalg.fullrun()).getPath());	
				}
			}
	    });
	}
		
}
