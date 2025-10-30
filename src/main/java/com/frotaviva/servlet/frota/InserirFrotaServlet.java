package com.frotaviva.servlet.frota;

import com.frotaviva.dao.FrotaDAO;
import com.frotaviva.exception.ErroAoConsultar;
import com.frotaviva.exception.ErroAoInserir;
import com.frotaviva.model.Frota;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

@WebServlet(name = "InserirFrota", value = "/inserir-frota")
public class InserirFrotaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int tamanhoFrota;
        String tipoFrota;
        String regiao;

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

            tamanhoFrota = Integer.parseInt(request.getParameter("tamanhoFrota"));
            tipoFrota = request.getParameter("tipoFrota");
            regiao = request.getParameter("regiao");

            Frota frota = new Frota(tamanhoFrota, tipoFrota, regiao, idEmpresa);

            if (dao.inserir(frota) == 1){
                response.sendRedirect("/listar-frota");
                return;
            }
            request.setAttribute("mensagem", "Erro ao inserir frota. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);

        } catch (ErroAoInserir e) {
            request.setAttribute("mensagem", "Erro ao inserir frota. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}