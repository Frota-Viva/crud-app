package com.frotaviva.servlet;
import com.frotaviva.dao.EmpresaDAO;
import com.frotaviva.model.Empresa;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        EmpresaDAO empresaDAO = new EmpresaDAO();
        Empresa empresa = empresaDAO.getEmpresa(email, senha);

        if (empresa != null) {
            req.setAttribute("id", empresa.getId());
            res.sendRedirect("/");
        } else {
            req.setAttribute("erro", "Email ou senha incorretas.");
            req.getRequestDispatcher("WEB-INF/view/login.jsp").forward(req, res);

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/login.jsp").forward(req, res);
    }
}
