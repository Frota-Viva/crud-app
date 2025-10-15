package com.frotaviva.dao;

import com.frotaviva.util.Conexao;
import com.frotaviva.model.Informacoes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InformacoesDAO implements DAO<Informacoes>{

    /*
     * Logger está sendo usado para tratamento e rastreamento de exceções.
     * Ele permite registrar mensagens de erro detalhadas com diagnóstico completo.
     */

    private static final Logger log = LoggerFactory.getLogger(InformacoesDAO.class);

    /*
     * A classe InformacoesDAO conta com os métodos:
     * inserir
     * atualizarInformacoes
     * deletar
     * buscarPorId
     * buscarTodos
     */

    public int inserir(Informacoes informacoes) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "INSERT INTO informacoes(temp_ar_admissao, posicao_acelerador, tempo_motor_ligado, " +
                "carga_motor, rpm_motor, dt_hora_leitura, pressao_pneu, sensores_oxigenio, qtd_combustivel, " +
                "temp_arrefecimento, pressao_coletor_admissao, id_caminhao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

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

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }
            return 0;

        } catch (SQLException sqle) {
            log.error("Erro ao cadastrar informações do caminhão.", sqle);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }


    public int atualizarInformacoes(Informacoes informacoes) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "UPDATE informacoes SET temp_ar_admissao = ?, posicao_acelerador = ?, tempo_motor_ligado = ?, " +
                "carga_motor = ?, rpm_motor = ?, dt_hora_leitura = ?, pressao_pneu = ?, sensores_oxigenio = ?, qtd_combustivel = ?, " +
                "temp_arrefecimento = ?, pressao_coletor_admissao = ?, id_caminhao = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

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

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }
            return 0;

        } catch (SQLException sqle) {
            log.error("Erro ao atualizar informações do caminhão.", sqle);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }


    public int deletar(long id) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "DELETE FROM informacoes WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }
            return 0;

        } catch (SQLException sqle) {
            log.error("Erro ao deletar informações do caminhão.", sqle);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }


    public Informacoes buscarPorId(long id) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "SELECT * FROM informacoes WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long idInformacoes = rs.getLong("id");
                BigDecimal temp_ar_admissao = rs.getBigDecimal("temp_ar_admissao");
                BigDecimal posicao_acelerador = rs.getBigDecimal("posicao_acelerador");
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

                stmt.close();

                return new Informacoes(
                    idInformacoes,
                    temp_ar_admissao,
                    posicao_acelerador,
                    tempo_motor_ligado,
                    carga_motor,
                    rpm_motor,
                    dt_hora_leitura,
                    pressao_pneu,
                    sensores_oxigenio,
                    qtd_combustivel,
                    temp_arrefecimento,
                    pressao_coletor_admissao,
                    id_caminhao
                );
            }
            return null;

        } catch (SQLException sqle) {
            log.error("Erro ao buscar informações do caminhão.", sqle);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
    public List<Informacoes> buscarTodos() {
        List<Informacoes> informacoesList = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "SELECT * FROM informacoes";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                long idInformacoes = rs.getLong("id");
                BigDecimal temp_ar_admissao = rs.getBigDecimal("temp_ar_admissao");
                BigDecimal posicao_acelerador = rs.getBigDecimal("posicao_acelerador");
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

                stmt.close();

                Informacoes informacoes = new Informacoes(
                        idInformacoes,
                        temp_ar_admissao,
                        posicao_acelerador,
                        tempo_motor_ligado,
                        carga_motor,
                        rpm_motor,
                        dt_hora_leitura,
                        pressao_pneu,
                        sensores_oxigenio,
                        qtd_combustivel,
                        temp_arrefecimento,
                        pressao_coletor_admissao,
                        id_caminhao
                );
                informacoesList.add(informacoes);
            }
            return informacoesList;

        } catch (SQLException sqle) {
            log.error("Erro ao buscar informações do caminhão.", sqle);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
}
