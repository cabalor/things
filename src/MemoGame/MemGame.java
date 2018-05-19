package MemoGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

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
		
		
		
	public MemGame(){
		
		mainJFrame = new JFrame("Menu");
		mainJFrame.setSize(600, 400);
		mainJFrame.setLocationRelativeTo(null);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainJFrame.setLayout(new BorderLayout());
    	
		JLabel napis = new JLabel("Witaj w grze memory");
		napis.setOpaque(false);
		JButton button = new JButton("nowaGra");
		button.setPreferredSize(new Dimension(150, 20));
		button.setFont(new Font("Arial", Font.BOLD, 12));
		JButton button2 = new JButton("wyjscie");
		button2.setPreferredSize(new Dimension(150, 20));
		button2.setFont(new Font("Arial", Font.BOLD, 12));
		JPanel first = new JPanel();
		first.add(napis);
		JPanel second = new JPanel();
		second.add(button);
		JPanel third = new JPanel();
		third.add(button2);
		JPanel kontener = new JPanel(new GridLayout(3, 1));
		kontener.add(first);
		kontener.add(second);
		kontener.add(third);
		mainJFrame.add(kontener, BorderLayout.NORTH);
		mainJFrame.setVisible(true);
        mainJFrame.setJMenuBar(menu(menuListner()));
        
        button.addActionListener(menuListner());
        button2.addActionListener(menuListner());
		
	}
	
	
	private static String wyniki() {
		StringBuilder sb = null;
		List<String> lista = new ArrayList<>();
		int liczbaLinii = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader("wyniki.txt"));
			sb = new StringBuilder();
		    String str;
		    while(((str = br.readLine()) != null)) {
		    	if(liczbaLinii< 12) {
		    	lista.add(str);
		    	}
		    	liczbaLinii++;
		    }
		    br.close();
		} catch ( Exception e) {
			e.printStackTrace();
		}
		
		Collections.sort(lista, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (o1 == null) {
			        return -1;
			    }
			    if (o2 == null) {
			        return 1;
			    }
			    if (o1.equals( o2 )) {
			        return 0;
			    }
			    return o1.compareTo(o2);
			}
		});
		for(String x:lista) {
			sb.append(x);
	    	sb.append("\n");
		}
		
		return sb.toString();
	}
	
	
	private static void nowaGra() {
		mainJFrame.setVisible(false);
		new GameSelector();
	}
	

	public static JMenuBar menu(ActionListener listner) {
		JMenuBar mainMenu = new JMenuBar();
		//JMenuItem rezygnacja = new JMenuItem("rezygnacja");
		JMenuItem exit = new JMenuItem("wyjscie");
		JMenuItem info = new JMenuItem("wyniki");

		mainMenu.add(exit);
		mainMenu.add(info);
		//mainMenu.add(rezygnacja);
		exit.addActionListener(listner);
        info.addActionListener(listner);
        //rezygnacja.addActionListener(listner);
        return mainMenu;
	}
	
	
	public static ActionListener menuListner() {
		return new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                switch (event.getActionCommand()) {
                    case "rezygnacja": new MemGame();
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
	
	public void rezygnuje() {
		
		
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MemGame());
		
	}
	
	
	
}
