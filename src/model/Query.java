package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import model.DBConnection;
import java.sql.SQLException;
import model.DBConnection;

public class Query {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Query q=new Query();
        if(q.inserimentoParole()){
            System.out.println("fine corretta");
        }else{
            System.out.println("fine non corretta");
        }

    }

    public String[] getQuerySelectId() {
        int codice=0;
        DBConnection c=new DBConnection();
        String query="";
        ResultSet s= c.QuerySelect(c.getC(),query,codice);
        int i=0;
        String marca,targa,anno,valore,id,foto,prop;
        if(s!=null) {
            String[] dati=new String[100];

            return dati;
        }
        return null;
    }

    public boolean getInsertParola(String parola,DBConnection conn){
        String querySelect="INSERT INTO tbl_parole (parola) VALUES('"+parola+"')";
        return conn.QueryGenerica(conn.getC(),querySelect);
    }

    public boolean inserimentoParole() {
        //connessione al db
        //System.out.print("ciao");
        BufferedReader reader;
        try {
            String line;
            reader = new BufferedReader(new FileReader("file/parole.txt"));
            DBConnection conn = new DBConnection();
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                if(getInsertParola(line,conn)){
                    System.out.println("giusto");
                }else {
                    System.out.println("errore");
                    return false;
                }
            }

            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
