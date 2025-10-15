package com.frotaviva.servlet;
import com.frotaviva.dao.EmpresaDAO;
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

        System.out.println(email);
        System.out.println(senha);

        if (EmpresaDAO.getEmpresa(email, senha) != null) {
            res.sendRedirect("/");

            System.out.println("Era pra dar certo");
        } else {
            req.setAttribute("erroLogin", "Empresa não cadastrada");
            req.getRequestDispatcher("WEB-INF/view/login.jsp").forward(req,res);

            System.out.println("Faiou");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/login.jsp").forward(req, res);
    }
}
