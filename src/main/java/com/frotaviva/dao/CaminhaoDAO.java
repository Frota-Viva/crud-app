package com.frotaviva.dao;

import com.frotaviva.util.Conexao;
import com.frotaviva.model.Caminhao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CaminhaoDAO {
    public static boolean cadastrarCaminhao(Caminhao caminhao){
        String sql = "INSERT INTO caminhao(placa, status, km_rodados, modelo, capacidade, id_frota, id_motorista) " +
                "VALUES(?, ?, ?, ?, ? ,? ,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, caminhao.getPlaca());
            stmt.setString(2, caminhao.getStatus());
            stmt.setInt(3, caminhao.getKmRodados());
            stmt.setString(4, caminhao.getModelo());
            stmt.setInt(5, caminhao.getCapacidade());
            stmt.setLong(6, caminhao.getIdFrota());
            stmt.setLong(7, caminhao.getIdMotorista());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarPlaca(Caminhao caminhao){
        String sql = "UPDATE caminhao SET placa = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, caminhao.getPlaca());
            stmt.setLong(2, caminhao.getId());

            if (stmt.executeUpdate() > 0) return true;

            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarStatus(Caminhao caminhao){
        String sql = "UPDATE caminhao SET status = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, caminhao.getStatus());
            stmt.setLong(2, caminhao.getId());

            if (stmt.executeUpdate() > 0) return true;

            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarKmRodados(Caminhao caminhao){
        String sql = "UPDATE caminhao SET km_rodados = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, caminhao.getKmRodados());
            stmt.setLong(2, caminhao.getId());

            if (stmt.executeUpdate() > 0) return true;

            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarIdMotorista(Caminhao caminhao){
        String sql = "UPDATE caminhao SET id_motorista = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, caminhao.getIdMotorista());
            stmt.setLong(2, caminhao.getId());

            if (stmt.executeUpdate() > 0) return true;

            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarIdFrota(Caminhao caminhao){
        String sql = "UPDATE caminhao SET id_frota = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, caminhao.getIdFrota());
            stmt.setLong(2, caminhao.getId());

            if (stmt.executeUpdate() > 0) return true;

            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean deletarCaminhao(Caminhao caminhao){
        String sql = "DELETE FROM caminhao WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, caminhao.getId());

            if (stmt.executeUpdate() > 0) return true;

            return false;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }

    public static Caminhao getCaminhao(long id){
        String sql = "SELECT * FROM caminhao WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                long idCaminhao = rs.getLong("id");
                String placa = rs.getString("placa");
                String status = rs.getString("status");
                int km_rodados = rs.getInt("km_rodados");
                String modelo = rs.getString("modelo");
                int capacidade = rs.getInt("capacidade");
                long id_frota = rs.getLong("id_frota");
                long id_motorista = rs.getLong("id_motorista");

                return new Caminhao(idCaminhao, placa, status, km_rodados, modelo, capacidade, id_frota, id_motorista);
            }

            return null;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }
}

