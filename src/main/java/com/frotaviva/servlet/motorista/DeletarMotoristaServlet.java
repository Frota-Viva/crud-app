package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;

import com.frotaviva.exception.ErroAoDeletar;
import com.frotaviva.exception.ErroAoInserir;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteMotorista", value = "/deletar-motorista")
public class DeletarMotoristaServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id;

        try{

            id = Long.parseLong(request.getParameter("id"));
            MotoristaDAO dao = new MotoristaDAO();

            if (dao.deletar(id) != 1) {
                request.setAttribute("mensagem", "Erro ao deletar motoristas. Tente novamente mais tarde.");
                request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
                return;
            }

                response.sendRedirect("/listar-motoristas?msg=Motorista+deletado+com+sucesso");

        } catch (ErroAoDeletar e) {
            response.sendRedirect("/listar-motorista?msg=Erro ao deletar motorista. Tente novamente mais tarde");
        } catch (Exception e) {
            response.sendRedirect("/listar-motorista?msg=Ocorreu+um+erro+inesperado+ao+deletar+motorista.+Tente+novamente+mais+tarde");
        }
    }

}
