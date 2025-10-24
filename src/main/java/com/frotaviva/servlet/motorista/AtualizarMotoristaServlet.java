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

@WebServlet(name = "AtualizarMotorista", value = "/atualizar-motorista")
public class AtualizarMotoristaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id;
        String cpf;
        String senha;
        String nome;
        String email;

        try{

            HttpSession session = request.getSession(true);
            Object idSession = session.getAttribute("idEmpresa");

            if (idSession == null){
                response.sendRedirect("/");
                return;
            }

            long idEmpresa = (long) idSession;

            id = Long.parseLong(request.getParameter("id"));
            cpf = request.getParameter("cpf");
            senha = request.getParameter("senha");
            nome = request.getParameter("nome");
            email = request.getParameter("email");

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

            MotoristaDAO dao = new MotoristaDAO();

            Motorista motorista = dao.buscarPorId(id);

            motorista.setNome(nome);
            motorista.setEmail(email);
            motorista.setSenha(senha);
            motorista.setIdEmpresa(idEmpresa);
            motorista.setCpf(cpf);

            if (dao.atualizar(motorista) == 1) {
                response.sendRedirect("/lista-motorista?msg=Sucesso");
            } else{
                response.sendRedirect("/erro.jsp");
            }

        } catch (Exception e){ // ainda nao tem a pagina de erro
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}
