package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.model.Motorista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListaMotoristas", value = "/listar-motoristas")
public class ListarMotoristasServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try{

            HttpSession session = request.getSession(true); //Pega a sessão
            Object id = session.getAttribute("idEmpresa"); //Pega o id da empresa na sessão

            String buscar = request.getParameter("buscar");

            //Verifica se o id existe
            if (id == null){
                response.sendRedirect("/");
                return;
            }

            long idEmpresa = (long) id;

            MotoristaDAO dao = new MotoristaDAO();
            List<Motorista> motoristas;
            if (buscar != null && !buscar.isBlank()){
                motoristas = dao.buscarPorEmpresaComNome(idEmpresa, buscar);
            } else {
                motoristas = dao.buscarPorEmpresa(idEmpresa);
            }

            request.setAttribute("motoristas", motoristas);
            request.getRequestDispatcher("WEB-INF/view/motorista/listar-motorista.jsp").forward(request, response);

        } catch (Exception e){
            request.setAttribute("erro", "Erro ao tentar buscar motoristas");
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }

    }
}
