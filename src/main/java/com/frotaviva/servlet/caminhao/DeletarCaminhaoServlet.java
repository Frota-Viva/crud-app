package com.frotaviva.servlet.caminhao;

import com.frotaviva.dao.CaminhaoDAO;
import com.frotaviva.exception.ErroAoDeletar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Responsável por remover um caminhão do banco de dados com base eno seu ID.
 * <p>
 * Este servlet realiza a exclusão de um caminhão a partir do ID informado na requisição.
 * Ele verifica se o usuário está logado e garante que a operação só seja executada
 * por um usuário autenticado.
 * </p>
 *
 * <p>
 * Funcionalidades principais:
 * </p>
 * 
 * <ul>
 *     <li>Valida se o usuário está logado através da sessão;</li>
 *     <li>Obtém o ID do caminhão a ser deletado;</li>
 *     <li>Executa a exclusão por meio do {@link CaminhaoDAO};</li>
 *     <li>Redireciona com uma mensagem de sucesso ou erro conforme o resultado.</li>
 * </ul>
 * </p>
 *
 * @author Davi
 */
@WebServlet(name = "DeleteCaminhao", value = "/deletar-caminhao")
public class DeletarCaminhaoServlet extends HttpServlet {

    /**
     * Processa as requisições GET responsáveis por excluir um caminhão.
     * <p>
     * Se o usuário não possuir uma sessão válida, ele é redirecionado para a página inicial.
     * Se o caminhão for excluído com sucesso, o usuário é redirecionado para a página
     * de listagem com uma mensagem confirmando a exclusão.
     * </p>
     * 
     * <p>
     * Seo usuário não estiver logado ele é redirecionado para a landing page.
     * </p>
     *
     * <p>
     * O método realiza as seguintes etapas:
     * <ul>
     *     <li>Recupera o ID da empresa da sessão;</li>
     *     <li>Verifica se há sessão ativa;</li>
     *     <li>Obtém o ID do caminhão a partir dos parâmetros da requisição;</li>
     *     <li>Realiza a exclusão no banco de dados por meio do {@link CaminhaoDAO};</li>
     *     <li>Encaminha ou redireciona conforme o sucesso da operação.</li>
     * </ul>
     * </p>
     *
     * @param request requisição HTPP recebida
     * @param response requisição HTTP enviada ao cliente
     * @throws ServletException se ocorrer um erro ao encaminhar a requisição
     * @throws IOException se ocorrer falha na comunicação com o cliente
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true); // Pega a sessão
        Object idEmpresa = session.getAttribute("Empresa");  // Pega o id da empresa na sessão

        if (idEmpresa == null) {
            response.sendRedirect("/");
            return;
        }

        long id = Long.parseLong(request.getParameter("id"));
        CaminhaoDAO dao = new CaminhaoDAO();

        try {

            if (dao.deletar(id) == 1) {
                response.sendRedirect("/listar-caminhao?msg=Caminhao deletado com sucesso.");
                return;
            }

            request.setAttribute("msg", "Erro ao deletar caminhao. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/listar-caminhao.jsp").forward(request, response);

        } catch (ErroAoDeletar e) {
            response.sendRedirect("/listar-caminhao?msg=Erro ao deletar caminhao. Tente novamente mais tarde.");
        } catch (Exception e) {
            response.sendRedirect("/listar-caminhao?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }
    }

}
