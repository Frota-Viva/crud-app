package com.frotaviva.servlet.perfil;

import com.frotaviva.dao.EmpresaDAO;
import com.frotaviva.exception.ErroAoDeletar;
import com.frotaviva.model.Empresa;
import com.frotaviva.util.Senhas;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(value = "/home/perfil/excluir-conta")
public class ExcluirContaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        Object empresaSession = session.getAttribute("empresa");
        Empresa empresaPagina = (Empresa) empresaSession;
        request.setAttribute("empresa", empresaPagina);
        request.getRequestDispatcher("/WEB-INF/view/excluir-Perfil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            EmpresaDAO empresaDAO = new EmpresaDAO();
            HttpSession session = request.getSession(true);

            Object idSession = session.getAttribute("idEmpresa");

            if (idSession == null) {
                response.sendRedirect("/");
                return;
            }

            long idEmpresa = (long) idSession;
            Object empresaSession = session.getAttribute("empresa");
            Empresa empresaPagina = (Empresa) empresaSession;
            request.setAttribute("empresa", empresaPagina);

            String senhaRecebida = request.getParameter("senha");

            String senhaBD = Senhas.getSenhaHash(idEmpresa);

            if (!Senhas.verificarSenha(senhaRecebida,senhaBD)){
                request.setAttribute("erroSenha","Senha incorreta!");
                request.getRequestDispatcher("/WEB-INF/view/excluir-Perfil.jsp").forward(request, response);
                return;
            }
            if (empresaDAO.deletar(idEmpresa) == 1){
                response.sendRedirect("/login?exclusaoConta=Conta excluída com sucesso!");
                return;
            } else {
                request.setAttribute("erro","Não foi possível excluir!");
                request.getRequestDispatcher("/WEB-INF/view/excluir-Perfil.jsp").forward(request, response);
                return;
            }
        } catch (ErroAoDeletar ead){
            response.sendRedirect("/home?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");
        } catch (Exception e){
            response.sendRedirect("/home?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }


    }
}
