package com.frotaviva.servlet;
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
 * Responsável por gerenciar o login das empresas no sistema
 * <p>
 * Recebe os dados d de login, valida as credenciais com o banco de dados
 * e cria a sessão da empresa se der sucesso, senão retorna um erro
 * e redireciona novamente para a página de login.
 * </p>
 * 
 * @author Ricardo
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    /**
     * Recebe uma requisição POST, valida as credenciais da empresa e cria a sessão.
     * <p>
     * Se o login der certo, o usuário é redirecionado para a página inicial.
     * Caso contrário, é exibida uma mensagem de erro na tela de login.
     * </p>
     *
     * @param req objeto da requisição HTTP
     * @param res objeto da resposta HTTP
     * @throws ServletException se ocorrer um erro de servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        EmpresaDAO empresaDAO = new EmpresaDAO();

        Empresa empresa = empresaDAO.getEmpresa(email, senha);

        if (empresa != null) {

            session.setAttribute("idEmpresa", empresa.getId());
            res.sendRedirect("/home");

        } else {

            req.setAttribute("erro", "Email ou senha incorretas.");
            req.getRequestDispatcher("WEB-INF/view/login.jsp").forward(req, res);

        }
    }

    /**
     * Recebe uma requisição GET e redireciona o usuário para a página de login.
     *
     * @param req objeto da requisição HTTP
     * @param res objeto da resposta HTTP
     * @throws ServletException se ocorrer um erro de servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/login.jsp").forward(req, res);
    }
}
