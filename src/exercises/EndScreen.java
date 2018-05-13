package exercises;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EndScreen {

	
	JFrame endScreenJFrame;
	private String nick;
	
	public EndScreen(String czas) {
		endScreenJFrame = new JFrame("wprowadz wynik");
		endScreenJFrame.setSize(400, 300);
		endScreenJFrame.setLocationRelativeTo(null);
		endScreenJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endScreenJFrame.setVisible(true);
		JTextField textField = new JTextField();
		JLabel label = new JLabel("Twój wynik to "+ String.valueOf(MainBoard.liczbaProb)+". Wpisz nick'a");
		textField.setPreferredSize(new Dimension(100, 40));
		JButton endButton = new JButton("zapisz wynik");
		endScreenJFrame.setLayout(new FlowLayout());
		endScreenJFrame.add(label);
		endScreenJFrame.add(textField);
		endScreenJFrame.add(endButton);
		
		
		endButton.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			nick = textField.getText();
			System.out.println(nick);
			endScreenJFrame.setVisible(false);
			MemGame.mainJFrame.setVisible(true);
			try {
				write(nick, String.valueOf(MainBoard.liczbaProb), czas);
			} catch (IOException e1) {
				System.err.println("cos poszlo nie tak z plikiem");
				e1.printStackTrace();
			}
			}
		});
		
	
	}
	
	public void write(String name, String wynik,String czas) throws IOException {
		File file = new File("wyniki.txt");
		if (!file.exists()) {
            file.createNewFile();
        }
		FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(wynik +" punktow zdobyl "+ name+". W  czasie "+ czas);
        bw.newLine();
        bw.close();
		
	}
	
}
