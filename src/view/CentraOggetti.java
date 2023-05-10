package view;

import javax.swing.*;
import java.awt.*;

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

    public void centerComponent(JComponent c, int y){
        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        Dimension frameSize = c.getSize ();
        c.setLocation ((screenSize.width - frameSize.width) / 2, y);
    }
}
