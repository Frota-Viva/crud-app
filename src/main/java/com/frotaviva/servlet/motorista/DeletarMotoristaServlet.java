package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteMotorista", value = "/deletar-motorista")
public class DeletarMotoristaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id;

        try{

            id = Long.parseLong(request.getParameter("id"));
            MotoristaDAO dao = new MotoristaDAO();

            if (dao.deletar(id) == 1) {
                response.sendRedirect("listar-motoristas");
                return;
            }
            request.setAttribute("erro", "Motorista não encontrado!");

        } catch (Exception e){
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }

}
