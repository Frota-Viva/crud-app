package com.frotaviva.dao;

import com.frotaviva.model.Manutencao;
import com.frotaviva.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.frotaviva.dao.AbstractDAO.Operacao.*;

public class ManutencaoDAO extends AbstractDAO implements DAO<Manutencao> {

    public int inserir(Manutencao manutencao) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Conexao conexao = new Conexao();

        String sql = "INSERT INTO manutencao(dt_cadastro, dt_conclusao, tipo_manutencao, custo, " +
                "ultimo_motorista, descricao_servico, id_caminhao) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

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

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao inserir manutenção", e);
            throw throwDAOException(e, INSERT);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int atualizar(Manutencao manutencao) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Conexao conexao = new Conexao();

        String sql = "UPDATE manutencao SET custo = ?, dt_conclusao = ?, dt_cadastro = ?, tipo_manutencao = ?, id_caminhao = ?, descricao_servico = ?, ultimo_motorista = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setBigDecimal(1, manutencao.getCusto());
            stmt.setLong(2, manutencao.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar custo da manutenção", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int atualizarCusto(Manutencao manutencao) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Conexao conexao = new Conexao();

        String sql = "UPDATE manutencao SET custo = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setBigDecimal(1, manutencao.getCusto());
            stmt.setLong(2, manutencao.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar custo da manutenção", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int atualizarDescricaoServico(Manutencao manutencao) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Conexao conexao = new Conexao();

        String sql = "UPDATE manutencao SET descricao_servico = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, manutencao.getDescricaoServico());
            stmt.setLong(2, manutencao.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar descrição do serviço da manutenção", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int atualizarDtConclusao(Manutencao manutencao) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Conexao conexao = new Conexao();

        String sql = "UPDATE manutencao SET dt_conclusao = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setDate(1, manutencao.getDtConclusao());
            stmt.setLong(2, manutencao.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar data de conclusão da manutenção", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int deletar(long id) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Conexao conexao = new Conexao();

        String sql = "DELETE FROM manutencao WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao deletar manutenção", e);
            throw throwDAOException(e, DELETE);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public Manutencao buscarPorId(long id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        Conexao conexao = new Conexao();

        String sql = "SELECT * FROM manutencao WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Manutencao(
                        rs.getLong("id"),
                        rs.getDate("dt_cadastro"),
                        rs.getDate("dt_conclusao"),
                        rs.getString("tipo_manutencao"),
                        rs.getBigDecimal("custo"),
                        rs.getLong("ultimo_motorista"),
                        rs.getString("descricao_servico"),
                        rs.getLong("id_caminhao")
                );
            }
            return null;

        } catch (SQLException e) {
            log.error("Erro ao buscar manutenção por ID", e);
            throw throwDAOException(e, SELECT);
        } finally {
            fechar(stmt, rs);
            conexao.desconectar(conn);
        }
    }

    public List<Manutencao> buscarTodos() {
        List<Manutencao> manutencoes = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        Conexao conexao = new Conexao();

        String sql = "SELECT * FROM manutencao";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                manutencoes.add(new Manutencao(
                        rs.getLong("id"),
                        rs.getDate("dt_cadastro"),
                        rs.getDate("dt_conclusao"),
                        rs.getString("tipo_manutencao"),
                        rs.getBigDecimal("custo"),
                        rs.getLong("ultimo_motorista"),
                        rs.getString("descricao_servico"),
                        rs.getLong("id_caminhao")
                ));
            }
            return manutencoes;

        } catch (SQLException e) {
            log.error("Erro ao buscar todas as manutenções", e);
            throw throwDAOException(e, SELECT);
        } finally {
            fechar(stmt, rs);
            conexao.desconectar(conn);
        }
    }
}
