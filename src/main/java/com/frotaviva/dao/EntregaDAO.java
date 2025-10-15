package com.frotaviva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frotaviva.model.Endereco;
import com.frotaviva.model.Entrega;
import com.frotaviva.util.Conexao;

public class EntregaDAO implements DAO<Entrega>{

    /*
     * Logger está sendo usado para melhor rastreamento e diagnóstico de exceções.
     */

    private static final Logger log = LoggerFactory.getLogger(EntregaDAO.class);

    /*
     * Métodos da classe EntregaDAO:
     * inserir
     * atualizarDescricao|Datas|Endereco
     * deletar
     * buscarPorId
     * buscarTodos
     */

    public int inserir(Entrega entrega){
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "INSERT INTO entrega(descricao_produto, dt_pedido, dt_entrega, cep, rua, complemento, numero, " +
                "pais, estado, cidade, id_motorista) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Endereco endereco = entrega.getEndereco();

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

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

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }

            return 0;

        } catch (SQLException sqle) {
            log.error("Erro ao cadastrar entrega", sqle);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int atualizarDescricao(Entrega entrega) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "UPDATE entrega SET descricao_produto = ? WHERE cod_entrega = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entrega.getDescricaoProduto());
            stmt.setLong(2, entrega.getCod_entrega());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }

            return 0;

        } catch (SQLException sqle) {
            log.error("Erro ao atualizar descrição da entrega", sqle);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int atualizarDatas(Entrega entrega) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "UPDATE entrega SET dt_pedido = ?, dt_entrega = ? WHERE cod_entrega = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDate(1, entrega.getDtPedido());

            if (entrega.getDtEntrega() != null) {
                stmt.setDate(2, entrega.getDtEntrega());
            } else {
                stmt.setNull(2, Types.DATE);
            }

            stmt.setLong(3, entrega.getCod_entrega());

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }

            return 0;

        } catch (SQLException sqle) {
            log.error("Erro ao atualizar datas da entrega", sqle);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int atualizarEndereco(Entrega entrega) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "UPDATE entrega SET pais = ?, cep = ?, estado = ?, cidade = ?, rua = ?, numero = ?, " +
                "complemento = ? WHERE cod_entrega = ?";
        Endereco endereco = entrega.getEndereco();

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

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

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }

            return 0;

        } catch (SQLException sqle) {
            log.error("Erro ao atualizar endereço da entrega", sqle);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public int deletar(long cod_entrega) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "DELETE FROM entrega WHERE cod_entrega = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, cod_entrega);

            if (stmt.executeUpdate() > 0) {
                stmt.close();
                return 1;
            }

            return 0;

        } catch (SQLException sqle) {
            log.error("Erro ao deletar entrega", sqle);
            return -1;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public Entrega buscarPorId(long cod_entrega) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "SELECT * FROM entrega WHERE cod_entrega = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, cod_entrega);

            ResultSet rs = stmt.executeQuery();

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

        } catch (SQLException sqle) {
            log.error("Erro ao buscar entrega", sqle);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
    public List<Entrega> buscarTodos() {
        List<Entrega> entregas = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "SELECT * FROM entrega";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

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

        } catch (SQLException sqle) {
            log.error("Erro ao buscar entrega", sqle);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
}
