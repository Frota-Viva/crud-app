package com.frotaviva.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Senhas {

    private static final Logger log = LoggerFactory.getLogger(Senhas.class);

    public static String hashSenha(String senha){
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public static boolean verificarSenha(String senhaDigitada,String hash){

        if (hash == null || hash.isEmpty()) {
            return false;
        }
        if (senhaDigitada == null || senhaDigitada.isEmpty()) {
            return false;
        }

        return BCrypt.checkpw(senhaDigitada, hash);
    }

    public static String getSenhaHash(long id){
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "SELECT senha FROM empresa WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            return rs.getString("senha");
        } 
        catch (SQLException sqle){
            log.error("Erro ao recuperar senha", sqle);
        }
        finally{
            conexao.desconectar(conn);
        }
        return null;
    }
    public static String getSenhaHash(String email){
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "SELECT senha FROM empresa WHERE email = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return rs.getString("senha");
            }
            return "";
        }
        catch (SQLException sqle){
            log.error("Erro ao recuperar senha", sqle);
        }
        finally{
            conexao.desconectar(conn);
        }
        return null;
    }
}
