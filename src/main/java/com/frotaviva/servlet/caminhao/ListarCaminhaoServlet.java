package com.frotaviva.servlet.caminhao;

import com.frotaviva.dao.CaminhaoDAO;
import com.frotaviva.exception.ErroAoConsultar;
import com.frotaviva.model.Caminhao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarCaminhao", value = "/listar-caminhao")
public class ListarCaminhaoServlet extends HttpServlet {

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

        CaminhaoDAO dao = new CaminhaoDAO();

        try{

            List<Caminhao> caminhoes;

            if (buscar != null && !buscar.isBlank()){
                System.out.println("Tem buscar");
                caminhoes = dao.buscarPorEmpresaComPlaca(idEmpresa, buscar);
            } else {
                System.out.println("Não tem buscar");
                caminhoes = dao.buscarPorEmpresa(idEmpresa);
            }

            request.setAttribute("caminhoes", caminhoes);
            request.getRequestDispatcher("WEB-INF/view/caminhao/listar-caminhao.jsp").forward(request, response);
        } catch (ErroAoConsultar e) {
            request.setAttribute("mensagem", "Erro ao listar caminhao. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}
