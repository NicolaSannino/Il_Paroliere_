package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class HomePage extends JFrame implements ActionListener {

    RoundedButton btnOpenGioco, btnStatistiche;

    private JComboBox<String> selectBox;
    private JPanel panelComboBox, ContBtn;
    private JLabel titoloSelectBox, titoloBoxImp, ErrorNome;
    private JTextField InsNome;

    private CustomCaret caret;
    private Timer caretTimer;
    private boolean caretVisible;

    String selectedOption = "Facile";

    Color coloreBtnPrima;

    public HomePage(){

        this.setTitle("HomePage il Paroliere");
        this.setSize(1100, 700);
        this.setContentPane(new CustomContentPane());

        Border bordo1 = BorderFactory.createLineBorder(Color.black, 4);
        Border bordo2 = BorderFactory.createLineBorder(Color.black, 3);
        Font fontA = new Font ("MV Boli", Font.BOLD, 20);
        Font fontB = new Font ("MV Boli", Font.BOLD, 13);

        //======================================================================================================
        // TITOLO FRAME
        //======================================================================================================

        JLabel labelTitolo = new JLabel();
        labelTitolo.setText("Homepage Paroliere");
        labelTitolo.setForeground(new Color(0, 0, 0));
        labelTitolo.setFont(new Font("Arial Bold Italic", Font.BOLD, 40));

        labelTitolo.setOpaque(false);
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
        selectBox.setBackground(Color.BLACK);
        selectBox.setForeground(Color.WHITE);
        selectBox.setBorder(null);
        selectBox.setFont(fontB);
        selectBox.setOpaque(true);
        selectBox.setSelectedIndex(0);
        selectBox.setRenderer(new CustomComboBoxRenderer());

        ErrorNome = new JLabel();
        ErrorNome.setText("Inserisci Nome Utente");
        ErrorNome.setHorizontalAlignment(JLabel.CENTER);
        ErrorNome.setFont(new Font("MV Boli", Font.BOLD, 20));
        ErrorNome.setForeground(Color.RED);
        ErrorNome.setBounds(150,190,300,30);
        ErrorNome.setVisible(false);

        InsNome = new JTextField("Nome Utente");
        InsNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Codice per cliccare il bottone
                    btnOpenGioco.doClick();
                    //InsNome.getParent().requestFocus();
                }
            }
        });

        caret = new CustomCaret();
        InsNome.setCaret(caret);

        // Imposta la frequenza di lampeggio del cursore
        int blinkRate = 500;
        caretTimer = new Timer(blinkRate, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caretVisible = !caretVisible;
                caret.setVisible(caretVisible);
            }
        });
        caretTimer.start();

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
        //panelComboBox.setBackground(new Color(123, 50, 250));
        panelComboBox.setOpaque(false);
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
        btnOpenGioco.setBounds(250,0,200,50);
        btnOpenGioco.addActionListener(this);
        btnOpenGioco.setFont(new Font("MV Boli", Font.BOLD, 15));
        btnOpenGioco.setBackground(Color.BLACK);
        btnOpenGioco.setForeground(Color.white);
        coloreBtnPrima = btnOpenGioco.getBackground();
        btnOpenGioco.addMouseListener(createMouseListener(coloreBtnPrima));
        btnOpenGioco.setBorder(null);

        btnStatistiche = new RoundedButton("STATISTICHE");
        btnStatistiche.setBounds(0,0,200,50);
        btnStatistiche.addActionListener(this);
        btnStatistiche.setFont(new Font("MV Boli", Font.BOLD, 15));
        btnStatistiche.setBackground(Color.BLACK);
        btnStatistiche.setForeground(Color.white);
        coloreBtnPrima = btnStatistiche.getBackground();
        btnStatistiche.addMouseListener(createMouseListener(coloreBtnPrima));
        btnStatistiche.setBorder(null);

        ContBtn = new JPanel();
        //ContBtn.setBackground(new Color(123, 50, 250));
        ContBtn.setOpaque(false);
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

        this.setLayout(null);
        this.setResizable(false);
        //this.getContentPane().setBackground(new Color(123, 50, 250));
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

    //======================================================================================================
    // METODO CENTRA COMPONENTI E FRAME
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
                (screenSize.height - frameSize.height) / 2 - 30);
    }

    //======================================================================================================
    // METODO ONMOUSEHOVET BOTTONI
    //======================================================================================================

    private MouseAdapter createMouseListener(Color c) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setBackground(c);
            }
        };
    }

    //======================================================================================================
    // METODO ACTION LISTENER
    //======================================================================================================

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnOpenGioco){

            if ((InsNome.getText().equals("")) || (InsNome.getText().equals("Nome Utente"))){
                ErrorNome.setVisible(true);
            }else{
                if(selectedOption.equals("Facile")){
                    CampoGioco newPage = new CampoGioco(6,InsNome.getText());
                }

                if(selectedOption.equals("Medio")){
                    CampoGioco newPage = new CampoGioco(5,InsNome.getText());
                }

                if(selectedOption.equals("Difficile")){
                    CampoGioco newPage = new CampoGioco(4,InsNome.getText());
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

    //======================================================================================================
    // METODO PER MODIFICARE CURSORE DI TESTO
    //======================================================================================================

    private class CustomCaret extends DefaultCaret {
        private static final Color CARET_COLOR = Color.WHITE;
        private static final int CARET_WIDTH = 2;

        @Override
        public void paint(Graphics g) {
            if (isVisible()) {
                JTextComponent textComponent = getComponent();
                if (textComponent == null) {
                    return;
                }

                try {
                    Rectangle caretRectangle = textComponent.getUI().modelToView(textComponent, getDot());
                    if (caretRectangle == null) {
                        return;
                    }

                    g.setColor(CARET_COLOR);
                    g.fillRect(caretRectangle.x, caretRectangle.y, CARET_WIDTH, caretRectangle.height);
                } catch (Exception e) {
                    // Gestione dell'eccezione
                }
            }
        }
    }

    //======================================================================================================
    // METODO PER CENTRARE LE PAROLE ALL'INTERNO DI UNA SELECTBOX
    //======================================================================================================

    private static class CustomComboBoxRenderer extends BasicComboBoxRenderer {

        public CustomComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(SwingConstants.CENTER); // Centra l'elemento all'interno della select box
            setBorder(new EmptyBorder(5, 10, 5, 10)); // Imposta i margini interni per una maggiore spaziatura
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Personalizza ulteriormente l'aspetto degli elementi nella lista se necessario

            return this;
        }
    }

    private static class CustomContentPane extends JPanel {
        private Image backgroundImage;

        public CustomContentPane() {
            // Carica l'immagine di sfondo
            backgroundImage = new ImageIcon("file/sfondo2.png").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Disegna l'immagine di sfondo
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HomePage();
        });
    }
}