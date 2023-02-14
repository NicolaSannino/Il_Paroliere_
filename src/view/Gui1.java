package view;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.JFrame;
import java.awt.EventQueue;
public class Gui1 {
    private JFrame frame;
    private JTextArea textArea1;
    private JPanel panel1;

    public Gui1() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel lblNewLabel = new JLabel("Il paroliere");
        lblNewLabel.setBounds(181, 33, 53, 14);
        frame.getContentPane().add(lblNewLabel);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

}
