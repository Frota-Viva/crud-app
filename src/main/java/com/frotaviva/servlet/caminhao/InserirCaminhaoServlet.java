package com.frotaviva.servlet.caminhao;

import com.frotaviva.dao.CaminhaoDAO;
import com.frotaviva.dao.FrotaDAO;
import com.frotaviva.exception.ErroAoInserir;
import com.frotaviva.model.Caminhao;
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
 * Responsável por cadastrar novos caminhões no banco de dados.
 * <p>
 * Este servlet recebe as informações do formulário de cadastro, realiza validações nos
 * campos fornecidos e insere o caminhão no banco de dados, garantindo que a frota
 * informada pertença à empresa logada.
 * </p>
 * <p>
 * Principais funcionalidades:
 * <ul>
 *     <li><b>redirectComMensagem:</b> Redireciona para /listar-caminhao com mensagem codificada;</li>
 *     <li><b>GET:</b> Exibe a página de cadastro de caminhão;</li>
 *     <li><b>POST:</b> Valida os campos e insere o caminhão no banco de dados;</li>
 *     <li>Redireciona o usuário com mensagens de sucesso ou erro conforme o resultado.</li>
 * </ul>
 * </p>
 *
 * @author Davi Alcanfor
 */
@WebServlet(name = "InserirCaminhao", value = "/inserir-caminhao")
public class InserirCaminhaoServlet extends HttpServlet {

    /**
     * Recebe uma mensagem, codifica ela para o padrão UTF-8 e redireciona para /listar-caminhao.
     * <p>
     * Este método evita a repetição de código para codificação de mensagens em diferentes pontos.
     * </p>
     *
     * @param response para executar o redirect
     * @param mensagem mensagem de sucesso ou erro
     * @throws IOException se ocorrer erro de entrada ou saída
     */
    private void redirectComMensagem(HttpServletResponse response, String mensagem) throws IOException {
        String mensagemEncoded = URLEncoder.encode(mensagem, StandardCharsets.UTF_8);
        response.sendRedirect("/listar-caminhao?msg=" + mensagemEncoded);
    }

    /**
     * Exibe a página de cadastro de caminhão.
     * <p>
     * Se não houver empresa logada, o usuário é redirecionado para a landingPage.
     * </p>
     *
     * @param request  requisição HTTP recebida
     * @param response resposta HTTP enviada ao cliente
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

        request.getRequestDispatcher("WEB-INF/view/caminhao/inserir-caminhao.jsp").forward(request, response);
    }

    /**
     * Processa os dados do formulário de cadastro de caminhão.
     * <p>
     * Este método realiza a validação dos campos enviados, garantindo que:
     * <ul>
     *     <li>A frota informada pertence à empresa logada;</li>
     *     <li>Os campos obrigatórios estão preenchidos e válidos;</li>
     *     <li>O caminhão seja corretamente inserido no banco de dados.</li>
     * </ul>
     * </p>
     * <p>
     * Se alguma validação falhar, o formulário é reexibido com mensagens de erro.
     * Se ocorrer uma exceção inesperada, o usuário é redirecionado com mensagem apropriada.
     * </p>
     *
     * @param request  requisição HTTP contendo os dados do formulário
     * @param response resposta HTTP enviada ao cliente
     * @throws ServletException se ocorrer erro interno no servlet
     * @throws IOException se ocorrer erro de entrada ou saída durante o processamento
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean erro = false;

        // Recuperando a sessão do usuário
        HttpSession session = request.getSession(true);
        Object id = session.getAttribute("idEmpresa");

        if (id == null) {
            response.sendRedirect("/");
            return;
        }
        long idEmpresa = (long) id;

        try {
            CaminhaoDAO dao = new CaminhaoDAO();

            String placa = request.getParameter("placa");
            String status = request.getParameter("status");
            int kmRodados = Integer.parseInt(request.getParameter("kmRodados"));
            String modelo = request.getParameter("modelo");
            int capacidade = Integer.parseInt(request.getParameter("capacidade"));
            long idFrota = Long.parseLong(request.getParameter("idFrota"));

            // Validação dos dados
            if (!Validar.placa(placa)) {
                request.setAttribute("erroPlaca", "Placa inválida! Deve seguir o padrão: XXX1X11");
                erro = true;
            }

            if (!Validar.status(status)) {
                request.setAttribute("erroStatus", "Status inválido! Deve ser 'I' | 'A' | 'M'.");
                erro = true;
            }

            if (modelo == null || modelo.isEmpty()) {
                request.setAttribute("erroModelo", "Modelo inválido! Não pode ser nulo.");
                erro = true;
            }

            if (capacidade <= 0 || Validar.testeVazio(String.valueOf(capacidade))) {
                request.setAttribute("erroCapacidade", "Capacidade inválida! Deve ser maior que zero.");
                erro = true;
            }

            if (kmRodados < 0 || Validar.testeVazio(String.valueOf(kmRodados))) {
                request.setAttribute("erroKms", "Kilometragem inválida! Deve ser maior ou igual que zero.");
                erro = true;
            }

            /*
              Verifica se o id_empresa da frota corresponde ao id do usuário logado (idEmpresa)
              para evitar que um caminhão seja vinculado a uma frota que não pertence à empresa.
             */
            FrotaDAO frotaDAO = new FrotaDAO();
            Frota frota = frotaDAO.buscarPorId(idFrota);

            if (frota == null) {
                request.setAttribute("erroFrota", "Frota não encontrada!");
                erro = true;
            } else if (frota.getIdEmpresa() != idEmpresa) {
                request.setAttribute("erroFrota", "Frota inválida! Selecione uma frota da sua empresa.");
                erro = true;
            }

            if (erro) {
                request.getRequestDispatcher("WEB-INF/view/caminhao/inserir-caminhao.jsp").forward(request, response);
                return;
            }

            // Inserção do caminhão
            Caminhao caminhao = new Caminhao(placa, status, kmRodados, modelo, capacidade, idFrota);

            if (dao.inserir(caminhao) == 1) {
                redirectComMensagem(response, "Caminhão inserido com sucesso!");
                return;
            }

            redirectComMensagem(response, "Erro ao inserir caminhão.");

        } catch (ErroAoInserir e) {
            redirectComMensagem(response, "Erro ao inserir caminhão: " + e.getMessage());
        } catch (NumberFormatException e) {
            redirectComMensagem(response, "Formato inválido inserido." + e.getMessage());
        } catch (Exception e) {
            redirectComMensagem(response, "Erro inesperado: " + e.getMessage());
        }
    }
}
