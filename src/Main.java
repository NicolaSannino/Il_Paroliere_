import view.Gui1;

public class Main {
    public static void main(String[] args) {
        try {
            Gui1 window = new Gui1();
            window.getFrame().setExtendedState(window.getFrame().MAXIMIZED_BOTH);
            window.getFrame().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}