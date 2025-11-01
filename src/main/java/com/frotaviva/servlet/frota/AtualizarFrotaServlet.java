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


@WebServlet(name = "AtualizarFrota", value = "/atualizar-frota")
public class AtualizarFrotaServlet extends HttpServlet {

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

            // Verifica se a frota pertence à empresa do usuário logado
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
     * Redireciona para a listagem de frotas com uma mensagem.
     *
     * @param response objeto HttpServletResponse
     * @param mensagem mensagem a ser exibida
     */
    private void redirectComMensagem(HttpServletResponse response, String mensagem) throws IOException {
        String mensagemEncoded = URLEncoder.encode(mensagem, StandardCharsets.UTF_8);
        response.sendRedirect("/listar-frota?msg=" + mensagemEncoded);
    }
}