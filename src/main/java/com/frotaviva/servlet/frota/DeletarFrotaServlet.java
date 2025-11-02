package com.frotaviva.servlet.frota;

import com.frotaviva.dao.FrotaDAO;
import com.frotaviva.exception.ErroAoDeletar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeletFrota", value = "/deletar-frota")
public class DeletarFrotaServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        try{
            FrotaDAO dao = new FrotaDAO();

            if (dao.deletar(id) == 1) {
                response.sendRedirect("/listar-frota");
                return;
            }

            request.setAttribute("msg", "Erro ao deletar frota. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/listar-frota.jsp").forward(request, response);

        } catch (ErroAoDeletar e) {
            response.sendRedirect("/listar-frota?msg=Erro ao deletar frota. Tente novamente mais tarde.");
        } catch (Exception e) {
            response.sendRedirect("/listar-frota?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }
    }

}
