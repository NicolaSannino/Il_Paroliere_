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
public class Gui1 extends JFrame implements ActionListener{
    private JFrame frame;
    private JTextArea textArea1;
    private JPanel panel1;
    private JLabel Matrice;

    public Gui1() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel lblNewLabel = new JLabel("Il paroliere");
        lblNewLabel.setBounds(181, 33, 53, 14);
        frame.getContentPane().add(lblNewLabel);

        for(int i=1;i<5;i++){
            for (int j=1;j<5;j++){
                Matrice=new JLabel(String.valueOf(i*j));
                Matrice.setBounds(i*50,j*50,50,50);
                frame.add(Matrice);
            }
        }
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
