package com.frotaviva.servlet.frota;

import com.frotaviva.dao.FrotaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeletFrota", value = "/deletar-frota")
public class DeletarFrotaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{

            long id = Long.parseLong(request.getParameter("id"));
            FrotaDAO dao = new FrotaDAO();

            if (dao.deletar(id) == 1) {
                response.sendRedirect("listar-frota");
                return;
            }
            request.setAttribute("erro", "Frota não encontrada...");

        } catch (Exception e){
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }

}
