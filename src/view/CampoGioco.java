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

import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import model.DBConnectionMariaDB;
import model.Query;

import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controller.dailettera;

import view.RoundedButton;

public class CampoGioco extends JFrame implements ActionListener{

	private int difficolta = 6;

	int numParTrovate = 0;
	int totPunteggio = 0;
	int punteggio;
	String nome_utenti;

	boolean parolaEsistente = false;
	Vector<String> paroleTrovate = new Vector(0,1);

	DefaultTableModel model;
	JPanel panelTabellaRis;

	JTable table;
	Vector<JButton> buttonsclick = new Vector(0,1);

	JButton[][] buttons = new JButton[difficolta][difficolta];

	JToggleButton toggleButton = new JToggleButton("Bottoni");

	private char[][] tabella = new char[difficolta][difficolta];
	private JLabel Testo, timeLabel, ContBtnTabella, Utente,Punteggio;
	private JTextField testo;
	private RoundedButton Invio, Annulla, Termina, NuovaPartita;
	private JPanel GrigliaGioco, ContBtn;
	private Timer timer;
	RoundedButton Tabella;

	int minutes = 10;
	int seconds = 480;
	boolean fine = false;

	boolean buttonTesto = true;

	Font font1 = new Font("MV Boli", Font.BOLD, 15);
	Color colore1 = Color.BLACK;

