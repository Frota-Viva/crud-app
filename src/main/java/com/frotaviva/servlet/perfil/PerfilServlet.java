package com.frotaviva.servlet.perfil;
import com.frotaviva.dao.EmpresaDAO;
import com.frotaviva.model.Empresa;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/home/perfil")
public class PerfilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Empresa empresa;

        Object empresaSession = request.getAttribute("empresa");
        if (empresaSession == null){
            EmpresaDAO empresaDAO = new EmpresaDAO();

            Object idSession = session.getAttribute("idEmpresa");

            if (idSession == null){
                response.sendRedirect("/");
                return;
            }
            long idEmpresa = (long) idSession;

            empresa = empresaDAO.buscarPorId(idEmpresa);

            if (empresa == null){
                response.sendRedirect("/");
                return;
            }
            request.setAttribute("empresa",empresa);
        } else {
            empresa = (Empresa) empresaSession;
            request.setAttribute("empresa",empresa);
        }

        request.getRequestDispatcher("/WEB-INF/view/perfil.jsp").forward(request, response);


    }



}
