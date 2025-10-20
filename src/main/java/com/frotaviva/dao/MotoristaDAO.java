package com.frotaviva.dao;

import com.frotaviva.model.Motorista;
import com.frotaviva.util.Conexao;
import com.frotaviva.util.Senhas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.frotaviva.dao.AbstractDAO.Operacao.*;

public class MotoristaDAO extends AbstractDAO implements DAO<Motorista> {

    public int inserir(Motorista motorista) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO motorista(nome, email, cpf, senha, id_empresa) VALUES(?, ?, ?, ?, ?)";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getEmail());
            stmt.setString(3, motorista.getCpf());
            stmt.setString(4, Senhas.hashSenha(motorista.getSenha()));
            stmt.setLong(5, motorista.getIdEmpresa());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao inserir motorista", e);
            throw throwDAOException(e, INSERT);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int atualizarNome(Motorista motorista) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Conexao conexao = new Conexao();
        String sql = "UPDATE motorista SET nome = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, motorista.getNome());
            stmt.setLong(2, motorista.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar nome do motorista", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int atualizarEmail(Motorista motorista) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Conexao conexao = new Conexao();
        String sql = "UPDATE motorista SET email = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, motorista.getEmail());
            stmt.setLong(2, motorista.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar email do motorista", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int atualizarSenha(Motorista motorista) {
        PreparedStatement stmt = null;
        Connection conn = null;
        Conexao conexao = new Conexao();
        String sql = "UPDATE motorista SET senha = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            String senhaArmazenada = Senhas.getSenhaHash(motorista.getId());
            if (Senhas.verificarSenha(motorista.getSenha(), senhaArmazenada)) return 0;

            stmt.setString(1, Senhas.hashSenha(motorista.getSenha()));
            stmt.setLong(2, motorista.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar senha do motorista", e);
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
        String sql = "DELETE FROM motorista WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao deletar motorista", e);
            throw throwDAOException(e, DELETE);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public Motorista buscarPorId(long id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM motorista WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return new Motorista(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("senha"),
                        rs.getLong("id_empresa")
                );
            }
            return null;

        } catch (SQLException e) {
            log.error("Erro ao buscar motorista por ID", e);
            throw throwDAOException(e, SELECT);
        } finally {
            fechar(stmt, rs);
            conexao.desconectar(conn);
        }
    }

    public List<Motorista> buscarTodos() {
        List<Motorista> motoristas = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM motorista";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                motoristas.add(new Motorista(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cpf"),
                        rs.getString("senha"),
                        rs.getLong("id_empresa")
                ));
            }
            return motoristas;

        } catch (SQLException e) {
            log.error("Erro ao buscar todos os motoristas", e);
            throw throwDAOException(e, SELECT);
        } finally {
            fechar(stmt, rs);
            conexao.desconectar(conn);
        }
    }
}
