package com.frotaviva.servlet.entrega;

import com.frotaviva.dao.EntregaDAO;
import com.frotaviva.model.Entrega;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarEntregas", value = "/listar-entregas")
public class ListarEntregas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Pega o id da empresa e verifica se existe
        HttpSession session = req.getSession(true);
        Object id = session.getAttribute("idEmpresa");

        if (id == null){
            resp.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id;

        try {
            EntregaDAO entregaDAO = new EntregaDAO();
            List<Entrega> entregas = entregaDAO.buscarPorIdEmpresa(idEmpresa);

            req.setAttribute("entregas", entregas);
            req.getRequestDispatcher("/WEB-INF/entrega/listar-entregas.jsp").forward(req, resp);
        } catch (Exception e) { //Ainda não tem a página de erro

        }


    }
}
