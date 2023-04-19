package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionMariaDB {


    //Variabili per connessione

    private static final String DB_DRIVER = "org.mariadb.jdbc.Driver"; //mariadb
    //private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver"; //mysql
    private static final String DB_CONNECTION ="jdbc:mariadb://172.22.201.51:3306/Comolli_Sannino_Tarabella_Paroliere";//nome database
    //private static final String DB_CONNECTION ="jdbc:mysql://localhost/parole";
    private static final String DB_USER = "utentedb";
    //private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Cobi_2022_$";
    //private static final String DB_PASSWORD = "";
    private  Connection c;


    //Connessione al database

    public DBConnectionMariaDB() {
        String url;
        Connection con = null;
        System.out.println("Inizio Connessione...");
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            System.out.println("Cerco i driver...");
            Class.forName(DB_DRIVER);
            System.out.println("Driver trovati");
        } catch (Exception ex) {
            // handle the error
            System.out.println("errore JDBC");
        }
        try
        {
            Connection dbConnection = null;
            System.out.println("Provo a connettermi al Database...");
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);

            System.out.println("SQL Connection to DB eseguita correttamente!");
            c=dbConnection;
            //DBConnection.QuerySelect(c,"Utente1","Password","\"SELECT * FROM anagrafiche a INNER JOIN utenti u ON a.CF=u.CfAnagrafica WHERE u.Password=\"+pass+\" && Username='\"+user+\"'\";");


        }
        catch (SQLException e)
        {
            System.out.println("Connection to dbmio database failed");
            System.out.println(e.getErrorCode() + ":" + e.getMessage());
            // throw new SQLException(e.getErrorCode() + ":" + e.getMessage());
        }
    }

    //Per le select
    public static ResultSet QuerySelect(Connection c,String query,int codice) {
        Statement stmt = null;
        //boolean trovato=false;
        try {
            stmt = c.createStatement();
            //System.out.println("ciao");
            //String select = "SELECT * FROM tblutenti";
            //String q="SELECT * FROM anagrafiche a INNER JOIN utenti u ON a.CF=u.CfAnagrafica WHERE u.Password='Password' && Username='Utente1'";
            ResultSet UtentiList = stmt.executeQuery(query);
            if(codice==1) {//select generale
                //System.out.println("+");
                //System.out.println(UtentiList.);
                if(UtentiList!=null) {
                    UtentiList.next();
                    return UtentiList;
                }
            }


            if(codice==0) {//select login
                if(UtentiList.isBeforeFirst()==false) {//la query non ha restutuito tuple
                    //System.out.println("Autenticazione fallita");
                    System.out.println("ciao");
                    return null;
                }else {
                    if(UtentiList.next()==true) {//la query ha restutuito tuple
                        //System.out.println(UtentiList.getString("Cf"));
                        //System.out.println(UtentiList.getString("Username"));
                        //System.out.println(UtentiList.getString("Password"));
                        //trovato=true;
                        return UtentiList;
                    }else {
                        return null;
                    }
                }

                /**
                 while (UtentiList.next()){
                 System.out.println(UtentiList.getString("id"));
                 System.out.println(UtentiList.getString("username"));
                 System.out.println(UtentiList.getString("password"));
                 trovato=true;
                 }
                 return trovato;
                 */

            }else {
                return UtentiList;
            }

            //gestione errori in Java
        }catch(SQLException sqle){
            System.out.println("SELECT ERROR");
            return null;
        }
        catch(Exception err){
            System.out.println("GENERIC ERROR");
            return null;
        }
    }

    //Per insert e update
    public static boolean QueryGenerica(Connection c,String query) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            //int UtentiList = stmt.executeUpdate(query);
            int UtentiList = stmt.executeUpdate(query);
            stmt.close();
            return true;
        }catch(SQLException sqle)
        {
            System.out.println("ERROR");
            return false;
        }
        catch(Exception err)
        {
            System.out.println("GENERIC ERROR");
            return false;
        }
    }


    //getter e setter

    public Connection getC() {
        return c;
    }


    public void setC(Connection c) {
        this.c = c;
    }



    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }


}
