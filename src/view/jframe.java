package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class jframe extends JFrame implements ActionListener{

	private JLabel Testo, Titolo;
	private JButton Tabella, Invio; 

	public jframe(String titolo) {
		this.setTitle(titolo);
		this.setLayout(null);
		char[] consonanti = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'z'};
		char[] vocali = {'a', 'e', 'i', 'o', 'u'};
		int a=0;
		for(int i=1;i<5;i++){
			a++;
			for(int j=1;j<5;j++){
				Random rand = new Random();
				int num;
				if (a%2==0){
					num = vocali[rand.nextInt(5)];
				}else{
					num = consonanti[rand.nextInt(18)];
				}
				Tabella=new JButton(""+(char)num);
				this.add(Tabella);
				Tabella.addActionListener(this);
				Tabella.setBounds(250+(i*50),250+(j*50),50,50);
				a++;
			}       
		}

		Testo=new JLabel("Parola da trovare");
		this.add(Testo);
		Testo.setBounds(350,600,100,50);
		Titolo=new JLabel("Il Paroliere");
		this.add(Titolo);
		Titolo.setBounds(370,200,200,50);
		Invio=new JButton("Cerca Parola");
		this.add(Invio);
		Invio.setBounds(335,700,135,20);


		this.setSize(800, 800);
		this.setVisible(true);
		this.centerFrame(this);
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

		for(int i=97;i<122;i++){
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
	}
}
