package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.dao.TelefoneMotoristaDAO;
import com.frotaviva.model.Motorista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListaMotoristas", value = "/listar-motoristas")
public class ListarMotoristasServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MotoristaDAO dao = new MotoristaDAO();
        TelefoneMotoristaDAO telefoneDAO = new TelefoneMotoristaDAO();

        try{
            List<Motorista> motoristas = dao.buscarTodos();

            request.setAttribute("motoristas", motoristas);
            request.setAttribute("telefones", telefoneDAO);

            request.getRequestDispatcher("WEB-INF/view/listar-motoristas.jsp").forward(request, response);

        } catch (Exception e){ // ainda nao tem a pagina de erro
        }
    }
}
