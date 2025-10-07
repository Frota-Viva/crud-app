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

public class FiltrosDAO {
    public static List<Map<String, String>> perfilMotorista(){
        List<Map<String, String>> motoristasCaminhoes = new ArrayList<>();
        String sql = "SELECT * FROM perfilmotorista"; //Selecionando a view de perfil do motorista

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String placa = rs.getString("placa");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone_principal = rs.getString("Telefone_Principal");
                String tipo_frota = rs.getString("tipo_frota");

                Map<String, String> dados = new HashMap<>();
                dados.put("placa", placa);
                dados.put("nome", nome);
                dados.put("email", email);
                dados.put("telefone_principal", telefone_principal);
                dados.put("tipo_frota", tipo_frota);
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
