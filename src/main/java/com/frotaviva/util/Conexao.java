package com.frotaviva.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Responsável por gerenciar a conexão com o banco de dados Postgresql
 * <p>
 * Utiliza as variáveis de ambiente (URL_DB, USER_DB, PASSWORD_DB) para estabelecer
 * a conexão com o banco de dados 
 * </p>
 * <p>Métodos da classe:</p>
 * <ul>
 * <li> conectar() - Cria e retorna a conexão com o banco de dados </li>
 * <li> desconectar() - Fecha a conexão com o banco de dados </li>
 * </ul>
 * 
 * @author Ricardo
 */

public class Conexao {

    private final static Dotenv dotenv = Dotenv.configure().load();
    private final static String url = dotenv.get("URL_DB");
    private final static String user = dotenv.get("USER_DB");
    private final static String password = dotenv.get("PASSWORD_DB");
    

    /**
     * Estabelece e retorna a conexão com o banco de dados Postgresql a partir das variáveis de ambiente (URL_DB, USER_DB, PASSWORD_DB)
     * 
    * @return uma "Connection" -> conexão com o BD
     */
    public Connection conectar(){

        Connection conn = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } 
        catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
        catch (SQLException sqle){
            throw new RuntimeException("Driver não encontrado");
        }
        finally{
            return conn;
        }

    }

    /**
     * Fecha a conexão com o banco de dados
     * 
     * @param conn conexão a ser fechada
     */
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
