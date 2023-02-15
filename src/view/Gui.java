package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Gui extends JFrame implements ActionListener{
    private JFrame frame;
    private JLabel Matrice;
    public Gui() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for(int i=1;i<5;i++){
            for (int j=1;j<5;j++){
                Matrice=new JLabel(String.valueOf(i*j));
                Matrice.setBounds(i*50,j*50,50,50);
                frame.add(Matrice);
            }
        }
    }
    public JFrame getFrame() {
        return frame;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
