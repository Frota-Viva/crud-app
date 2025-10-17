package com.frotaviva.servlet;
import com.frotaviva.dao.EmpresaDAO;
import com.frotaviva.model.Empresa;
import com.frotaviva.model.Endereco;
import com.frotaviva.util.Validar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/cadastro.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        boolean erro = false; //Variável de controle para erros

//        Recebe os valores digitados pelo usuário e faz as verificações necessárias
        String tipoEmpresa = req.getParameter("tipoEmpresa");

        //Faz a validação do CNPJ
        String cnpj = req.getParameter("cnpj");
        cnpj = Empresa.cnpjValidado(cnpj);
        if (cnpj == null){
            req.setAttribute("erroCnpj", "Formato não compatível ou cnpj inválido.");
            erro = true;
        }

        //Faz a validação do email
        String email = req.getParameter("email");
        if (! Validar.email(email)){
            req.setAttribute("erroEmail", "Formato não compatível");
            erro = true;
        }

        //Faz a validação da senha
        String senha = req.getParameter("senha");
        if (! Validar.senha(senha)){
            req.setAttribute("erroSenha", "");
            erro = true;
        }

        String nome = req.getParameter("nome");

        //Faz a validação do CEP
        String cep = req.getParameter("cep");
        cep = Validar.cepValidado(cep);
        if (cep == null){
            req.setAttribute("erroCep", "");
            erro = true;
        }

        String rua = req.getParameter("rua");
        String complemento = req.getParameter("complemento");

        //Faz a validação do número
        int numero = 0;
        try {
            numero = Integer.parseInt(req.getParameter("numero"));
        } catch (NumberFormatException e){
            req.setAttribute("erroNumero", "Apenas valores números são permitidos");
            erro = true;
        }

        String pais = req.getParameter("pais");
        String estado = req.getParameter("estado");
        String cidade = req.getParameter("cidade");

        //Verifica se possui algum erro e retorna ao usuário
        if (erro) return;

//        Instancia um objeto Empresa e um Endereco
        Endereco endereco = new Endereco(pais, cep, estado, cidade, rua, numero, complemento);
        Empresa empresa = new Empresa(tipoEmpresa, cnpj, email, senha, nome, endereco);

//        Insere a impresa no banco de dados e manda o usuário para o início
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresaDAO.inserir(empresa);
        res.sendRedirect("/inicio");

    }
}
