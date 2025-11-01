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

/**
 * Responsável por gerenciar o cadastro de novas empresas no sistema.
 * <p>
 * Retorna para a página de cadastro e processa os dados enviados pelo formulário,
 * realizando validações, verificando campos já existentes no bando de dados e inserindo os registros no banco de dados.
 * </p>
 * 
 * @author Ricardo
 */
@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {

    /**
     * Recebe uma requisição GET e encaminha o usuário para a página de cadastro (cadastro.jsp).
     *
     * @param req objeto da requisição HTTP
     * @param res objeto da resposta HTTP
     * @throws ServletException se ocorrer um erro de servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/cadastro.jsp").forward(req, res);
    }

    /**
     * Recebe uma requisição POST, valida os dados do cadastro, verifica se
     * o CNPJ ou o email já existem no banco de dados e insere a empresa nele.
     * <p>
     * Caso haja erro de validação ou dados faltando, o usuário é retornado à página de cadastro
     * com as mensagens de erro específicas. Em caso de sucesso, o usuário é redirecionado para a página inicial.
     * </p>
     *
     * @param req objeto da requisição HTTP
     * @param res objeto da resposta HTTP
     * @throws ServletException se ocorrer um erro de servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        boolean erro = false; //Variável de controle para erros
        boolean dadoFaltando = false; //variável de controle para dados faltando
        EmpresaDAO empresaDAO = new EmpresaDAO();

        // Recebe os valores digitados pelo usuário e faz as verificações necessárias

        // Faz a validação do tipo-empresa
        String tipoEmpresa = req.getParameter("tipoEmpresa");
        if ((tipoEmpresa == null) || tipoEmpresa.equals("")) {
            dadoFaltando = true;
        }

        // Faz a validação do CNPJ
        String cnpj = req.getParameter("cnpj");
        cnpj = Empresa.cnpjValidado(cnpj);
        if (cnpj == null){
            req.setAttribute("erroCnpj", "Formato não compatível ou cnpj inválido!");
            erro = true;
        }

        // Faz a validação do telefone
        String telefone = req.getParameter("telefone");
        telefone = Validar.telefoneValidado(telefone);
        if (telefone == null){
            req.setAttribute("erroTelefone", "Formato não compatível!");
            erro = true;
        }

        // Faz a validação do email
        String email = req.getParameter("email");
        if (! Validar.email(email)){
            req.setAttribute("erroEmail", "Formato não compatível!");
            erro = true;
        }

        // Faz a validação da senha
        String senha = req.getParameter("senha");
        if (! Validar.senha(senha)){
            req.setAttribute("erroSenha", "A senha deve conter no mínimo: 8 dígitos, 1 letra maiúscula," +
                    " 1 letra minúscula, 1 número e 1 caractere especial ($*&@#!?)!");
            erro = true;
        }

        // Faz a validação do nome
        String nome = req.getParameter("nome");
        if (nome == null || nome.equals("")) dadoFaltando = true;

        // Faz a validação do CEP
        String cep = req.getParameter("cep");
        cep = Validar.cepValidado(cep);
        if (cep == null){
            req.setAttribute("erroCep", "Formato não compatível!");
            erro = true;
        }

        // Faz a validação da rua
        String rua = req.getParameter("rua");
        if (rua == null || rua.equals("")) dadoFaltando = true;

        // Pega o complemento (pode ser nulo)
        String complemento = req.getParameter("complemento");

        // Faz a validação do número
        int numero = 0;
        try {
            numero = Integer.parseInt(req.getParameter("numero"));
        } catch (NumberFormatException e){
            req.setAttribute("erroNumero", "Apenas valores numéricos são permitidos!");
            erro = true;
        }

        // Faz a validação do país
        String pais = req.getParameter("pais");
        if (pais == null || pais.equals("")) dadoFaltando = true;

        // Faz a validação do estado
        String estado = req.getParameter("estado");
        if (estado == null || estado.equals("")) dadoFaltando = true;

        // Faz a validação da cidade
        String cidade = req.getParameter("cidade");
        if (cidade == null || cidade.equals("")) dadoFaltando = true;

        // Verifica se possui algum erro ou dado faltando e retorna ao usuário
        if (dadoFaltando) req.setAttribute("dadoFaltando", "Todas as informações com (*) devem ser preenchidas!");
        if (erro || dadoFaltando) {
            req.getRequestDispatcher("WEB-INF/view/cadastro.jsp").forward(req, res);
            return;
        }

        // Verifica se o email ou o cnpj já existem no banco de dados
        if ( (empresaDAO.buscarPorEmail(email) == 1) || (empresaDAO.buscarPorCnpj(cnpj) == 1) ){
            req.setAttribute("existeEmpresa", "Essa empresa já existe!");
            req.getRequestDispatcher("WEB-INF/view/cadastro.jsp").forward(req, res);
            return;
        }

        // Instancia um objeto Empresa, um Endereco e um TelefoneEmpresa
        Endereco endereco = new Endereco(pais, cep, estado, cidade, rua, numero, complemento);
        Empresa empresa = new Empresa(tipoEmpresa, cnpj, email, senha, nome, endereco);

        // Insere a empresa no banco de dados e manda o usuário para o início
        switch (empresaDAO.inserir(empresa)){
            case 1 -> res.sendRedirect("/inicio");
            case 0 -> req.setAttribute("erroCadastrar", "Erro ao cadastrar empresa");
            default -> req.setAttribute("erroBD", "Erro no banco de dados");
        }
    }
}
