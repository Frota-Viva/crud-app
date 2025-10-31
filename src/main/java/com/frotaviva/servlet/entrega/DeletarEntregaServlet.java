package com.frotaviva.servlet.entrega;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.exception.ErroAoConsultar;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeletarEntregaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        MotoristaDAO dao = new MotoristaDAO();

        try{

            if (dao.deletar(id) == 1){
                response.sendRedirect("/listar-entregas");
                return;
            }

            request.setAttribute("mensagem", "Erro ao deletar entrega. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);

        } catch (ErroAoConsultar e) {
            request.setAttribute("mensagem", "Erro ao deletar entrega. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}
