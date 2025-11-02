package com.frotaviva.servlet.frota;

import com.frotaviva.dao.FrotaDAO;
import com.frotaviva.exception.ErroAoInserir;
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
 * Responsável por cadastrar novas frotas no banco de dados.
 * <p>
 * Este servlet processa os dados recebidos do formulário de cadastro,
 * valida os campos e insere uma nova frota no banco de dados.
 * </p>
 * <p>
 * Principais funcionalidades:
 * <ul>
 *     <li><b>redirectComMensagen:</b> codifica a mensagem desejado e redireciona para <code>/listar-frota</code></li>
 *     <li><b>GET:</b> Exibe a página de cadastro de frota;</li>
 *     <li><b>POST:</b> Valida os campos e insere a frota no banco de dados;</li>
 *     <li>Redireciona o usuário com mensagens de sucesso ou erro conforme o resultado.</li>
 * </ul>
 * </p>
 * <p>
 * Se não houver empresa logada, o usuário é redirecionado para a landing page.
 * Se ocorrer algum erro na validação ou inserção, mensagens específicas são exibidas.
 * </p>
 * 
 * @author Davi Alcanfor
 */
@WebServlet(name = "InserirFrota", value = "/inserir-frota")
public class InserirFrotaServlet extends HttpServlet {

    /**
     * Recebe uma mensagem, codifica ela para UTF-8 e redireciona para a listagem de frotas.
     * <p>
     * Evita repetição de codificação de mensagens em diversos pontos do código.
     * </p>
     *
     * @param response resposta HTTP para executar o redirect
     * @param mensagem mensagem de sucesso ou erro a ser exibida
     * @throws IOException se ocorrer erro de redirecionamento
     */
    private void redirectComMensagem(HttpServletResponse response, String mensagem) throws IOException {
        String mensagemEncoded = URLEncoder.encode(mensagem, StandardCharsets.UTF_8);
        response.sendRedirect("/listar-frota?msg=" + mensagemEncoded);
    }

    /**
     * Exibe a página de cadastro de frota se a empresa estiver logada.
     * <p>
     * Se não houver empresa logada, redireciona para a landing page.
     * </p>
     *
     * @param request  requisição HTTP recebida
     * @param response resposta HTTP enviada
     * @throws IOException se ocorrer erro de entrada ou saída
     * @throws ServletException se ocorrer erro no processamento do servlet
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(true);
        Object id_empresa = session.getAttribute("idEmpresa");

        if (id_empresa == null) {
            response.sendRedirect("/");
            return;
        }

        request.getRequestDispatcher("WEB-INF/view/frota/inserir-frota.jsp").forward(request, response);
    }

    /**
     * Processa os dados do formulário de cadastro de frota.
     * <p>
     * Valida cada campo, cria o objeto {@link Frota} e insere ele no banco
     * utilizando o método </code>inserir</code> da classe {@link FrotaDAO}.
     * </p>
     * <p>
     * Se houver erro de validação, o formulário é reexibido com mensagens específicas.
     * Se ocorrer exceção, o usuário é redirecionado com mensagem explicativa.
     * </p>
     *
     * @param request  requisição HTTP contendo os dados do formulário
     * @param response resposta HTTP enviada ao cliente
     * @throws ServletException se ocorrer erro interno no servlet
     * @throws IOException se ocorrer erro de entrada ou saída
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean erro = false;

        HttpSession session = request.getSession(true);
        Object id = session.getAttribute("idEmpresa");

        if (id == null){
            response.sendRedirect("/");
            return;
        }
        long idEmpresa = (long) id;

        try {
            FrotaDAO dao = new FrotaDAO();

            int tamanhoFrota = Integer.parseInt(request.getParameter("tamanhoFrota"));
            String tipoFrota = request.getParameter("tipoFrota");
            String regiao = request.getParameter("regiao");

            // Validação dos campos
            if (tamanhoFrota <= 0 || Validar.testeVazio(String.valueOf(tamanhoFrota))) {
                request.setAttribute("erroTamanhoFrota", "Tamanho da frota inválido! Deve ser maior que zero.");
                erro = true;
            }

            if (tipoFrota == null || tipoFrota.isEmpty()){
                request.setAttribute("erroTipoFrota", "Tipo de frota inválido! Não pode ser nulo.");
                erro = true;
            }

            if (regiao == null || regiao.isEmpty()){
                request.setAttribute("erroRegiao", "Região inválida! Não pode ser nula.");
                erro = true;
            }

            if (erro){
                request.getRequestDispatcher("WEB-INF/view/frota/inserir-frota.jsp").forward(request, response);
                return;
            }

            Frota frota = new Frota(tamanhoFrota, tipoFrota, regiao, idEmpresa);

            if (dao.inserir(frota) == 1){
                redirectComMensagem(response, "Frota inserida com sucesso!");
                return;
            }

            redirectComMensagem(response, "Erro ao inserir frota.");

        } catch (ErroAoInserir e) {
            redirectComMensagem(response, "Erro ao inserir frota: " + e.getMessage());
        } catch (NumberFormatException e) {
            redirectComMensagem(response, "Formato inválido inserido: " + e.getMessage());
        } catch (Exception e) {
            redirectComMensagem(response, "Erro inesperado: " + e.getMessage());
        }
    }
}
