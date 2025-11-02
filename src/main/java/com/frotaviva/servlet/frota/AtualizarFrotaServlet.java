package com.frotaviva.servlet.frota;

import com.frotaviva.dao.FrotaDAO;
import com.frotaviva.exception.ErroAoAtualizar;
import com.frotaviva.model.Frota;
import com.frotaviva.util.Validar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Responsável por atualizar os dados de uma frota cadastrada.
 * <p>
 * Este servlet exibe os dados da frota para atualização e processa as alterações
 * enviadas pelo usuário, validando os campos antes de atualizar o registro no banco.
 * </p>
 * <p>
 * Principais funcionalidades:
 * <ul>
 *     <li><b>GET:</b> Exibe o formulário com os dados atuais da frota;</li>
 *     <li><b>POST:</b> Valida e atualiza os dados da frota;</li>
 *     <li>Redireciona o usuário com mensagens de sucesso ou erro conforme o resultado da operação.</li>
 * </ul>
 * </p>
 * <p>
 * Se não houver uma empresa logada, o usuário é redirecionado para a landing page.
 * Se ocorrer algum erro durante a atualização, são exibidas mensagens específicas.
 * </p>
 * 
 * @author Davi Alcanfor
 */
@WebServlet(name = "AtualizarFrota", value = "/atualizar-frota")
public class AtualizarFrotaServlet extends HttpServlet {

    /**
     * Exibe o formulário de atualização da frota.
     * <p>
     * Obtém o ID da frota a partir dos parâmetros da requisição e busca o registro no banco.
     * Se o ID for inválido ou a frota não existir, redireciona para a listagem de frotas.
     * </p>
     *
     * @param req  requisição HTTP recebida
     * @param resp resposta HTTP enviada
     * @throws ServletException se ocorrer um erro no processamento do servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id;
        FrotaDAO dao = new FrotaDAO();

        HttpSession session = req.getSession(true);
        Object id_empresa = session.getAttribute("idEmpresa");

        if (id_empresa == null) {
            resp.sendRedirect("/");
            return;
        }

        try {
            id = Long.parseLong(req.getParameter("id"));

            Frota frota = dao.buscarPorId(id);

            if (frota == null) {
                resp.sendRedirect("/listar-frota");
                return;
            }

            req.setAttribute("frota", frota);
            req.getRequestDispatcher("WEB-INF/view/frota/atualizar-frota.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendRedirect("/listar-frota");
        }
    }

    /**
     * Processa a atualização da respectiva frota.
     * <p>
     * Valida os campos de entrada, atualiza o objeto {@link Frota} e chama
     * o método <code>atualizar</code> da classe {@link FrotaDAO} para realizar as alterações.
     * </p>
     * <p>
     * Se houver erros de validação, o formulário é exibido novamente com as mensagens de erro.
     * Se houver uma exceção, o usuário é redirecionado com mensagem explicativa.
     * </p>
     *
     * @param request  requisição HTTP contendo os dados do formulário
     * @param response resposta HTTP enviada ao cliente
     * @throws ServletException se ocorrer um erro interno no servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id;
        int tamanhoFrota;
        String tipoFrota;
        String regiao;

        HttpSession session = request.getSession(true);
        Object id_empresa = session.getAttribute("idEmpresa");
        boolean erro = false;

        if (id_empresa == null) {
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id_empresa;

        try {
            FrotaDAO dao = new FrotaDAO();

            id = Long.parseLong(request.getParameter("id"));
            tipoFrota = request.getParameter("tipoFrota");
            regiao = request.getParameter("regiao");

            String tamanhoFrotaReq = request.getParameter("tamanhoFrota");
            tamanhoFrota = (!Validar.testeVazio(tamanhoFrotaReq)) ? Integer.parseInt(tamanhoFrotaReq) : -1;

            if (Validar.testeVazio(tipoFrota)) {
                request.setAttribute("erroTipoFrota", "Tipo de frota inválido! Não pode ser nulo.");
                erro = true;
            }

            if (Validar.testeVazio(regiao)) {
                request.setAttribute("erroRegiao", "Região inválida! Não pode ser nula.");
                erro = true;
            }

            if (tamanhoFrota <= 0 || Validar.testeVazio(String.valueOf(tamanhoFrota))) {
                request.setAttribute("erroTamanhoFrota", "Tamanho da frota inválido! Deve ser maior que 0.");
                erro = true;
            }

            Frota frota = dao.buscarPorId(id);

            // Se a frota não pertence à empresa logada, exibe erro
            if (frota.getIdEmpresa() != idEmpresa) {
                request.setAttribute("erroGeral", "Frota inválida! Digite uma frota existente.");
                erro = true;
            }

            if (erro) {
                request.setAttribute("frota", frota);
                request.getRequestDispatcher("WEB-INF/view/frota/atualizar-frota.jsp").forward(request, response);
                return;
            }

            frota.setTamanhoFrota(tamanhoFrota);
            frota.setTipoFrota(tipoFrota);
            frota.setRegiao(regiao);
            frota.setIdEmpresa(idEmpresa);

            if (dao.atualizar(frota) == 1) {
                redirectComMensagem(response, "Frota atualizada com sucesso!");
                return;
            }

            redirectComMensagem(response, "Erro ao atualizar frota.");

        } catch (ErroAoAtualizar e) {
            redirectComMensagem(response, "Erro ao atualizar frota: " + e.getMessage());
        } catch (NumberFormatException e) {
            redirectComMensagem(response, "Formato inválido inserido: " + e.getMessage());
        } catch (Exception e) {
            redirectComMensagem(response, "Erro inesperado: " + e.getMessage());
        }
    }

    /**
     * Redireciona para a listagem de frotas com uma mensagem codificada.
     *
     * @param response objeto HttpServletResponse
     * @param mensagem mensagem a ser exibida
     * @throws IOException se ocorrer erro de redirecionamento
     */
    private void redirectComMensagem(HttpServletResponse response, String mensagem) throws IOException {
        String mensagemEncoded = URLEncoder.encode(mensagem, StandardCharsets.UTF_8);
        response.sendRedirect("/listar-frota?msg=" + mensagemEncoded);
    }
}
