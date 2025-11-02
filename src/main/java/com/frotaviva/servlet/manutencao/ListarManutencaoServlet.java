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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Object id = session.getAttribute("idEmpresa");

        String buscar = request.getParameter("buscar");

        if (id == null) {
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id;

        try {

            ManutencaoDAO dao = new ManutencaoDAO();

            List<Manutencao> manutencoes;

            if (buscar != null && !buscar.isBlank()){
                manutencoes = dao.buscarPorEmpresaComTipo(idEmpresa, buscar);
            } else {
                manutencoes = dao.buscarPorEmpresa(idEmpresa);
            }

            request.setAttribute("manutencoes", manutencoes);
            request.getRequestDispatcher("WEB-INF/view/manutencao/listar-manutencao.jsp").forward(request, response);

        } catch (ServletException e) {
            response.sendRedirect("/home?msg=Ocorreu um no servidor. Tente novamente mais tarde.");
        } catch (ErroAoConsultar e) {
            response.sendRedirect("/home?msg=Erro ao acessar o encontrar motoristas. Tente novamente mais tarde.");
        } catch (Exception e) {
            response.sendRedirect("/home?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }
    }
}


