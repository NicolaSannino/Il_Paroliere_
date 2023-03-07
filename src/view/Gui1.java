package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.JFrame;

public class Gui1 extends JFrame implements ActionListener{
    private JFrame frame;
    private JTextArea textArea1;
    private JPanel panel;
    private JLabel Matrice;

    public Gui1() {
        setTitle("Matrice 4x4");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        for (int i = 1; i <= 16; i++) {
            JLabel label = new JLabel(Integer.toString(i));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(label);
        }
        add(panel);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

}
