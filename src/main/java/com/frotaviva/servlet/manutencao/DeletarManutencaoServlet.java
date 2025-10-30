package com.frotaviva.servlet.manutencao;

import com.frotaviva.dao.ManutencaoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeletManutencao", value = "/deletar-manutencao")
public class DeletarManutencaoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{

            long id = Long.parseLong(request.getParameter("id"));
            ManutencaoDAO dao = new ManutencaoDAO();

            if (dao.deletar(id) == 1) {
                response.sendRedirect("/listar-motoristas");
                return;
            }
            request.setAttribute("erro", "Manutenção não encontrada...");

        } catch (Exception e){
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }

}
