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

        HttpSession session = request.getSession(true);
        Object id = session.getAttribute("idEmpresa");

        String buscar = request.getParameter("buscar");

        if (id == null){
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id;

        try{
            FrotaDAO dao = new FrotaDAO();

            List<Frota> frotas;

            if (buscar != null && !buscar.isBlank()){
                frotas = dao.buscarPorEmpresaComTipoFrota(idEmpresa, buscar);
            } else {
                frotas = dao.buscarPorEmpresa(idEmpresa);
            }

            request.setAttribute("frotas", frotas);
            request.getRequestDispatcher("/WEB-INF/view/frota/listar-frota.jsp").forward(request, response);

        } catch (ErroAoConsultar e) {
            response.sendRedirect("/home?msg=Erro ao consultar frotas");
        } catch (Exception e) {
            response.sendRedirect("/home?msg=Ocorreu um erro desconhecido");
        }
    }
}