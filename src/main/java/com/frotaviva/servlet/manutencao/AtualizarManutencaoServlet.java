package com.frotaviva.servlet.manutencao;

import com.frotaviva.dao.ManutencaoDAO;
import com.frotaviva.exception.ErroAoDeletar;
import com.frotaviva.model.Manutencao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Responsável por atualizar os registros de manutenção dos veículos.
 * <p>
 * Este servlet exibe o formulário de edição com os dados existentes e realiza as alterações feitas pelo usuário.
 * </p>
 * <p>
 * Principais funcionalidades:
 * <ul>
 *     <li><b>GET:</b> Busca a manutenção pelo ID e envia os dados para a página <code>atualizar-manutencao.jsp</code>;</li>
 *     <li><b>POST:</b> Valida e atualiza os dados da manutenção no banco de dados;</li>
 *     <li>Redireciona o usuário para a listagem ou exibe mensagens de erro de acordo com o resultado.</li>
 * </ul>
 * </p>
 * 
 * @author Davi Alcanfor
 */
@WebServlet(name = "AtualizarManutencao", value = "/atualizar-manutencao")
public class AtualizarManutencaoServlet extends HttpServlet {

    /**
     * Exibe o formulário de atualização da manutenção.
     * <p>
     * Se não houver uma empresa logada, redireciona para a landing page.
     * Verifica se a manutenção existe e, se não existir, redireciona para a listagem de manutenções.
     * </p>
     *
     * @param req  requisição HTTP recebida
     * @param resp resposta HTTP a ser enviada
     * @throws ServletException se ocorrer erro no processamento do servlet
     * @throws IOException se ocorrer erro de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        Object id_empresa = session.getAttribute("idEmpresa");

        if (id_empresa == null) {
            resp.sendRedirect("/");
            return;
        }

        try {
            long id = Long.parseLong(req.getParameter("id"));
            ManutencaoDAO dao = new ManutencaoDAO();

            Manutencao manutencao = dao.buscarPorId(id);

            if (manutencao == null) {
                resp.sendRedirect("/listar-manutencao");
                return;
            }

            req.setAttribute("manutencao", manutencao);
            req.getRequestDispatcher("WEB-INF/view/manutencao/atualizar-manutencao.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendRedirect("/listar-frota");
        }
    }

    /**
     * Realiza a atualização da manutenção recebida do formulário.
     * <p>
     * Se algum campo estiver inválido, exibe mensagem de erro. Caso todos os campos estejam corretos,
     * atualiza o registro no banco de dados.
     * </p>
     *
     * @param request  requisição HTTP com os dados do formulário
     * @param response resposta HTTP enviada ao cliente
     * @throws ServletException se ocorrer erro no processamento do servlet
     * @throws IOException se ocorrer erro de entrada ou saída
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            long id = Long.parseLong(request.getParameter("id"));
            long ultimoMotorista = Long.parseLong(request.getParameter("ultimoMotorista"));
            long idCaminhao = Long.parseLong(request.getParameter("idCaminhao"));
            Date dtCadastro = sdf.parse(request.getParameter("dtCadastro"));
            Date dtConclusao = !request.getParameter("dtConclusao").equals("") ?
                    sdf.parse(request.getParameter("dtConclusao")) : null;
            BigDecimal custo = new BigDecimal(request.getParameter("custo"));
            String tipoManutencao = request.getParameter("tipoManutencao");
            String descricaoServico = request.getParameter("descricaoServico");

            if (tipoManutencao == null || tipoManutencao.isEmpty()) {
                request.setAttribute("erro", "Tipo de manutenção inválido! Não pode ser nulo.");
            }

            if (descricaoServico == null || descricaoServico.isEmpty()) {
                request.setAttribute("erro", "Descrição inválida! Não pode ser nula.");
            }

            ManutencaoDAO dao = new ManutencaoDAO();
            Manutencao manutencao = dao.buscarPorId(id);

            java.sql.Date dtCadastroBD = new java.sql.Date(dtCadastro.getTime());
            manutencao.setDescricaoServico(descricaoServico);
            manutencao.setUltimoMotorista(ultimoMotorista);
            manutencao.setIdCaminhao(idCaminhao);
            manutencao.setTipoManutencao(tipoManutencao);
            manutencao.setCusto(custo);
            manutencao.setDtCadastro(dtCadastroBD);

            if (dtConclusao != null) {
                java.sql.Date dtConclusaoBD = new java.sql.Date(dtConclusao.getTime());
                manutencao.setDtConclusao(dtConclusaoBD);
            }

            if (dao.atualizar(manutencao) == 1) {
                response.sendRedirect("/listar-manutencao?msg=Manutencao atualizada com sucesso!");
                return;
            } else {
                request.setAttribute("msg", "Erro ao atualizar manutencao. Tente novamente mais tarde.");
                request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
            }

        } catch (ErroAoDeletar e) {
            response.sendRedirect("/home?msg=Erro ao atualizar manutencao. Tente novamente mais tarde.");
        } catch (Exception e) {
            response.sendRedirect("/home?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }
    }
}
