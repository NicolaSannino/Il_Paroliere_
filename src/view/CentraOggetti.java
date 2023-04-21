package view;

import javax.swing.*;

public class CentraOggetti {
    public CentraOggetti(){

    }

    public void centraLabel(JLabel label,JFrame f,int h){
        int labelWidth = label.getPreferredSize().width;;
        int labelHeight = label.getPreferredSize().height;
        int containerWidth = f.getWidth();
        int containerHeight = f.getHeight();
        int labelX = (containerWidth - labelWidth) / 2;
        int labelY = (containerHeight - labelHeight) / 2;
        label.setBounds(labelX, h, labelWidth, labelHeight);
    }
}
