package view;

import javax.swing.*;

public class CentraOggetti {
    public CentraOggetti(){

    }

    public void centraLabel(JComponent c,JFrame f,int h){
        int labelWidth = c.getPreferredSize().width;;
        int labelHeight = c.getPreferredSize().height;
        int containerWidth = f.getWidth();
        int containerHeight = f.getHeight();
        int labelX = (containerWidth - labelWidth) / 2;
        int labelY = (containerHeight - labelHeight) / 2;
        c.setBounds(labelX, h, labelWidth, labelHeight);
    }
}
