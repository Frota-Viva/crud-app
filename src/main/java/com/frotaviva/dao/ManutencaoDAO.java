package com.frotaviva.dao;

import com.frotaviva.util.Conexao;
import com.frotaviva.model.Manutencao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManutencaoDAO extends AbstractDAO implements DAO<Manutencao>{

    /*
    * Logger está sendo usado para melhor tratamento e rastreamento de exceções.
    * Ele registra mensagens de erro personalizadas com diagnóstico completo.
    */

    public int inserir(Manutencao manutencao) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "INSERT INTO manutencao(dt_cadastro, dt_conclusao, tipo_manutencao, custo, ultimo_motorista, " +
                "descricao_servico, id_caminhao) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDate(1, manutencao.getDtCadastro());

            if (manutencao.getDtConclusao() != null) {
                stmt.setDate(2, manutencao.getDtConclusao());
            } else {
                stmt.setNull(2, Types.DATE);
            }

            stmt.setString(3, manutencao.getTipoManutencao());
            stmt.setBigDecimal(4, manutencao.getCusto());
            stmt.setLong(5, manutencao.getUltimoMotorista());
            stmt.setString(6, manutencao.getDescricaoServico());
            stmt.setLong(7, manutencao.getIdCaminhao());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao cadastrar manutenção.", e);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int inserirDtConclusao(Manutencao manutencao) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "UPDATE manutencao SET dt_conclusao = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDate(1, manutencao.getDtConclusao());
            stmt.setLong(2, manutencao.getId());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao inserir data de conclusão da manutenção.", e);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int alterarCusto(Manutencao manutencao) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "UPDATE manutencao SET custo = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setBigDecimal(1, manutencao.getCusto());
            stmt.setLong(2, manutencao.getId());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao alterar custo da manutenção.", e);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int alterarDescricaoServico(Manutencao manutencao) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "UPDATE manutencao SET descricao_servico = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, manutencao.getDescricaoServico());
            stmt.setLong(2, manutencao.getId());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao alterar descrição do serviço de manutenção.", e);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int deletar(long id) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "DELETE FROM manutencao WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao deletar manutenção.", e);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public Manutencao buscarPorId(long id) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "SELECT * FROM manutencao WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long idManutencao = rs.getLong("id");
                Date dt_cadastro = rs.getDate("dt_cadastro");
                Date dt_conclusao = rs.getDate("dt_conclusao");
                String tipo_manutencao = rs.getString("tipo_manutencao");
                BigDecimal custo = rs.getBigDecimal("custo");
                long ultimo_motorista = rs.getLong("ultimo_motorista");
                String descricao_servico = rs.getString("descricao_servico");
                long id_caminhao = rs.getLong("id_caminhao");

                return new Manutencao(
                        idManutencao,
                        dt_cadastro,
                        dt_conclusao,
                        tipo_manutencao,
                        custo,
                        ultimo_motorista,
                        descricao_servico,
                        id_caminhao
                );
            }
            return null;

        } catch (SQLException e) {
            log.error("Erro ao buscar manutenção pelo ID.", e);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
    public List<Manutencao> buscarTodos() {
        List<Manutencao> manutencoes = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "SELECT * FROM manutencao";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                long idManutencao = rs.getLong("id");
                Date dt_cadastro = rs.getDate("dt_cadastro");
                Date dt_conclusao = rs.getDate("dt_conclusao");
                String tipo_manutencao = rs.getString("tipo_manutencao");
                BigDecimal custo = rs.getBigDecimal("custo");
                long ultimo_motorista = rs.getLong("ultimo_motorista");
                String descricao_servico = rs.getString("descricao_servico");
                long id_caminhao = rs.getLong("id_caminhao");

                Manutencao manutencao = new Manutencao(
                        idManutencao,
                        dt_cadastro,
                        dt_conclusao,
                        tipo_manutencao,
                        custo,
                        ultimo_motorista,
                        descricao_servico,
                        id_caminhao
                );
                manutencoes.add(manutencao);
            }
            return manutencoes;

        } catch (SQLException e) {
            log.error("Erro ao buscar manutenção pelo ID.", e);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
}
