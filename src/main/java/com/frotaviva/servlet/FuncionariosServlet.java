package com.frotaviva.servlet;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.model.Motorista;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FuncionariosServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("home").forward(request, response);

    }
}
