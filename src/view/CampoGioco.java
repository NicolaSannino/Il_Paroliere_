package view;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import model.DBConnection;
import model.DBConnectionMariaDB;
import model.Query;


public class CampoGioco extends JFrame implements ActionListener{
	Vector<JButton> buttonsclick=new Vector(0,1);

	JButton[][] buttons = new JButton[4][4];

	private char[][] tabella = new char[4][4];
	private JLabel Testo;
	//private JTextField testo;

	private JButton Tabella, Invio;
	private JPanel GrigliaGioco;

	//label e classe timer
	private Timer timer;
	private JLabel timeLabel;
	int minutes=10;
	int seconds = 480;
	boolean fine=false;

	public CampoGioco(){

		this.setTitle("Campo da Gioco");

		JLabel labelTitolo = new JLabel();
		labelTitolo.setText("Campo Da Gioco Paroliere");
		labelTitolo.setForeground(new Color(0, 0, 0));
		labelTitolo.setFont(new Font("MV Boli", Font.PLAIN, 40));

		labelTitolo.setBackground(new Color(123, 50, 250));
		labelTitolo.setOpaque(true);
		//labelTitolo.setVerticalAlignment(labelTitolo.TOP);
		//labelTitolo.setHorizontalAlignment(labelTitolo.CENTER);
		//labelTitolo.setBounds(550, 10, 400, 70);
		labelTitolo.setSize(500, 70);
		this.centerComponent(labelTitolo, 20);

		GrigliaGioco = new JPanel();
		GrigliaGioco.setLayout(new GridLayout(4, 4, 10, 10));

		char[] consonanti = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'z'};
		char[] vocali = {'a', 'e', 'i', 'o', 'u'};
		int a=0;
		for(int i = 1;i < 5;i++){
			a++;
			for(int j = 1;j < 5; j++){
				Random rand = new Random();
				int num;

				if (a%2 == 0){
					num = vocali[rand.nextInt(5)];
				}else{
					num = consonanti[rand.nextInt(18)];
				}

				Tabella = new JButton(""+(char)num);
				tabella[i-1][j-1]=(char)num;
				buttons[i-1][j-1] = Tabella;
				Tabella.setName("Btn"+a);
				Tabella.addActionListener(this);
				Tabella.setSize(50, 50);
				GrigliaGioco.add(Tabella);

				a++;
			}
		}

		stampaMatrice();

		GrigliaGioco.setVisible(true);
		GrigliaGioco.setSize(250, 250);
		GrigliaGioco.setBackground(new Color(123, 50, 250));
		this.centerComponent(GrigliaGioco, 200);

		Border border = BorderFactory.createLineBorder(Color.blue, 3);

		Testo = new JLabel("Inserisci Lettere");
		//testo= new JTextField("Inserisci lettere");

		//testo.setOpaque(false);
		this.add(Testo);
		//this.add(testo);

		Font fontA = new Font ("Book Antiqua", Font. BOLD, 15);

		Testo.setBorder(border);
		//testo.setVerticalAlignment(labelTitolo.CENTER);
		//testo.setHorizontalAlignment(labelTitolo.CENTER);
		//testo.setSize(200,40);
		//testo.setBackground(new Color(0, 0, 255));
		//testo.setBounds(684,560,130,31);
		Testo.setSize(230,30);
		Testo.setFont(fontA);
		Testo.setForeground(Color.white);
		this.centerComponent(Testo, 550);

		Invio = new JButton("Cerca Parola");
		this.add(Invio);
		//Invio.setBounds(335,700,135,20);
		Invio.addActionListener(this);
		Invio.setSize(200,50);
		this.centerComponent(Invio, 700);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int frameWidth = (int) screenSize.getWidth();
		int frameHeight = (int) screenSize.getHeight();

