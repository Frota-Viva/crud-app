package com.frotaviva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.frotaviva.exception.*;
import com.frotaviva.model.Endereco;
import com.frotaviva.model.Entrega;
import com.frotaviva.util.Conexao;

import static com.frotaviva.dao.AbstractDAO.Operacao.*;

public class EntregaDAO extends AbstractDAO implements DAO<Entrega>{

    /*
     * Métodos da classe EntregaDAO:
     * inserir
     * atualizarDescricao|Datas|Endereco
     * deletar
     * buscarPorId|Todos
     */

    public int inserir(Entrega entrega){
        PreparedStatement stmt = null;
        Conexao conexao = new Conexao();
        Connection con = null;

        String sql = "INSERT INTO entrega(descricao_produto, dt_pedido, dt_entrega, cep, rua, complemento, numero, " +
                "pais, estado, cidade, id_motorista) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);

            Endereco endereco = entrega.getEndereco();

            stmt.setString(1, entrega.getDescricaoProduto());
            stmt.setDate(2, entrega.getDtPedido());

            if (entrega.getDtEntrega() != null) {
                stmt.setDate(3, entrega.getDtEntrega());
            } else {
                stmt.setNull(3, Types.DATE);
            }

            stmt.setString(4, endereco.getCep());
            stmt.setString(5, endereco.getRua());

            if (endereco.getComplemento() != null) {
                stmt.setString(6, endereco.getComplemento());
            } else {
                stmt.setNull(6, Types.VARCHAR);
            }

            stmt.setInt(7, endereco.getNumero());
            stmt.setString(8, endereco.getPais());
            stmt.setString(9, endereco.getEstado());
            stmt.setString(10, endereco.getCidade());
            stmt.setLong(11, entrega.getIdMotorista());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao cadastrar entrega", e);
            throw throwDAOException(e, INSERT);
        } finally {
            fechar(stmt);
            conexao.desconectar(con);
        }
    }

    public int atualizarDescricao(Entrega entrega) {
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "UPDATE entrega SET descricao_produto = ? WHERE cod_entrega = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, entrega.getDescricaoProduto());
            stmt.setLong(2, entrega.getCod_entrega());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar descrição da entrega", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int atualizarDatas(Entrega entrega) {
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "UPDATE entrega SET dt_pedido = ?, dt_entrega = ? WHERE cod_entrega = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setDate(1, entrega.getDtPedido());

            if (entrega.getDtEntrega() != null) {
                stmt.setDate(2, entrega.getDtEntrega());
            } else {
                stmt.setNull(2, Types.DATE);
            }

            stmt.setLong(3, entrega.getCod_entrega());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar datas da entrega", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int atualizarEndereco(Entrega entrega) {
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "UPDATE entrega SET pais = ?, cep = ?, estado = ?, cidade = ?, rua = ?, numero = ?, " +
                "complemento = ? WHERE cod_entrega = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            Endereco endereco = entrega.getEndereco();

            stmt.setString(1, endereco.getPais());
            stmt.setString(2, endereco.getCep());
            stmt.setString(3, endereco.getEstado());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getRua());
            stmt.setInt(6, endereco.getNumero());

            if (endereco.getComplemento() != null) {
                stmt.setString(7, endereco.getComplemento());
            } else {
                stmt.setNull(7, Types.VARCHAR);
            }

            stmt.setLong(8, entrega.getCod_entrega());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao atualizar endereço da entrega", e);
            throw throwDAOException(e, UPDATE);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int deletar(long cod_entrega) {
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "DELETE FROM entrega WHERE cod_entrega = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, cod_entrega);

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e) {
            log.error("Erro ao deletar entrega", e);
            throw throwDAOException(e, DELETE);
        } finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public Entrega buscarPorId(long cod_entrega) {
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        String sql = "SELECT * FROM entrega WHERE cod_entrega = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, cod_entrega);

            rs = stmt.executeQuery();

            if (rs.next()) {
                long idEntrega = rs.getLong("cod_entrega");
                String descricaoProduto = rs.getString("descricao_produto");
                Date dtPedido = rs.getDate("dt_pedido");
                Date dtEntrega = rs.getDate("dt_entrega");
                String cep = rs.getString("cep");
                String rua = rs.getString("rua");
                String complemento = rs.getString("complemento");
                int numero = rs.getInt("numero");
                String pais = rs.getString("pais");
                String estado = rs.getString("estado");
                String cidade = rs.getString("cidade");
                long idMotorista = rs.getLong("id_motorista");

                return new Entrega(
                        idEntrega,
                        descricaoProduto,
                        dtPedido,
                        dtEntrega,
                        new Endereco(pais, cep, estado, cidade, rua, numero, complemento),
                        idMotorista
                );
            }

            return null;

        } catch (SQLException e) {
            log.error("Erro ao buscar entrega", e);
            throw throwDAOException(e, SELECT);
        } finally {
            fechar(stmt, rs);
            conexao.desconectar(conn);
        }
    }

    public List<Entrega> buscarTodos() {
        List<Entrega> entregas = new ArrayList<>();
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        String sql = "SELECT * FROM entrega";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                long idEntrega = rs.getLong("cod_entrega");
                String descricaoProduto = rs.getString("descricao_produto");
                Date dtPedido = rs.getDate("dt_pedido");
                Date dtEntrega = rs.getDate("dt_entrega");
                String cep = rs.getString("cep");
                String rua = rs.getString("rua");
                String complemento = rs.getString("complemento");
                int numero = rs.getInt("numero");
                String pais = rs.getString("pais");
                String estado = rs.getString("estado");
                String cidade = rs.getString("cidade");
                long idMotorista = rs.getLong("id_motorista");

                Entrega entrega = new Entrega(
                        idEntrega,
                        descricaoProduto,
                        dtPedido,
                        dtEntrega,
                        new Endereco(pais, cep, estado, cidade, rua, numero, complemento),
                        idMotorista
                );
                entregas.add(entrega);
            }

            return entregas;

        } catch (SQLException e) {
            log.error("Erro ao buscar entrega", e);
            throw throwDAOException(e, SELECT);
        } finally {
            fechar(stmt, rs);
            conexao.desconectar(conn);
        }
    }

    public List<Entrega> buscarPorIdEmpresa(long idEmpresa) {
        List<Entrega> entregas = new ArrayList<>();
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        String sql = "select * from entrega e join motorista m on m.id = e.id_motorista " +
                "join empresa ep on ep.id = m.id_empresa " +
                "where ep.id = 1;";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                long idEntrega = rs.getLong("cod_entrega");
                String descricaoProduto = rs.getString("descricao_produto");
                Date dtPedido = rs.getDate("dt_pedido");
                Date dtEntrega = rs.getDate("dt_entrega");
                String cep = rs.getString("cep");
                String rua = rs.getString("rua");
                String complemento = rs.getString("complemento");
                int numero = rs.getInt("numero");
                String pais = rs.getString("pais");
                String estado = rs.getString("estado");
                String cidade = rs.getString("cidade");
                long idMotorista = rs.getLong("id_motorista");

                Entrega entrega = new Entrega(
                        idEntrega,
                        descricaoProduto,
                        dtPedido,
                        dtEntrega,
                        new Endereco(pais, cep, estado, cidade, rua, numero, complemento),
                        idMotorista
                );
                entregas.add(entrega);
            }

            return entregas;

        } catch (SQLException e) {
            log.error("Erro ao buscar entrega", e);
            throw throwDAOException(e, SELECT);
        } finally {
            fechar(stmt, rs);
            conexao.desconectar(conn);
        }
    }
}