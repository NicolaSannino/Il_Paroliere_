package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {

    JButton btnOpenGioco = new JButton();

    public HomePage(){

        this.setTitle("HomePage Il Paroliere");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLayout(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setBackground(new Color(123, 50, 250));

        JPanel panelTitolo = new JPanel();

        JLabel labelTitolo = new JLabel();
        labelTitolo.setText("Homepage Paroliere");
        labelTitolo.setForeground(new Color(0, 0, 0));
        labelTitolo.setFont(new Font("MV Boli", Font.PLAIN, 40));

        labelTitolo.setBackground(new Color(123, 50, 250));
        labelTitolo.setOpaque(true);
        labelTitolo.setVerticalAlignment(labelTitolo.TOP);
        labelTitolo.setHorizontalAlignment(labelTitolo.CENTER);
        labelTitolo.setBounds(550, 10, 400, 70);

        btnOpenGioco.setBounds(550, 500, 200, 40);
        btnOpenGioco.setFocusable(false);
        btnOpenGioco.addActionListener(this);
        btnOpenGioco.setText("Gioca");

        this.setVisible(true);
        this.add(labelTitolo);
        this.add(btnOpenGioco);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnOpenGioco){
            this.dispose();
            CampoGioco newPage = new CampoGioco();
        }
    }
}

