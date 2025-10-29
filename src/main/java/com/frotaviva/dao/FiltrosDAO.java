package com.frotaviva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.frotaviva.model.InformacoesHome;

import com.frotaviva.util.Conexao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FiltrosDAO {

    private static final Logger log = LoggerFactory.getLogger(FiltrosDAO.class); // pega a classe usada


    /*
     * Métodos da classe FiltrosDAO:
     * perfilMotorista
     * informacoesCaminhao
     */

    public static List<Map<String, String>> perfisMotoristas(long id) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        List<Map<String, String>> perfisMotoristas = new ArrayList<>();
        String sql = "SELECT * FROM perfismotoristas WHERE id_empresa = ? ";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String placa = rs.getString("placa");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefonePrincipal = rs.getString("telefone_principal");
                String tipoFrota = rs.getString("tipo_frota");

                Map<String, String> perfilMotorista = new HashMap<>();
                perfilMotorista.put("placa", placa);
                perfilMotorista.put("nome", nome);
                perfilMotorista.put("email", email);
                perfilMotorista.put("telefone_principal", telefonePrincipal);
                perfilMotorista.put("tipo_frota", tipoFrota);

                perfisMotoristas.add(perfilMotorista);
            }

            stmt.close();
            rs.close();
            return perfisMotoristas;

        } catch (SQLException sqle) {
            log.error("Erro ao buscar perfis de motoristas", sqle);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
    public static List<Map<String, String>> buscarPerfisPorNome(long id, String nomeBuscar, int offset) {
        Conexao conexao = new Conexao();
        Connection conn = null;

        List<Map<String, String>> perfisMotoristas = new ArrayList<>();
        String sql = "SELECT * FROM perfismotoristas WHERE id_empresa = ? AND nome LIKE ?" +
                " LIMIT 9 OFFSET ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.setString(2, "%"+nomeBuscar+"%");
            stmt.setInt(3, offset);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String placa = rs.getString("placa");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefonePrincipal = rs.getString("telefone_principal");
                String tipoFrota = rs.getString("tipo_frota");

                Map<String, String> perfilMotorista = new HashMap<>();
                perfilMotorista.put("placa", placa);
                perfilMotorista.put("nome", nome);
                perfilMotorista.put("email", email);
                perfilMotorista.put("telefone_principal", telefonePrincipal);
                perfilMotorista.put("tipo_frota", tipoFrota);

                perfisMotoristas.add(perfilMotorista);
            }

            stmt.close();
            rs.close();
            return perfisMotoristas;

        } catch (SQLException sqle) {
            log.error("Erro ao buscar perfis de motoristas", sqle);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }

    public static List<Map<String, String>> informacoesCaminhao() {
        Conexao conexao = new Conexao();
        Connection conn = null;

        List<Map<String, String>> motoristasCaminhoes = new ArrayList<>();
        String sql = """
            SELECT m.nome, c.placa 
            FROM motorista m
            JOIN caminhao c ON m.id = c.id_motorista
            ORDER BY m.nome
            """
                ;

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String placa = rs.getString("placa");

                Map<String, String> dados = new HashMap<>();
                dados.put("nome", nome);
                dados.put("placa", placa);

                motoristasCaminhoes.add(dados);
            }
            stmt.close();
            return motoristasCaminhoes;

        } catch (SQLException sqle) {
            log.error("Erro ao buscar informações do caminhão", sqle);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
    public static InformacoesHome informacoesHome(long idEmpresa){
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "select * from informacoesHome where id_empresa=?";

        try {
            conn = conexao.conectar();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1,idEmpresa);

            ResultSet rset = stmt.executeQuery();

            if (rset.next()){
                return new InformacoesHome(rset.getString("nome"),
                        rset.getLong("id_empresa"),
                        rset.getInt("tamanho_frota"),
                        rset.getInt("ativos"),
                        rset.getInt("inativos"),
                        rset.getInt("manutencao"),
                        rset.getInt("entregue"),
                        rset.getInt("a_caminho"),
                        rset.getInt("qt_entrega"),
                        rset.getInt("preventivas"),
                        rset.getInt("corretivas"),
                        rset.getDouble("custo_preventivas"),
                        rset.getDouble("custo_corretivas"));
            }
            return null;

        } catch (SQLException sqle){
            log.error("Erro ao buscar informações do caminhão", sqle);
            return null;
        } finally {
            conexao.desconectar(conn);
        }
    }
}


//    public static List<Map<String, String>> informacoesCaminhao(){
//        List<Map<String, String>> motoristasCaminhoes = new ArrayList<>();
//        String sql = """
//                SELECT m.nome, c.placa FROM motorista m
//                JOIN caminhao c
//                ON m.id = c.id_motorista
//                ORDER BY m.nome""";
//
//        try (Connection conn = Conexao.conectar();
//             PreparedStatement stmt = conn.prepareStatement(sql);){
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()){
//                String nome = rs.getString("nome");
//                String placa = rs.getString("placa");
//
//                Map<String, String> dados = new HashMap<>();
//                dados.put("nome", nome);
//                dados.put("placa", placa);
//                motoristasCaminhoes.add(dados);
//            }
//            return motoristasCaminhoes;
//        } catch (SQLException sqle){
//            sqle.printStackTrace();
//            return null;
//        }
//    }
