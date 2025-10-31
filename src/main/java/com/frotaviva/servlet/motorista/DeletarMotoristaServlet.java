package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;

import com.frotaviva.exception.ErroAoDeletar;
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

            if (dao.deletar(id) == 1) {
                System.out.println("Deu certo deletar");
                response.sendRedirect("/listar-motoristas?msg=Motorista+deletado+com+sucesso");
                return;
            }

            request.setAttribute("mensagem", "Erro ao deletar motoristas. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);

        } catch (ErroAoDeletar e) {
            request.setAttribute("mensagem", "Erro ao deletar motoristas. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }

}
