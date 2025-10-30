package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.exception.ErroAoConsultar;
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

@WebServlet(name = "InserirMotoristas", value = "/inserir-motoristas")
public class InserirMotoristaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome;
        String email;
        String cpf;
        String senha;


        HttpSession session = request.getSession(true);
        Object idSession = session.getAttribute("idEmpresa");

        if (idSession == null){
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) idSession;

        try{

            MotoristaDAO dao = new MotoristaDAO();

            nome = request.getParameter("nome");
            email = request.getParameter("email");
            cpf = request.getParameter("cpf");
            senha = request.getParameter("senha");

            if (!Validar.senha(senha)){
                request.setAttribute("erro", "Senha inválida! Deve conter números, caracteres maiúsculos e minúsculos e ter mais que 8 caracteres.");
                request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
            }

            if (!Validar.email(email)){
                request.setAttribute("erro", "Email inválido! Deve conter '@exemplo.com'.");
                request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);

            }

            if (!Validar.cpf(cpf)){
                request.setAttribute("erro", "CPF inválido! Deve conter 11 dígitos");
                request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
            } else{
                cpf = Validar.cpfValidado(cpf);
            }

            if (nome ==  null || nome.isEmpty()){
                request.setAttribute("erro", "Nome inválido! Não pode ser nulo.");
                request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
            }

            Motorista motorista = new Motorista(nome, email, cpf, senha, idEmpresa);

            if (dao.inserir(motorista) != 1){
                response.sendRedirect("listar-motoristas");
            }

            request.setAttribute("mensagem", "Erro ao inserir motoristas. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);

        } catch (ErroAoInserir e) {
            request.setAttribute("mensagem", "Erro ao acessar o encontrar motoristas. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}