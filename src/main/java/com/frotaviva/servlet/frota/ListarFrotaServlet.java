package com.frotaviva.servlet.frota;

import com.frotaviva.dao.FrotaDAO;
import com.frotaviva.exception.ErroAoConsultar;
import com.frotaviva.model.Frota;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListaFrota", value = "/listar-frota")
public class ListarFrotaServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true); //Pega a sessão
        Object id = session.getAttribute("idEmpresa"); //Pega o id da empresa na sessão

        //Verifica se o id existe
        if (id == null){
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id;

        try{
            FrotaDAO dao = new FrotaDAO();

            List<Frota> frota = dao.buscarPorEmpresa(idEmpresa);

            request.setAttribute("frota", frota);
            request.getRequestDispatcher("WEB-INF/view/frota/listar-frota.jsp").forward(request, response);

        } catch (ErroAoConsultar e) {
            request.setAttribute("mensagem", "Erro ao listar frotas. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}

