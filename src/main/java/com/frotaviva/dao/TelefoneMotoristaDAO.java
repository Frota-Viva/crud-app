package com.frotaviva.dao;

import com.frotaviva.model.TelefoneMotorista;
import com.frotaviva.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.frotaviva.dao.AbstractDAO.Operacao.*;

public class TelefoneMotoristaDAO extends AbstractDAO implements DAO<TelefoneMotorista> {

    /*
     * A classe TelefoneMotoristaDAO conta com os métodos:
     * inserir
     * atualizarTelefoneMotorista
     * deletar
     * buscarPorId
     * buscarPorIdMotorista
     * buscarTodos
     */

    public int inserir(TelefoneMotorista telefoneMotorista) {
        PreparedStatement stmt = null;
        Connection con = null;
        Conexao conexao = new Conexao();
        String sql = "INSERT INTO telefone_motorista(telefone_motorista, id_motorista) VALUES(?, ?)";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);

            stmt.setString(1, telefoneMotorista.getTelefoneMotorista());
            stmt.setLong(2, telefoneMotorista.getIdMotorista());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao inserir telefone do motorista", e);
            throw throwDAOException(e, INSERT);
        } finally {
            fechar(stmt);
            conexao.desconectar(con);
        }
    }

    public int atualizar(TelefoneMotorista telefoneMotorista) {
        PreparedStatement stmt = null;
        Connection con = null;
        Conexao conexao = new Conexao();

        String sql = "UPDATE telefone_motorista SET telefone_motorista = ?, id_motorista = ? WHERE id = ?";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);

            stmt.setString(1, telefoneMotorista.getTelefoneMotorista());
            stmt.setLong(2, telefoneMotorista.getIdMotorista());
            stmt.setLong(3, telefoneMotorista.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar telefone do motorista", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(con);
        }
    }

    public int atualizarTelefoneMotorista(TelefoneMotorista telefoneMotorista) {
        PreparedStatement stmt = null;
        Connection con = null;
        Conexao conexao = new Conexao();
        String sql = "UPDATE telefone_motorista SET telefone_motorista = ? WHERE id = ?";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);

            stmt.setString(1, telefoneMotorista.getTelefoneMotorista());
            stmt.setLong(2, telefoneMotorista.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar telefone do motorista", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(con);
        }
    }

    public int deletar(long id) {
        PreparedStatement stmt = null;
        Connection con = null;
        Conexao conexao = new Conexao();
        String sql = "DELETE FROM telefone_motorista WHERE id = ?";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao deletar telefone do motorista", e);
            throw throwDAOException(e, DELETE);
        } finally {
            fechar(stmt);
            conexao.desconectar(con);
        }
    }

    public TelefoneMotorista buscarPorId(long id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM telefone_motorista WHERE id = ?";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                return new TelefoneMotorista(
                        rs.getLong("id"),
                        rs.getString("telefone_motorista"),
                        rs.getLong("id_motorista")
                );
            }
            return null;

        } catch (SQLException e) {
            log.error("Erro ao buscar telefone do motorista por ID", e);
            throw throwDAOException(e, SELECT);
        } finally {
            fechar(stmt, rs);
            conexao.desconectar(con);
        }
    }

    public List<TelefoneMotorista> buscarPorIdMotorista(long idMotorista) {
        List<TelefoneMotorista> telefones = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM telefone_motorista WHERE id_motorista = ?";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, idMotorista);

            rs = stmt.executeQuery();

            while (rs.next()) {
                telefones.add(new TelefoneMotorista(
                        rs.getLong("id"),
                        rs.getString("telefone_motorista"),
                        rs.getLong("id_motorista")
                ));
            }
            return telefones;

        } catch (SQLException e) {
            log.error("Erro ao buscar telefones do motorista", e);
            throw throwDAOException(e, SELECT);
        } finally {
            fechar(stmt, rs);
            conexao.desconectar(con);
        }
    }

    public List<TelefoneMotorista> buscarTodos() {
        List<TelefoneMotorista> telefones = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        Conexao conexao = new Conexao();
        String sql = "SELECT * FROM telefone_motorista";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                telefones.add(new TelefoneMotorista(
                        rs.getLong("id"),
                        rs.getString("telefone_motorista"),
                        rs.getLong("id_motorista")
                ));
            }
            return telefones;

        } catch (SQLException e) {
            log.error("Erro ao buscar todos os telefones", e);
            throw throwDAOException(e, SELECT);
        } finally {
            fechar(stmt, rs);
            conexao.desconectar(con);
        }
    }
}
