package com.frotaviva.servlet.manutencao;

import com.frotaviva.dao.ManutencaoDAO;

import com.frotaviva.exception.ErroAoConsultar;
import com.frotaviva.exception.ErroAoDeletar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteManutencao", value = "/deletar-manutencao")
public class DeletarManutencaoServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        try{

            ManutencaoDAO dao = new ManutencaoDAO();

            if (dao.deletar(id) == 1) {
                response.sendRedirect("/listar-motoristas");
                return;
            }

            request.setAttribute("erro", "Manutenção não encontrada...");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);

        } catch (ErroAoDeletar e) {
            request.setAttribute("mensagem", "Erro ao acessar manutencoes. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }

}
