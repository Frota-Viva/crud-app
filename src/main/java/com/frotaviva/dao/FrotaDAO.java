package com.frotaviva.dao;

import com.frotaviva.model.Frota;
import com.frotaviva.util.Conexao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrotaDAO implements DAO<Frota>{

    /*
     * Logger utilizado para rastreamento e diagnóstico de exceções.
     */
    private static final Logger log = LoggerFactory.getLogger(FrotaDAO.class);

    /*
     * Métodos da classe FrotaDAO:
     * inserir
     * atualizarTamanhoFrota|TipoFrota|Regiao
     * deletar
     * buscarPorId
     * buscarTodos
     */

    public int inserir(Frota frota) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "INSERT INTO frota(tamanho_frota, tipo_frota, regiao, id_empresa) VALUES(?, ?, ?, ?)";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, frota.getTamanhoFrota());
            stmt.setString(2, frota.getTipoFrota());
            stmt.setString(3, frota.getRegiao());
            stmt.setLong(4, frota.getIdEmpresa());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }

            return 0;

        } catch (SQLException sqle) {
            log.error("Erro ao cadastrar frota", sqle);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int atualizarTamanhoFrota(Frota frota) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "UPDATE frota SET tamanho_frota = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, frota.getTamanhoFrota());
            stmt.setLong(2, frota.getId());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }

            return 0;
        } catch (SQLException sqle) {
            log.error("Erro ao atualizar tamanho da frota", sqle);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int atualizarTipoFrota(Frota frota) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "UPDATE frota SET tipo_frota = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, frota.getTipoFrota());
            stmt.setLong(2, frota.getId());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }

            return 0;
        } catch (SQLException sqle) {
            log.error("Erro ao atualizar tipo da frota", sqle);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int atualizarRegiao(Frota frota) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "UPDATE frota SET regiao = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, frota.getRegiao());
            stmt.setLong(2, frota.getId());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }

            return 0;
        } catch (SQLException sqle) {
            log.error("Erro ao atualizar região da frota", sqle);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int deletar(long id) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "DELETE FROM frota WHERE id = ?";

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
            log.error("Erro ao deletar frota", sqle);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public Frota buscarPorId(long id) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "SELECT * FROM frota WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long idFrota = rs.getLong("id");
                int tamanhoFrota = rs.getInt("tamanho_frota");
                String tipoFrota = rs.getString("tipo_frota");
                String regiao = rs.getString("regiao");
                long idEmpresa = rs.getLong("id_empresa");

                stmt.close();

                return new Frota(idFrota, tamanhoFrota, tipoFrota, regiao, idEmpresa);
            }

            return null;

        } catch (SQLException sqle) {
            log.error("Erro ao buscar frota", sqle);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
    public List<Frota> buscarTodos() {
        List<Frota> frotas = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "SELECT * FROM frota";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long idFrota = rs.getLong("id");
                int tamanhoFrota = rs.getInt("tamanho_frota");
                String tipoFrota = rs.getString("tipo_frota");
                String regiao = rs.getString("regiao");
                long idEmpresa = rs.getLong("id_empresa");

                stmt.close();

                Frota frota = new Frota(idFrota, tamanhoFrota, tipoFrota, regiao, idEmpresa);
                frotas.add(frota);
            }

            return frotas;

        } catch (SQLException sqle) {
            log.error("Erro ao buscar frota", sqle);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
}
