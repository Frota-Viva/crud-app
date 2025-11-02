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

@WebServlet(name = "InserirFrota", value = "/inserir-frota")
public class InserirFrotaServlet extends HttpServlet {

    /**
     *
     * Recebe uma mensagem, codifica ela para o padrão UTF_8 e redireciona para /listar da entidade
     * Apenas para maior legibilidade do código, pois cada mensagem teria que ser codificada e isso
     * seria repetido muitas vezes no código.
     *
     * @param response para executar o redirect
     * @param mensagem para a especificação da mensagem de erro/sucesso
     *
     *  @author Davi
     */

    private void redirectComMensagem(HttpServletResponse response, String mensagem) throws IOException {
        String mensagemEncoded = URLEncoder.encode(mensagem, StandardCharsets.UTF_8);
        response.sendRedirect("/listar-frota?msg=" + mensagemEncoded);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/view/frota/inserir-frota.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean erro = false;

        // Sessão

        HttpSession session = request.getSession(true);
        Object id = session.getAttribute("idEmpresa");

        if (id == null){
            response.sendRedirect("/");
            return;
        }
        long idEmpresa = (long) id;

        try{

            FrotaDAO dao = new FrotaDAO();

            int tamanhoFrota = Integer.parseInt(request.getParameter("tamanhoFrota"));
            String tipoFrota = request.getParameter("tipoFrota");
            String regiao = request.getParameter("regiao");

            // Validação

            /*
               Os atributos de erro são setados um por um unicamente
               para que sejam reconhecidos e todos sejam identificaveis ao
               usuario.
             */

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