	public CampoGioco(int dim,String nome){

		this.setTitle("Campo da Gioco");
		this.setSize(1500, 800);

		difficolta = dim;

		Border border = BorderFactory.createLineBorder(Color.black, 3); //Bordo del jtextfield
		Border border2 = BorderFactory.createLineBorder(Color.WHITE	, 3); //Bordo dei btn della griglia

		//======================================================================================================
		// CREAZIONE LABEL UTENTE
		//======================================================================================================

		nome_utenti = nome;
		Utente=new JLabel(nome_utenti);
		Utente.setFont(new Font("MV Boli", Font.BOLD, 35));
		Utente.setBounds(30, 30, 200,40);

		//======================================================================================================
		// CREAZIONE LABEL PUNTEGGIO FINALE
		//======================================================================================================

		Punteggio = new JLabel();
		Punteggio.setText(String.valueOf(totPunteggio));
		Punteggio.setFont(new Font("MV Boli", Font.BOLD, 35));
		Punteggio.setOpaque(true); // Imposta la proprietà opaca su true per rendere visibile il colore di sfondo
		Punteggio.setBackground(Color.BLACK);
		Punteggio.setBounds(1150, 600, 301,60);
		Punteggio.setHorizontalAlignment(Punteggio.CENTER);
		Punteggio.setVerticalAlignment(Punteggio.CENTER);
		Punteggio.setForeground(Color.WHITE);

		//======================================================================================================
		// TITOLO CAMPO DA GIOCO
		//======================================================================================================

		JLabel labelTitolo = new JLabel();
		labelTitolo.setText("Campo Da Gioco Paroliere");
		labelTitolo.setForeground(new Color(0, 0, 0));
		labelTitolo.setFont(new Font("Arial Bold Italic", Font.BOLD, 40));

		labelTitolo.setBackground(new Color(123, 50, 250));
		labelTitolo.setOpaque(true);
		labelTitolo.setSize(600, 70);
		labelTitolo.setHorizontalAlignment(labelTitolo.CENTER);
		this.centerComponent(this, labelTitolo, 20);

		//======================================================================================================
		// BOTTONE ATTIVA DISATTIVA JBUTTON
		//======================================================================================================

		CentraOggetti c = new CentraOggetti();
		toggleButton.addActionListener(this);
		toggleButton.setPreferredSize(new Dimension(80, 40));
		toggleButton.setMargin(new Insets(0, 10, 0, 10));
		toggleButton.setVisible(true);
		toggleButton.setFont(font1);
		toggleButton.setBackground(Color.BLACK);
		toggleButton.setForeground(Color.WHITE);

		if(difficolta == 4){
			toggleButton.setBounds(400,300,100,30);
		}else if(difficolta == 5){
			toggleButton.setBounds(380,310,100,30);
		} else if (difficolta == 6) {
			toggleButton.setBounds(360,340,100,30);
		}

		//======================================================================================================
		// CREAZIONE GRIGLIA DA GIOCO BOTTONI
		//======================================================================================================

		GrigliaGioco = new JPanel();
		ContBtn = new JPanel();
		GrigliaGioco.setLayout(new GridLayout(difficolta, difficolta, 0, 0));

		int a = 0;

		for(int i = 1;i < difficolta+1; i++){
			a++;
			for(int j = 1;j < difficolta+1; j++){
				Random rand = new Random();
				int num;
				num = rand.nextInt(100000);
				Tabella = new RoundedButton(""+(char)dailettera.controllo(num));
				Tabella.setBorder(null);
				ContBtnTabella = new JLabel();

				tabella[i-1][j-1] = (char)dailettera.controllo(num);
				buttons[i-1][j-1] = Tabella;
				Tabella.setName("Btn"+a);
				Tabella.addActionListener(this);

				ContBtnTabella.setSize(75,75);
				ContBtnTabella.setLayout(null);
				ContBtnTabella.setBorder(border2);

				int labelWidth = ContBtnTabella.getWidth();
				int labelHeight = ContBtnTabella.getHeight();

				int x = (labelWidth - 55) / 2;
				int y = (labelHeight - 55) / 2;

				ContBtnTabella.add(Tabella);

				Tabella.setBounds(x, y, 55, 55);
				Tabella.setBackground(Color.BLACK);
				Tabella.setFont(new Font("MV Boli", Font.BOLD, 20));
				Tabella.setForeground(Color.WHITE);

				GrigliaGioco.add(ContBtnTabella);

				a++;
			}
		}

		stampaMatrice();

		ContBtnTabella.setVisible(true);
		ContBtnTabella.setBackground(new Color(123, 50, 250));

		GrigliaGioco.setVisible(true);

		if(difficolta == 4){
			GrigliaGioco.setSize(302, 302);
			this.centerComponent(this, GrigliaGioco, 160);
		}else if(difficolta == 5){
			GrigliaGioco.setSize(376, 376);
			this.centerComponent(this, GrigliaGioco, 140);
		} else if (difficolta == 6) {
			GrigliaGioco.setSize(450, 450);
			this.centerComponent(this, GrigliaGioco, 130);
		}

		GrigliaGioco.setBackground(new Color(123, 50, 250));
		GrigliaGioco.setBorder(border2);

		//======================================================================================================
		// CREAZIONE TABELLA RISULTATI
		//======================================================================================================

		//Creazione dell'oggetto TableCellRenderer personalizzato
		Font font = new Font("Arial", Font.BOLD, 13);
		Color foregroundColor = Color.black;
		TableCellRenderer renderer = new CustomTableCellRenderer(font, foregroundColor);

		//Creazione campi del modello dati della tabella
		model = new DefaultTableModel();
		model.addColumn("Parola");
		model.addColumn("Punteggio");

		// Creazione della tabella
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(40);
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);
		table.getTableHeader().setReorderingAllowed(false);
		table.disable();

		//Grafica tabella
		table.setFont(font1);
		table.setBackground(Color.CYAN);
		table.setForeground(Color.BLACK);
		table.getTableHeader().setFont(font1);

		//Panel in cui inserire tabella
		panelTabellaRis = new JPanel(new BorderLayout());
		//panelTabellaRis.add(Box.createVerticalStrut(30)); // Spazio vuoto tra etichetta e tabella
		//panelTabellaRis.add(titleLabelTblRis);
		panelTabellaRis.setVisible(true);

		panelTabellaRis.setBounds(1150,160,301,450);

		if(difficolta == 4){
			panelTabellaRis.setBounds(1150,160,301,450);
		}else if(difficolta == 5){
			panelTabellaRis.setBounds(1150,140,301,450);
		} else if (difficolta == 6) {
			panelTabellaRis.setBounds(1150,130,301,450);
		}

