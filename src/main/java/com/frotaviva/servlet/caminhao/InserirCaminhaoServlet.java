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

@WebServlet(name = "InserirCaminhao", value = "/inserir-caminhao")
public class InserirCaminhaoServlet extends HttpServlet {

    /**
     *
     * Recebe uma mensagem, codifica ela para o padrão UTF_8 e redireciona para /listar-caminhao
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
        response.sendRedirect( "/listar-caminhao?msg=" + mensagemEncoded);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/view/caminhao/inserir-caminhao.jsp").forward(request, response);
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

            CaminhaoDAO dao = new CaminhaoDAO();

            String placa = request.getParameter("placa");
            String status = request.getParameter("status");
            int kmRodados = Integer.parseInt(request.getParameter("kmRodados"));
            String modelo = request.getParameter("modelo");
            int capacidade = Integer.parseInt(request.getParameter("capacidade"));
            long idFrota = Long.parseLong(request.getParameter("idFrota"));


            if (!Validar.placa(placa)){
                request.setAttribute("erroPlaca", "Placa inválida! Deve seguir o padrão: XXX1X11");
                erro = true;
            }

            if (!Validar.status(status)){
                request.setAttribute("erroStatus", "Status inválido! Deve ser 'I' | 'A' | 'M'.");
                erro = true;
            }

            if (modelo == null || modelo.isEmpty()){
                request.setAttribute("erroModelo", "Modelo inválido! Não pode ser nulo.");
                erro = true;
            }

            if (capacidade <= 0 || Validar.testeVazio(String.valueOf(capacidade))){
                request.setAttribute("erroCapacidade", "Capacidade inválida! Deve ser maior que zero.");
                erro = true;
            }

            if (kmRodados < 0 || Validar.testeVazio(String.valueOf(kmRodados))){
                request.setAttribute("erroKms", "Kilometragem inválida! Deve ser maior ou igual que zero.");
                erro = true;
            }

            /*
              Verifica o id_empresa de frota bate com o id do usuário logado (idEmpresa)
              para que nao inclue um caminhao em uma frota que nao é dele ou nao existe
             */

            FrotaDAO frotaDAO = new FrotaDAO();
            Frota frota = frotaDAO.buscarPorId(idFrota);

            if (frota == null){
                request.setAttribute("erroFrota", "Frota não encontrada!");
                erro = true;
            } else if (frota.getIdEmpresa() != idEmpresa){
                request.setAttribute("erroFrota", "Frota inválida! Selecione uma frota da sua empresa.");
                erro = true;
            }

            if (erro){
                request.getRequestDispatcher("WEB-INF/view/caminhao/inserir-caminhao.jsp").forward(request, response);
                return;
            }

            Caminhao caminhao = new Caminhao(placa, status, kmRodados, modelo, capacidade, idFrota);

            if (dao.inserir(caminhao) == 1){
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