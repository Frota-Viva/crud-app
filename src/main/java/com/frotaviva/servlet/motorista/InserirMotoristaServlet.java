package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.model.Motorista;

import com.frotaviva.util.Validar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "InserirMotoristas", value = "/inserir-motoristas")
public class InserirMotoristaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome;
        String email;
        String cpf;
        String senha;

        try{

            HttpSession session = request.getSession(true);
            Object idSession = session.getAttribute("idEmpresa");

            if (idSession == null){
                response.sendRedirect("/");
                return;
            }


            long idEmpresa = (long) idSession;

            MotoristaDAO dao = new MotoristaDAO();


            nome = (String)request.getAttribute("nome");
            email = (String)request.getAttribute("email");
            cpf = (String)request.getAttribute("cpf");
            senha = (String)request.getAttribute("senha");

            if (!Validar.senha(senha)){
                request.setAttribute("erro", "Senha inválida! Deve conter números, caracteres maiúsculos e minúsculos e ter mais que 8 caracteres.");
            }

            if (!Validar.email(email)){
                request.setAttribute("erro", "Email inválido! Deve conter '@exemplo.com'.");
            }

            if (!Validar.cpf(cpf)){
                request.setAttribute("erro", "CPF inválido! Deve conter 11 dígitos");
            } else{
                cpf = Validar.cpfValidado(cpf);
            }

            if (nome ==  null || nome.isEmpty()){
                request.setAttribute("erro", "Nome inválido! Não pode ser nulo.");
            }

            Motorista motorista = new Motorista(nome, email, cpf, senha, idEmpresa);

            if (dao.inserir(motorista) == 1){
                response.sendRedirect("listar-motoristas");
            }
            response.sendRedirect("listar-motoristas");

        } catch (Exception e){ // ainda nao tem a pagina de erro
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}