package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.exception.ErroAoInserir;
import com.frotaviva.model.Motorista;
import com.frotaviva.util.Validar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "InserirMotoristas", value = "/inserir-motoristas")
public class InserirMotoristaServlet extends HttpServlet {

    /**
     * Recebe uma mensagem, codifica ela para o padrão UTF_8 e redireciona para /listar-motoristas
     * Apenas para maior legibilidade do código, pois cada mensagem teria que ser codificada e isso
     * seria repetido muitas vezes no código.
     *
     * @param response para executar o redirect
     * @param mensagem para a especificação da mensagem de erro/sucesso
     */
    private void redirectComMensagem(HttpServletResponse response, String mensagem) throws IOException {
        String mensagemEncoded = URLEncoder.encode(mensagem, StandardCharsets.UTF_8);
        response.sendRedirect("/listar-motoristas?msg=" + mensagemEncoded);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/view/motorista/inserir-motorista.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean erro = false;

        HttpSession session = request.getSession(true);
        Object id = session.getAttribute("idEmpresa");

        if (id == null){
            response.sendRedirect("/");
            return;
        }
        long idEmpresa = (long) id;

        try{

            MotoristaDAO dao = new MotoristaDAO();

            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");

            if (nome == null || nome.isEmpty()){
                request.setAttribute("erroNome", "Nome inválido! Não pode ser nulo.");
                erro = true;
            }

            if (!Validar.email(email)){
                request.setAttribute("erroEmail", "Email inválido! Deve conter '@exemplo.com'.");
                erro = true;
            }

            if (!Validar.cpf(cpf)){
                request.setAttribute("erroCpf", "CPF inválido! Deve conter 11 dígitos.");
                erro = true;
            } else {
                cpf = Validar.cpfValidado(cpf);
            }

            if (!Validar.senha(senha)){
                request.setAttribute("erroSenha", "Senha inválida! Deve conter números, caracteres maiúsculos e minúsculos e ter mais que 8 caracteres.");
                erro = true;
            }

            if (erro){
                request.getRequestDispatcher("WEB-INF/view/motorista/inserir-motorista.jsp").forward(request, response);
                return;
            }

            Motorista motorista = new Motorista(nome, email, cpf, senha, idEmpresa);

            if (dao.inserir(motorista) == 1){
                redirectComMensagem(response, "Motorista inserido com sucesso!");
                return;
            }

            redirectComMensagem(response, "Erro ao inserir motorista.");

        } catch (ErroAoInserir e) {
            redirectComMensagem(response, "Erro ao inserir motorista: " + e.getMessage());
        } catch (Exception e) {
            redirectComMensagem(response, "Erro inesperado: " + e.getMessage());
        }
    }
}