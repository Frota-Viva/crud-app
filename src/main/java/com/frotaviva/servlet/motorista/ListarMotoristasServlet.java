package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;
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

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try{

            MotoristaDAO dao = new MotoristaDAO();

            List<Motorista> motoristas = dao.buscarTodos();

            request.setAttribute("motoristas", motoristas);
            request.getRequestDispatcher("WEB-INF/view/listar-motoristas.jsp").forward(request, response);

        } catch (Exception e){ // ainda nao tem a pagina de erro
            request.setAttribute("erro", "Erro ao tentar buscar motoristas");
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}
