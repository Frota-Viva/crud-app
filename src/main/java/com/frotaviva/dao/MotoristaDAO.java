package com.frotaviva.dao;

import com.frotaviva.util.Conexao;
import com.frotaviva.util.Senhas;
import com.frotaviva.model.Motorista;
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
 * A classe MotoristaDAO conta com os métodos:
 * cadastrarMotorista
 * atualizarNome|Email|Senha
 * deletarMotorista
 * getMotorista
 */

public class MotoristaDAO {

    private static final Logger log = LoggerFactory.getLogger(MotoristaDAO.class);

    public static boolean cadastrarMotorista(Motorista motorista) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "INSERT INTO motorista(nome, email, cpf, senha, id_empresa) VALUES(?, ?, ?, ?, ?)";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getEmail());
            stmt.setString(3, motorista.getCpf());
            stmt.setString(4, Senhas.hashSenha(motorista.getSenha()));
            stmt.setLong(5, motorista.getIdEmpresa());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return true;
            }
            return false;

        } catch (SQLException e) {
            log.error("Erro ao cadastrar motorista.", e);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public static boolean atualizarNome(Motorista motorista) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "UPDATE motorista SET nome = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, motorista.getNome());
            stmt.setLong(2, motorista.getId());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return true;
            }
            return false;

        } catch (SQLException e) {
            log.error("Erro ao atualizar nome do motorista.", e);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public static boolean atualizarEmail(Motorista motorista) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "UPDATE motorista SET email = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, motorista.getEmail());
            stmt.setLong(2, motorista.getId());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return true;
            }
            return false;

        } catch (SQLException e) {
            log.error("Erro ao atualizar email do motorista.", e);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public static boolean atualizarSenha(Motorista motorista) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "UPDATE motorista SET senha = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            String senhaArmazenada = Senhas.getSenhaHash(motorista.getId());
            if (Senhas.verificarSenha(motorista.getSenha(), senhaArmazenada)) return false;

            stmt.setString(1, Senhas.hashSenha(motorista.getSenha()));
            stmt.setLong(2, motorista.getId());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return true;
            }
            return false;

        } catch (SQLException e) {
            log.error("Erro ao atualizar senha do motorista.", e);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public static boolean deletarMotorista(Motorista motorista) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "DELETE FROM motorista WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, motorista.getId());

            if (stmt.executeUpdate() > 0) return true;
            return false;

        } catch (SQLException e) {
            log.error("Erro ao deletar motorista.", e);
            return false;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public static Motorista getMotorista(long id) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "SELECT * FROM motorista WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                long idMotorista = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");
                long idEmpresa = rs.getLong("id_empresa");

                return new Motorista(idMotorista, nome, email, cpf, senha, idEmpresa);
            }
            return null;

        } catch (SQLException e) {
            log.error("Erro ao resgatar motorista.", e);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
}
