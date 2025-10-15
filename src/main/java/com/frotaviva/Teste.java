package com.frotaviva;

import com.frotaviva.dao.CaminhaoDAO;
import com.frotaviva.dao.EmpresaDAO;
import com.frotaviva.model.Caminhao;
import com.frotaviva.model.Empresa;
import com.frotaviva.util.Conexao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Teste {
    public static void main(String[] args) {

        CaminhaoDAO caminhaoDAO = new CaminhaoDAO();

        List<Caminhao> caminhoes = new ArrayList<>();
        caminhoes = caminhaoDAO.buscarTodos();

        for (Caminhao caminhao :
                caminhoes) {
            System.out.println(caminhao);
            System.out.println();
        }

    }
}
