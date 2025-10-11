package com.frotaviva.dao;

import com.frotaviva.util.Conexao;
import com.frotaviva.model.TelefoneMotorista;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Logger está sendo usado para melhor tratamento e rastreamento de exceções.
 * Ele registra mensagens de erro personalizadas com diagnóstico completo.
 */

/*
 * A classe TelefoneMotoristaDAO conta com os métodos:
 * cadastrarTelefoneMotorista
 * atualizarTelefoneMotorista
 * deletarTelefoneMotorista
 * getTelefoneMotorista
 */

public class TelefoneMotoristaDAO {

    private static final Logger log = LoggerFactory.getLogger(TelefoneMotoristaDAO.class);

    public static boolean cadastrarTelefoneMotorista(TelefoneMotorista telefoneMotorista) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        
        String sql = "INSERT INTO telefone_motorista(telefone_motorista, id_motorista) VALUES(?, ?)";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, telefoneMotorista.getTelefoneMotorista());
            stmt.setLong(2, telefoneMotorista.getIdMotorista());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return true;
            }
            return false;

        } catch (SQLException e) {
            log.error("Erro ao cadastrar telefone do motorista.", e);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public static boolean atualizarTelefoneMotorista(TelefoneMotorista telefoneMotorista) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "UPDATE telefone_motorista SET telefone_motorista = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, telefoneMotorista.getTelefoneMotorista());
            stmt.setLong(2, telefoneMotorista.getId());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return true;
            }
            return false;

        } catch (SQLException e) {
            log.error("Erro ao atualizar telefone do motorista.", e);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public static boolean deletarTelefoneMotorista(TelefoneMotorista telefoneMotorista) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "DELETE FROM telefone_motorista WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, telefoneMotorista.getId());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return true;
            }
            return false;

        } catch (SQLException e) {
            log.error("Erro ao deletar telefone do motorista.", e);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public static TelefoneMotorista getTelefoneMotorista(long id) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "SELECT * FROM telefone_motorista WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long idTelefoneMotorista = rs.getLong("id");
                String telefoneMotorista = rs.getString("telefone_motorista");
                long idMotorista = rs.getLong("id_motorista");

                return new TelefoneMotorista(idTelefoneMotorista, telefoneMotorista, idMotorista);
            }
            return null;

        } catch (SQLException e) {
            log.error("Erro ao resgatar telefone do motorista.", e);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
}
