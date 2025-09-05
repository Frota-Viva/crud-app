package com.frotaviva.dao;

import com.frotaviva.Conexao;
import com.frotaviva.model.Informacoes;

import java.math.BigDecimal;
import java.sql.*;

public class InformacoesDAO {
    public static boolean cadastrarInformacoes(Informacoes informacoes){
        String sql = "INSERT INTO informacoes(temp_ar_admissao, posicao_acelerador, tempo_motor_ligado, " +
                "carga_motor, rpm_motor, dt_hora_leitura, pressao_pneu, sensores_oxigenio, qtd_combustivel, " +
                "temp_arrefecimento, pressao_coletor_admissao, id_caminhao)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setBigDecimal(1, informacoes.getTempturaArAdmissao());
            stmt.setBigDecimal(2, informacoes.getPosicaoAcelerador());
            stmt.setTime(3, informacoes.getTempoMotorLigado());
            stmt.setBigDecimal(4, informacoes.getCargaMotor());
            stmt.setBigDecimal(5, informacoes.getRpmMotor());
            stmt.setTimestamp(6, informacoes.getDataHoraLeitura());
            stmt.setBigDecimal(7, informacoes.getPressaoPneu());
            stmt.setBigDecimal(8, informacoes.getSensoresOxigenio());
            stmt.setBigDecimal(9, informacoes.getQtdCombustivel());
            stmt.setBigDecimal(10, informacoes.getTempArrefecimento());
            stmt.setBigDecimal(11, informacoes.getPressaoColetorAdmissao());
            stmt.setLong(12, informacoes.getIdCaminhao());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean alterarInformacoes(Informacoes informacoes){
        String sql = "UPDATE informacoes SET temp_ar_admissao = ?, posicao_acelerador = ?, tempo_motor_ligado = ?, " +
                "carga_motor = ?, rpm_motor = ?, dt_hora_leitura = ?, pressao_pneu = ?, sensores_oxigenio = ?, qtd_combustivel = ?, " +
                "temp_arrefecimento = ?, pressao_coletor_admissao = ?, id_caminhao = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setBigDecimal(1, informacoes.getTempturaArAdmissao());
            stmt.setBigDecimal(2, informacoes.getPosicaoAcelerador());
            stmt.setTime(3, informacoes.getTempoMotorLigado());
            stmt.setBigDecimal(4, informacoes.getCargaMotor());
            stmt.setBigDecimal(5, informacoes.getRpmMotor());
            stmt.setTimestamp(6, informacoes.getDataHoraLeitura());
            stmt.setBigDecimal(7, informacoes.getPressaoPneu());
            stmt.setBigDecimal(8, informacoes.getSensoresOxigenio());
            stmt.setBigDecimal(9, informacoes.getQtdCombustivel());
            stmt.setBigDecimal(10, informacoes.getTempArrefecimento());
            stmt.setBigDecimal(11, informacoes.getPressaoColetorAdmissao());
            stmt.setLong(12, informacoes.getIdCaminhao());
            stmt.setLong(13, informacoes.getId());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean deletarInformacoes(Informacoes informacoes){
        String sql = "DELETE FROM informacoes WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, informacoes.getId());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static Informacoes getInformacoes(long id){
        String sql = "SELECT * FROM informacoes WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            long idInformacoes = rs.getLong("id");
            BigDecimal temp_ar_admissao = rs.getBigDecimal("temp_ar_admissao");
            BigDecimal posicao_acelerador = rs.getBigDecimal("poicao_acelerador");
            Time tempo_motor_ligado = rs.getTime("tempo_motor_ligado");
            BigDecimal carga_motor = rs.getBigDecimal("carga_motor");
            BigDecimal rpm_motor = rs.getBigDecimal("rpm_motor");
            Timestamp dt_hora_leitura = rs.getTimestamp("dt_hora_leitura");
            BigDecimal pressao_pneu = rs.getBigDecimal("pressao_pneu");
            BigDecimal sensores_oxigenio = rs.getBigDecimal("sensores_oxigenio");
            BigDecimal qtd_combustivel = rs.getBigDecimal("qtd_combustivel");
            BigDecimal temp_arrefecimento = rs.getBigDecimal("temp_arrefecimento");
            BigDecimal pressao_coletor_admissao = rs.getBigDecimal("pressao_coletor_admissao");
            long id_caminhao = rs.getLong("id_caminhao");

            return new Informacoes(idInformacoes, temp_ar_admissao, posicao_acelerador, tempo_motor_ligado,
                    carga_motor, rpm_motor, dt_hora_leitura, pressao_pneu, sensores_oxigenio,
                    qtd_combustivel, temp_arrefecimento, pressao_coletor_admissao, id_caminhao);

        } catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }
}
