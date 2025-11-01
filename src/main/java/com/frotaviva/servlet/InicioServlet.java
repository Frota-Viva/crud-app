package com.frotaviva.servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Responsável por redirecionar o usuário à página de escolha entre 'login' ou 'cadastro'.
 * <p>
 * Redireciona o usuário para a página inicio.html quando uma requisição GET é recebida.
 * </p>
 * 
 * @author Ricardo
 */
@WebServlet("/inicio")
public class InicioServlet extends HttpServlet {

    /**
     * Recebe uma requisição GET e encaminha o usuário para a página inicio.html.
     *
     * @param req objeto da requisição HTTP
     * @param res objeto da resposta HTTP
     * @throws ServletException se ocorrer um erro de servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("inicio.html").forward(req, res);
    }
}
