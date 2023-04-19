package view;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import model.DBConnection;
import model.DBConnectionMariaDB;
import model.Query;


public class CampoGioco extends JFrame implements ActionListener{

	private JLabel Testo;
	private JTextField testo;

	private JButton Tabella, Invio;
	private JPanel GrigliaGioco;

	public CampoGioco(){

		this.setTitle("Campo da Gioco");

		JLabel labelTitolo = new JLabel();
		labelTitolo.setText("Campo Da GIoco");
		labelTitolo.setForeground(new Color(0, 0, 0));
		labelTitolo.setFont(new Font("MV Boli", Font.PLAIN, 40));

		labelTitolo.setBackground(new Color(123, 50, 250));
		labelTitolo.setOpaque(true);
		labelTitolo.setVerticalAlignment(labelTitolo.TOP);
		labelTitolo.setHorizontalAlignment(labelTitolo.CENTER);
		labelTitolo.setBounds(550, 10, 400, 70);
		labelTitolo.setSize(400, 70);
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
				Tabella.setName("Btn"+a);
				Tabella.addActionListener(this);
				Tabella.setSize(50, 50);
				GrigliaGioco.add(Tabella);

				a++;
			}
		}

		GrigliaGioco.setVisible(true);
		GrigliaGioco.setSize(250, 250);
		GrigliaGioco.setBackground(new Color(123, 50, 250));
		this.centerComponent(GrigliaGioco, 200);

		Border border = BorderFactory.createLineBorder(Color.blue, 3);

		Testo = new JLabel("Inerisci Lettere");
		testo= new JTextField("Inserisci lettere");
		this.add(Testo);
		this.add(testo);

		testo.setBorder(border);
		//testo.setVerticalAlignment(labelTitolo.CENTER);
		//testo.setHorizontalAlignment(labelTitolo.CENTER);
		//testo.setSize(200,40);
		testo.setBounds(684,560,130,31);
		testo.setBackground(new Color(0, 0, 255));
		this.centerComponent(Testo, 550);

		Invio = new JButton("Cerca Parola");
		this.add(Invio);
		//Invio.setBounds(335,700,135,20);
		Invio.addActionListener(this);
		Invio.setSize(200,50);
		this.centerComponent(Invio, 700);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLayout(null);
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
		String azione=e.getActionCommand();

		for(int i=97;i<123;i++){
			i=(char)i;
			if(azione.equals(Character.toString(i))){
				if(testo.getText().equals("Inserisci lettere")){
					testo.setText("");
					testo.setText(testo.getText()+azione);
				}else{
					testo.setText(testo.getText()+azione);
				}
			}
		}
		if(azione.equals("Cerca Parola")){
			System.out.println(testo.getText());
			DBConnectionMariaDB conn = new DBConnectionMariaDB();
			Query q=new Query();
			try {
				if(q.ricercaParolaDb(testo.getText(),conn) == true){
					System.out.println("parola trovata");
				}else{
					System.out.println("parola non trovata");
				}
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}

		}
	}
}
