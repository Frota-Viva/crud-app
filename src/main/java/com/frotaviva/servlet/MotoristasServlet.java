package com.frotaviva.servlet;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.dao.TelefoneMotoristaDAO;
import com.frotaviva.model.Motorista;

import com.frotaviva.model.TelefoneMotorista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Motoristas", value = "/motoristas")
public class MotoristasServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       MotoristaDAO dao = new MotoristaDAO();

        try{
            List<Motorista> motoristas = dao.buscarTodos();

            request.setAttribute("motoristas", motoristas);

            request.getRequestDispatcher("WEB-INF/view/funcionarios.jsp").forward(request, response);

        } catch (Exception e){ // ainda nao tem a pagina de erro
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}
