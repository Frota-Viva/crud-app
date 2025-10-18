package com.frotaviva.servlet;
import com.frotaviva.dao.FiltrosDAO;
import com.frotaviva.model.InformacoesHome;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLOutput;

@WebServlet("/home")
public class HomeServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        InformacoesHome informacoesHome;

        Object id = session.getAttribute("idEmpresa");

        if (id != null) {

            informacoesHome = FiltrosDAO.informacoesHome((long) id);

            if (informacoesHome != null) {

                req.setAttribute("informacoesHome", informacoesHome);
                req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, res);
                return;

            }

            return;
        }

        res.sendRedirect("/");
    }

}
