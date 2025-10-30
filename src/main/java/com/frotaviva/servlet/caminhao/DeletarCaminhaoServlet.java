package com.frotaviva.servlet.caminhao;

import com.frotaviva.dao.CaminhaoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteCaminhao", value = "/deletar-caminhao")
public class DeletarCaminhaoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        CaminhaoDAO dao = new CaminhaoDAO();

        try{

            if (dao.deletar(id) == 1) {
                response.sendRedirect("/listar-caminhoes");
                return;
            }
            request.setAttribute("erro", "Caminhoes não encontrado...");

        } catch (Exception e){ // ainda nao tem a pagina de erro
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }

}
