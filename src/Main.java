import view.Gui;

public class Main {
    public static void main(String[] args) {
        try {
            Gui window = new Gui();
            window.getFrame().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}