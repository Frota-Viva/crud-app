package com.frotaviva.servlet.telefone;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.dao.TelefoneMotoristaDAO;
import com.frotaviva.exception.ErroAoConsultar;
import com.frotaviva.model.Motorista;

import com.frotaviva.model.TelefoneMotorista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListaTelefones", value = "/listar-telefones")
public class ListarTelefoneServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true); //Pega a sessão
        Object id = session.getAttribute("idEmpresa"); //Pega o id da empresa na sessão

        //Verifica se o id existe
        if (id == null){
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id;

        try{

            TelefoneMotoristaDAO dao = new TelefoneMotoristaDAO();
            List<TelefoneMotorista> telefones = dao.buscarPorEmpresa(idEmpresa);

            request.setAttribute("telefones", telefones);
            request.getRequestDispatcher("WEB-INF/view/motorista/listar-telefones.jsp").forward(request, response);

        } catch (ErroAoConsultar e) {
            request.setAttribute("mensagem", "Erro ao encontrar telefones. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}
