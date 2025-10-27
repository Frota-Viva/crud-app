package com.frotaviva.servlet.frota;

import com.frotaviva.dao.FrotaDAO;
import com.frotaviva.model.Frota;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListaFrota", value = "/listar-frota")
public class ListarFrotaServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try{
            FrotaDAO dao = new FrotaDAO();

            List<Frota> frota = dao.buscarTodos();

            request.setAttribute("frota", frota);
            request.getRequestDispatcher("WEB-INF/view/listar-frota.jsp").forward(request, response);

        } catch (Exception e){
            request.getRequestDispatcher("WEB-INF/view/listar-frota.jsp").forward(request, response);
        }
    }
}

