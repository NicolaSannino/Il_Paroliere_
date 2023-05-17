package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HomePage extends JFrame implements ActionListener {

    RoundedButton btnOpenGioco, btnStatistiche;

    private JComboBox<String> selectBox;
    private JPanel panelComboBox, ContBtn;
    private JLabel titoloSelectBox;

    String selectedOption = "Facile";

    public HomePage(){

        this.setTitle("HomePage il Paroliere");
        this.setSize(1100, 700);

        //======================================================================================================
        // TITOLO FRAME
        //======================================================================================================

        JLabel labelTitolo = new JLabel();
        labelTitolo.setText("Homepage Paroliere");
        labelTitolo.setForeground(new Color(0, 0, 0));
        labelTitolo.setFont(new Font("MV Boli", Font.PLAIN, 40));

        labelTitolo.setBackground(new Color(123, 50, 250));
        labelTitolo.setOpaque(true);
        labelTitolo.setVerticalAlignment(labelTitolo.TOP);
        labelTitolo.setHorizontalAlignment(labelTitolo.CENTER);
        labelTitolo.setSize(400, 70);
        this.centerComponent(this,labelTitolo, 20);

        //======================================================================================================
        // CREAZIONE SELECT BOX DIFFICOLTA
        //======================================================================================================

        String[] options = {"Facile", "Medio", "Difficile"};
        selectBox = new JComboBox<>(options);
        selectBox.addActionListener(this);

        titoloSelectBox = new JLabel();
        titoloSelectBox.setText("Seleziona la difficoltà");
        titoloSelectBox.setFont(new Font("MV Boli", Font.BOLD, 25));

        panelComboBox = new JPanel();
        panelComboBox.setSize(300, 100);
        panelComboBox.setBackground(new Color(123, 50, 250));
        panelComboBox.setLayout(new FlowLayout());
        panelComboBox.add(titoloSelectBox);
        panelComboBox.add(selectBox);
        this.centerComponent(this, panelComboBox, 230);

        //======================================================================================================
        // BOTTONI PER AVVIARE PARTITA O PAGINA STATISTICHE
        //======================================================================================================

        btnOpenGioco = new RoundedButton("GIOCA");
        btnOpenGioco.setBounds(0,0,200,50);
        btnOpenGioco.addActionListener(this);
        btnOpenGioco.setFont(new Font("MV Boli", Font.BOLD, 15));
        btnOpenGioco.setBackground(Color.BLACK);
        btnOpenGioco.setForeground(Color.white);
        btnOpenGioco.setBorder(null);

        btnStatistiche = new RoundedButton("STATISTICHE");
        btnStatistiche.setBounds(250,0,200,50);
        btnStatistiche.addActionListener(this);
        btnStatistiche.setFont(new Font("MV Boli", Font.BOLD, 15));
        btnStatistiche.setBackground(Color.BLACK);
        btnStatistiche.setForeground(Color.white);
        btnStatistiche.setBorder(null);

        ContBtn = new JPanel();
        ContBtn.setBackground(new Color(123, 50, 250));
        ContBtn.setSize(450,50);
        ContBtn.setLayout(null);
        this.centerComponent(this, ContBtn, 500);
        ContBtn.add(btnOpenGioco);
        ContBtn.add(btnStatistiche);
        ContBtn.setVisible(true);

        //======================================================================================================
        // IMPOSTAZIONI FRAME
        //======================================================================================================

        this.add(labelTitolo);
        this.add(panelComboBox);
        this.add(ContBtn);

        ImageIcon icon = new ImageIcon("file/ParoliereIcon.png");
        this.setIconImage(icon.getImage());

        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(123, 50, 250));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centerFrame(this);
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
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
                (screenSize.height - frameSize.height) / 2 - 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnOpenGioco){

            if(selectedOption.equals("Facile")){
                CampoGioco newPage = new CampoGioco(6);
            }

            if(selectedOption.equals("Medio")){
                CampoGioco newPage = new CampoGioco(5);
            }

            if(selectedOption.equals("Difficile")){
                CampoGioco newPage = new CampoGioco(4);
            }

            this.dispose();
        }

        if(e.getSource() == btnStatistiche){

            try {
                PageStatistiche pagStat = new PageStatistiche();
            } catch (SQLException ex) {
                System.out.println("Non ci sono partite");
                throw new RuntimeException(ex);
            }
            this.dispose();
        }

        if(e.getSource() == selectBox){
            selectedOption = (String) selectBox.getSelectedItem();
            System.out.println("Opzione selezionata: " + selectedOption);
        }
    }
}