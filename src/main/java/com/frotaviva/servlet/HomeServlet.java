package com.frotaviva.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println(req.getAttribute("idEmpresa"));
        req.setAttribute("idEmpresa", req.getAttribute("idEmpresa"));
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, res);
    }
}
