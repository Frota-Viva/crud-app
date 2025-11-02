package com.frotaviva.servlet.perfil;

import com.frotaviva.dao.EmpresaDAO;
import com.frotaviva.model.Empresa;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Responsável por exibir as informações do perfil da empresa logada.
 * <p>
 * Este servlet verifica se há uma empresa na sessão ou nos atributos da requisição.
 * Caso não haja, ele busca a empresa no banco de dados com base no ID armazenado na sessão.
 * Se nenhuma empresa for encontrada, o usuário é redirecionado para a página inicial.
 * </p>
 * <p>
 * Quando a empresa é encontrada, o usuário é redirecionado para a página
 * perfil.jsp, onde os dados são exibidos para visualização ou edição.
 * </p>
 * 
 * @author Lucas Cayres
 */
@WebServlet("/home/perfil")
public class PerfilServlet extends HttpServlet {

    /**
     * Processa uma requisição GET para exibir os dados do perfil da empresa logada.
     * <p>
     * O método realiza as seguintes operações:
     * <ul>
     *     <li>Verifica se já existe um objeto <code>empresa</code> na requisição;</li>
     *     <li>Caso não exista, tenta recuperar a empresa pelo ID armazenado na sessão;</li>
     *     <li>Se não houver sessão ativa ou a empresa não for encontrada, redireciona para a página inicial;</li>
     *     <li>Encaminha os dados da empresa para a página perfil.jsp para exibição.</li>
     * </ul>
     * </p>
     *
     * @param request objeto da requisição HTTP
     * @param response objeto da resposta HTTP
     * @throws ServletException se ocorrer um erro de servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Empresa empresa;

        Object empresaSession = request.getAttribute("empresa");

        // Caso não haja empresa nos atributos da requisição, tenta obtê-la pelo ID da sessão
        if (empresaSession == null) {
            EmpresaDAO empresaDAO = new EmpresaDAO();
            Object idSession = session.getAttribute("idEmpresa");

            // Se o ID da empresa não estiver na sessão, redireciona para a página inicial
            if (idSession == null) {
                response.sendRedirect("/");
                return;
            }

            long idEmpresa = (long) idSession;
            empresa = empresaDAO.buscarPorId(idEmpresa);

            // Se a empresa não for encontrada no banco, redireciona para a página inicial
            if (empresa == null) {
                response.sendRedirect("/");
                return;
            }

            request.setAttribute("empresa", empresa);
        } else {
            // Caso a empresa já esteja na requisição, apenas a reaproveita
            empresa = (Empresa) empresaSession;
            request.setAttribute("empresa", empresa);
        }

        // Encaminha para a página de perfil
        request.getRequestDispatcher("/WEB-INF/view/perfil.jsp").forward(request, response);
    }
}
