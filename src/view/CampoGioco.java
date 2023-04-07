package view;
import javax.swing.*;
import java.awt.*;

public class CampoGioco extends JFrame{

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

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLayout(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.getContentPane().setBackground(new Color(123, 50, 250));

		this.setVisible(true);
		this.add(labelTitolo);
	}
}
