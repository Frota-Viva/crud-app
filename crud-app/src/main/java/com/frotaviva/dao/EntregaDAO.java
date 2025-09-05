package com.frotaviva.dao;

import com.frotaviva.Conexao;
import com.frotaviva.model.Endereco;
import com.frotaviva.model.Entrega;

import java.sql.*;

public class EntregaDAO {
    public static boolean cadastrarEntrega(Entrega entrega){
        String sql = "INSERT INTO entrega(descricao_produto, dt_pedido, dt_entrega, cep, rua, complemento, numero, " +
                "pais, estado, cidade, id_motorista) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Endereco endereco = entrega.getEndereco();
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, entrega.getDescricaoProduto());
            stmt.setDate(2, entrega.getDtPedido());
            if (entrega.getDtEntrega() != null) {
                stmt.setDate(3, entrega.getDtEntrega());
            } else {
                stmt.setNull(3, Types.DATE);
            }
            stmt.setString(4, endereco.getCep());
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

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean deletarEntrega(Entrega entrega){
        String sql = "DELETE FROM entrega WHERE cod_entrega = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, entrega.getCod_entrega());

            if (stmt.executeUpdate() > 0) return true;

            return false;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }
    }
    public static Entrega getEntrega(long cod_entrega){
        String sql = "SELECT * FROM entrega WHERE cod_entrega = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, cod_entrega);

            ResultSet rs = stmt.executeQuery();

            long idEntrega = rs.getLong("cod_entrega");
            String descricao_produto = rs.getString("descricao_produto");
            Date dt_pedido = rs.getDate("dt_pedido");
            Date dt_entrega = rs.getDate("dt_entrega");
            String cep = rs.getString("cep");
            String rua = rs.getString("rua");
            String complemento = rs.getString("complemento");
            int numero = rs.getInt("numero");
            String pais = rs.getString("pais");
            String estado = rs.getString("estado");
            String cidade = rs.getString("cidade");
            long id_motorista = rs.getLong("id_motorista");

            return new Entrega(idEntrega, descricao_produto, dt_pedido, dt_entrega, new Endereco(
                    pais, cep, estado, cidade, rua, numero, complemento), id_motorista);
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }
}