		JScrollPane scrollPane = new JScrollPane(table);
		panelTabellaRis.add(scrollPane, BorderLayout.CENTER);
		panelTabellaRis.setVisible(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setVisible(true);
		TableColumn column;

		column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(149); // Imposta la larghezza desiderata per la colonna
		column.setResizable(false);
		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(149); // Imposta la larghezza desiderata per la colonna
		column.setResizable(false);

		//======================================================================================================
		// JLABEL PER INSERIMENTO PAROLA DA CERCARE
		//======================================================================================================

		Font fontA = new Font ("MV Boli", Font.BOLD, 20);

		Testo = new JLabel("Inserisci lettere");
		testo = new JTextField("Inserisci lettere");
		Testo.setOpaque(true);
		Testo.setBackground(Color.BLACK);
		Testo.setForeground(Color.WHITE);
		testo.setBackground(Color.BLACK);
		testo.setForeground(Color.WHITE);
		Testo.setFont(fontA);
		testo.setFont(fontA);
		Testo.setBorder(border);
		testo.setBorder(border);
		Testo.setSize(280,35);
		testo.setSize(280,35);

		testo.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (testo.getText().equals("Inserisci lettere")) {
					testo.setText("");
					buttonTesto = false;
					testo.setForeground(Color.WHITE);
				}
			}

