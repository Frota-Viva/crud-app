package com.frotaviva.dao;

import com.frotaviva.model.Frota;
import com.frotaviva.util.Conexao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FrotaDAO {

    /*
     * Logger utilizado para rastreamento e diagnóstico de exceções.
     */
    private static final Logger log = LoggerFactory.getLogger(FrotaDAO.class);

    /*
     * Métodos da classe FrotaDAO:
     * cadastrarFrota
     * atualizarTamanhoFrota|TipoFrota|Regiao
     * deletarFrota
     * getFrota
     */

    public static boolean cadastrarFrota(Frota frota) {
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
                return true;
            }

            return false;

        } catch (SQLException sqle) {
            log.error("Erro ao cadastrar frota", sqle);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public static boolean atualizarTamanhoFrota(Frota frota) {
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
                return true;
            }

            return false;
        } catch (SQLException sqle) {
            log.error("Erro ao atualizar tamanho da frota", sqle);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public static boolean atualizarTipoFrota(Frota frota) {
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
                return true;
            }

            return false;
        } catch (SQLException sqle) {
            log.error("Erro ao atualizar tipo da frota", sqle);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public static boolean atualizarRegiao(Frota frota) {
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
                return true;
            }

            return false;
        } catch (SQLException sqle) {
            log.error("Erro ao atualizar região da frota", sqle);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public static boolean deletarFrota(Frota frota) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "DELETE FROM frota WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, frota.getId());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return true;
            }

            return false;

        } catch (SQLException sqle) {
            log.error("Erro ao deletar frota", sqle);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public static Frota getFrota(long id) {
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
}
