package exercises;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameSelector extends JFrame {

	public JFrame frame;
	
	
 	public GameSelector() {
		frame = new JFrame("Menu");
    	frame.setSize(300, 200);
    	frame.setLocationRelativeTo(null);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//frame.setJMenuBar(MemGame.menu);
    	MemGame.frame.setVisible(false);
    	frame.setVisible(true);
    	
    	JButton button = new JButton("4x4");
		JButton button2 = new JButton("4x3");
		JPanel kontener = new JPanel(new BorderLayout());
		//JPanel kontener = new JPanel(new GridBagLayout());
		//GridBagConstraints gBConst = new GridBagConstraints();
		JLabel napis = new JLabel("wybierze wielkoœæ planszy");
        //gBConst.gridx = 0;
        //gBConst.gridy = 0;
        //kontener.add(napis, gbc);
		JPanel buttonPanel = new JPanel();
		JPanel top = new JPanel();
		//gBConst.fill = GridBagConstraints.HORIZONTAL;
		//gBConst.gridy++;;
		//kontener.add(button, gbc);
		//gBConst.gridy++;
		//kontener.add(button2, gbc);
		
		top.add(napis);
		buttonPanel.add(button);
		buttonPanel.add(button2);
		kontener.add(top, BorderLayout.NORTH);
		kontener.add(buttonPanel, BorderLayout.CENTER);
    	frame.add(kontener);
    	
    	ActionListener buttonListner = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
                case "4x4": System.out.println("4x4");
                break;
                case "4x3": System.out.println("4x3");
                break;
            }
				
			}
		};
    	
    	button.addActionListener(buttonListner);
    	button2.addActionListener(buttonListner);
    	
        
	}
	
}
