package view;

import model.DBConnectionMariaDB;
import model.Query;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

public class PageStatistiche extends JFrame implements ActionListener {

    RoundedButton btnExit, btnOrderTime, btnOrderDiff, btnOrderPoint,btnInvia;
    JPanel panelContBtnStats;

    DefaultTableModel model;
    JTable table;
    JTextField testo;
    JScrollPane scrollPane,scrollPane1;
    JPanel panel,panelCercaParole;

    Font font1 = new Font("MV Boli", Font.BOLD, 15);
    Color coloreBtnPrima, ColorbtnOrderPoint, ColorbtnOrderTime, ColorbtnOrderDiff;
    Font fontA = new Font ("MV Boli", Font.BOLD, 20);
    Font fontB = new Font ("MV Boli", Font.BOLD, 15);
    Border bordo2 = BorderFactory.createLineBorder(Color.black, 3);
    JLabel titoloSelectBox;
    ScrollableComboBoxExample.ScrollableComboBox<String> comboBox;
    private JComboBox<String> selectBox;

    public PageStatistiche() throws SQLException {

        this.setTitle("Pagina Statistiche");
        this.setSize(1100, 700);

        //======================================================================================================
        // TITOLO FRAME
        //======================================================================================================

        JLabel labelTitolo = new JLabel();
        labelTitolo.setText("Pagina Statistiche Paroliere");
        labelTitolo.setForeground(new Color(0, 0, 0));
        labelTitolo.setFont(new Font("Arial Bold Italic", Font.PLAIN, 40));

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
        coloreBtnPrima = btnOrderPoint.getBackground();
        btnOrderPoint.addMouseListener(createMouseListener(coloreBtnPrima));
        btnOrderPoint.setBorder(null);

        btnOrderTime = new RoundedButton("TEMPO");
        btnOrderTime.setBounds(0, 0, 200, 60);
        btnOrderTime.addActionListener(this);
        btnOrderTime.setFont(new Font("MV Boli", Font.BOLD, 15));
        btnOrderTime.setBackground(Color.BLACK);
        btnOrderTime.setForeground(Color.white);
        coloreBtnPrima = btnOrderTime.getBackground();
        btnOrderTime.addMouseListener(createMouseListener(coloreBtnPrima));
        btnOrderTime.setBorder(null);

        btnOrderDiff = new RoundedButton("DIFFICOLTÀ");
        btnOrderDiff.setBounds(500, 0, 200, 60);
        btnOrderDiff.addActionListener(this);
        btnOrderDiff.setFont(new Font("MV Boli", Font.BOLD, 15));
        btnOrderDiff.setBackground(Color.BLACK);
        btnOrderDiff.setForeground(Color.white);
        coloreBtnPrima = btnOrderDiff.getBackground();
        btnOrderDiff.addMouseListener(createMouseListener(coloreBtnPrima));
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
        coloreBtnPrima = btnExit.getBackground();
        btnExit.addMouseListener(createMouseListener(coloreBtnPrima));
        btnExit.setBorder(null);
        centerComponent(this, btnExit, 570);

        //======================================================================================================
        // CREAZIONE TABELLA STATISTICHE
        //======================================================================================================

        panel = new JPanel(new BorderLayout());

        panelCercaParole = new JPanel(null);
        panelCercaParole.setVisible(true);
        panelCercaParole.setBounds(600,250,300,300);
        this.add(panelCercaParole);
        panelCercaParole.setBackground(Color.black);

        btnInvia = new RoundedButton("Invia");
        btnInvia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedWord = (String) comboBox.getSelectedItem();
                System.out.println(selectedWord);
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

                String[] risultati1;
                try {
                    risultati1 = q.getQuerySelectPartiteParole(selectedWord);

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
        });
        btnInvia.setFont(new Font("MV Boli", Font.BOLD, 15));
        btnInvia.setBackground(Color.WHITE);
        btnInvia.setForeground(Color.BLUE);
        btnInvia.setSize(150,40);
        centerIntoPanel(panelCercaParole, btnInvia, 240);
        coloreBtnPrima = btnInvia.getBackground();
        btnInvia.addMouseListener(createMouseListener(coloreBtnPrima));
        btnInvia.setBorder(null);
        //scrollPane1=new JScrollPane();
        panelCercaParole.add(btnInvia);

