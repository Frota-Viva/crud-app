package com.frotaviva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frotaviva.model.Motorista;
import com.frotaviva.util.Conexao;
import com.frotaviva.util.Senhas;




public class MotoristaDAO extends AbstractDAO implements DAO<Motorista>{

    /*
     * A classe MotoristaDAO conta com os métodos:
     * inserir
     * atualizarNome|Email|Senha
     * deletar
     * buscarPorId
     * buscarTodos
     */

    public int inserir(Motorista motorista) {
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
                return 1;
            }
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao cadastrar motorista.", e);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int atualizarNome(Motorista motorista) {
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
                return 1;
            }
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar nome do motorista.", e);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int atualizarEmail(Motorista motorista) {
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
                return 1;
            }
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar email do motorista.", e);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int atualizarSenha(Motorista motorista) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "UPDATE motorista SET senha = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            String senhaArmazenada = Senhas.getSenhaHash(motorista.getId());
            if (Senhas.verificarSenha(motorista.getSenha(), senhaArmazenada)) return 0;

            stmt.setString(1, Senhas.hashSenha(motorista.getSenha()));
            stmt.setLong(2, motorista.getId());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar senha do motorista.", e);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int deletar(long id) {
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "DELETE FROM motorista WHERE id = ?";

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
            log.error("Erro ao deletar motorista.", e);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public Motorista buscarPorId(long id) {
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
    public List<Motorista> buscarTodos() {
        List<Motorista> motoristas = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = null;
        String sql = "SELECT * FROM motorista";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                long idMotorista = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String cpf = rs.getString("cpf");
                String senha = rs.getString("senha");
                long idEmpresa = rs.getLong("id_empresa");

                Motorista motorista = new Motorista(idMotorista, nome, email, cpf, senha, idEmpresa);
                motoristas.add(motorista);
            }
            return motoristas;

        } catch (SQLException e) {
            log.error("Erro ao resgatar motorista.", e);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
}
