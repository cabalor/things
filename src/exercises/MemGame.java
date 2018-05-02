package exercises;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MemGame {
	
		public static JFrame frame;
		public static JMenuBar menu;
		
		
	public MemGame(){
		
		frame = new JFrame("Menu");
    	frame.setSize(800, 600);
    	frame.setLocationRelativeTo(null);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
    	frame.setLayout(new BorderLayout());
        //frame.setLayout(new GridLayout(4,4));
    	
    	
		menu = new JMenuBar();
		JMenuItem exit = new JMenuItem("wyjscie");
		JMenuItem info = new JMenuItem("wyniki");
		JMenuItem newGame = new JMenuItem("nowaGra");
		JButton button = new JButton("nowaGra");
		JButton button2 = new JButton("wyjscie");
		menu.add(exit);
		menu.add(info);
		menu.add(newGame);
		
		JPanel first = new JPanel();
		//first.setLayout(new FlowLayout(FlowLayout.CENTER));
		first.add(button);
		JPanel second = new JPanel();
		second.add(button2);
		JPanel containerPanel = new JPanel(new GridLayout(2, 1));
		containerPanel.add(first);
		containerPanel.add(second);
		frame.add(containerPanel, BorderLayout.NORTH);
		frame.setJMenuBar(menu);
		frame.setVisible(true);
		
		
		ActionListener menuList = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                switch (event.getActionCommand()) {
                    case "End?": /*show();*/
                    break;
                    case "nowaGra": nowaGra();
                    break;
                    case "wyjscie": System.exit(0);
                    break;
                    case "wyniki": JOptionPane.showMessageDialog(frame, wyniki(), null, JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        };
        
        
        exit.addActionListener(menuList);
        info.addActionListener(menuList);
        newGame.addActionListener(menuList);
        
        button.addActionListener(menuList);
        button2.addActionListener(menuList);
		
		
		
	}
	
	
	public static String wyniki() {
		return "najlepsze wyniki\n dupa\n dup\nasdsadasdasdsadddsada\nadsasdasdadasddd\nffffff";
	}
	
	
	private void nowaGra() {
		new GameSelector();
	}
	
	
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MemGame());
		
		
	}

}
