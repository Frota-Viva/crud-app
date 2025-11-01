package com.frotaviva.servlet.manutencao;

import com.frotaviva.dao.CaminhaoDAO;
import com.frotaviva.dao.ManutencaoDAO;
import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.exception.ErroAoInserir;
import com.frotaviva.model.Caminhao;
import com.frotaviva.model.Manutencao;
import com.frotaviva.model.Motorista;

import com.frotaviva.util.Validar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;

@WebServlet(name = "InserirManutencao", value = "/inserir-manutencao")
public class InserirManutencaoServlet extends HttpServlet {

    /**
     * Recebe uma mensagem, codifica ela para o padrão UTF_8 e redireciona para /listar-manutencao
     * Apenas para maior legibilidade do código, pois cada mensagem teria que ser codificada e isso
     * seria repetido muitas vezes no código.
     *
     * @param response para executar o redirect
     * @param mensagem para a especificação da mensagem de erro/sucesso
     *
     * @author Davi
     */
    private void redirectComMensagem(HttpServletResponse response, String mensagem) throws IOException {
        String mensagemEncoded = URLEncoder.encode(mensagem, StandardCharsets.UTF_8);
        response.sendRedirect("/listar-manutencao?msg=" + mensagemEncoded);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/view/manutencao/inserir-manutencao.jsp").forward(request, response);
    }

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

            ManutencaoDAO dao = new ManutencaoDAO();

            Date dtCadastro = Date.valueOf(request.getParameter("dtCadastro"));
            Date dtConclusao = Date.valueOf(request.getParameter("dtConclusao"));
            String descricao = request.getParameter("descricao");
            BigDecimal custo = new BigDecimal(request.getParameter("custo"));
            long ultimoMotorista = Long.parseLong(request.getParameter("ultimoMotorista"));
            long idCaminhao = Long.parseLong(request.getParameter("idCaminhao"));
            String tipoManutencao = request.getParameter("tipoManutencao");

            if (dtCadastro == null){
                request.setAttribute("erroDtCadastro", "Data de cadastro inválida! Não pode ser nula.");
                erro = true;
            }

            if (dtConclusao == null || Validar.testeVazio(String.valueOf(dtConclusao))){
                request.setAttribute("erroDtConclusao", "Data de conclusão inválida! Não pode ser nula.");
                erro = true;
            }

            if (dtCadastro != null && dtConclusao != null && dtConclusao.before(dtCadastro)
                    ||  Validar.testeVazio(String.valueOf(dtCadastro))){
                request.setAttribute("erroDtConclusao", "Data de conclusão deve ser maior que a data de cadastro!");
                erro = true;
            }

            if (descricao == null || descricao.isEmpty()){
                request.setAttribute("erroDescricao", "Descrição inválida! Não pode ser nula.");
                erro = true;
            }

            if (custo.compareTo(BigDecimal.ZERO) <= 0 || Validar.testeVazio(String.valueOf(custo))){
                request.setAttribute("erroCusto", "Custo inválido! Deve ser maior que zero.");
                erro = true;
            }

            if (tipoManutencao == null || tipoManutencao.isEmpty()){
                request.setAttribute("erroTipoManutencao", "Tipo de manutenção inválido! Não pode ser nulo.");
                erro = true;
            }

            CaminhaoDAO caminhaoDAO = new CaminhaoDAO();
            Caminhao caminhao = caminhaoDAO.buscarPorId(idCaminhao);

            if (caminhao == null){
                request.setAttribute("erroCaminhao", "Caminhão não encontrado!");
                erro = true;
            }

            MotoristaDAO motoristaDAO = new MotoristaDAO();
            Motorista motorista = motoristaDAO.buscarPorId(ultimoMotorista);

            if (motorista == null){
                request.setAttribute("erroMotorista", "Motorista não encontrado!");
                erro = true;
            } else if (motorista.getIdEmpresa() != idEmpresa){
                request.setAttribute("erroMotorista", "Motorista inválido! Selecione um motorista da sua empresa.");
                erro = true;
            }

            if (erro){
                request.getRequestDispatcher("WEB-INF/view/manutencao/inserir-manutencao.jsp").forward(request, response);
                return;
            }

            Manutencao manutencao = new Manutencao(dtCadastro, dtConclusao, tipoManutencao,
                    custo, ultimoMotorista, descricao, idCaminhao);

            if (dao.inserir(manutencao) == 1){
                redirectComMensagem(response, "Manutenção inserida com sucesso!");
                return;
            }

            redirectComMensagem(response, "Erro ao inserir manutenção.");

        } catch (ErroAoInserir e) {
            redirectComMensagem(response, "Erro ao inserir manutenção: " + e.getMessage());
        } catch (NumberFormatException e) {
            redirectComMensagem(response, "Formato inválido inserido: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            redirectComMensagem(response, "Data inválida: " + e.getMessage());
        } catch (Exception e) {
            redirectComMensagem(response, "Erro inesperado: " + e.getMessage());
        }
    }
}