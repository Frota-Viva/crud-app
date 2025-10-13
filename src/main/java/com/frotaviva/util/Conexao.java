package com.frotaviva.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class Conexao {

    private final static Dotenv dotenv = Dotenv.configure().load();
    private final static String url = dotenv.get("URL_DB");
    private final static String user = dotenv.get("USER_DB");
    private final static String password = dotenv.get("PASSWORD_DB");
    
    public Connection conectar(){

        Connection conn = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } 
        catch (SQLException sqle){
            throw new RuntimeException("Driver não encontrado");
        }
        catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        finally{
            return conn;
        }

    }

    public void desconectar(Connection conn){
        try {
            if (conn != null && !conn.isClosed()){
                conn.close();
            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

}
