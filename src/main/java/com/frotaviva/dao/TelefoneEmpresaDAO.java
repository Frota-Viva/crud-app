package com.frotaviva.dao;

import com.frotaviva.util.Conexao;
import com.frotaviva.model.TelefoneEmpresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelefoneEmpresaDAO extends AbstractDAO implements DAO<TelefoneEmpresa>{

    /*
     * A classe TelefoneEmpresaDAO conta com os métodos:
     * inserir
     * atualizarTelefoneEmpresa
     * deletar
     * buscarPorId|IdEmpresa
     * buscarTodos
     */

    public int inserir(TelefoneEmpresa telefoneEmpresa) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "INSERT INTO telefone_empresa(telefone_empresa, id_empresa) VALUES(?, ?)";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, telefoneEmpresa.getTelefoneEmpresa());
            stmt.setLong(2, telefoneEmpresa.getIdEmpresa());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao cadastrar telefone da empresa.", e);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int atualizarTelefoneEmpresa(TelefoneEmpresa telefoneEmpresa) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "UPDATE telefone_empresa SET telefone_empresa = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, telefoneEmpresa.getTelefoneEmpresa());
            stmt.setLong(2, telefoneEmpresa.getId());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar telefone da empresa.", e);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int deletar(long id) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "DELETE FROM telefone_empresa WHERE id = ?";

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
            log.error("Erro ao deletar telefone da empresa.", e);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public TelefoneEmpresa buscarPorId(long id) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "SELECT * FROM telefone_empresa WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long idTelefoneEmpresa = rs.getLong("id");
                String telefoneEmpresa = rs.getString("telefone_empresa");
                long idEmpresa = rs.getLong("id_empresa");

                return new TelefoneEmpresa(idTelefoneEmpresa, telefoneEmpresa, idEmpresa);
            }
            return null;

        } catch (SQLException e) {
            log.error("Erro ao resgatar telefone da empresa.", e);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
    public List<TelefoneEmpresa> buscarPorIdEmpresa(long id_empresa) {
        List<TelefoneEmpresa> telefones = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "SELECT * FROM telefone_empresa WHERE id_empresa = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id_empresa);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                long idTelefoneEmpresa = rs.getLong("id");
                String telefoneEmpresa = rs.getString("telefone_empresa");
                long idEmpresa = rs.getLong("id_empresa");

                TelefoneEmpresa telefone = new TelefoneEmpresa(idTelefoneEmpresa, telefoneEmpresa, idEmpresa);
                telefones.add(telefone);
            }
            return telefones;

        } catch (SQLException e) {
            log.error("Erro ao resgatar os telefones da empresa.", e);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
    public List<TelefoneEmpresa> buscarTodos() {
        List<TelefoneEmpresa> telefones = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "SELECT * FROM";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                long idTelefoneEmpresa = rs.getLong("id");
                String telefoneEmpresa = rs.getString("telefone_empresa");
                long idEmpresa = rs.getLong("id_empresa");

                TelefoneEmpresa telefone = new TelefoneEmpresa(idTelefoneEmpresa, telefoneEmpresa, idEmpresa);
                telefones.add(telefone);
            }
            return telefones;

        } catch (SQLException e) {
            log.error("Erro ao resgatar os telefones.", e);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
}
