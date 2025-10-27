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
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(true);

        Empresa empresa;
        EmpresaDAO empresaDAO = new EmpresaDAO();

        Object idSession = session.getAttribute("idEmpresa");

        if (idSession == null){
            res.sendRedirect("/");
            return;
        }
        long idEmpresa = (long) idSession;

        empresa = empresaDAO.buscarPorId(idEmpresa);

        if (empresa == null){
            res.sendRedirect("/");
            return;
        }
        req.setAttribute("empresa",empresa);
        req.getRequestDispatcher("/WEB-INF/view/perfil.jsp").forward(req, res);


    }



}
