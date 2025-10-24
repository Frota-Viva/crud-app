package com.frotaviva.servlet.manutencao;

import com.frotaviva.dao.ManutencaoDAO;
import com.frotaviva.model.Manutencao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListaManutencao", value = "/listar-manutencao")
public class ListarManutencaoServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try{
            ManutencaoDAO dao = new ManutencaoDAO();

            List<Manutencao> manutencao = dao.buscarTodos();

            request.setAttribute("manutencao", manutencao);
            request.getRequestDispatcher("WEB-INF/view/listar-motoristas.jsp").forward(request, response);

        } catch (Exception e){
            request.getRequestDispatcher("WEB-INF/view/listar-motoristas.jsp").forward(request, response);
        }
    }
}

