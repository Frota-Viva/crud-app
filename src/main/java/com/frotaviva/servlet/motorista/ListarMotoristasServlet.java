package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.exception.ErroAoConsultar;
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


        HttpSession session = request.getSession(true); //Pega a sessão
        Object id = session.getAttribute("idEmpresa"); //Pega o id da empresa na sessão

        String buscar = request.getParameter("buscar");

        //Verifica se o id existe
        if (id == null){
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id;

        try{
            MotoristaDAO dao = new MotoristaDAO();
            List<Motorista> motoristas;
            if (buscar != null && !buscar.isBlank()){
                System.out.println("Tem buscar");
                motoristas = dao.buscarPorEmpresaComNome(idEmpresa, buscar);
            } else {
                System.out.println("entru pra pegar motoristas");
                motoristas = dao.buscarPorEmpresa(idEmpresa);
            }
            System.out.println("Pegou motoristas");
            request.setAttribute("motoristas", motoristas);
            System.out.println("setou motoristas");
            request.getRequestDispatcher("WEB-INF/view/motorista/listar-motorista.jsp").forward(request, response);

        } catch (ErroAoConsultar e) {
            request.setAttribute("mensagem", "Erro ao encontrar motoristas. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }

    }
}
