import view.Gui;

public class Main {
    public static void main(String[] args) {
        try {
            Gui window = new Gui();
            window.getFrame().setExtendedState(window.getFrame().MAXIMIZED_BOTH);
            window.getFrame().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}