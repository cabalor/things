package exercises;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameSelector {

	public static JFrame selectorFrame;
	
	
 	public GameSelector() {
 		selectorFrame = new JFrame("Menu");
 		selectorFrame.setSize(300, 200);
 		selectorFrame.setLocationRelativeTo(null);
 		selectorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		selectorFrame.setVisible(true);
    	
    	JButton button4x3 = new JButton("4x3");
		JButton button4x4 = new JButton("4x4");
		JButton button5x4 = new JButton("5x4");
		JPanel kontener = new JPanel(new BorderLayout());
		JLabel napis = new JLabel("wybierze wielkoœæ planszy");
		JPanel buttonPanel = new JPanel();
		JPanel top = new JPanel();
		
		top.add(napis);
		buttonPanel.add(button4x3);
		buttonPanel.add(button4x4);
		buttonPanel.add(button5x4);
		kontener.add(top, BorderLayout.NORTH);
		kontener.add(buttonPanel, BorderLayout.CENTER);
		selectorFrame.add(kontener);
    	
    	ActionListener buttonListner = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
                case "4x3": new MainBoard(4, 3);
                break;
                case "4x4": new MainBoard(4, 4);
                break;
                case "5x4": new MainBoard(5, 4);
                break;
            }
				
			}
		};
    	
		button4x3.addActionListener(buttonListner);
		button4x4.addActionListener(buttonListner);
		button5x4.addActionListener(buttonListner);
    	
	}
	
}
