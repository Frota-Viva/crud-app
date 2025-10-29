package com.frotaviva;

import com.frotaviva.dao.CaminhaoDAO;
import com.frotaviva.dao.EmpresaDAO;
import com.frotaviva.model.Caminhao;
import com.frotaviva.model.Empresa;
import com.frotaviva.model.Endereco;
import com.frotaviva.util.Conexao;
import com.frotaviva.util.Senhas;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Teste {
    public static void main(String[] args) {

//        System.out.println(Senhas.hashSenha("&KfYvsy@9v"));
//        System.out.println(Senhas.hashSenha("P3W1@@Ix%e"));


//
//        Empresa empresa = new Empresa(
//                4L,
//                "Logistica",
//                "49752380000100",
//                "das-neveshelena@leao.net",
//                "Moura Rezende S/A",
//                new Endereco(
//                        "95604230",
//                        "Campo Eduardo das Neves",
//                        "Complemento não informado",
//                        "SP",
//                        "Brasil",
//                        7738,
//                        "Souza da Serra"
//                )
//        );
//        Empresa empresa2 = new Empresa(
//                4L,
//                "Logistica",
//                "49752380000100",
//                "das-neveshelena@leao.net",
//                "Moura Rezende S/A",
//                new Endereco(
//                        "95604230",
//                        "Campo Eduardo das Neves",
//                        "Complemento não informado",
//                        "SP",
//                        "Brasil",
//                        7738,
//                        "Souza da Serra"
//                )
//        );
//        System.out.println(empresa.equals(empresa2));


        EmpresaDAO empresaDAO = new EmpresaDAO();
        List<Empresa> empresas = empresaDAO.buscarTodos();

        for (Empresa e : empresas) {
            System.out.println(e.toString());
        }
    }
}
