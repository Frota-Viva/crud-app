package com.frotaviva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frotaviva.exception.*;
import com.frotaviva.model.Frota;
import com.frotaviva.util.Conexao;

import static com.frotaviva.dao.AbstractDAO.Operacao.*;

public class FrotaDAO extends AbstractDAO implements DAO<Frota>{

    /*
     * Métodos da classe FrotaDAO:
     * inserir
     * atualizarTamanhoFrota|TipoFrota|Regiao
     * deletar
     * buscarPorId|Todos
     */

    public int inserir(Frota frota) {
        PreparedStatement stmt = null;
        Conexao conexao = new Conexao();
        Connection con = null;

        String sql = "INSERT INTO frota(tamanho_frota, tipo_frota, regiao, id_empresa) VALUES(?, ?, ?, ?)";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, frota.getTamanhoFrota());
            stmt.setString(2, frota.getTipoFrota());
            stmt.setString(3, frota.getRegiao());
            stmt.setLong(4, frota.getIdEmpresa());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao cadastrar frota", e);
            throw throwDAOException(e, INSERT);
        } finally {
            fechar(stmt);
            conexao.desconectar(con);
        }
    }

    public int atualizar(Frota frota) {
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection con = null;

        String sql = "UPDATE frota SET tamanho_frota = ?, tipo_frota = ?, regiao = ?, id_empresa = ? WHERE id = ?";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, frota.getTamanhoFrota());
            stmt.setLong(2, frota.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar tamanho da frota", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(con);
        }
    }

    public int atualizarTamanhoFrota(Frota frota) {
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection con = null;

        String sql = "UPDATE frota SET tamanho_frota = ? WHERE id = ?";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);

            stmt.setInt(1, frota.getTamanhoFrota());
            stmt.setLong(2, frota.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar tamanho da frota", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(con);
        }
    }

    public int atualizarTipoFrota(Frota frota) {
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "UPDATE frota SET tipo_frota = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, frota.getTipoFrota());
            stmt.setLong(2, frota.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar tipo da frota", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int atualizarRegiao(Frota frota) {
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "UPDATE frota SET regiao = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, frota.getRegiao());
            stmt.setLong(2, frota.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar região da frota", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int deletar(long id) {
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection con = null;

        String sql = "DELETE FROM frota WHERE id = ?";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao deletar frota", e);
            throw throwDAOException(e, DELETE);
        } finally {
            fechar(stmt);
            conexao.desconectar(con);
        }
    }

    public Frota buscarPorId(long id) {
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        String sql = "SELECT * FROM frota WHERE id = ?";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);

            stmt.setLong(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {
                long idFrota = rs.getLong("id");
                int tamanhoFrota = rs.getInt("tamanho_frota");
                String tipoFrota = rs.getString("tipo_frota");
                String regiao = rs.getString("regiao");
                long idEmpresa = rs.getLong("id_empresa");

                return new Frota(idFrota, tamanhoFrota, tipoFrota, regiao, idEmpresa);
            }

            return null;

        } catch (SQLException e) {
            log.error("Erro ao buscar frota", e);
            throw throwDAOException(e, SELECT);
        } finally {
            fechar(stmt, rs);
            conexao.desconectar(con);
        }
    }

    public List<Frota> buscarPorEmpresa(long id_empresa) {
        List<Frota> frotas = new ArrayList<>();
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        String sql = "SELECT * FROM frota WHERE id_empresa = ?";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);

            stmt.setLong(1, id_empresa);

            rs = stmt.executeQuery();

            while (rs.next()) {
                long idFrota = rs.getLong("id");
                int tamanhoFrota = rs.getInt("tamanho_frota");
                String tipoFrota = rs.getString("tipo_frota");
                String regiao = rs.getString("regiao");
                long idEmpresa = rs.getLong("id_empresa");

                Frota frota = new Frota(idFrota, tamanhoFrota, tipoFrota, regiao, idEmpresa);
                frotas.add(frota);
            }

            return frotas;

        } catch (SQLException e) {
            log.error("Erro ao buscar frotsa", e);
            throw throwDAOException(e, SELECT);
        } finally {
            fechar(stmt, rs);
            conexao.desconectar(con);
        }
    }
    public List<Frota> buscarPorEmpresaComTipoFrota(long id_empresa, String buscar) {
        List<Frota> frotas = new ArrayList<>();
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        String sql = "SELECT * FROM frota WHERE id_empresa = ? AND tipo_frota ILIKE ?";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);

            stmt.setLong(1, id_empresa);
            stmt.setString(2, "%" + buscar + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                long idFrota = rs.getLong("id");
                int tamanhoFrota = rs.getInt("tamanho_frota");
                String tipoFrota = rs.getString("tipo_frota");
                String regiao = rs.getString("regiao");
                long idEmpresa = rs.getLong("id_empresa");

                Frota frota = new Frota(idFrota, tamanhoFrota, tipoFrota, regiao, idEmpresa);
                frotas.add(frota);
            }

            return frotas;

        } catch (SQLException e) {
            log.error("Erro ao buscar frotsa", e);
            throw throwDAOException(e, SELECT);
        } finally {
            fechar(stmt, rs);
            conexao.desconectar(con);
        }
    }

    public List<Frota> buscarTodos() {
        List<Frota> frotas = new ArrayList<>();
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        String sql = "SELECT * FROM frota";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                long idFrota = rs.getLong("id");
                int tamanhoFrota = rs.getInt("tamanho_frota");
                String tipoFrota = rs.getString("tipo_frota");
                String regiao = rs.getString("regiao");
                long idEmpresa = rs.getLong("id_empresa");

                Frota frota = new Frota(idFrota, tamanhoFrota, tipoFrota, regiao, idEmpresa);
                frotas.add(frota);
            }

            return frotas;

        } catch (SQLException e) {
            log.error("Erro ao buscar frota", e);
            throw throwDAOException(e, SELECT);
        } finally {
            fechar(stmt, rs);
            conexao.desconectar(con);
        }
    }
}