		timeLabel = new JLabel("00:00");
		timeLabel.setFont(new Font("MV Boli", Font.BOLD, 35));
		//CentraOggetti c = new CentraOggetti();
		//c.centraLabel(timeLabel,this,100);
		timeLabel.setBounds(30, 30, 200,40);
		this.add(timeLabel);

		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fine == false){
					seconds--;
					int hour = seconds / 3600;
					int minute = (seconds % 3600) / 60;
					int second = seconds % 60;
					timeLabel.setText(String.format("%02d:%02d", minute, second));
					if(Integer.compare(seconds,0)==0){
						fine = true;
						timer.stop();
						CampoGioco.this.dispose();
					}
				}
			}
		});
		timer.start();

		this.setSize(frameWidth, frameHeight);
		this.setLayout(null);
		this.setResizable(false);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.getContentPane().setBackground(new Color(123, 50, 250));
		this.add(GrigliaGioco);
		this.setVisible(true);
		this.add(labelTitolo);
	}

	public void centerComponent(JComponent c, int y){
		Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
		Dimension frameSize = c.getSize ();
		c.setLocation ((screenSize.width - frameSize.width) / 2, y);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean trovato=false;
		String azione = e.getActionCommand();
		JButton button = (JButton) e.getSource();
		buttonsclick.add(button);

		for (int i = 97; i < 123; i++) {
			i = (char) i;
			if (azione.equals(Character.toString(i))) {
				if (Testo.getText().equals("Inserisci Lettere")) {
					Testo.setText("");
					Testo.setText(Testo.getText() + azione);
				} else {
					Testo.setText(Testo.getText() + azione);
				}
				for(int n=0;n<4 && trovato==false;n++){
					for(int j=0;j<4 && trovato==false;j++){
						if(buttons[n][j].equals(button)){
							updateButtons(n,j);
							trovato=true;
						}
					}
				}
			}
		}
		if (azione.equals("Cerca Parola")) {
			System.out.println(Testo.getText());
			DBConnectionMariaDB conn = new DBConnectionMariaDB();
			Query q = new Query();


			if (controllaParola(Testo.getText()) == true) {
				System.out.println("parola trovata");
				try {
					if(q.ricercaParolaDb(Testo.getText(),conn) == true){
						System.out.println("parola trovata");
					}else{
						System.out.println("parola non trovata");
					}
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				}

			} else {
				System.out.println("parola non trovata");
			}


		}
	}

	public void stampaMatrice(){
		for(int i=0;i<4;i++){
			System.out.println();
			for(int j=0;j<4;j++){
				System.out.println(tabella[i][j]);
			}
		}
	}

	public boolean controllaParola(String parola) {
		// Controlla che la parola non sia vuota o null
		if (parola == null || parola.length() == 0) {
			return false;
		}

		// Converte la parola in minuscolo per confrontare i caratteri
		parola = parola.toLowerCase();

		// Cerca la prima lettera della parola nella tabella
		for (int riga = 0; riga < tabella.length; riga++) {
			for (int colonna = 0; colonna < tabella[0].length; colonna++) {
				if (tabella[riga][colonna] == parola.charAt(0)) {
					// Se la prima lettera è stata trovata, cerca le lettere successive
					if (cercaParola(parola, riga, colonna, 1)) {
						return true;
					}
				}
			}
		}

		// Se la parola non è stata trovata, restituisce false
		return false;
	}

	private boolean cercaParola(String parola, int riga, int colonna, int indice) {
		// Se tutte le lettere della parola sono state trovate, restituisce true
		if (indice == parola.length()) {
			return true;
		}

		// Cerca la lettera successiva nella tabella
		for (int riga2 = riga - 1; riga2 <= riga + 1; riga2++) {
			for (int colonna2 = colonna - 1; colonna2 <= colonna + 1; colonna2++) {
				// Controlla che la posizione sia all'interno della tabella
				if (riga2 >= 0 && riga2 < tabella.length && colonna2 >= 0 && colonna2 < tabella[0].length && tabella[riga2][colonna2] == parola.charAt(indice)) {
					// Se la lettera successiva è stata trovata, cerca le lettere successive
					if (cercaParola(parola, riga2, colonna2, indice + 1)) {
						return true;
					}
				}
			}
		}

		// Se la parola non è stata trovata, restituisce false
		return false;
	}

	private void updateButtons(int row, int col) {
		// Impostazione della cliccabilità di tutti i bottoni a false
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				buttons[i][j].setEnabled(false);
			}
		}
		// Impostazione della cliccabilità dei bottoni adiacenti alla cella selezionata a false
		if (row > 0 ) {
			if(ifBottoneGiaCliccato(buttons[row - 1][col])==false) {
				buttons[row - 1][col].setEnabled(true); // Cellula sopra
			}

			if(col>0){
				if(col < 3){
					if(ifBottoneGiaCliccato(buttons[row -1][col +1 ])==false){
						buttons[row - 1][col + 1].setEnabled(true); // Cellula sopra
						if(ifBottoneGiaCliccato(buttons[row - 1][col - 1 ])==false){
							buttons[row - 1][col - 1].setEnabled(true); // Cellula sopra
						}
					}
				}else{
					if(ifBottoneGiaCliccato(buttons[row - 1][col - 1 ])==false){
						buttons[row - 1][col - 1].setEnabled(true); // Cellula sopra
					}
				}
			}else{
				if(ifBottoneGiaCliccato(buttons[row +1][col + 1 ])==false){
					buttons[row - 1][col + 1].setEnabled(true); // Cellula sopra
				}
			}

		}
		if (row < 3) {
			if(ifBottoneGiaCliccato(buttons[row + 1][col])==false){
				buttons[row + 1][col].setEnabled(true); // Cellula sotto
			}

			if(col>0){
				if(col < 3){
					if(ifBottoneGiaCliccato(buttons[row +1][col +1 ])==false){
						buttons[row + 1][col + 1].setEnabled(true); // Cellula sopra
						if(ifBottoneGiaCliccato(buttons[row +1][col - 1 ])==false){
							buttons[row + 1][col - 1].setEnabled(true); // Cellula sopra
						}
					}
				}else{
					if(ifBottoneGiaCliccato(buttons[row +1][col - 1 ])==false){
						buttons[row + 1][col - 1].setEnabled(true); // Cellula sopra
					}
				}
			}else{
				if(ifBottoneGiaCliccato(buttons[row +1][col + 1 ])==false){
					buttons[row + 1][col + 1].setEnabled(true); // Cellula sopra
				}
			}
		}else{

		}
		if (col > 0) {
			if(ifBottoneGiaCliccato(buttons[row][col - 1])==false){
				buttons[row][col - 1].setEnabled(true); // Cellula a sinistra
			}
		}
		if (col < 3) {
			if(ifBottoneGiaCliccato(buttons[row][col + 1])==false){
				buttons[row][col + 1].setEnabled(true); // Cellula a destra
			}
		}


	}



	public boolean ifBottoneGiaCliccato(JButton b){
		boolean trovato=false;
		Iterator <JButton> j = buttonsclick.iterator();

		while(j.hasNext() && trovato==false){
			if(b.equals(j.next())){
				trovato=true;
			}
		}

		return trovato;
	}
}
