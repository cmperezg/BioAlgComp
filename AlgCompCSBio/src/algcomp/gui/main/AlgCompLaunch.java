package algcomp.gui.main;

import java.io.IOException;

import javax.swing.SwingUtilities;
//Run this class for main program.
public class AlgCompLaunch {
	public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                try {
					new MainFrame().displayGUI();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
    }
}
