package exercises;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MemGame {
	
		public static JFrame mainJFrame;
		//public static JMenuBar mainMenu;
		
		
	public MemGame(){
		
		mainJFrame = new JFrame("Menu");
		mainJFrame.setSize(800, 600);
		mainJFrame.setLocationRelativeTo(null);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainJFrame.setLayout(new BorderLayout());
    	
    	
		//mainMenu = new JMenuBar();
		JLabel napis = new JLabel("witaj w grze memory");
		//JMenuItem exit = new JMenuItem("wyjscie");
		//JMenuItem info = new JMenuItem("wyniki");
		//JMenuItem newGame = new JMenuItem("nowaGra");
		JButton button = new JButton("nowaGra");
		JButton button2 = new JButton("wyjscie");
		//mainMenu.add(exit);
		//mainMenu.add(info);
		//mainMenu.add(newGame);
		
		JPanel first = new JPanel();
		//first.setLayout(new FlowLayout(FlowLayout.CENTER));
		first.add(napis);
		JPanel second = new JPanel();
		second.add(button);
		JPanel third = new JPanel();
		third.add(button2);
		JPanel containerPanel = new JPanel(new GridLayout(3, 1));
		containerPanel.add(first);
		containerPanel.add(second);
		containerPanel.add(third);
		mainJFrame.add(containerPanel, BorderLayout.NORTH);
		//mainJFrame.setJMenuBar(mainMenu);
		mainJFrame.setVisible(true);
		
		
		/*ActionListener menuList = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                switch (event.getActionCommand()) {
                    case "End?": show();
                    break;
                    case "nowaGra": nowaGra();
                    break;
                    case "wyjscie": System.exit(0);
                    break;
                    case "wyniki": JOptionPane.showMessageDialog(mainJFrame, wyniki(), null, JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        };*/
        
        mainJFrame.setJMenuBar(menu(menuListner()));
        //exit.addActionListener(menuList);
        //info.addActionListener(menuList);
        //newGame.addActionListener(menuList);
        
        button.addActionListener(menuListner());
        button2.addActionListener(menuListner());
		
		
		
	}
	
	
	public static String wyniki() {
		return "najlepsze wyniki\n dupa\n dup\nasdsadasdasdsadddsada\nadsasdasdadasddd\nffffff";
	}
	
	
	private static void nowaGra() {
		new GameSelector();
	}
	
	
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MemGame());
		
		
	}

	public static JMenuBar menu(ActionListener listner) {
		JMenuBar mainMenu = new JMenuBar();
		JMenuItem exit = new JMenuItem("wyjscie");
		JMenuItem info = new JMenuItem("wyniki");
		//JMenuItem newGame = new JMenuItem("nowaGra");
		mainMenu.add(exit);
		mainMenu.add(info);
		//mainMenu.add(newGame);
		exit.addActionListener(listner);
        info.addActionListener(listner);
        //newGame.addActionListener(listner);
        return mainMenu;
	}
	
	public static ActionListener menuListner() {
		return new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                switch (event.getActionCommand()) {
                    case "End?": /*show();*/
                    break;
                    case "nowaGra": nowaGra();
                    break;
                    case "wyjscie": System.exit(0);
                    break;
                    case "wyniki": JOptionPane.showMessageDialog(mainJFrame, wyniki(), null, JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
	};
	}
}
