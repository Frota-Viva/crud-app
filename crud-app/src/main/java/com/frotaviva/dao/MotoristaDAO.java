package com.frotaviva.dao;

import java.sql.*;

import com.frotaviva.Conexao;
import com.frotaviva.Senhas;
import com.frotaviva.model.Empresa;
import com.frotaviva.model.Motorista;


public class MotoristaDAO {
    public boolean cadastrarMotorista(Motorista motorista){
        String sql = "INSERT INTO motorista(nome, email, cpf, senha, id_empresa) VALUES(?, ?, ?, ?, ?)";
        String senhaHash = Senhas.hashSenha(motorista.getSenha());
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getEmail());
            stmt.setString(3, motorista.getCpf());
            stmt.setString(4, senhaHash);
            stmt.setLong(5, motorista.getIdEmpresa());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }

    public static boolean atualizarNome(Motorista motorista){
        String sql = "UPDATE empresa SET nome = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, motorista.getNome());
            stmt.setLong(2, motorista.getId());

            if (stmt.executeUpdate() > 0) return true;

            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarEmail(Motorista motorista){
        String sql = "UPDATE empresa SET email = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, motorista.getEmail());
            stmt.setLong(2, motorista.getId());

            if (stmt.executeUpdate() > 0) return true;

            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarSenha(Motorista motorista){
        String senhaArmazenada = Senhas.getSenhaHash(motorista.getId());
        if (Senhas.verificarSenha(motorista.getSenha(), senhaArmazenada)) return false;

        String sql = "UPDATE empresa SET senha = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            String senhaHash = Senhas.hashSenha(motorista.getSenha());
            stmt.setString(1, senhaHash);
            stmt.setLong(2, motorista.getId());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }

    public Motorista getMotorista(long id){
        String sql = "SELECT * FROM motorista WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            long idMotorista = rs.getLong("id");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String cpf = rs.getString("cpf");
            String senha = rs.getString("senha");
            long id_empresa = rs.getLong("id_empresa");

            return new Motorista(id, nome, email, cpf, senha, id_empresa);
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }
}
