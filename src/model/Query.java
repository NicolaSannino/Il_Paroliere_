package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public String[][] getQuerySelectPartite() throws SQLException {
        int codice=0;
        DBConnectionMariaDB c=new DBConnectionMariaDB();
        String query="SELECT * FROM partite";
        ResultSet s= c.QuerySelect(c.getC(),query,codice);
        int i=0;
        String numero,punteggio,tempo,parole_trovate,diff;
        if(s!=null) {
            String[][] dati=new String[100][5];
            numero=s.getString("numero");
            punteggio=s.getString("punteggio");
            tempo=s.getString("tempo");
            parole_trovate=s.getString("parole_trovate");
            diff=s.getString("difficolta");
            dati[0][0]=numero;
            dati[0][1]=punteggio;
            dati[0][2]=tempo;
            dati[0][3]=parole_trovate;
            dati[0][4]=diff;
            int j=1;
            while(s.next()){
                numero=s.getString("numero");
                punteggio=s.getString("punteggio");
                tempo=s.getString("tempo");
                parole_trovate=s.getString("parole_trovate");
                diff=s.getString("difficolta");
                dati[j][0]=numero;
                dati[j][1]=punteggio;
                dati[j][2]=tempo;
                dati[j][3]=parole_trovate;
                dati[j][4]=diff;
                j++;
            }
            return dati;
        }
        return null;
    }

    public boolean getInsertPartita(int punteggio,String tempo,String diff,int parole,DBConnectionMariaDB conn){
        String querySelect="INSERT INTO partite (punteggio,tempo,parole_trovate,difficolta) VALUES("+punteggio+",'"+tempo+"',"+parole+",'"+diff+"')";
        return conn.QueryGenerica(conn.getC(),querySelect);
    }

    public boolean getInsertParola(String parola,DBConnection conn){
        String querySelect="INSERT INTO tbl_parole (parola) VALUES('"+parola+"')";
        return conn.QueryGenerica(conn.getC(),querySelect);
    }

    public boolean ricercaParolaDb(String p, DBConnectionMariaDB conn) throws SQLException {
        String querySelect="SELECT parola FROM tbl_parole WHERE parola='"+p+"';";
        ResultSet s= conn.QuerySelect(conn.getC(),querySelect,0);
        if(s ==null) {
            return false;
        }
        String parola=s.getString("parola");
        if(parola.equals(p)==true){
            return true;
        }else {
            return false;
        }
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
