package exercises;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

public class MainBoard implements ActionListener {

	JFrame gameJFrame;
	ImageIcon zakryty;
	ImageIcon kreci��NaObrazku;
	ImageIcon[] obrazkiNaGuzikach;
	ImageIcon[] ukryteObrazki;
	JButton[] guziki;
	ArrayList<Boolean> lista = new ArrayList<>();
	int size;
	int halfSize;
	int klikniecie;
	int pierwszyZaznaczony;
	int liczbaProb;

	MainBoard(int width, int height) {

		GameSelector.selectorFrame.setVisible(false);

		halfSize = (width * height) / 2;
		size = width * height;
		zakryty = new ImageIcon("question.png");
		kreci��NaObrazku = new ImageIcon("tileFlip.gif");
		ukryteObrazki = new ImageIcon[size];
		guziki = new JButton[size];
		obrazkiNaGuzikach = new ImageIcon[halfSize];

		gameJFrame = new JFrame("JMemoryGame");
		gameJFrame.setSize(width * 100, height * 100);
		gameJFrame.setLocationRelativeTo(null);
		gameJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameJFrame.setLayout(new GridLayout(width, height));
		gameJFrame.setJMenuBar(MemGame.menu(MemGame.menuListner()));

		// zaciaganie obrazkow na guziki
		for (int i = 0; i < halfSize; i++) {
			obrazkiNaGuzikach[i] = new ImageIcon("fox" + Integer.toString(i + 1) + ".png");
		}

		// generacja guzikow na planszy
		for (int i = 0; i < guziki.length; i++) {
			guziki[i] = new JButton(zakryty);
			guziki[i].setContentAreaFilled(false);
			guziki[i].setRolloverIcon(kreci��NaObrazku);
			guziki[i].setActionCommand(Integer.toString(i));
			guziki[i].addActionListener(this);
			gameJFrame.add(guziki[i]);
		}

		
		klikniecie = 0;
		pierwszyZaznaczony = -1;
		liczbaProb = 0;
		gra();

		gameJFrame.setVisible(true);
	}

	private void gra() {
		ArrayList<Integer> listaObrazkow = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			listaObrazkow.add(i);
		}
		Random rand = new Random();
		for (int i = 0; i < halfSize; i++) {
			int pozycjaObrazka = rand.nextInt(listaObrazkow.size());
			System.out.println("i = " + i + " pozycja obrazka " + pozycjaObrazka);
			ukryteObrazki[listaObrazkow.get(pozycjaObrazka)] = obrazkiNaGuzikach[i];
			listaObrazkow.remove(pozycjaObrazka);
			listaObrazkow.forEach(dupa -> System.out.println(dupa));

			pozycjaObrazka = rand.nextInt(listaObrazkow.size());
			System.out.println("i = " + i + " " + pozycjaObrazka);
			ukryteObrazki[listaObrazkow.get(pozycjaObrazka)] = obrazkiNaGuzikach[i];
			listaObrazkow.remove(pozycjaObrazka);
			System.out.println("i = " + i + " pozycja obrazka " + pozycjaObrazka);
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
				guziki[i].setIcon(zakryty);
				guziki[i].setRolloverIcon(kreci��NaObrazku);
				guziki[i].setBorderPainted(true);
			}
		}
	}

	
	private void endGame() {
		if (lista.contains(false)) {
			System.out.println("jeszcze jest gra");
			sprawdz();
		} else {
			System.out.println("koniec gry");
			gameJFrame.setVisible(false);
			new MemGame();
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (klikniecie < 2 && guziki[Integer.parseInt(event.getActionCommand())].getIcon() == zakryty) {

			guziki[Integer.parseInt(event.getActionCommand())].setIcon(ukryteObrazki[Integer.parseInt(event.getActionCommand())]);
			guziki[Integer.parseInt(event.getActionCommand())].setRolloverIcon(ukryteObrazki[Integer.parseInt(event.getActionCommand())]);
			guziki[Integer.parseInt(event.getActionCommand())].setBorderPainted(false);
			klikniecie++;
			new java.util.Timer().schedule(
					new TimerTask() {
						public void run() {
							zaslonGuziki();
							System.err.println("wykonuje si�");
						}
					}, 2000);
			
			
			if (klikniecie == 1) {
				pierwszyZaznaczony = Integer.parseInt(event.getActionCommand());
				System.out.println("jestem tutaj 1 if");
			}
			
			if (klikniecie == 2 && guziki[pierwszyZaznaczony].getIcon() == guziki[Integer.parseInt(event.getActionCommand())].getIcon()) {
				System.out.println("jestem tutaj 2 if");
				lista.set(pierwszyZaznaczony, true);
				lista.set(Integer.parseInt(event.getActionCommand()), true);
				endGame();

			}
			
		} else {
			zaslonGuziki();
			guziki[Integer.parseInt(event.getActionCommand())].setIcon(ukryteObrazki[Integer.parseInt(event.getActionCommand())]);
			guziki[Integer.parseInt(event.getActionCommand())].setRolloverIcon(ukryteObrazki[Integer.parseInt(event.getActionCommand())]);
			guziki[Integer.parseInt(event.getActionCommand())].setBorderPainted(false);
			klikniecie = 1;
			pierwszyZaznaczony = Integer.parseInt(event.getActionCommand());
			liczbaProb++;
			System.out.println(liczbaProb);
			
		}

	}

	public void sprawdz() {
		for (Boolean x : lista) {
			System.out.println(x);
		}
	}

}