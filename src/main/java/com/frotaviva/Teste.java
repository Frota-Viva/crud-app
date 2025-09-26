package com.frotaviva;

import com.frotaviva.dao.EmpresaDAO;
import com.frotaviva.model.Empresa;
import com.frotaviva.util.Conexao;

import java.sql.Connection;

public class Teste {
    public static void main(String[] args) {
        Connection conn = Conexao.conectar();
        System.out.println(conn);
        Empresa empresa = EmpresaDAO.getEmpresa("21403579000103", "nviana@mendonca.br",
                "&KfYvsy@9v");
        System.out.println(empresa);
    }
}
