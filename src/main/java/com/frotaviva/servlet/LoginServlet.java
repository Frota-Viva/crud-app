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
        String cnpj = req.getParameter("cnpj");
        String senha = req.getParameter("senha");

        if (EmpresaDAO.getEmpresa(cnpj, email, senha) != null) req.getRequestDispatcher("index.html").forward(req, res);
    }
}
