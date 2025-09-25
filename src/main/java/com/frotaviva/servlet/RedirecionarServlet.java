package com.frotaviva.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/redirecionar")
public class RedirecionarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String acao = req.getParameter("acao");

        switch (acao){
            case "login" -> req.getRequestDispatcher("WEB-INF/view/login.jsp").forward(req, res);
            default -> res.sendError(404);
        }
    }
}
