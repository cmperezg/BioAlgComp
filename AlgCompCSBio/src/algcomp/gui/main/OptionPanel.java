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

public class OptionPanel extends JPanel{
	String type;
	public OptionPanel(String _type){
		type = _type;
		if(type.equals("Genetic")){
			genPan();
		}
		
	}

	//preferred size of panel
	public Dimension getPreferredSize(){
			
	    return (new Dimension(300, 500));
	}
	
	private void genPan(){
		JLabel gensizeL = new JLabel("Generation size: ", SwingConstants.RIGHT);
		JLabel mutprobL = new JLabel("Mutation probability: ", SwingConstants.RIGHT);
		JTextField gensizeTF = new JTextField(10);
		JTextField mutprobTF = new JTextField(10);
		
		JButton runstepB = new JButton("Run Step");
		JButton runallB = new JButton("Full Run");

		this.setLayout(new GridLayout(3,2));
		
		this.add(gensizeL);
		this.add(gensizeTF);
		this.add(mutprobL);
		this.add(mutprobTF);
		this.add(runstepB);
		this.add(runallB);
		
		//This function runs when runstep button is clicked
		runstepB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Will run one step someday :D ");
			}
	    });
		
		//This function runs when runall button is clicked
		runallB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Will launch a full run someday :D ");
			}
	    });
	}
		
}
