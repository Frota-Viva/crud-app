package com.frotaviva.servlet.telefone;

import com.frotaviva.dao.TelefoneMotoristaDAO;
import com.frotaviva.exception.ErroAoConsultar;
import com.frotaviva.model.TelefoneMotorista;
import com.frotaviva.util.Validar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AtualizarTelefones", value = "/atualizar-telefones")
public class AtualizarTelefoneServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id;
        long id_motorista;
        String telefone;

        id = Long.parseLong(request.getParameter("id"));
        id_motorista = Long.parseLong(request.getParameter("id_motorista"));
        telefone = request.getParameter("telefone");

        if (!Validar.telefone(telefone)) {
            request.setAttribute("erro", "Telefone inválido!");
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        } else {
            telefone = Validar.telefoneValidado(telefone);
        }

        if (id_motorista <= 0) {
            request.setAttribute("erro", "ID Motorista inválido! Não pode ser menor ou igual a zero");
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }

        TelefoneMotoristaDAO dao = new TelefoneMotoristaDAO();
        TelefoneMotorista telefoneMotorista = dao.buscarPorId(id);

        telefoneMotorista.setIdMotorista(id_motorista);
        telefoneMotorista.setTelefoneMotorista(telefone);

        try {
            if (dao.atualizar(telefoneMotorista) == 1) {
                response.sendRedirect("/listar-motorista.jsp");
                return;
            }

            request.setAttribute("mensagem", "Erro ao atualizar telefones. Tente novamente mais tarde.");
            request.getRequestDispatcher("WEB-INF/view/motorista/listar-telefones.jsp").forward(request, response);

        } catch (ErroAoConsultar e) {
            request.setAttribute("mensagem", "Erro ao atualizar telefones. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");

        }
    }
}