			public void focusLost(FocusEvent e) {

			}
		});

		testo.setVisible(false);

		if(difficolta == 6){
			this.centerComponent(this, Testo, 620);
			this.centerComponent(this, testo, 620);
		}else{
			this.centerComponent(this, Testo, 550);
			this.centerComponent(this, testo, 550);
		}

		//======================================================================================================
		// BOTTONI PER CERCARE O ANNULARE LA PAROLA INSERITA E TERMINA PARTITA
		//======================================================================================================

		Invio = new RoundedButton("Cerca Parola");
		Invio.setBounds(0,0,200,50);
		Invio.addActionListener(this);
		Invio.setFont(new Font("MV Boli", Font.BOLD, 20));
		Invio.setBackground(Color.BLACK);
		Invio.setForeground(Color.white);
		Invio.setBorder(null);

		Annulla = new RoundedButton("Annulla Parola");
		Annulla.setBounds(250,0,200,50);
		Annulla.addActionListener(this);
		Annulla.setFont(new Font("MV Boli", Font.BOLD, 20));
		Annulla.setBackground(Color.BLACK);
		Annulla.setForeground(Color.white);
		Annulla.setBorder(null);

		ContBtn.add(Invio);
		ContBtn.add(Annulla);
		ContBtn.setVisible(true);
		ContBtn.setSize(450,50);
		ContBtn.setBackground(new Color(123, 50, 250));
		ContBtn.setLayout(null);
		this.centerComponent(this, ContBtn, 700);

		Termina = new RoundedButton("Termina Partita");
		Termina.setBounds(1270, 35, 170, 50);
		Termina.addActionListener(this);
		Termina.setFont(new Font("MV Boli", Font.BOLD, 17));
		Termina.setBackground(Color.BLACK);
		Termina.setForeground(Color.white);
		Termina.setBorder(null);

		NuovaPartita = new RoundedButton("Nuova Partita");
		NuovaPartita.setBounds(30, 700, 170, 50);
		NuovaPartita.addActionListener(this);
		NuovaPartita.setFont(new Font("MV Boli", Font.BOLD, 17));
		NuovaPartita.setBackground(Color.BLACK);
		NuovaPartita.setForeground(Color.white);
		NuovaPartita.setBorder(null);

		//======================================================================================================
		// TIMER DELLA PARTITA
		//======================================================================================================

		timeLabel = new JLabel("00:00");
		timeLabel.setFont(new Font("MV Boli", Font.BOLD, 35));
		timeLabel.setBounds(200, 30, 200,40);

		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fine == false){

					seconds--;
					int hour = seconds / 3600;
					int minute = (seconds % 3600) / 60;
					int second = seconds % 60;
					timeLabel.setText(String.format("%02d:%02d", minute, second));

					if(Integer.compare(seconds,0)==0){
						DBConnectionMariaDB connTermine1 = new DBConnectionMariaDB();
						Query q2 = new Query();
						String dif1="Facile";
						if(Integer.compare(difficolta,6)==0){
							dif1="Facile";
						}

						if(Integer.compare(difficolta,5)==0){
							dif1="Medio";
						}

						if(Integer.compare(difficolta,4)==0){
							dif1="Difficile";
						}
						q2.getInsertPartita(totPunteggio,"08:00:00",dif1,nome_utenti,numParTrovate,connTermine1);
						fine = true;
						timer.stop();
						CampoGioco.this.dispose();
					}
				}
			}
		});
		timer.start();

		//======================================================================================================
		// IMPOSTAZIONI FRAME
		//======================================================================================================

		this.add(Punteggio);
		this.add(Utente);
		this.add(labelTitolo);
		this.add(GrigliaGioco);
		this.add(ContBtn);
		this.add(Termina);
		this.add(timeLabel);
		this.add(toggleButton);
		this.add(panelTabellaRis);
		this.add(NuovaPartita);
		this.add(Testo);
		this.add(testo);

		ImageIcon icon = new ImageIcon("file/ParoliereIcon.png");
		this.setIconImage(icon.getImage());

		this.setLayout(null);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(123, 50, 250));
		//this.getContentPane().setBackground(Color.darkGray);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centerFrame(this);
	}

	//======================================================================================================
	// METODO CENTRA COMPONENTI - FRAME
	//======================================================================================================

	public void centerComponent(Frame f, JComponent c, int y){
		Dimension frameSize = f.getSize();
		Dimension componentSize = c.getSize();
		c.setLocation ((frameSize.width - componentSize.width) / 2, y);
	}

	public static void centerFrame(Frame f)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
		Dimension frameSize = f.getSize ();
		f.setLocation ((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2 - 20);
	}

	//======================================================================================================
	// METODO PER GLI ACTIONLISTENER DEI BOTTONI
	//======================================================================================================

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean trovato = false;
		String azione = e.getActionCommand();

		if((testo.getText().equals("") || testo.getText().equals("Inserisci lettere")&& (Testo.getText().equals("Inserisci lettere")||Testo.getText().equals("")))){
			buttonTesto = true;
		}else{
			buttonTesto = false;
		}

		if(azione.equals("Bottoni") || azione.equals("Tastiera")){
			System.out.println(buttonTesto);
			if (toggleButton.isSelected() && buttonTesto) {
				// se il pulsante è selezionato, eseguire queste azioni
				toggleButton.setText("Tastiera");
				toggleButton.setFont(font1);
				toggleButton.setBackground(Color.BLACK);
				toggleButton.setForeground(Color.WHITE);
				testo.setVisible(true);
				Testo.setVisible(false);
				AzzeraBottoni();
			} else {
				if(buttonTesto){
					// se il pulsante non è selezionato, eseguire queste azioni
					toggleButton.setText("Bottoni");
					toggleButton.setFont(font1);
					toggleButton.setBackground(Color.BLACK);
					toggleButton.setForeground(Color.WHITE);
					testo.setVisible(false);
					Testo.setVisible(true);
					SettaBottoni();
				}
			}
		}else{
			JButton button = (JButton) e.getSource();
			button.setBackground(Color.WHITE);
			button.setForeground(Color.BLACK);
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
					for(int n=0;n<difficolta && trovato==false;n++){
						for(int j=0;j<difficolta && trovato==false;j++){
							if(buttons[n][j].equals(button)){
								updateButtons(n,j);
								trovato=true;
							}
						}
					}
				}
			}

			if(azione.equals("Annulla Parola")){
				//System.out.println(difficolta);

				button.setBackground(Color.BLACK);
				button.setForeground(Color.WHITE);

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

				button.setBackground(Color.BLACK);
				button.setForeground(Color.WHITE);

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

				Iterator<String> c = paroleTrovate.iterator();
				while(c.hasNext() && parolaEsistente == false){
					if(t.equals(c.next())){
						parolaEsistente=true;
					}
				}

				if (controllaParola(t) == true && parolaEsistente == false) {
					//System.out.println("parola trovata");
					try {
						if(q.ricercaParolaDb(t,conn) == true){
							//System.out.println("parola trovata");

							paroleTrovate.add(t);
							punteggio = t.length();
							model.addRow(new Object[]{t, punteggio});
							table.setModel(model);
							totPunteggio=totPunteggio+punteggio;
							Punteggio.setText(String.valueOf(totPunteggio));
							numParTrovate++;

						}else{
							System.out.println("parola non trovata");
						}
					} catch (SQLException ex) {
						throw new RuntimeException(ex);
					}

				} else {
					System.out.println("parola non trovata");
				}

				parolaEsistente=false;

				if(toggleButton.isSelected()){
					testo.setText("Inserisci lettere");
					Testo.setText("Inserisci lettere");
					AzzeraBottoni();
				}else{
					testo.setText("Inserisci lettere");
					Testo.setText("Inserisci lettere");
					SettaBottoni();
				}
				buttonsclick.clear();

			}
		}

		if(azione.equals("Termina Partita")){
			DBConnectionMariaDB connTermine = new DBConnectionMariaDB();
			Query q1 = new Query();


			int totSec=480-seconds;

			int totOra=totSec / 3600;
			int totMinuti=(totSec % 3600) / 60;
			int totSecondi=totSec % 60;

			String dif="Facile";
			if(Integer.compare(difficolta,6)==0){
				dif="Facile";
			}

			if(Integer.compare(difficolta,5)==0){
				dif="Medio";
			}

			if(Integer.compare(difficolta,4)==0){
				dif="Difficile";
			}


			String tempo=Integer.toString(totOra)+":"+Integer.toString(totMinuti)+":"+Integer.toString(totSecondi);

			System.out.println(tempo);
			System.out.println(totPunteggio);
			System.out.println(numParTrovate);

			q1.getInsertPartita(totPunteggio,tempo,dif,nome_utenti,numParTrovate,connTermine);

			HomePage newPage = new HomePage();
			this.dispose();
		}

		if(azione.equals("Nuova Partita")){
			CampoGioco newPage = new CampoGioco(difficolta,nome_utenti);
			this.dispose();
		}

	}

	public void stampaMatrice(){
		for(int i=0;i<difficolta;i++){
			System.out.println();
			for(int j=0;j<difficolta;j++){
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

	//======================================================================================================
	// METODO PER CERCARE LA PAROLA
	//======================================================================================================

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

	//======================================================================================================
	// METODO PER VERIFICARE LA CLICCABILITA DEI BOTTONI
	//======================================================================================================

	private void updateButtons(int row, int col) {
		// Impostazione della cliccabilità di tutti i bottoni a false
		for (int i = 0; i < difficolta; i++) {
			for (int j = 0; j < difficolta; j++) {
				buttons[i][j].setEnabled(false);
				if (ifBottoneGiaCliccato(buttons[i][j])){
					buttons[i][j].setBackground(Color.WHITE);
					buttons[i][j].setForeground(Color.BLACK);
				}else{
					buttons[i][j].setBackground(Color.BLACK);
					buttons[i][j].setForeground(Color.WHITE);
				}
				buttons[i][j].setBorder(null);
			}
		}
		// Impostazione della cliccabilità dei bottoni adiacenti alla cella selezionata a false
		if (row > 0 ) {
			if(ifBottoneGiaCliccato(buttons[row - 1][col])==false) {
				buttons[row - 1][col].setEnabled(true); // Cellula sopra
				buttons[row - 1][col].setBackground(Color.GREEN);
				buttons[row - 1][col].setForeground(Color.BLACK);
			}

			if(col>0){
				if(col < difficolta-1){
					if(ifBottoneGiaCliccato(buttons[row -1][col +1 ])==false){
						buttons[row - 1][col + 1].setEnabled(true); // Cellula sopra
						buttons[row - 1][col + 1].setBackground(Color.GREEN);
						buttons[row - 1][col + 1].setForeground(Color.BLACK);
					}
					if(ifBottoneGiaCliccato(buttons[row - 1][col - 1 ])==false){
						buttons[row - 1][col - 1].setEnabled(true); // Cellula sopra
						buttons[row - 1][col - 1].setBackground(Color.GREEN);
						buttons[row - 1][col - 1].setForeground(Color.BLACK);
					}
				}else{
					if(ifBottoneGiaCliccato(buttons[row - 1][col - 1 ])==false){
						buttons[row - 1][col - 1].setEnabled(true); // Cellula sopra
						buttons[row - 1][col - 1].setBackground(Color.GREEN);
						buttons[row - 1][col - 1].setForeground(Color.BLACK);
					}
				}
			}else{
				if(row<difficolta){
					if(ifBottoneGiaCliccato(buttons[row ][col + 1 ])==false){
						buttons[row][col + 1].setEnabled(true); // Cellula sopra
						buttons[row][col + 1].setBackground(Color.GREEN);
						buttons[row][col + 1].setForeground(Color.BLACK);
					}
					if(ifBottoneGiaCliccato(buttons[row -1][col + 1 ])==false){
						buttons[row-1][col + 1].setEnabled(true); // Cellula sopra
						buttons[row-1][col + 1].setBackground(Color.GREEN);
						buttons[row-1][col + 1].setForeground(Color.BLACK);
					}
				}

			}

		}
		if (row < difficolta-1) {
			if(ifBottoneGiaCliccato(buttons[row + 1][col])==false){
				buttons[row + 1][col].setEnabled(true); // Cellula sotto
				buttons[row + 1][col].setBackground(Color.GREEN);
				buttons[row + 1][col].setForeground(Color.BLACK);
			}

			if(col>0){
				if(col < difficolta-1){
					if(ifBottoneGiaCliccato(buttons[row +1][col +1 ])==false){
						buttons[row + 1][col + 1].setEnabled(true); // Cellula sopra
						buttons[row + 1][col + 1].setBackground(Color.GREEN);
						buttons[row + 1][col + 1].setForeground(Color.BLACK);
					}
					if(ifBottoneGiaCliccato(buttons[row +1][col - 1 ])==false){
						buttons[row + 1][col - 1].setEnabled(true); // Cellula sopra
						buttons[row + 1][col - 1].setBackground(Color.GREEN);
						buttons[row + 1][col - 1].setForeground(Color.BLACK);
					}
				}else{
					if(ifBottoneGiaCliccato(buttons[row +1][col - 1 ])==false){
						buttons[row + 1][col - 1].setEnabled(true); // Cellula sopra
						buttons[row + 1][col - 1].setBackground(Color.GREEN);
						buttons[row + 1][col - 1].setForeground(Color.BLACK);
					}
				}
			}else{
				if(ifBottoneGiaCliccato(buttons[row +1][col + 1 ])==false){
					buttons[row + 1][col + 1].setEnabled(true); // Cellula sopra
					buttons[row + 1][col + 1].setBackground(Color.GREEN);
					buttons[row + 1][col + 1].setForeground(Color.BLACK);
				}
				if(ifBottoneGiaCliccato(buttons[row - 1][col + 1 ])==false){
					buttons[row - 1][col + 1].setEnabled(true); // Cellula sopra
					buttons[row - 1][col + 1].setBackground(Color.GREEN);
					buttons[row - 1][col + 1].setForeground(Color.BLACK);
				}
			}
		}

		if (col > 0) {
			if(ifBottoneGiaCliccato(buttons[row][col - 1])==false){
				buttons[row][col - 1].setEnabled(true); // Cellula a sinistra
				buttons[row][col - 1].setBackground(Color.GREEN);
				buttons[row][col - 1].setForeground(Color.BLACK);
			}
		}
		if (col < difficolta-1) {
			if(ifBottoneGiaCliccato(buttons[row][col + 1])==false){
				buttons[row][col + 1].setEnabled(true); // Cellula a destra
				buttons[row][col + 1].setBackground(Color.GREEN);
				buttons[row][col + 1].setForeground(Color.BLACK);
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
		for (int i = 0; i < difficolta; i++) {
			for (int j = 0; j < difficolta; j++) {
				buttons[i][j].setEnabled(false);
				buttons[i][j].setBorder(null);
				buttons[i][j].setBackground(Color.WHITE);
				buttons[i][j].setForeground(Color.BLACK);
			}
		}
	}

	public void SettaBottoni(){
		for (int i = 0; i < difficolta; i++) {
			for (int j = 0; j < difficolta; j++) {
				buttons[i][j].setEnabled(true);
				buttons[i][j].setBorder(null);
				buttons[i][j].setBackground(Color.BLACK);
				buttons[i][j].setForeground(Color.WHITE);
			}
		}
	}
	public int getDifficolta() {
		return difficolta;
	}

	public void setDifficolta(int difficolta) {
		this.difficolta = difficolta;
	}
}
