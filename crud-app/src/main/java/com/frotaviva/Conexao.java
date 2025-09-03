package com.frotaviva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class Conexao {
    private final static Dotenv dotenv = Dotenv.configure().directory("../").filename(".env").load();
    private final static String url = dotenv.get("URL_DB");
    private final static String user = dotenv.get("USER_DB");
    private final static String password = dotenv.get("PASSWORD_DB");
    public Connection conectar(){
        Connection conn = null;
        try {
//        Informado o driver que será usado
            Class.forName("org.postgresql.Driver");

//        Criando a conexão com o BD
            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException sqle){
            sqle.printStackTrace();
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        return conn;

    }
    public void desconectar(Connection conn){
        try {
            if (conn != null && !conn.isClosed()){
                //Desconectando o BD
                conn.close();

            }
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }


}
