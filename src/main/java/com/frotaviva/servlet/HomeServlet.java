package com.frotaviva.servlet;
import com.frotaviva.dao.EmpresaDAO;
import com.frotaviva.dao.FiltrosDAO;
import com.frotaviva.model.Empresa;
import com.frotaviva.model.InformacoesHome;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Responsável por gerenciar a página inicial (home) da empresa logada.
 * <p>
 * Verifica se a empresa está logada através da sessão, obtém as informações da home e
 * encaminha os dados para a página home.jsp.
 * </p>
 * 
 * @author Ricardo
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    /**
     * Recebe uma requisição GET, verifica a sessão da empresa, busca as informações da home
     * e encaminha para a página home.jsp.
     * <p>
     * Se não houver sessão ativa ou a empresa não existir, o usuário é redirecionado para a página landing page.
     * </p>
     *
     * @param req objeto da requisição HTTP
     * @param res objeto da resposta HTTP
     * @throws ServletException se ocorrer um erro de servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(true); //Pega a sessão ou cria uma se não existir

        InformacoesHome informacoesHome;
        Empresa empresa;
        EmpresaDAO empresaDAO = new EmpresaDAO();

        Object id = session.getAttribute("idEmpresa"); //Pega o id da empresa pela sessão

        //Se o id for nulo retorna pra Landing
        if (id == null) {
            res.sendRedirect("/");
            return;
        }
        long idNum = (long) id;

        //Pega a empresa pelo id
        empresa = empresaDAO.buscarPorId(idNum);

        //Se a empresa for nula retorna pra Landing
        if (empresa == null){
            res.sendRedirect("/");
            return;
        }
        session.setAttribute("empresa", empresa); //Seta um atributo empresa na sessão com o objeto da empresa

        //Pega as informações da home pelo id
        informacoesHome = FiltrosDAO.informacoesHome(idNum);

        //Se as informações da home não forem nulas seta o atributo, senão seta um atributo de erro
        if (informacoesHome != null) {
            req.setAttribute("informacoesHome", informacoesHome);
        }

        req.setAttribute("empresa", empresa); //Seta o atributo empresa
        req.getRequestDispatcher("WEB-INF/view/home.jsp").forward(req, res);

    }
}
