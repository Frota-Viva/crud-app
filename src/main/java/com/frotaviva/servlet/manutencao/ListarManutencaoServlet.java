package com.frotaviva.servlet.manutencao;

import com.frotaviva.dao.ManutencaoDAO;
import com.frotaviva.exception.ErroAoConsultar;
import com.frotaviva.model.Manutencao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListaManutencao", value = "/listar-manutencao")
public class ListarManutencaoServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Object id = session.getAttribute("idEmpresa");

        if (id == null) {
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id;

        try {

            ManutencaoDAO dao = new ManutencaoDAO();

            List<Manutencao> manutencao = dao.buscarPorEmpresa(idEmpresa);

            request.setAttribute("manutencao", manutencao);
            request.getRequestDispatcher("WEB-INF/view/listar-motoristas.jsp").forward(request, response);

        } catch (ServletException e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (ErroAoConsultar e) {
            request.setAttribute("mensagem", "Erro ao acessar o encontrar motoristas. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}


