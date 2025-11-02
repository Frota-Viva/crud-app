package com.frotaviva.servlet.entrega;

import com.frotaviva.dao.EntregaDAO;
import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.exception.ErroAoInserir;
import com.frotaviva.model.Endereco;
import com.frotaviva.model.Entrega;
import com.frotaviva.model.Motorista;
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
import java.sql.Date;

@WebServlet(name = "InserirEntrega", value = "/inserir-entregas")
public class InserirEntrega extends HttpServlet {

    /**
     * Recebe uma mensagem, codifica ela para o padrão UTF-8 e redireciona para /listar-entregas.
     * <p>
     * Evita repetição de código de codificação de mensagens em múltiplos pontos.
     * </p>
     *
     * @param response para executar o redirect
     * @param mensagem mensagem de sucesso ou erro
     * @throws IOException se ocorrer erro de entrada ou saída
     */
    private void redirectComMensagem(HttpServletResponse response, String mensagem) throws IOException {
        String mensagemEncoded = URLEncoder.encode(mensagem, StandardCharsets.UTF_8);
        response.sendRedirect("/listar-entregas?msg=" + mensagemEncoded);
    }

    /**
     * Exibe a página de cadastro de entrega.
     * <p>
     * Se não houver empresa logada, o usuário é redirecionado para a página inicial.
     * </p>
     *
     * @param request  requisição HTTP recebida
     * @param response resposta HTTP enviada ao cliente
     * @throws IOException se ocorrer erro de entrada/saída
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

        request.getRequestDispatcher("WEB-INF/view/entrega/inserir-entrega.jsp").forward(request, response);
    }

    /**
     * Processa os dados do formulário de cadastro de entrega.
     * <p>
     * Principais etapas:
     * <ul>
     *     <li>Verifica se há uma empresa logada;</li>
     *     <li>Recebe e valida os campos do formulário (datas, descrição, endereço, motorista, código da entrega);</li>
     *     <li>Garante que o motorista existe e pertence à empresa;</li>
     *     <li>Cria o objeto {@link Entrega} e insere no banco de dados usando {@link EntregaDAO};</li>
     *     <li>Redireciona com mensagens específicas de acordo com o resultado.</li>
     * </ul>
     * </p>
     * <p>
     * Se houver erros de validação, o formulário é reexibido com mensagens de erro.
     * Se ocorrer uma exceção inesperada, o usuário é redirecionado com uma mensagem explicativa.
     * </p>
     *
     * @param request  requisição HTTP contendo os dados do formulário
     * @param response resposta HTTP enviada ao cliente
     * @throws ServletException se ocorrer erro interno no servlet
     * @throws IOException se ocorrer erro de entrada/saída durante o processamento
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

        try{

            EntregaDAO dao = new EntregaDAO();

            long codEntrega = Long.parseLong(request.getParameter("codEntrega"));
            String descricao = request.getParameter("descricao");
            Date dataPedido = Date.valueOf(request.getParameter("dataPedido"));
            String dataConclusaoParam = request.getParameter("dataConclusao");
            Date dataConclusao = (dataConclusaoParam != null && !dataConclusaoParam.isEmpty()) ? Date.valueOf(dataConclusaoParam) : null;
            String cep = request.getParameter("cep");
            String rua = request.getParameter("rua");
            String complemento = request.getParameter("complemento");
            int numero = Integer.parseInt(request.getParameter("numero"));
            String pais = request.getParameter("pais");
            String estado = request.getParameter("estado");
            String cidade = request.getParameter("cidade");
            long idMotorista = Long.parseLong(request.getParameter("idMotorista"));

            // Validações
            if (descricao == null || descricao.isEmpty()){
                request.setAttribute("erroDescricao", "Descrição inválida! Não pode ser nula.");
                erro = true;
            }

            if (dataPedido == null || Validar.testeVazio(String.valueOf(dataPedido))){
                request.setAttribute("erroDataPedido", "Data de pedido inválida! Não pode ser nula.");
                erro = true;
            }

            if (dataConclusao != null && dataPedido != null && dataConclusao.before(dataPedido)
                    || Validar.testeVazio(String.valueOf(dataConclusao))){
                request.setAttribute("erroDataConclusao", "Data de conclusão deve ser posterior à data de pedido!");
                erro = true;
            }

            if (!Validar.cep(cep)){
                request.setAttribute("erroCep", "CEP inválido! Formato esperado: XXXXX-XXX");
                erro = true;
            } else {
                cep = Validar.cepValidado(cep);
            }

            if (rua == null || rua.isEmpty()){
                request.setAttribute("erroRua", "Rua inválida! Não pode ser nula.");
                erro = true;
            }

            if (numero <= 0 || Validar.testeVazio(String.valueOf(numero))){
                request.setAttribute("erroNumero", "Número inválido! Deve ser maior que zero.");
                erro = true;
            }

            if (pais == null || pais.isEmpty()){
                request.setAttribute("erroPais", "País inválido! Não pode ser nulo.");
                erro = true;
            }

            if (estado == null || estado.isEmpty()){
                request.setAttribute("erroEstado", "Estado inválido! Não pode ser nulo.");
                erro = true;
            }

            if (cidade == null || cidade.isEmpty()){
                request.setAttribute("erroCidade", "Cidade inválida! Não pode ser nula.");
                erro = true;
            }

            // Verifica se já existe uma entrega com esse código
            if (dao.buscarPorId(codEntrega) != null){
                request.setAttribute("erroCodEntrega", "Já existe uma entrega com esse código!");
                erro = true;
            }

            // Garante que o motorista pertence à empresa do usuário logado
            MotoristaDAO motoristaDAO = new MotoristaDAO();
            Motorista motorista = motoristaDAO.buscarPorId(idMotorista);

            if (motorista == null){
                request.setAttribute("erroMotorista", "Motorista não encontrado!");
                erro = true;
            } else if (motorista.getIdEmpresa() != idEmpresa){
                request.setAttribute("erroMotorista", "Motorista inválido! Selecione um motorista da sua empresa.");
                erro = true;
            }

            if (erro){
                request.getRequestDispatcher("WEB-INF/view/entrega/inserir-entrega.jsp").forward(request, response);
                return;
            }

            Endereco endereco = new Endereco(pais, cep, estado, cidade, rua, numero, complemento);
            Entrega entrega = new Entrega(descricao, dataPedido, dataConclusao, endereco, idMotorista);

            if (dao.inserir(entrega) == 1){
                redirectComMensagem(response, "Entrega inserida com sucesso!");
                return;
            }

            redirectComMensagem(response, "Erro ao inserir entrega.");

        } catch (ErroAoInserir e) {
            redirectComMensagem(response, "Erro ao inserir entrega: " + e.getMessage());
        } catch (NumberFormatException e) {
            redirectComMensagem(response, "Formato inválido inserido: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            redirectComMensagem(response, "Data inválida: " + e.getMessage());
        } catch (Exception e) {
            redirectComMensagem(response, "Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}
