import view.CampoGioco;

public class Main {
    public static void main(String[] args) {
        try {
            CampoGioco f = new CampoGioco("Il Paroliere");
            f.setDefaultCloseOperation(CampoGioco.EXIT_ON_CLOSE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}