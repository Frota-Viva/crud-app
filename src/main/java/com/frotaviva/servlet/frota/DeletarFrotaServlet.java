package com.frotaviva.servlet.frota;

import com.frotaviva.dao.FrotaDAO;
import com.frotaviva.exception.ErroAoDeletar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Responsável por excluir um registro de uma frota específica do banco de dados.
 * <p>
 * Este servlet recebe o ID da frota via parâmetro de requisição e realiza
 * a exclusão do registro correspondente no banco de dados utilizando {@link FrotaDAO}.
 * </p>
 * <p>
 * Principais funcionalidades:
 * <ul>
 *     <li>Obtém o ID da frota a partir dos parâmetros da requisição;</li>
 *     <li>Utiliza o {@link FrotaDAO} para deletar o registro;</li>
 *     <li>Redireciona o usuário para a listagem de frotas se a exclusão for bem-sucedida;</li>
 *     <li>Exibe mensagens de erro na página de listagem se houver falha durante o processo.</li>
 * </ul>
 * </p>
 * 
 * @author Davi Alcanfor
 */
@WebServlet(name = "DeletFrota", value = "/deletar-frota")
public class DeletarFrotaServlet extends HttpServlet {

    /**
     * Processa a requisição GET para deletar uma frota.
     * <p>
     * Se a exclusão for realizada com sucesso, redireciona para a listagem de frotas.
     * Se ocorrer algum erro, encaminha para a página de listagem com a mensagem apropriada.
     * </p>
     * 
     * @param request  requisição HTTP recebida contendo o ID da frota
     * @param response resposta HTTP enviada ao cliente
     * @throws ServletException se ocorrer um erro no processamento do servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Object id_empresa = session.getAttribute("idEmpresa");

        if (id_empresa == null) {
            response.sendRedirect("/");
            return;
        }

        long id = Long.parseLong(request.getParameter("id"));

        try {
            FrotaDAO dao = new FrotaDAO();

            if (dao.deletar(id) == 1) {
                response.sendRedirect("/listar-frota");
                return;
            }

            request.setAttribute("mensagem", "Erro ao deletar frota. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);

        } catch (ErroAoDeletar e) {
            request.setAttribute("mensagem", "Erro ao deletar frota. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}
