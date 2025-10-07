package com.frotaviva.dao;

import com.frotaviva.util.Conexao;
import com.frotaviva.model.TelefoneMotorista;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class TelefoneMotoristaDAO {
    public static boolean cadastrarTelefoneMotorista(TelefoneMotorista telefoneMotorista){
        String sql = "INSERT INTO telefone_motorista(telefone_motorista, id_motorista) VALUES(? , ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt =  conn.prepareStatement(sql)){
            stmt.setString(1, telefoneMotorista.getTelefoneMotorista());
            stmt.setLong(2, telefoneMotorista.getIdMotorista());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarTelefoneMotorista(TelefoneMotorista telefoneMotorista){
        String sql = "UPDATE telefone_empresa SET telefone_motorista = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, telefoneMotorista.getTelefoneMotorista());
            stmt.setLong(2, telefoneMotorista.getId());

            if (stmt.executeUpdate() > 0) return true;

            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean deletarTelefoneMotorista(TelefoneMotorista telefoneMotorista){
        String sql = "DELETE FROM telefone_motorista WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, telefoneMotorista.getId());

            if (stmt.executeUpdate() > 0) return true;

            return false;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }

    }
    public static TelefoneMotorista getTelefoneMotorista(long id){
        String sql = "SELECT * FROM telefone_motorista WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareCall(sql)){
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                long idTelefoneMotorista = rs.getLong("id");
                String telefone_motorista = rs.getString("telefone_motorista");
                long id_motorista = rs.getLong("id_motorista");

                return new TelefoneMotorista(idTelefoneMotorista, telefone_motorista, id_motorista);
            }
            return null;

        } catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }
}
