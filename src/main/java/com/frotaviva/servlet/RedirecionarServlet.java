package com.frotaviva.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Responsável por gerenciar os redirecionamentos para a página de login
 * 
 * @author Ricardo
 */

@WebServlet("/redirecionar")
public class RedirecionarServlet extends HttpServlet {

    /**
     * Recebe uma requisição GET e, se a 'ação' for 'login', redireciona para a página de login,
     * senão retorna o erro 404.
     * 
     * @param req objeto da requisição
     * @param res objeto da resposta
     * @throws ServletException se ocorrer um erro de servlet
     * @throws IOException se ocorrer um erro de I/O
     */
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
