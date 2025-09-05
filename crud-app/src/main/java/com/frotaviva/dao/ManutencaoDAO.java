package com.frotaviva.dao;

import com.frotaviva.Conexao;
import com.frotaviva.model.Manutencao;

import java.math.BigDecimal;
import java.sql.*;

public class ManutencaoDAO {
    public static boolean cadastrarManutencao(Manutencao manutencao){
        String sql = "INSER INTO manutencao(dt_cadastro, dt_conclusao, tipo_manutencao, custo, ultimo_motorista," +
                " descricao_servico, id_caminhao) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setDate(1, manutencao.getDtCadastro()); //Transforma a dtCadastro em tipo Date no SQL
            if (manutencao.getDtConclusao() != null){
                stmt.setDate(2, manutencao.getDtConclusao());
            } else {
                stmt.setNull(2, Types.DATE);
            }
            stmt.setString(3, manutencao.getTipoManutencao());
            stmt.setBigDecimal(4, manutencao.getCusto());
            stmt.setLong(5, manutencao.getUltimoMotorista());
            stmt.setString(6, manutencao.getDescricaoServico());
            stmt.setLong(7, manutencao.getIdCaminhao());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }

    public static boolean inserirDtConclusao(Manutencao manutencao){
        String sql = "UPDATE manutencao SET dt_conclusao = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setDate(1, manutencao.getDtConclusao());
            stmt.setLong(2, manutencao.getId());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean alterarCusto(Manutencao manutencao){
        String sql = "UPDATE manutencao SET custo = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setBigDecimal(1, manutencao.getCusto());
            stmt.setLong(2, manutencao.getId());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean alterarDescricaoServico(Manutencao manutencao){
        String sql = "UPDATE manutencao SET descricao_servico = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, manutencao.getDescricaoServico());
            stmt.setLong(2, manutencao.getId());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean deletarManutencao(Manutencao manutencao){
        String sql = "DELETE FROM manutencao WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, manutencao.getId());

            if (stmt.executeUpdate() > 0) return true;

            return false;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }
    public static Manutencao getManutencao(long id){
        String sql = "SELECT * FROM manutencao WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            long idManutencao = rs.getLong("id");
            Date dt_cadastro = rs.getDate("dt_cadastro");
            Date dt_conclusao = rs.getDate("dt_conclusao");
            String tipo_manutencao = rs.getString("tipo_manutencao");
            BigDecimal custo = rs.getBigDecimal("custo");
            long ultimo_motorista = rs.getLong("ultimo_motorista");
            String descricao_servico = rs.getString("descricao_servico");
            long id_caminhao = rs.getLong("id_caminhao");

            return new Manutencao(idManutencao, dt_cadastro, dt_conclusao, tipo_manutencao,
                    custo, ultimo_motorista, descricao_servico, id_caminhao);
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }
}
