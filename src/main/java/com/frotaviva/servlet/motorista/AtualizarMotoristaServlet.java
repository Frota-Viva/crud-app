package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.model.Motorista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AtualizarMotorista", value = "/atualizar-motorista")
public class AtualizarMotoristaServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        long idEmpresa = Long.parseLong(request.getParameter("idEmpresa"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");

        MotoristaDAO dao = new MotoristaDAO();

        try{
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
