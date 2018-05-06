package exercises;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainBoard {

	JFrame gameJFrame;
	ImageIcon znakZapytania;
	ImageIcon kreci像NaObrazku;
	ImageIcon[] obrazki;
	ImageIcon[] ukryteObrazki;
	JButton[] guziki;
	ArrayList<Boolean> lista = new ArrayList<>();
	int size;
	int halfSize;
	int klikniecie;
	int pierwszyZaznaczony;
	public static int liczbaProb;
	Timer timer;

	MainBoard(int width, int height) {

		GameSelector.selectorFrame.setVisible(false);

		halfSize = (width * height) / 2;
		size = width * height;
		znakZapytania = new ImageIcon("znakZapytania.png");
		kreci像NaObrazku = new ImageIcon("flip.gif");
		ukryteObrazki = new ImageIcon[size];
		guziki = new JButton[size];
		obrazki = new ImageIcon[halfSize];

		gameJFrame = new JFrame("JMemoryGame");
		gameJFrame.setSize(width * 100, height * 100);
		gameJFrame.setLocationRelativeTo(null);
		gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(width, height));
		gameJFrame.setLayout(new BorderLayout());
		gameJFrame.setJMenuBar(MemGame.menu(MemGame.menuListner()));

		// zaciaganie obrazkow
		for (int i = 0; i < halfSize; i++) {
			obrazki[i] = new ImageIcon("obrazek" + Integer.toString(i + 1) + ".png");
		}

		// generacja guzikow na planszy
		for (int i = 0; i < guziki.length; i++) {
			guziki[i] = new JButton(znakZapytania);
			guziki[i].setContentAreaFilled(false);
			guziki[i].setRolloverIcon(kreci像NaObrazku);
			guziki[i].setActionCommand(Integer.toString(i));
			guziki[i].addActionListener(akcjaKlikania);
			panel.add(guziki[i]);
		}

		klikniecie = 0;
		pierwszyZaznaczony = -1;
		liczbaProb = 0;
		gra();
		gameJFrame.add(timer(), BorderLayout.NORTH);
		gameJFrame.add(panel, BorderLayout.CENTER);
		gameJFrame.setVisible(true);
	}

	private void gra() {
		ArrayList<Integer> listaObrazkow = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			listaObrazkow.add(i);
		}
		Random rand = new Random();
		for (int i = 0; i < halfSize; i++) {
			int random = rand.nextInt(listaObrazkow.size());
			System.out.println("i = " + i + " pozycja obrazka " + random);
			ukryteObrazki[listaObrazkow.get(random)] = obrazki[i];
			listaObrazkow.remove(random);
			listaObrazkow.forEach(dupa -> System.out.println(dupa));

			random = rand.nextInt(listaObrazkow.size());
			System.out.println("i = " + i + " " + random);
			ukryteObrazki[listaObrazkow.get(random)] = obrazki[i];
			listaObrazkow.remove(random);
			System.out.println("i = " + i + " pozycja obrazka " + random);
			listaObrazkow.forEach(dupa -> System.out.println(dupa));
		}
		for (int i = 0; i < size; i++) {
			lista.add(i, false);
			System.out.println("i " + i + lista.get(i));
		}
	}

	private void zaslonGuziki() {
		for (int i = 0; i < size; i++) {
			if (!lista.get(i)) {
				guziki[i].setIcon(znakZapytania);
				guziki[i].setRolloverIcon(kreci像NaObrazku);
				guziki[i].setBorderPainted(true);
				guziki[i].setEnabled(true);
			}
		}
	}

	private void endGame() {
		if (!lista.contains(false)) {
			timer.stop();
			gameJFrame.setVisible(false);
			new EndScreen();
			;
		}
	}

	ActionListener akcjaKlikania = new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			if (klikniecie < 2 && guziki[Integer.parseInt(event.getActionCommand())].getIcon() == znakZapytania) {
				guziki[Integer.parseInt(event.getActionCommand())]
						.setIcon(ukryteObrazki[Integer.parseInt(event.getActionCommand())]);
				guziki[Integer.parseInt(event.getActionCommand())]
						.setRolloverIcon(ukryteObrazki[Integer.parseInt(event.getActionCommand())]);
				guziki[Integer.parseInt(event.getActionCommand())].setBorderPainted(false);

				klikniecie++;

				/*
				 * new java.util.Timer().schedule( new TimerTask() { public void run() {
				 * zaslonGuziki(); } }, 2000);
				 */

				if (klikniecie == 1) {
					pierwszyZaznaczony = Integer.parseInt(event.getActionCommand());
					System.out.println("jestem tutaj 1 if");
				}

				if (klikniecie == 2 && guziki[pierwszyZaznaczony]
						.getIcon() == guziki[Integer.parseInt(event.getActionCommand())].getIcon()) {
					System.out.println("jestem tutaj 2 if");
					lista.set(pierwszyZaznaczony, true);
					lista.set(Integer.parseInt(event.getActionCommand()), true);
					endGame();

				}

			} else {
				zaslonGuziki();
				guziki[Integer.parseInt(event.getActionCommand())]
						.setIcon(ukryteObrazki[Integer.parseInt(event.getActionCommand())]);
				guziki[Integer.parseInt(event.getActionCommand())]
						.setRolloverIcon(ukryteObrazki[Integer.parseInt(event.getActionCommand())]);
				guziki[Integer.parseInt(event.getActionCommand())].setBorderPainted(false);
				klikniecie = 1;
				pierwszyZaznaczony = Integer.parseInt(event.getActionCommand());
				liczbaProb++;
				System.out.println(liczbaProb);

			}

		}

	};

	public void sprawdz() {
		for (Boolean x : lista) {
			System.out.println(x);
		}
	}

	public JLabel timer() {
		final JLabel timeLabel = new JLabel();
		Date start = new Date();
		final DateFormat timeFormat = new SimpleDateFormat("mm:ss");
		ActionListener timerListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String time = timeFormat.format(new Date(date.getTime()-start.getTime()));
				timeLabel.setText(time);
			}
		};
		timer = new Timer(1000, timerListener);
		
		timer.setInitialDelay(0);
		timer.start();
		return timeLabel;
	}

}
