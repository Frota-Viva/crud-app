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

        if (id == null){
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id;

        try{
            CaminhaoDAO dao = new CaminhaoDAO();

            List<Caminhao> caminhoes = dao.buscarPorEmpresa(idEmpresa);

            request.setAttribute("caminhoes", caminhoes);
            request.getRequestDispatcher("WEB-INF/view/caminhao/listar-caminhao.jsp").forward(request, response);

        } catch (ErroAoConsultar e) {
            response.sendRedirect("/home?msg=Erro ao consultar motoristas");
        } catch (Exception e) {
            response.sendRedirect("/home?msg=Ocorreu um erro desconhecido");
        }
    }
}
