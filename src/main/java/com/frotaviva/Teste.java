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

        String texto = "11.222.333/0001-45";
        String regex = "^[A-Za-z].*";
        System.out.println(texto.replaceAll("[^0-9]", ""));

    }
}
