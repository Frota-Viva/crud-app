package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.exception.ErroAoDeletar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Responsável por excluir um motorista do sistema.
 * <p>
 * Este servlet realiza a exclusão de um motorista a partir do ID recebido via requisição.
 * </p>
 * <p>
 * Principais funcionalidades:
 * <ul>
 *     <li>Recebe o ID do motorista a ser excluído através do parâmetro da requisição;</li>
 *     <li>Utiliza o {@link MotoristaDAO} para remover o registro correspondente no banco de dados;</li>
 *     <li>Redireciona para a listagem de motoristas com mensagens de sucesso ou erro conforme o resultado.</li>
 * </ul>
 * </p>
 *
 * @author Davi Alcanfor
 */
@WebServlet(name = "DeleteMotorista", value = "/deletar-motorista")
public class DeletarMotoristaServlet extends HttpServlet {

    /**
     * Realiza a exclusão de um motorista de acordo com o ID passado como parâmetro.
     * <p>
     * Principais etapas:
     * <ul>
     *     <li>Obtém o ID do motorista a partir dos parâmetros da requisição;</li>
     *     <li>Chama o método {@code deletar()} do {@link MotoristaDAO} para remover o registro;</li>
     *     <li>Redireciona para a listagem de motoristas se a exclusão for bem-sucedida;</li>
     *     <li>Encaminha o usuário para a página de erro se ocorrer alguma falha durante o processo.</li>
     * </ul>
     * </p>
     * <p>
     * O servlet também trata exceções específicas ({@link ErroAoDeletar}) e genéricas,
     * exibindo mensagens apropriadas ao usuário se algo inesperado ocorrer.
     * </p>
     *
     * @param request  requisição HTTP contendo o ID do motorista a ser excluído
     * @param response resposta HTTP a ser enviada ao cliente
     * @throws ServletException se ocorrer um erro interno no servlet
     * @throws IOException se ocorrer um erro de entrada ou saída durante o redirecionamento
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id;

        try {
            id = Long.parseLong(request.getParameter("id"));
            MotoristaDAO dao = new MotoristaDAO();

            if (dao.deletar(id) != 1) {
                request.setAttribute("mensagem", "Erro ao deletar motorista. Tente novamente mais tarde.");
                request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
                return;
            }

            response.sendRedirect("/listar-motoristas?msg=Motorista+deletado+com+sucesso");

        } catch (ErroAoDeletar e) {
            response.sendRedirect("/listar-motoristas?msg=Erro+ao+deletar+motorista.+Tente+novamente+mais+tarde");
        } catch (Exception e) {
            response.sendRedirect("/listar-motoristas?msg=Ocorreu+um+erro+inesperado+ao+deletar+motorista.+Tente+novamente+mais+tarde");
        }
    }
}
