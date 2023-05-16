package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageStatistiche extends JFrame implements ActionListener {

    JButton btnExit;

    public PageStatistiche(){

        this.setTitle("Pagina Statistiche");
        this.setSize(1100, 700);

        //======================================================================================================
        // TITOLO FRAME
        //======================================================================================================

        JLabel labelTitolo = new JLabel();
        labelTitolo.setText("Pagina Statistiche Paroliere");
        labelTitolo.setForeground(new Color(0, 0, 0));
        labelTitolo.setFont(new Font("MV Boli", Font.PLAIN, 40));

        labelTitolo.setBackground(new Color(123, 50, 250));
        labelTitolo.setOpaque(true);
        labelTitolo.setVerticalAlignment(labelTitolo.TOP);
        labelTitolo.setHorizontalAlignment(labelTitolo.CENTER);
        labelTitolo.setSize(600, 70);
        this.centerComponent(this, labelTitolo, 20);

        //======================================================================================================
        // IMPOSTAZIONI FRAME
        //======================================================================================================

        btnExit = new JButton();
        btnExit.setText("HOME PAGE");
        btnExit.addActionListener(this);
        btnExit.setBounds(70, 35, 150, 40);

        //======================================================================================================
        // IMPOSTAZIONI FRAME
        //======================================================================================================

        this.add(labelTitolo);
        this.add(btnExit);

        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(123, 50, 250));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centerFrame(this);
    }

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
                (screenSize.height - frameSize.height) / 2 -30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnExit){
            HomePage pageHome = new HomePage();
        }
    }
}
