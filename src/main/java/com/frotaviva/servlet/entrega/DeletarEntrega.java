package com.frotaviva.servlet.entrega;

import com.frotaviva.dao.EntregaDAO;
import com.frotaviva.exception.ErroAoDeletar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet responsável por deletar uma entrega do banco de dados.
 * <p>
 * Este servlet recebe o ID da entrega como parâmetro, verifica se a operação
 * é possível e realiza a exclusão da entrega.
 * </p>
 * <p>
 * Em caso de sucesso, redireciona para a listagem de entregas com mensagem de confirmação.
 * Em caso de falha ou erro inesperado, redireciona com mensagem específica.
 * </p>
 * 
 * @author Ricardo
 */
@WebServlet(name = "DeletarServlet", value = "/deletar-entrega")
public class DeletarEntrega extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        try {
            EntregaDAO dao = new EntregaDAO();

            if (dao.deletar(id) == 1) {
                response.sendRedirect("/listar-entregas?msg=Entrega deletada com sucesso!");
                return;
            }

            response.sendRedirect("/listar-entregas?msg=Erro ao deletar entrega. Tente novamente mais tarde.");

        } catch (ErroAoDeletar e) {
            response.sendRedirect("/listar-entregas?msg=Erro ao deletar entrega: " + e.getMessage());
        } catch (Exception e) {
            response.sendRedirect("/listar-entregas?msg=Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}
