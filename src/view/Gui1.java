package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import javax.swing.JFrame;

public class Gui1 extends JFrame implements ActionListener{
    private JFrame frame;
    private JTextArea textArea1;
    private JPanel panel;
    private JLabel Matrice;

    public Gui1() {
        frame = new JFrame();
        setTitle("Il paroliere");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        panel = new JPanel();
        panel.setLayout(new GridLayout(12, 16));

        for (int i = 1; i <= 192; i++) {
            if(i==24){
                JLabel label = new JLabel("Il Paro");
                label.setHorizontalAlignment(JLabel.RIGHT);
                //label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(label);
            }else if(i==25){
                JLabel label = new JLabel("liere");
                //label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(label);
            }else if(i >= 71 && i <= 74){
                Random rand = new Random();
                int randomNum = rand.nextInt(26) + 97;
                char a = (char) randomNum;
                JButton btn = new JButton(Character.toString(a));
                btn.setHorizontalAlignment(JButton.CENTER);
                btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(btn);
            }else if(i >= 87 && i <= 90){
                Random rand = new Random();
                int randomNum = rand.nextInt(26) + 97;
                char a = (char) randomNum;
                JButton btn = new JButton(Character.toString(a));
                btn.setHorizontalAlignment(JButton.CENTER);
                btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(btn);
            }else if(i >= 103 && i <= 106){
                Random rand = new Random();
                int randomNum = rand.nextInt(26) + 97;
                char a = (char) randomNum;
                JButton btn = new JButton(Character.toString(a));
                btn.setHorizontalAlignment(JButton.CENTER);
                btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(btn);
            } else if(i >= 119 && i <= 122){
                Random rand = new Random();
                int randomNum = rand.nextInt(26) + 97;
                char a = (char) randomNum;
                JButton btn = new JButton(Character.toString(a));
                btn.setHorizontalAlignment(JButton.CENTER);
                btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(btn);
            }else{
                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);
                //label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panel.add(label);
            }

            //JLabel label = new JLabel(Integer.toString(i));
            //label.setHorizontalAlignment(JLabel.CENTER);
            //label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            //panel.add(label);
        }
        frame.add(panel);
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