package com.frotaviva.servlet.telefone;

import com.frotaviva.dao.MotoristaDAO;

import com.frotaviva.dao.TelefoneMotoristaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteTelefone", value = "/deletar-telefone")
public class DeletarTelefoneServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id;

        try{

            id = Long.parseLong(request.getParameter("id"));
            TelefoneMotoristaDAO dao = new TelefoneMotoristaDAO();

            if (dao.deletar(id) == 1) {
                response.sendRedirect("listar-telefones");
                return;
            }
            request.setAttribute("erro", "Telefone não encontrado!");

        } catch (Exception e){
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }

}
