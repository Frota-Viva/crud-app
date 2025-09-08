package com.frotaviva.util;

import com.frotaviva.util.Conexao;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Senhas {
    public static String hashSenha(String senha){
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public static boolean verificarSenha(String senhaDigitada,String hash){
        return BCrypt.checkpw(senhaDigitada, hash);
    }
    public static String getSenhaHash(long id){
        String sql = "SELECT senha FROM empresa WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            return rs.getString("senha");
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }
}
