package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.model.Motorista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteMotorista", value = "/deletar-motorista")
public class DeletarMotoristaServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        MotoristaDAO dao = new MotoristaDAO();

        try{

            if (dao.deletar(id) == 1){
                response.sendRedirect("/lista-motorista");
            }
            else{
                response.sendRedirect("/erro.jsp");
            }

        } catch (Exception e){ // ainda nao tem a pagina de erro
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }

}
