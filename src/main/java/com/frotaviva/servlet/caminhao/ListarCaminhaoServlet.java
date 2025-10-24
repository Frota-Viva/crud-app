package com.frotaviva.servlet.caminhao;

import com.frotaviva.dao.CaminhaoDAO;
import com.frotaviva.model.Caminhao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarCaminhao", value = "/listar-caminhao")
public class ListarCaminhaoServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CaminhaoDAO dao = new CaminhaoDAO();

        try{

            List<Caminhao> caminhoes = dao.buscarTodos();

            request.setAttribute("caminhoes", caminhoes);

            request.getRequestDispatcher("WEB-INF/view/listar-caminhoes.jsp").forward(request, response);

        } catch (Exception e){ // ainda nao tem a pagina de erro
            request.getRequestDispatcher("WEB-INF/view/listar-caminhoes.jsp").forward(request, response);
        }
    }
}
