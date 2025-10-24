package com.frotaviva.servlet.telefone;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.dao.TelefoneMotoristaDAO;
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

        try{

            TelefoneMotoristaDAO dao = new TelefoneMotoristaDAO();

            List<TelefoneMotorista> telefones = dao.buscarTodos();

            request.setAttribute("telefones", telefones);
            request.getRequestDispatcher("WEB-INF/view/listar-telefones.jsp").forward(request, response);

        } catch (Exception e){ // ainda nao tem a pagina de erro
            request.getRequestDispatcher("WEB-INF/view/listar-telefones.jsp").forward(request, response);
        }
    }
}
