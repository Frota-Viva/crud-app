package com.frotaviva.servlet.manutencao;

import com.frotaviva.dao.ManutencaoDAO;
import com.frotaviva.model.Manutencao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

@WebServlet(name = "InserirManutencao", value = "/inserir-manutencao")
public class InserirManutencaoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id;
        Date dtCadastro;
        Date dtConclusao;
        String descricao;
        BigDecimal custo;
        long ultimoMotorista;
        long idCaminhao;
        String tipoManutencao;

        try{

            ManutencaoDAO dao = new ManutencaoDAO();

            id = Long.parseLong(request.getParameter("id"));
            dtCadastro = Date.valueOf(request.getParameter("dtCadastro"));
            dtConclusao = Date.valueOf(request.getParameter("dtConclusao"));
            descricao = request.getParameter("descricao");
            custo = new BigDecimal(request.getParameter("custo"));
            ultimoMotorista = Long.parseLong(request.getParameter("ultimoMotorista"));
            idCaminhao = Long.parseLong(request.getParameter("idCaminhao"));
            tipoManutencao = request.getParameter("tipoManutencao");


            Manutencao manutencao = new Manutencao(id, dtCadastro, dtConclusao, tipoManutencao,
                    custo, ultimoMotorista, descricao, idCaminhao);

            if (dao.inserir(manutencao) == 1){
                response.sendRedirect("listar-manutencao");
            }
            response.sendRedirect("listar-manutencao");

        } catch (Exception e){ // ainda nao tem a pagina de erro
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}