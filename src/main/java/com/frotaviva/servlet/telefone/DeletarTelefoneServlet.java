package com.frotaviva.servlet.telefone;

import com.frotaviva.dao.MotoristaDAO;

import com.frotaviva.dao.TelefoneMotoristaDAO;
import com.frotaviva.exception.ErroAoInserir;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteTelefone", value = "/deletar-telefone")
public class DeletarTelefoneServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id;
        id = Long.parseLong(request.getParameter("id"));

        try{
            TelefoneMotoristaDAO dao = new TelefoneMotoristaDAO();

            if (dao.deletar(id) == 1) {
                response.sendRedirect("listar-telefones");
                return;
            }

            request.setAttribute("mensagem", "Erro ao deletar telefones. Tente novamente mais tarde.");
            request.getRequestDispatcher("WEB-INF/view/motorista/listar-telefones.jsp").forward(request, response);

        } catch (ErroAoInserir e) {
            request.setAttribute("mensagem", "Erro ao deletar telefones. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}
