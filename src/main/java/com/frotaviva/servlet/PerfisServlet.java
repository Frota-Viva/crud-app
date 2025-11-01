package com.frotaviva.servlet;
import com.frotaviva.dao.FiltrosDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Responsável por gerenciar a exibição dos perfis dos motoristas da empresa na página principal
 * <p>
 * Verifica se há uma sessão ativa com o ID da empresa, busca os perfis dos motoristas de acordo
 * com a página e o termo de busca informados, e encaminha as informações para a página perfis.jsp.
 * </p>
 * 
 * @author Ricardo
 */

@WebServlet("/home/perfis")
public class PerfisServlet extends HttpServlet {

    /**
     * Recebe uma requisição GET, verifica a sessão da empresa e monta a lista de perfis
     * dos motoristas para exibição na página perfis.jsp.
     * <p>
     * Caso não exista sessão ativa, o usuário é redirecionado para a página inicial.
     * </p>
     *
     * @param req objeto da requisição HTTP
     * @param res objeto da resposta HTTP
     * @throws ServletException se ocorrer um erro de servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(true); //Pega a sessão

        Object id = session.getAttribute("idEmpresa"); //Pega o id da empresa na sessão

        //Verifica se o id existe
        if (id == null){
            res.sendRedirect("/");
            return;
        }

        String pagina = req.getParameter("pagina"); //Pega a página como parâmetro da URL
        String buscar = req.getParameter("buscar");

        //Verifica se a página está na URL
        if (pagina == null){
            pagina = "1";
        }

        try {
            int paginaAtual = Integer.parseInt(pagina);

            //Verifica se o valor recebido é menor que 1
            if (paginaAtual < 1){
                res.sendRedirect("/home/perfis");
                return;
            }

            long idEmpresa = (long) id;
            int offset = ( paginaAtual - 1 ) * 9;

            //Monta os perfis de cada motorista da empresa e seta o atributo na request
            List<Map<String, String>> perfisMotoristas;

            if (buscar != null && !buscar.isBlank()){
                perfisMotoristas = FiltrosDAO.buscarPerfisPorNome(idEmpresa, buscar, offset);
            } else {
                perfisMotoristas = FiltrosDAO.perfisMotoristas(idEmpresa);
            }
            req.setAttribute("perfisMotoristas", perfisMotoristas);
            req.setAttribute("paginaAtual", paginaAtual);

            req.getRequestDispatcher("/WEB-INF/view/perfis.jsp").forward(req, res);

        } catch (NumberFormatException e){
            res.sendRedirect("/home/perfis");
        }
    }
}
