package com.frotaviva.servlet.manutencao;

import com.frotaviva.dao.FrotaDAO;
import com.frotaviva.dao.ManutencaoDAO;
import com.frotaviva.exception.ErroAoAtualizar;
import com.frotaviva.exception.ErroAoDeletar;
import com.frotaviva.model.Frota;
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

@WebServlet(name = "AtualizarManutencao", value = "/atualizar-manutencao")
public class AtualizarManutencaoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;
        ManutencaoDAO dao = new ManutencaoDAO();

        HttpSession session = req.getSession(true);
        Object id_empresa = session.getAttribute("idEmpresa");

        if (id_empresa == null) {
            resp.sendRedirect("/");
            return;
        }

        try {
            id = Long.parseLong(req.getParameter("id"));

           Manutencao manutencao = dao.buscarPorId(id);

            if (manutencao == null) {
                resp.sendRedirect("/listar-frota");
                return;
            }

            req.setAttribute("manutencao", manutencao);
            req.getRequestDispatcher("WEB-INF/view/manutencao/atualizar-manutencao.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendRedirect("/listar-frota");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id;
        long ultimoMotorista;
        long idCaminhao;
        Date dtCadastro;
        Date dtConclusao;
        BigDecimal custo;
        String tipoManutencao;
        String descricaoServico;

        try{

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            id = Long.parseLong(request.getParameter("id"));
            ultimoMotorista = Long.parseLong(request.getParameter("ultimoMotorista"));
            idCaminhao = Long.parseLong(request.getParameter("idCaminhao"));
            dtCadastro = sdf.parse(request.getParameter("dtCadastro"));
            dtConclusao = sdf.parse(request.getParameter("dtConclusao"));
            custo = new BigDecimal(request.getParameter("custo"));
            tipoManutencao = request.getParameter("tipoManutencao");
            descricaoServico = request.getParameter("descricaoServico");

            if (tipoManutencao == null || tipoManutencao.isEmpty()){
                request.setAttribute("erro", "Tipo de manutenção inválido! Não pode ser nulo.");
            }

            if (descricaoServico == null || descricaoServico.isEmpty()){
                request.setAttribute("erro", "Descrição inválida! Não pode ser nula.");
            }

            ManutencaoDAO dao = new ManutencaoDAO();
            Manutencao manutecao = dao.buscarPorId(id);
            java.sql.Date dtCadastroBD = new java.sql.Date(dtCadastro.getTime());
            java.sql.Date dtConclusaoBD = new java.sql.Date(dtConclusao.getTime());

            manutecao.setDescricaoServico(descricaoServico);
            manutecao.setUltimoMotorista(ultimoMotorista);
            manutecao.setIdCaminhao(idCaminhao);
            manutecao.setTipoManutencao(tipoManutencao);
            manutecao.setCusto(custo);
            manutecao.setDtCadastro(dtCadastroBD);
            manutecao.setDtConclusao(dtConclusaoBD);

            if (dao.atualizar(manutecao) == 1) {
                response.sendRedirect("/listar-manutencao?msg=Manutencao atualizada com sucesso!");
                return;
            } else{
                request.setAttribute("msg", "Erro ao atualizar manutencao. Tente novamente mais tarde.");
                request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
            }

        } catch (ErroAoDeletar e) {
            response.sendRedirect("/home?msg=Erro ao atualizar manutencao. Tente novamente mais tarde.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/home?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }
    }
}
