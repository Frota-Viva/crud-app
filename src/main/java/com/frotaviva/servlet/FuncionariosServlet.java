package com.frotaviva.servlet;
import com.frotaviva.dao.FiltrosDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.util.List;
import java.util.Map;

@WebServlet("/home/funcionarios")
public class FuncionariosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(true); //Pega a sessão

        Object id = session.getAttribute("idEmpresa"); //Pega o id da empresa na sessão

        //Verifica se o id existe
        if (id == null){
            res.sendRedirect("/");
            return;
        }

        String pagina = req.getParameter("pagina"); //Pega a página como parâmetro da URL
        System.out.println(pagina);

        //Verifica se a página está na URL
        if (pagina == null){
            pagina = "1";
        }

        int paginaAtual = Integer.parseInt(pagina);
        //Verifica se o valor recebido é menor que 1
        if (paginaAtual < 1){
            res.sendRedirect("/home/funcionarios");
            return;
        }

        long idEmpresa = (long) id;
        int offset = ( paginaAtual - 1 ) * 9;

        System.out.println(paginaAtual);

        //Monta os perfis de cada motorista da empresa e seta o atributo na request
        List<Map<String, String>> perfisMotoristas = FiltrosDAO.perfisMotoristas(idEmpresa, offset);
        req.setAttribute("perfisMotoristas", perfisMotoristas);
        req.setAttribute("paginaAtual", paginaAtual);


        req.getRequestDispatcher("/WEB-INF/view/funcionarios.jsp").forward(req, res);
    }
}