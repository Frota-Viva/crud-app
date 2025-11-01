package com.frotaviva.servlet.entrega;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.exception.ErroAoDeletar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeletarServlet", value = "/deletar-entrega")
public class DeletarEntregaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        MotoristaDAO dao = new MotoristaDAO();

        try{

            if (dao.deletar(id) == 1){
                response.sendRedirect("/listar-entregas?msg=Entrega deletada com sucesso!");
                return;
            }

            response.sendRedirect("/listar-entregas?msg=Erro ao deletar entrega. Tente novamente mais tarde.");

        } catch (ErroAoDeletar e) {
            response.sendRedirect("/listar-entregas?msg=Erro ao inserir entrega: " + e.getMessage());
        } catch (NumberFormatException e) {
            response.sendRedirect("/listar-entregas?msg=Formato inválido inserido: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            response.sendRedirect("/listar-entregas?msg=Data inválida: " + e.getMessage());
        } catch (Exception e) {
            response.sendRedirect("/listar-entregas?msg=Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}
