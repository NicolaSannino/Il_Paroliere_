package view;
import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class CampoGioco extends JFrame implements ActionListener{

	private JLabel Testo;
	private JButton Tabella, Invio;

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
				this.add(Tabella);
				Tabella.addActionListener(this);
				Tabella.setBounds(250+(i*50),250+(j*50),50,50);
				a++;
			}
		}

		Testo = new JLabel("Parola da trovare");
		this.add(Testo);
		Testo.setBounds(350,600,100,50);
		Invio = new JButton("Cerca Parola");
		this.add(Invio);
		Invio.setBounds(335,700,135,20);
		Invio.addActionListener(this);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLayout(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.getContentPane().setBackground(new Color(123, 50, 250));

		this.setVisible(true);
		this.add(labelTitolo);
	}

	public static void centerFrame(Frame f)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
		Dimension frameSize = f.getSize ();
		f.setLocation ((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String azione=e.getActionCommand();

		for(int i=97;i<123;i++){
			i=(char)i;
			if(azione.equals(Character.toString(i))){
				if(Testo.getText().equals("Parola da trovare")){
					Testo.setText("");
					Testo.setText(Testo.getText()+azione);
				}else{
					Testo.setText(Testo.getText()+azione);
				}
			}
		}
		if(azione.equals("Cerca Parola")){
			System.out.println(Testo.getText());
		}
	}
}
