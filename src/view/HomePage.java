package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener {

    JButton btnOpenGioco = new JButton();

    private JComboBox<String> selectBox;
    private JPanel panelComboBox;
    private JLabel titoloSelectBox;

    String selectedOption="Facile";


    public HomePage(){

        this.setTitle("HomePage Il Paroliere");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLayout(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setBackground(new Color(123, 50, 250));
        this.setResizable(false);

        JLabel labelTitolo = new JLabel();
        labelTitolo.setText("Homepage Paroliere");
        labelTitolo.setForeground(new Color(0, 0, 0));
        labelTitolo.setFont(new Font("MV Boli", Font.PLAIN, 40));

        labelTitolo.setBackground(new Color(123, 50, 250));
        labelTitolo.setOpaque(true);
        labelTitolo.setVerticalAlignment(labelTitolo.TOP);
        labelTitolo.setHorizontalAlignment(labelTitolo.CENTER);
        //labelTitolo.setBounds(550, 10, 400, 70);
        labelTitolo.setSize(400, 70);
        this.centerComponent(labelTitolo, 20);

        //btnOpenGioco.setBounds(650, 500, 200, 40);
        btnOpenGioco.setFocusable(false);
        btnOpenGioco.addActionListener(this);
        btnOpenGioco.setText("Gioca");
        btnOpenGioco.setSize(250, 50);
        this.centerComponent(btnOpenGioco, 600);

        // Creazione della select box
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
        centerComponent(panelComboBox, 230);
        panelComboBox.add(titoloSelectBox);
        panelComboBox.add(selectBox);

        this.setVisible(true);
        this.add(labelTitolo);
        this.add(btnOpenGioco);
        this.add(panelComboBox);
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public void centerComponent(JComponent c, int y){
        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        Dimension frameSize = c.getSize ();
        c.setLocation ((screenSize.width - frameSize.width) / 2, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnOpenGioco){
            if(selectedOption.equals("Facile")){
                CampoGioco newPage = new CampoGioco(4);
            }

            if(selectedOption.equals("Medio")){
                CampoGioco newPage = new CampoGioco(5);
            }

            if(selectedOption.equals("Difficile")){
                CampoGioco newPage = new CampoGioco(6);
            }

            //lunghezza da passare nel costruttore

            //newPage.setDifficolta(selectedOption);
            this.dispose();
        }

        if(e.getSource() == selectBox){
            selectedOption = (String) selectBox.getSelectedItem();
            System.out.println("Opzione selezionata: " + selectedOption);
        }
    }
}