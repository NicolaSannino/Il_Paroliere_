package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

public class HomePage extends JFrame implements ActionListener {

    RoundedButton btnOpenGioco, btnStatistiche;

    private JComboBox<String> selectBox;
    private JPanel panelComboBox, ContBtn;
    private JLabel titoloSelectBox, titoloBoxImp, ErrorNome;
    private JTextField InsNome;

    String selectedOption = "Facile";

    public HomePage(){

        this.setTitle("HomePage il Paroliere");
        this.setSize(1100, 700);

        Border bordo1 = BorderFactory.createLineBorder(Color.black, 4);
        Border bordo2 = BorderFactory.createLineBorder(Color.black, 3);
        Font fontA = new Font ("MV Boli", Font. BOLD, 20);

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
        // CREAZIONE SELECT BOX DIFFICOLTA E INSERIMENTO NOME UTENTE
        //======================================================================================================

        titoloBoxImp = new JLabel();
        titoloBoxImp.setText("Impostazioni Paroliere");
        titoloBoxImp.setHorizontalAlignment(JLabel.CENTER);
        titoloBoxImp.setFont(new Font("MV Boli", Font.BOLD, 25));
        titoloBoxImp.setBounds(150,15,300,30);

        titoloSelectBox = new JLabel();
        titoloSelectBox.setText("Seleziona La Difficolta");
        titoloSelectBox.setHorizontalAlignment(JLabel.CENTER);
        titoloSelectBox.setFont(new Font("MV Boli", Font.BOLD, 15));
        titoloSelectBox.setBounds(150,90,300,30);

        String[] options = {"Facile", "Medio", "Difficile"};
        selectBox = new JComboBox<>(options);
        selectBox.addActionListener(this);
        selectBox.setBounds(200,120,200,30);

        ErrorNome = new JLabel();
        ErrorNome.setText("Inserisci Nome Utente");
        ErrorNome.setHorizontalAlignment(JLabel.CENTER);
        ErrorNome.setFont(new Font("MV Boli", Font.BOLD, 20));
        ErrorNome.setForeground(Color.RED);
        ErrorNome.setBounds(150,190,300,30);
        ErrorNome.setVisible(false);

        InsNome = new JTextField("Nome Utente");
        InsNome.setBounds(175,220,250,30);
        InsNome.setFont(fontA);
        InsNome.setBackground(Color.BLACK);
        InsNome.setForeground(Color.WHITE);
        InsNome.setBorder(bordo2);
        InsNome.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (InsNome.getText().equals("Nome Utente")) {
                    InsNome.setText("");
                    InsNome.setForeground(Color.WHITE);
                }
            }

            public void focusLost(FocusEvent e) {

            }
        });

        InsNome.setVisible(true);

        panelComboBox = new JPanel();
        panelComboBox.setSize(600, 300);
        panelComboBox.setBackground(new Color(123, 50, 250));
        panelComboBox.setLayout(null);
        panelComboBox.add(titoloBoxImp);
        panelComboBox.add(titoloSelectBox);
        panelComboBox.add(selectBox);
        panelComboBox.add(ErrorNome);
        panelComboBox.add(InsNome);
        panelComboBox.setBorder(bordo1);
        this.centerComponent(this, panelComboBox, 130);

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

        ImageIcon icon = new ImageIcon("file/ParoliereIcon.png");
        this.setIconImage(icon.getImage());

        this.add(labelTitolo);
        this.add(panelComboBox);
        this.add(ContBtn);
        //this.add(InsNome);

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

            if ((InsNome.getText().equals("")) || (InsNome.getText().equals("Nome Utente"))){
                ErrorNome.setVisible(true);
            }else{
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