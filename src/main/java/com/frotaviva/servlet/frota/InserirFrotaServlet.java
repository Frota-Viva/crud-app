package com.frotaviva.servlet.frota;

import com.frotaviva.dao.FrotaDAO;
import com.frotaviva.model.Frota;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        long idEmpresa;

        try{

            FrotaDAO dao = new FrotaDAO();

            tamanhoFrota = Integer.parseInt(request.getParameter("tamanhoFrota"));
            tipoFrota = request.getParameter("tipoFrota");
            regiao = request.getParameter("regiao");
            idEmpresa = Long.parseLong(request.getParameter("idEmpresa"));

            Frota frota = new Frota(tamanhoFrota, tipoFrota, regiao, idEmpresa);

            if (dao.inserir(frota) == 1){
                response.sendRedirect("/listar-frota");
            }
            response.sendRedirect("/listar-frota");

        } catch (Exception e){ // ainda nao tem a pagina de erro
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}