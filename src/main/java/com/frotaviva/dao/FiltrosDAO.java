package com.frotaviva.dao;

import com.frotaviva.util.Conexao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiltrosDAO {

    /*
     * Logger utilizado para rastreamento e diagnóstico de exceções.
     */

    private static final Logger log = LoggerFactory.getLogger(FiltrosDAO.class);

    /*
     * Métodos da classe FiltrosDAO:
     * perfilMotorista
     * informacoesCaminhao
     */

    public static List<Map<String, String>> perfilMotorista() {
        Conexao conexao = new Conexao();
        Connection conn = null;

        List<Map<String, String>> motoristasCaminhoes = new ArrayList<>();
        String sql = "SELECT * FROM perfilmotorista";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String placa = rs.getString("placa");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefonePrincipal = rs.getString("telefone_principal");
                String tipoFrota = rs.getString("tipo_frota");

                Map<String, String> dados = new HashMap<>();
                dados.put("placa", placa);
                dados.put("nome", nome);
                dados.put("email", email);
                dados.put("telefone_principal", telefonePrincipal);
                dados.put("tipo_frota", tipoFrota);

                motoristasCaminhoes.add(dados);
            }

            stmt.close();
            return motoristasCaminhoes;

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
