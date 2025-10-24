package com.frotaviva.servlet.entrega;

import com.frotaviva.dao.EntregaDAO;
import com.frotaviva.dao.MotoristaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeletarEntrega extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        MotoristaDAO dao = new MotoristaDAO();

        try{

            if (dao.deletar(id) == 1){
                resp.sendRedirect("/lista-entregas");
            }
            else{
                resp.sendRedirect("/erro.jsp");
            }

        } catch (Exception e){ // ainda nao tem a pagina de erro
            req.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(req, resp);
        }
    }
}
