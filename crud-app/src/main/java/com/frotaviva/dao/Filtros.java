package com.frotaviva.dao;

import com.frotaviva.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filtros {
    public static List<Map<String, String>> caminhoesComMotoristas(){
        List<Map<String, String>> motoristasCaminhoes = new ArrayList<>();
        String sql = """
                SELECT m.nome, c.placa FROM motorista m 
                JOIN caminhao c 
                ON m.id = c.id_motorista 
                ORDER BY m.nome""";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String nome = rs.getString("nome");
                String placa = rs.getString("placa");

                Map<String, String> dados = new HashMap<>();
                dados.put("nome", nome);
                dados.put("placa", placa);
                motoristasCaminhoes.add(dados);
            }
            return motoristasCaminhoes;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
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
}
