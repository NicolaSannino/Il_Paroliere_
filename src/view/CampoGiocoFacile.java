package view;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import model.DBConnectionMariaDB;
import model.Query;

import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class CampoGiocoFacile extends JFrame implements ActionListener{

	DefaultTableModel model;
	JPanel panelTabella;

	JTable table;
	Vector<JButton> buttonsclick=new Vector(0,1);

	JButton[][] buttons = new JButton[4][4];

	JToggleButton toggleButton=new JToggleButton("Bottoni");

	private char[][] tabella = new char[4][4];

	private JLabel Testo, timeLabel, ContBtnTabella;
	private JTextField testo;
	private JButton Tabella, Invio, Annulla, Termina;
	private JPanel GrigliaGioco, ContBtn;

	private Timer timer;

	int minutes=10;
	int seconds = 480;
	boolean fine = false;

	boolean buttonTesto = true;

	public CampoGiocoFacile(){

		this.setTitle("Campo da Gioco");

		JLabel labelTitolo = new JLabel();
		labelTitolo.setText("Campo Da Gioco Paroliere");
		labelTitolo.setForeground(new Color(0, 0, 0));
		labelTitolo.setFont(new Font("MV Boli", Font.PLAIN, 40));

		CentraOggetti c = new CentraOggetti();
		toggleButton.addActionListener(this);
		toggleButton.setBackground(Color.WHITE);
		toggleButton.setForeground(Color.GRAY);
		toggleButton.setPreferredSize(new Dimension(80, 40));
		toggleButton.setMargin(new Insets(0, 10, 0, 10));
		toggleButton.setBounds(400,300,80,25);
		toggleButton.setVisible(true);

		labelTitolo.setBackground(new Color(123, 50, 250));
		labelTitolo.setOpaque(true);
		//labelTitolo.setVerticalAlignment(labelTitolo.TOP);
		//labelTitolo.setHorizontalAlignment(labelTitolo.CENTER);
		//labelTitolo.setBounds(550, 10, 400, 70);
		labelTitolo.setSize(500, 70);
		this.centerComponent(labelTitolo, 20);

		GrigliaGioco = new JPanel();
		ContBtn = new JPanel();
		GrigliaGioco.setLayout(new GridLayout(4, 4, 0, 0));

		char[] consonanti = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'z'};
		char[] vocali = {'a', 'e', 'i', 'o', 'u'};
		int a=0;

		Border border2 = BorderFactory.createLineBorder(Color.CYAN, 3);

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
				ContBtnTabella = new JLabel();

				tabella[i-1][j-1] = (char)num;
				buttons[i-1][j-1] = Tabella;
				Tabella.setName("Btn"+a);
				Tabella.addActionListener(this);
				//Tabella.setSize(50, 50);

				ContBtnTabella.setSize(55,55);
				ContBtnTabella.setLayout(null);
				ContBtnTabella.setBorder(border2);

				int labelWidth = ContBtnTabella.getWidth();
				int labelHeight = ContBtnTabella.getHeight();

				int x = (75 - 50) / 2;
				int y = (75 - 50) / 2;

				Tabella.setBounds(x, y, 50, 50);

				ContBtnTabella.add(Tabella);

				GrigliaGioco.add(ContBtnTabella);

				a++;
			}
		}

		stampaMatrice();

		//Tabella Risultati

		// Creazione dell'oggetto TableCellRenderer personalizzato
		Font font = new Font("Arial", Font.PLAIN, 14);
		Color foregroundColor = Color.BLUE;
		TableCellRenderer renderer = new CustomTableCellRenderer(font, foregroundColor);

		// Creazione campi del modello dati della tabella
		model = new DefaultTableModel();
		model.addColumn("Parola");
		model.addColumn("Punteggio");
		model.addRow(new Object[]{"Parola", "Punteggio"});

		// Creazione dell'etichetta con il titolo della tabella
		JLabel titleLabel = new JLabel("Titolo della Tabella");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

		// Creazione della tabella
		table=new JTable();
		table.setSize(200,200);
		table.setModel(model);
		table.setDefaultRenderer(Object.class, renderer);

		//Panel in cui inserire tabella
		panelTabella= new JPanel();
		this.add(panelTabella);
		panelTabella.add(Box.createVerticalStrut(10)); // Spazio vuoto tra etichetta e tabella
		panelTabella.add(titleLabel);
		titleLabel.setVisible(true);
		//panelTabella.setBackground(Color.WHITE);
		panelTabella.setBounds(1000,300,300,300);
		panelTabella.setVisible(true);
		panelTabella.add(table);
		table.setVisible(true);

		ContBtnTabella.setVisible(true);
		ContBtnTabella.setBackground(new Color(123, 50, 250));

		GrigliaGioco.setVisible(true);
		GrigliaGioco.setSize(302, 302);
		GrigliaGioco.setBackground(new Color(123, 50, 250));
		GrigliaGioco.setBorder(border2);
		this.centerComponent(GrigliaGioco, 160);

		Border border = BorderFactory.createLineBorder(Color.black, 3);

		Testo = new JLabel("Inserisci lettere");
		testo= new JTextField("Inserisci lettere");
		testo.addFocusListener(new FocusListener() {
								   public void focusGained(FocusEvent e) {
									   if (testo.getText().equals("Inserisci lettere")) {
										   testo.setText("");
										   buttonTesto=false;
										   testo.setForeground(Color.BLACK);
									   }
								   }

								   public void focusLost(FocusEvent e) {

								   }
							   });

		this.add(Testo);
		this.add(testo);
		testo.setVisible(false);

		Font fontA = new Font ("Book Antiqua", Font. BOLD, 15);

		Testo.setBorder(border);
		testo.setBorder(border);
		Testo.setSize(230,30);
		testo.setSize(230,30);
		Testo.setFont(fontA);
		testo.setFont(fontA);
		Testo.setForeground(Color.BLACK);
		testo.setForeground(Color.BLACK);
		testo.setOpaque(false);
		this.centerComponent(Testo, 550);
		this.centerComponent(testo, 550);

		Invio = new JButton();
		Invio.setText("Cerca Parola");
		Invio.addActionListener(this);
		Invio.setBounds(0,0,200,50);

		Annulla = new JButton();
		Annulla.setText("Annulla Parola");
		Annulla.addActionListener(this);
		Annulla.setBounds(250,0,200,50);

		ContBtn.add(Invio);
		ContBtn.add(Annulla);
		ContBtn.setVisible(true);
		ContBtn.setSize(450,50);
		ContBtn.setBackground(new Color(123, 50, 250));
		ContBtn.setLayout(null);
		this.centerComponent(ContBtn, 700);

		Termina = new JButton();
		Termina.setText("Termina Partita");
		Termina.addActionListener(this);
		Termina.setBounds(1300, 40, 170, 40);

		timeLabel = new JLabel("00:00");
		timeLabel.setFont(new Font("MV Boli", Font.BOLD, 35));
		timeLabel.setBounds(30, 30, 200,40);

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
						CampoGiocoFacile.this.dispose();
					}
				}
			}
		});
		timer.start();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int frameWidth = (int) screenSize.getWidth();
		int frameHeight = (int) screenSize.getHeight();

		this.add(labelTitolo);
		this.add(GrigliaGioco);
		this.add(ContBtn);
		this.add(Termina);
		this.add(timeLabel);
		this.add(toggleButton);

		this.setSize(frameWidth, frameHeight);
		this.setLayout(null);
		this.setResizable(false);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.getContentPane().setBackground(new Color(123, 50, 250));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		if(azione.equals("Bottoni") || azione.equals("Tastiera")){
			System.out.println(buttonTesto);
			if (toggleButton.isSelected() && buttonTesto) {
				// se il pulsante è selezionato, eseguire queste azioni
				toggleButton.setText("Tastiera");
				testo.setVisible(true);
				Testo.setVisible(false);
				AzzeraBottoni();
			} else {
				if(buttonTesto){
					// se il pulsante non è selezionato, eseguire queste azioni
					toggleButton.setText("Bottoni");
					testo.setVisible(false);
					Testo.setVisible(true);
					SettaBottoni();
				}
			}
		}else{
			JButton button = (JButton) e.getSource();
			buttonsclick.add(button);

			for (int i = 97; i < 123; i++) {
				i = (char) i;
				if (azione.equals(Character.toString(i))) {
					if (Testo.getText().equals("Inserisci lettere")) {
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


			if(azione.equals("Annulla Parola")){
				buttonTesto=true;
				buttonsclick.clear();
				if(toggleButton.isSelected()){
					testo.setText("Inserisci lettere");
					Testo.setText("Inserisci lettere");
					AzzeraBottoni();
				}else{
					testo.setText("Inserisci lettere");
					Testo.setText("Inserisci lettere");
					SettaBottoni();
				}

			}

			if (azione.equals("Cerca Parola")) {
				System.out.println(Testo.getText());
				DBConnectionMariaDB conn = new DBConnectionMariaDB();
				Query q = new Query();
				buttonTesto=true;

				String t;
				if(toggleButton.isSelected()){
					t=testo.getText();
				}else{
					t=Testo.getText();
				}
				//System.out.println(Testo.getText());

				if (controllaParola(t) == true) {
					System.out.println("parola trovata");
					try {
						if(q.ricercaParolaDb(t,conn) == true){
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
				if(toggleButton.isSelected()){
					testo.setText("Inserisci lettere");
					Testo.setText("Inserisci lettere");
					AzzeraBottoni();
				}else{
					testo.setText("Inserisci lettere");
					Testo.setText("Inserisci lettere");
					//SettaBottoni();
				}
				buttonsclick.clear();
				
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

	public boolean ifBottoneGiaCliccato(JButton b) {
		boolean trovato = false;
		Iterator<JButton> j = buttonsclick.iterator();

		while (j.hasNext() && trovato == false) {
			if (b.equals(j.next())) {
				trovato = true;
			}
		}

		return trovato;
	}

	public void AzzeraBottoni(){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				buttons[i][j].setEnabled(false);
			}
		}
	}

	public void SettaBottoni(){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				buttons[i][j].setEnabled(true);
			}
		}
	}
}
