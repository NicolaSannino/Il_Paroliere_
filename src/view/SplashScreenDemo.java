package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class SplashScreenDemo {

    JFrame frame;
    JPanel panelImg;

    JLabel image = new JLabel(new ImageIcon("book.png"));
    JLabel text = new JLabel("CARICAMENTO TARCOMINO");
    JProgressBar progressBar = new JProgressBar();
    JLabel message = new JLabel();//Crating a JLabel for displaying the message

    Image immagine;

    public SplashScreenDemo()
    {
        createGUI();
        addImage();
        addText();
        addProgressBar();
        runningPBar();
    }

    public void createGUI(){
        frame = new JFrame();

        frame.setContentPane(new CustomContentPane());

        ImageIcon icon = new ImageIcon("file/ParoliereIcon.png");
        frame.setIconImage(icon.getImage());

        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        frame.setSize(500,200);
        frame.setLocationRelativeTo(null);
        //frame.getContentPane().setBackground(Color.magenta);
        frame.setVisible(true);
    }

    public void addImage(){
        image.setSize(500,200);
        frame.add(image);
    }

    public void addText()
    {
        text.setFont(new Font("Arial Bold Italic",Font.BOLD,30));
        //text.setBounds(170,20,600,40);
        text.setSize(450, 40);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setForeground(Color.BLACK);
        this.centerComponent(frame, text, 25);
        frame.add(text);
    }

    public void addProgressBar(){
        //progressBar.setBounds(100,150,400,30);
        progressBar.setSize(400,30);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.BLACK);
        progressBar.setValue(0);
        this.centerComponent(frame, progressBar,130);
        frame.add(progressBar);
    }

    public void runningPBar(){
        int i=0;//Creating an integer variable and intializing it to 0

        while( i<=100)
        {
            try{
                Thread.sleep(50);//Pausing execution for 50 milliseconds
                progressBar.setValue(i);//Setting value of Progress Bar
                message.setText("LOADING "+Integer.toString(i)+"%");//Setting text of the message JLabel
                i++;
                if(i==100) {
                    frame.dispose();
                    HomePage window1 = new HomePage();
                }
            }catch(Exception e){
                e.printStackTrace();
            }


        }
    }

    //======================================================================================================
    // METODO CENTRA COMPONENTI E FRAME
    //======================================================================================================

    public void centerComponent(Frame f, JComponent c, int y){
        Dimension frameSize = f.getSize();
        Dimension componentSize = c.getSize();
        c.setLocation ((frameSize.width - componentSize.width) / 2, y);
    }

    public static void centerFrame(Frame f)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        Dimension frameSize = f.getSize ();
        f.setLocation ((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2 - 30);
    }

    private static class CustomContentPane extends JPanel {
        private Image backgroundImage;

        public CustomContentPane() {
            // Carica l'immagine di sfondo
            backgroundImage = new ImageIcon("file/sfondo2.png").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Disegna l'immagine di sfondo
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}