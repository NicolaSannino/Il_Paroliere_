package view;
import model.DBConnectionMariaDB;
import model.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PageStatistiche extends JFrame implements ActionListener {
    JButton btnExit;
    DefaultTableModel model;
    JTable table;

    public PageStatistiche() throws SQLException {

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
        // CREAZIONE BOTTONE TORNA HOMEPAGE
        //======================================================================================================

        btnExit = new JButton();
        btnExit.setText("HOME PAGE");
        btnExit.addActionListener(this);
        btnExit.setBounds(70, 35, 150, 40);



        //======================================================================================================
        // CREAZIONE TABELLA STATISTICHE
        //======================================================================================================

        JPanel panel = new JPanel(new BorderLayout());

        // Creazione campi del modello dati della tabella
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Partita");
        model.addColumn("Punteggio");
        model.addColumn("Tempo");
        model.addColumn("Parole Trovate");
        model.addColumn("Difficoltà");

        // Aggiungi dati di esempio

        DBConnectionMariaDB c = new DBConnectionMariaDB();
        Query q = new Query();

        String[][] risultati = q.getQuerySelectPartite();
        if(risultati[0][0] != null){
            for (int i = 0; i < risultati.length; i++) {
                model.addRow(new Object[]{risultati[i][0],risultati[i][1],risultati[i][2],risultati[i][3],risultati[i][4]});
            }
            // Creazione della tabella
            JTable table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.setRowHeight(40);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer);
            table.getTableHeader().setReorderingAllowed(false);
            table.disable();

            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.setVisible(true);
            panel.setBackground(Color.BLACK);
            panel.setSize(400,200);

            centerComponent(this,panel,250);


            this.add(panel);

            Object[][] data = {
                    {"Partita", "Punteggio", "Tempo", "Parole Trovate", "Difficoltà"}
                    //risposta query

            };


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
            this.dispose();
        }
    }
}