        titoloSelectBox = new JLabel();
        titoloSelectBox.setText("Seleziona La Difficolta");
        titoloSelectBox.setHorizontalAlignment(JLabel.CENTER);
        titoloSelectBox.setFont(new Font("MV Boli", Font.BOLD, 15));
        titoloSelectBox.setBounds(150,90,300,30);
        panelCercaParole.add(titoloSelectBox);

        DBConnectionMariaDB c = new DBConnectionMariaDB();
        Query q = new Query();


        String[][] s=q.getQuerySelectPartite();
        // Crea un array di opzioni per la ComboBox
        if(s!=null){
            String[] options= new String[lunghezza(s)];
            for(int i=0;i<lunghezza(s);i++){
                options[i]=s[i][0];
            }
            // Crea una ComboBox personalizzata scrollable
            comboBox = new ScrollableComboBoxExample.ScrollableComboBox<>(options);
            //String selectedText = comboBox.getSelectedItem().toString();

            JPanel p=new JPanel();
            //comboBox.setSize(800,30);

            comboBox.setBackground(Color.BLACK);
            comboBox.setForeground(Color.BLACK);
            //comboBox.getButton().setSize(220,40);
            //centerComponent(panelCercaParole,comboBox.getPopupMenu(),120);

            p.add(comboBox);
            panelCercaParole.add(p);
            p.setBounds(90,100,120,40);
            //p.setOpaque(false);

            // Creazione campi del modello dati della tabella
            model = new DefaultTableModel();
            model.addColumn("Partita");
            model.addColumn("Punteggio");
            model.addColumn("Tempo");
            model.addColumn("Parole Trovate");
            model.addColumn("Difficoltà");
            model.addColumn("Nome Utente");

            // Aggiungi dati di esempio

            Query q2 = new Query();

            String[][] risultati = q2.getQuerySelectPartite();
            if (risultati[0][0] != null) {
                for (int i = 0; i < lunghezza(risultati); i++) {
                    model.addRow(new Object[]{risultati[i][0], risultati[i][1], risultati[i][2], risultati[i][3], risultati[i][4], risultati[i][5]});
                }

                // Creazione della tabella
                table = new JTable(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table.setRowHeight(50);

                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                table.setDefaultRenderer(Object.class, centerRenderer);
                table.getTableHeader().setReorderingAllowed(false);
                table.disable();

                //Grafica tabella
                table.setFont(font1);
                table.setBackground(Color.CYAN);
                table.setForeground(Color.BLACK);
                table.getTableHeader().setFont(font1);

                // Rendi le colonne non ridimensionabili
                TableColumn column;
                for (int i = 0; i < table.getColumnCount(); i++) {
                    column = table.getColumnModel().getColumn(i);
                    column.setResizable(false); // Rendi la colonna non ridimensionabile
                    column.setPreferredWidth(125); // Imposta la larghezza desiderata per la colonna
                }

                scrollPane = new JScrollPane(table);
                panel.add(scrollPane, BorderLayout.CENTER);
                panel.setVisible(true);
                panel.setSize(768, 230);

                centerComponent(this, panel, 280);

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
    }

    public void centerComponent(Frame f, JComponent c, int y) {
        Dimension frameSize = f.getSize();
        Dimension componentSize = c.getSize();
        c.setLocation((frameSize.width - componentSize.width) / 2, y);
    }

    public void centerComponent(JPanel f, JPopupMenu c, int y) {
        Dimension frameSize = f.getSize();
        Dimension componentSize = c.getSize();
        c.setLocation((frameSize.width - componentSize.width) / 2, y);
    }

    public void centerIntoPanel(JPanel p, JComponent c, int y) {
        Dimension frameSize = p.getSize();
        Dimension componentSize = c.getSize();
        c.setLocation((frameSize.width - componentSize.width) / 2, y);
    }

    public void centerIntoPanel(JPanel p, JCheckBox combo, int y) {
        Dimension frameSize = p.getSize();
        Dimension componentSize = combo.getSize();
        combo.setLocation((frameSize.width - componentSize.width) / 2, y);
    }

    public void centerIntoPanel(JPanel p, JScrollPane combo, int y) {
        Dimension frameSize = p.getSize();
        Dimension componentSize = combo.getSize();
        combo.setLocation((frameSize.width - componentSize.width) / 2, y);
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

    public int lunghezza(String[] str) {
        boolean trovato = false;
        int i = 0;
        while (str[i] != null && trovato == false) {
            if (str[i] == null) {
                trovato = true;
            }
            i++;
        }
        return i;
    }

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

            ColorbtnOrderTime = btnOrderTime.getBackground();
            ColorbtnOrderDiff = btnOrderDiff.getBackground();
            ColorbtnOrderPoint = btnOrderPoint.getBackground();

            btnOrderTime.addMouseListener(createMouseListener(ColorbtnOrderTime));
            btnOrderDiff.addMouseListener(createMouseListener(ColorbtnOrderDiff));
            btnOrderPoint.addMouseListener(createMouseListener(ColorbtnOrderPoint));

            System.out.println(table.getRowCount());

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

            ColorbtnOrderTime = btnOrderTime.getBackground();
            ColorbtnOrderDiff = btnOrderDiff.getBackground();
            ColorbtnOrderPoint = btnOrderPoint.getBackground();

            btnOrderTime.addMouseListener(createMouseListener(ColorbtnOrderTime));
            btnOrderDiff.addMouseListener(createMouseListener(ColorbtnOrderDiff));
            btnOrderPoint.addMouseListener(createMouseListener(ColorbtnOrderPoint));
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

            ColorbtnOrderTime = btnOrderTime.getBackground();
            ColorbtnOrderDiff = btnOrderDiff.getBackground();
            ColorbtnOrderPoint = btnOrderPoint.getBackground();

            btnOrderTime.addMouseListener(createMouseListener(ColorbtnOrderTime));
            btnOrderDiff.addMouseListener(createMouseListener(ColorbtnOrderDiff));
            btnOrderPoint.addMouseListener(createMouseListener(ColorbtnOrderPoint));
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
        if (risultati[0][0] != null) {
            for (int i = 0; i < lunghezza(risultati); i++) {
                model.addRow(new Object[]{risultati[i][0], risultati[i][1],risultati[i][1],risultati[i][2],risultati[i][3],risultati[i][4],risultati[i][5]});
            }

            // Creazione della tabella
            table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.setRowHeight(50);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer);
            table.getTableHeader().setReorderingAllowed(false);
            table.disable();

            //Grafica Tabella
            table.setFont(font1);
            table.setBackground(Color.CYAN);
            table.setForeground(Color.BLACK);
            table.getTableHeader().setFont(font1);

            // Rendi le colonne non ridimensionabili
            TableColumn column;
            for (int i = 0; i < table.getColumnCount(); i++) {
                column = table.getColumnModel().getColumn(i);
                column.setResizable(false); // Rendi la colonna non ridimensionabile
                column.setPreferredWidth(125);
            }

            scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.setVisible(true);
            panel.setBackground(Color.BLACK);
            panel.setSize(768, 230);

        }
    }

    public void RiempiTabella(String risultati[]) {
        model = new DefaultTableModel();
        model.addColumn("Parola");
        model.addColumn("Punteggio");

        // Aggiungi dati di esempio
        if (risultati != null) {
            for (int i = 0; i < lunghezza(risultati); i++) {
                model.addRow(new Object[]{risultati[i], risultati[i],});
            }

            // Creazione della tabella
            table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.setRowHeight(50);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table.setDefaultRenderer(Object.class, centerRenderer);
            table.getTableHeader().setReorderingAllowed(false);
            table.disable();

            //Grafica Tabella
            table.setFont(font1);
            table.setBackground(Color.CYAN);
            table.setForeground(Color.BLACK);
            table.getTableHeader().setFont(font1);

            // Rendi le colonne non ridimensionabili
            TableColumn column;
            for (int i = 0; i < table.getColumnCount(); i++) {
                column = table.getColumnModel().getColumn(i);
                column.setResizable(false); // Rendi la colonna non ridimensionabile
                column.setPreferredWidth(375);
            }

            scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.setVisible(true);
            panel.setBackground(Color.BLACK);
            panel.setSize(768, 230);

        }
    }
}