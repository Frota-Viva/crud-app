package com.frotaviva.dao;

import com.frotaviva.Conexao;
import com.frotaviva.Senhas;
import com.frotaviva.model.Frota;
import com.frotaviva.model.Motorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FrotaDAO {
    public static boolean cadastrarFrota(Frota frota){
        String sql = "INSERT INTO frota(tamanho_frota, tipo_frota, regiao, id_empresa)" +
                " VALUES(?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, frota.getTamanhoFrota());
            stmt.setString(2, frota.getTipoFrota());
            stmt.setString(3, frota.getRegiao());
            stmt.setLong(4, frota.getIdEmpresa());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarTamanhoFrota(Frota frota){
        String sql = "UPDATE frota SET tamanho_frota = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, frota.getTamanhoFrota());
            stmt.setLong(2, frota.getId());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarTipoFrota(Frota frota){
        String sql = "UPDATE frota SET tipo_frota = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, frota.getTipoFrota());
            stmt.setLong(2, frota.getId());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarRegiao(Frota frota){
        String sql = "UPDATE frota SET regiao = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, frota.getRegiao());
            stmt.setLong(2, frota.getId());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public Frota getFrota(long id){
        String sql = "SELECT * FROM frota WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            long idFrota = rs.getLong("id");
            int tamanho_frota = rs.getInt("tamanho_frota");
            String tipo_frota = rs.getString("tipo_frota");
            String regiao = rs.getString("regiao");
            long id_empresa = rs.getLong("id_empresa");

            return new Frota(idFrota, tamanho_frota, tipo_frota, regiao, id_empresa);
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }
}
