package view;

import model.DBConnectionMariaDB;
import model.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PageStatistiche extends JFrame implements ActionListener {

    RoundedButton btnExit, btnOrderTime, btnOrderDiff, btnOrderPoint;
    JPanel panelContBtnStats;

    DefaultTableModel model;
    JTable table;
    JScrollPane scrollPane;
    JPanel panel;

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
        // CREAZIONE BOTTONI ORDINAMENTO E TORNA HOMEPAGE
        //======================================================================================================

        btnOrderPoint = new RoundedButton("PUNTEGGIO");
        btnOrderPoint.setBounds(250, 0, 200, 60);
        btnOrderPoint.addActionListener(this);
        btnOrderPoint.setFont(new Font("MV Boli", Font.BOLD, 15));
        btnOrderPoint.setBackground(Color.BLACK);
        btnOrderPoint.setForeground(Color.white);
        btnOrderPoint.setBorder(null);

        btnOrderTime = new RoundedButton("TEMPO");
        btnOrderTime.setBounds(0, 0, 200, 60);
        btnOrderTime.addActionListener(this);
        btnOrderTime.setFont(new Font("MV Boli", Font.BOLD, 15));
        btnOrderTime.setBackground(Color.BLACK);
        btnOrderTime.setForeground(Color.white);
        btnOrderTime.setBorder(null);

        btnOrderDiff = new RoundedButton("DIFFICOLTÀ");
        btnOrderDiff.setBounds(500, 0, 200, 60);
        btnOrderDiff.addActionListener(this);
        btnOrderDiff.setFont(new Font("MV Boli", Font.BOLD, 15));
        btnOrderDiff.setBackground(Color.BLACK);
        btnOrderDiff.setForeground(Color.white);
        btnOrderDiff.setBorder(null);

        panelContBtnStats = new JPanel();
        panelContBtnStats.setBackground(new Color(123, 50, 250));
        panelContBtnStats.setSize(700, 60);
        panelContBtnStats.setLayout(null);
        panelContBtnStats.add(btnOrderPoint);
        panelContBtnStats.add(btnOrderTime);
        panelContBtnStats.add(btnOrderDiff);
        centerComponent(this, panelContBtnStats, 150);

        btnExit = new RoundedButton("HOME PAGE");
        btnExit.setSize(200, 60);
        btnExit.addActionListener(this);
        btnExit.setFont(new Font("MV Boli", Font.BOLD, 15));
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.white);
        btnExit.setBorder(null);
        centerComponent(this, btnExit, 550);

        //======================================================================================================
        // CREAZIONE TABELLA STATISTICHE
        //======================================================================================================

        panel = new JPanel(new BorderLayout());

        // Creazione campi del modello dati della tabella
        model = new DefaultTableModel();
        model.addColumn("Partita");
        model.addColumn("Punteggio");
        model.addColumn("Tempo");
        model.addColumn("Parole Trovate");
        model.addColumn("Difficoltà");
        model.addColumn("Nome Utente");

        // Aggiungi dati di esempio

        DBConnectionMariaDB c = new DBConnectionMariaDB();
        Query q = new Query();

        String[][] risultati = q.getQuerySelectPartite();
        if (risultati[0][0] != null) {
            for (int i = 0; i < lunghezza(risultati); i++) {
                model.addRow(new Object[]{risultati[i][0], risultati[i][1], risultati[i][2], risultati[i][3], risultati[i][4], risultati[i][5]});
            }

            // Creazione della tabella
            table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.setRowHeight(40);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer);
            table.getTableHeader().setReorderingAllowed(false);
            table.disable();

            // Rendi le colonne non ridimensionabili
            TableColumn column;
            for (int i = 0; i < table.getColumnCount(); i++) {
                column = table.getColumnModel().getColumn(i);
                column.setResizable(false); // Rendi la colonna non ridimensionabile
            }

            scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.setVisible(true);
            panel.setBackground(Color.BLACK);
            panel.setSize(500, 200);

            centerComponent(this, panel, 300);

            //======================================================================================================
            // IMPOSTAZIONI FRAME
            //======================================================================================================

            ImageIcon icon = new ImageIcon("file/ParoliereIcon.png");
            this.setIconImage(icon.getImage());

            this.add(panel);
            this.add(labelTitolo);
            this.add(panelContBtnStats);
            this.add(btnExit);

            this.setLayout(null);
            this.setResizable(false);
            this.getContentPane().setBackground(new Color(123, 50, 250));
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            centerFrame(this);
        }
    }

    public void centerComponent(Frame f, JComponent c, int y) {
        Dimension frameSize = f.getSize();
        Dimension componentSize = c.getSize();
        c.setLocation((frameSize.width - componentSize.width) / 2, y);
    }

    public static void centerFrame(Frame f) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = f.getSize();
        f.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2 - 30);
    }

    public int lunghezza(String[][] str) {
        boolean trovato = false;
        int i = 0;
        while (str[i][0] != null && trovato == false) {
            if (str[i][0] == null) {
                trovato = true;
            }
            i++;
        }
        return i;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnExit) {

            HomePage pageHome = new HomePage();
            this.dispose();
        }

        if (e.getSource() == btnOrderTime) {

            btnOrderTime.setBackground(Color.GRAY);
            btnOrderDiff.setBackground(Color.BLACK);
            btnOrderPoint.setBackground(Color.BLACK);

            System.out.println(table.getRowCount());

            Container parent = table.getParent();
            Container parent2=parent.getParent();
            Container parent3=parent2.getParent();

            if (parent != null) {
                parent.remove(table);
                parent.revalidate();
                parent.repaint();
                if(parent2!=null){
                    parent2.remove(parent);
                    parent.revalidate();
                    parent.repaint();
                    if(parent3!=null){
                        parent3.remove(parent2);
                        parent3.revalidate();
                        parent3.repaint();
                    }
                }
            }

           //Connessione al database e query per ottenere i dati

            DBConnectionMariaDB c = new DBConnectionMariaDB();
            Query q = new Query();

            String[][] risultati1;
            try {
                risultati1 = q.getQuerySelectPartiteTempo();
                /*
                for(int i=0;i<lunghezza(risultati);i++){
                    for (int j=0;j<4;j++){
                        System.out.println(risultati[i][j]);
                    }
                }
                */
                RiempiTabella(risultati1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        }

        if (e.getSource() == btnOrderDiff) {

            Container parent = table.getParent();
            Container parent2=parent.getParent();
            Container parent3=parent2.getParent();

            if (parent != null) {
                parent.remove(table);
                parent.revalidate();
                parent.repaint();
                if(parent2!=null){
                    parent2.remove(parent);
                    parent.revalidate();
                    parent.repaint();
                    if(parent3!=null){
                        parent3.remove(parent2);
                        parent3.revalidate();
                        parent3.repaint();
                    }
                }
            }

            DBConnectionMariaDB c = new DBConnectionMariaDB();
            Query q = new Query();

            String[][] risultati1;
            try {
                risultati1 = q.getQuerySelectPartiteDifficolta();
                /*
                for(int i=0;i<lunghezza(risultati);i++){
                    for (int j=0;j<4;j++){
                        System.out.println(risultati[i][j]);
                    }
                }
                */
                RiempiTabella(risultati1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            btnOrderDiff.setBackground(Color.GRAY);
            btnOrderTime.setBackground(Color.BLACK);
            btnOrderPoint.setBackground(Color.BLACK);
        }

        if (e.getSource() == btnOrderPoint) {

            Container parent = table.getParent();
            Container parent2=parent.getParent();
            Container parent3=parent2.getParent();

            if (parent != null) {
                parent.remove(table);
                parent.revalidate();
                parent.repaint();
                if(parent2!=null){
                    parent2.remove(parent);
                    parent.revalidate();
                    parent.repaint();
                    if(parent3!=null){
                        parent3.remove(parent2);
                        parent3.revalidate();
                        parent3.repaint();
                    }
                }
            }

            DBConnectionMariaDB c = new DBConnectionMariaDB();
            Query q = new Query();

            String[][] risultati1;
            try {
                risultati1 = q.getQuerySelectPartitePunteggio();
                /*
                for(int i=0;i<lunghezza(risultati);i++){
                    for (int j=0;j<4;j++){
                        System.out.println(risultati[i][j]);
                    }
                }
                */
                RiempiTabella(risultati1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            btnOrderPoint.setBackground(Color.GRAY);
            btnOrderTime.setBackground(Color.BLACK);
            btnOrderDiff.setBackground(Color.BLACK);
        }
    }

    public void RiempiTabella(String risultati[][]) {
        model = new DefaultTableModel();
        model.addColumn("Partita");
        model.addColumn("Punteggio");
        model.addColumn("Tempo");
        model.addColumn("Parole Trovate");
        model.addColumn("Difficoltà");
        model.addColumn("Nome Utente");

        // Aggiungi dati di esempio

        DBConnectionMariaDB c = new DBConnectionMariaDB();
        Query q = new Query();
        if (risultati[0][0] != null) {
            for (int i = 0; i < lunghezza(risultati); i++) {
                model.addRow(new Object[]{risultati[i][0], risultati[i][1], risultati[i][2], risultati[i][3], risultati[i][4], risultati[i][5]});
            }

            // Creazione della tabella
            table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.setRowHeight(40);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer);
            table.getTableHeader().setReorderingAllowed(false);
            table.disable();

            // Rendi le colonne non ridimensionabili
            TableColumn column;
            for (int i = 0; i < table.getColumnCount(); i++) {
                column = table.getColumnModel().getColumn(i);
                column.setResizable(false); // Rendi la colonna non ridimensionabile
            }

            scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.setVisible(true);
            panel.setBackground(Color.BLACK);
            panel.setSize(500, 200);

        }
    }
}