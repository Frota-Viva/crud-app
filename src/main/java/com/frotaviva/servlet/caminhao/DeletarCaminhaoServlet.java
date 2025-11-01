package com.frotaviva.servlet.caminhao;

import com.frotaviva.dao.CaminhaoDAO;

import com.frotaviva.exception.ErroAoDeletar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteCaminhao", value = "/deletar-caminhao")
public class DeletarCaminhaoServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        CaminhaoDAO dao = new CaminhaoDAO();

        try{

            if (dao.deletar(id) == 1) {
                response.sendRedirect("/listar-caminhao");
                return;
            }

            request.setAttribute("mensagem", "Erro ao deletar caminhao. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);

        } catch (ErroAoDeletar e) {
            request.setAttribute("mensagem", "Erro ao deletar caminhao. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }

}
