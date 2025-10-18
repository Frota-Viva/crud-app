package com.frotaviva.servlet;
import com.frotaviva.dao.EmpresaDAO;
import com.frotaviva.dao.FiltrosDAO;
import com.frotaviva.model.Empresa;
import com.frotaviva.model.InformacoesHome;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLOutput;

@WebServlet("/home")
public class HomeServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(true); //Pega a sessão ou cria uma se não existir

        InformacoesHome informacoesHome;
        Empresa empresa;
        EmpresaDAO empresaDAO = new EmpresaDAO();

        Object id = session.getAttribute("idEmpresa"); //Pega o id da empresa pela sessão

        //Se o id for nulo retorna pra Landing
        if (id == null) {
            res.sendRedirect("/");
            return;
        }
        long idNum = (long) id;

        //Pega a empresa e as informações da home pelo id
        empresa = empresaDAO.buscarPorId(idNum);
        informacoesHome = FiltrosDAO.informacoesHome(idNum);

        //Se a empresa for nula retorna pra Landing
        if (empresa == null){
            res.sendRedirect("/");
            return;
        }

        session.setAttribute("empresa", empresa); //Seta um atributo empresa na sessão com o objeto da empresa

        //Se as informações da home não forem nulas seta o atributo
        if (informacoesHome != null) {
            req.setAttribute("informacoesHome", informacoesHome);
        }

        req.setAttribute("empresa", empresa); //Seta o atributo empresa
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, res);

    }

}
