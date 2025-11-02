package com.frotaviva.servlet.manutencao;

import com.frotaviva.dao.ManutencaoDAO;
import com.frotaviva.exception.ErroAoDeletar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteManutencao", value = "/deletar-manutencao")
public class DeletarManutencaoServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        try{

            ManutencaoDAO dao = new ManutencaoDAO();

            if (dao.deletar(id) == 1) {
                response.sendRedirect("/listar-manutencao?msg=Manutencao deletada com sucesso");
                return;
            }

            response.sendRedirect("/listar-manutencao?msg=Nao foi possivel deletar manutencao.");

        } catch (ErroAoDeletar e) {
            response.sendRedirect("/listar-manutencao?msg=Ocorreu um erro ao deletar mantuencao.");

        } catch (Exception e) {
            response.sendRedirect("/listar-manutencao?msg=Ocorreu um erro inesperado ao deletar mantuencao.");

        }
    }

}