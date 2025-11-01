package com.frotaviva.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Responsável por gerenciar e garantir a segurança das senhas no banco de dados a partir da biblioteca BCrypt
 * <p>Contém métodos que garantem a segurança das informações dos usuário</p>
 * 
 * <p>Principais métodos</p>
 * <ul>
 * <li>hashSenha() -> gera um hash BCrypt da senha recebida</li>
 * <li>verificarSenha() -> compara um hash com uma senha</li>
 * <li>getSenhaHash() -> pega a senha (em hash) cadastrada no banco de dados de uma empresa específica</li>
 * </ul>
 * 
 * @author Ricardo 
 */

public class Senhas {

    private static final Logger log = LoggerFactory.getLogger(Senhas.class);

    /**
     * Recebe uma senha e transforma ela em um hash utilizando Bcrypt
     * 
     * @param senha senha que virará hash
     * @return uma String -> senha em forma de hash
     */
    public static String hashSenha(String senha){
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    /**
     * Recebe uma senha normal e um hash para comparar se os dois são iguais
     * 
     * @param senhaDigitada senha que será comparada com o hash
     * @param hash hash utlizado para verificar se a senha e ele coincidem
     */
    public static boolean verificarSenha(String senhaDigitada,String hash){

        if (hash == null || hash.isEmpty()) {
            return false;
        }
        if (senhaDigitada == null || senhaDigitada.isEmpty()) {
            return false;
        }

        return BCrypt.checkpw(senhaDigitada, hash);
    }

    /**
     * Recebe um id e retorna o hash de senha salvo no banco de dados com o respectivo id
     * 
     * @param id id da entidade com a senha desejada
     * @return uma String -> hash salvo no banco de dados
     */
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

    
    /**
     * Recebe um email e retorna o hash de senha salvo no banco de dados com o respectivo email
     *
     * @param id email da entidade com a senha desejada
     * @return uma String -> hash salvo no banco de dados
     */
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
