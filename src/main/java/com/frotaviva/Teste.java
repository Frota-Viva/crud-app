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

        System.out.println(Senhas.hashSenha("&KfYvsy@9v"));
        System.out.println(Senhas.hashSenha("P3W1@@Ix%e"));

    }
}
