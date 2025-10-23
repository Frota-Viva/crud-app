package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.model.Motorista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "InserirMotoristas", value = "/inserir-motoristas")
public class InserirMotoristaServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MotoristaDAO dao = new MotoristaDAO();

        long id = (long) request.getAttribute("id");
        String nome = (String)request.getAttribute("nome");
        String email = (String)request.getAttribute("email");
        String cpf = (String)request.getAttribute("cpf");
        String senha = (String)request.getAttribute("senha");
        long idEmpresa = (long)request.getAttribute("idEmpresa");

        try{
            Motorista motorista = new Motorista(id, nome, email, cpf, senha, idEmpresa);

            if (dao.inserir(motorista) == 1){
                response.sendRedirect("listar-motoristas");
            }
            response.sendRedirect("listar-motoristas");

        } catch (Exception e){ // ainda nao tem a pagina de erro
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}