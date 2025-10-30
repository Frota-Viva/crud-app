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

        System.out.println(Senhas.hashSenha("elsEN2Yi+s"));
        System.out.println(Senhas.hashSenha("((0CeuE4d#"));
        System.out.println(Senhas.hashSenha("Af2JIMepC@"));
        System.out.println(Senhas.hashSenha("#8N*qtunu5"));
        System.out.println(Senhas.hashSenha("2*1WK1_o4S"));


    }
}
