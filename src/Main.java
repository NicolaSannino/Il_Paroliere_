import view.jframe;

public class Main {
    public static void main(String[] args) {
        try {
            jframe f = new jframe("Il Paroliere");
            f.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}