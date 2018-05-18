package exercises;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainBoard {

	private JFrame gameJFrame;
	private ImageIcon znakZapytania;
	private ImageIcon animowanyGif;
	private List<MyImageIcon> ukryte = new ArrayList<>();
	private JButton[] guziki;
	private ArrayList<Boolean> lista = new ArrayList<>();
	private ArrayList<Integer> randomoweObrazki = new ArrayList<>() ;
	private int size;
	private int halfSize;
	private int klikniecie;
	private int pierwszyZaznaczony;
	public static int liczbaProb;
	public Timer zegarek;
	java.util.Timer timer2; 
	public TimerTask tTask;
	private boolean taskStarted;
	public String czas;
	public static final int liczbaObrazkow = 12;

	MainBoard(int width, int height) {

		GameSelector.selectorFrame.setVisible(false);

		halfSize = (width * height) / 2;
		size = width * height;
		znakZapytania = new ImageIcon("znakZapytania.png");
		animowanyGif = new ImageIcon("flip.gif");
		guziki = new JButton[size];
		gameJFrame = new JFrame("JMemoryGame");
		gameJFrame.setSize(width * 150, height * 150);
		gameJFrame.setLocationRelativeTo(null);
		gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(width, height));
		gameJFrame.setLayout(new BorderLayout());
		gameJFrame.setJMenuBar(MemGame.menu(MemGame.menuListner()));
		klikniecie = 0;
		pierwszyZaznaczony = -1;
		liczbaProb = 0;
		obrazki();
		
		for (int i = 0; i < halfSize; i++) {
			ukryte.add(new MyImageIcon("obrazek" + Integer.toString(randomoweObrazki.get(i)) + ".png", null, "" + i));
			ukryte.add(new MyImageIcon("obrazek" + Integer.toString(randomoweObrazki.get(i)) + ".png", null, "" + i));
		}

		
		for (int i = 0; i < guziki.length; i++) {
			guziki[i] = new JButton(znakZapytania);
			guziki[i].setContentAreaFilled(false);
			guziki[i].setRolloverIcon(animowanyGif);
			guziki[i].setActionCommand(Integer.toString(i));
			guziki[i].addActionListener(akcjaKlikania);
			panel.add(guziki[i]);
		}


		przygotowanieGuzikow();
		gameJFrame.add(timer(), BorderLayout.NORTH);
		gameJFrame.add(panel, BorderLayout.CENTER);
		gameJFrame.setVisible(true);
	}

	private void przygotowanieGuzikow() {

		Collections.shuffle(ukryte);

		for (int i = 0; i < size; i++) {
			lista.add(i, false);
		}
	}

	private void zaslonGuziki() {
		for (int i = 0; i < size; i++) {
			if (!lista.get(i)) {
				guziki[i].setIcon(znakZapytania);
				guziki[i].setRolloverIcon(animowanyGif);
				guziki[i].setBorderPainted(true);
				guziki[i].setEnabled(true);
			}
		}
	}

	private void endGame() {
		if (!lista.contains(false)) {
			zegarek.stop();
			gameJFrame.setVisible(false);
			new EndScreen(czas);
		}
	}

	ActionListener akcjaKlikania = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			
			if(taskStarted == true) {
				taskStarted = false;
				timer2.cancel();
			}
			
			if (klikniecie < 2 && guziki[Integer.parseInt(event.getActionCommand())].getIcon() == znakZapytania) {
				guziki[Integer.parseInt(event.getActionCommand())].setIcon(ukryte.get(Integer.parseInt(event.getActionCommand())));
				guziki[Integer.parseInt(event.getActionCommand())].setName(ukryte.get(Integer.parseInt(event.getActionCommand())).getValue());
				guziki[Integer.parseInt(event.getActionCommand())].setRolloverIcon(ukryte.get(Integer.parseInt(event.getActionCommand())));
				guziki[Integer.parseInt(event.getActionCommand())].setBorderPainted(false);
				liczbaProb++;
				klikniecie++;

				if (klikniecie == 1) {
					pierwszyZaznaczony = Integer.parseInt(event.getActionCommand());
				}

				if (klikniecie == 2 && guziki[pierwszyZaznaczony].getName().equals(guziki[Integer.parseInt(event.getActionCommand())].getName())) {
					lista.set(pierwszyZaznaczony, true);
					lista.set(Integer.parseInt(event.getActionCommand()), true);
					endGame();
				}

				 if(klikniecie == 2 && !guziki[pierwszyZaznaczony].getName().equals(guziki[Integer.parseInt(event.getActionCommand())].getName())) { 
					
					 
					 timer2 = new java.util.Timer();
					 timer2.schedule(tTask = new TimerTask() {
						 public void run() { 
							 zaslonGuziki();
				 } 
				 }, 2000);
					 taskStarted = true; 
				 }
					 

			} else {
				zaslonGuziki();
				guziki[Integer.parseInt(event.getActionCommand())].setIcon(ukryte.get(Integer.parseInt(event.getActionCommand())));
				guziki[Integer.parseInt(event.getActionCommand())].setName(ukryte.get(Integer.parseInt(event.getActionCommand())).getValue());
				guziki[Integer.parseInt(event.getActionCommand())].setRolloverIcon(ukryte.get(Integer.parseInt(event.getActionCommand())));
				guziki[Integer.parseInt(event.getActionCommand())].setBorderPainted(false);
				klikniecie = 1;
				pierwszyZaznaczony = Integer.parseInt(event.getActionCommand());

			}

		}

	};

	public JLabel timer() {
		final JLabel timeLabel = new JLabel();
		Date start = new Date();
		final DateFormat timeFormat = new SimpleDateFormat("mm:ss");
		ActionListener timerListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				czas = timeFormat.format(new Date(date.getTime() - start.getTime()));
				timeLabel.setText(czas);
				
			}
		};
		zegarek = new Timer(1000, timerListener);
		zegarek.setInitialDelay(0);
		zegarek.start();
		return timeLabel;
	}
	
	private void obrazki(){
		for (int i = 0; i < halfSize; i++) {
			int k = losuj();
			if(randomoweObrazki.contains(k)) {
			i--;
			} else {
				randomoweObrazki.add(k);
			}
		}
		
	}
	private int losuj() {
		return 1 + (int)(Math.random() * ((liczbaObrazkow - 1) + 1));
	}
	
}
