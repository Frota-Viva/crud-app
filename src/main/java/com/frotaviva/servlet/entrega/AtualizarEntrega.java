package com.frotaviva.servlet.entrega;

import com.frotaviva.dao.EntregaDAO;
import com.frotaviva.model.Endereco;
import com.frotaviva.model.Entrega;
import com.frotaviva.util.Validar;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

public class AtualizarEntrega extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean erro = false;
        boolean dadoFaltando = false;
        Date dataConclusao = null;
        Date dataPedido = null;
        EntregaDAO entregaDAO = new EntregaDAO();

        //Faz a validação do código da entrega
        long codEntrega = 0;
        String codEntregaReq = req.getParameter("codEntrega");
        if (codEntregaReq == null || codEntregaReq.equals("")){
            dadoFaltando = true;
        }
        try {
            codEntrega = Long.parseLong(codEntregaReq);
        } catch (NumberFormatException e) {
            req.setAttribute("erroCodEntrega", "Apenas valores numéricos são permitidos!");
        }

        //Faz a validação da descrição do produto da entrega
        String descricaoReq = req.getParameter("descricao");
        if ( descricaoReq == null || descricaoReq.equals("") ){
            dadoFaltando = true;
        }

        //Faz a validação da data de pedido da entrega
        String dataPedidoReq = req.getParameter("dataPedido");
        if (! Validar.data(dataPedidoReq)){
            erro = true;
            req.setAttribute("erroDataPedido", "A data deve estar no formato yyyy-MM-dd!");
        } else {
            dataPedido = Date.valueOf(dataPedidoReq);
        }

        //Faz a validação da data de conclusão da entrega
        String dataConclusaoReq = req.getParameter("dataConclusao");
        if ( dataConclusaoReq != null && ! dataConclusaoReq.equals("") && ! Validar.data(dataConclusaoReq) ){
            erro = true;
            req.setAttribute("erroDataPedido", "A data deve estar no formato yyyy-MM-dd!");
        }
        if (! (dataConclusaoReq.equals("")) && ! (dataConclusaoReq == null)){
            dataConclusao = Date.valueOf(dataConclusaoReq);
        }

        //Faz a validação do cep
        String cep = req.getParameter("cep");
        cep = Validar.cepValidado(cep);
        if (cep == null){
            req.setAttribute("erroCep", "Formato não compatível!");
            erro = true;
        }

        //Faz a validação da rua
        String rua = req.getParameter("rua");
        if (rua == null || rua.equals("")) dadoFaltando = true;

        //Pega o complemento (pode ser nulo)
        String complemento = req.getParameter("complemento");

        //Faz a validação do número
        int numero = 0;
        try {
            numero = Integer.parseInt(req.getParameter("numero"));
        } catch (NumberFormatException e){
            req.setAttribute("erroNumero", "Apenas valores numéricos são permitidos!");
            erro = true;
        }

        //Faz a validação do país
        String pais = req.getParameter("pais");
        if (pais == null || pais.equals("")) dadoFaltando = true;

        //Faz a validação do estado
        String estado = req.getParameter("estado");
        if (estado == null || estado.equals("")) dadoFaltando = true;

        //Faz a validação da cidade
        String cidade = req.getParameter("cidade");
        if (cidade == null || cidade.equals("")) dadoFaltando = true;

        //Faz a validação do idMotorista
        long idMotorista = 0;
        String idMotoristaReq = req.getParameter("idMotorista");
        try {
            idMotorista = Long.parseLong(idMotoristaReq);
        } catch (NumberFormatException e) {
            req.setAttribute("erroIdMotorista", "Apenas valores numéricos são permitidos!");
            erro = true;
        }

        //Verifica se existe algum erro nos dados
        if (dadoFaltando) req.setAttribute("dadoFaltando", "Todos os campos com (*) devem ser preenchidos!");
        if (dadoFaltando || erro) {
            req.getRequestDispatcher("/WEB-INF/entrega/atualizar-entrega.jsp").forward(req, resp);
            return;
        }

        //Verifica a entrega realmente existe
        Entrega entrega = entregaDAO.buscarPorId(codEntrega);
        if (entrega == null){
            resp.sendRedirect("/");
            return;
        }



        //Altera as informações da entrega do BD
        try {
            Endereco endereco = new Endereco(pais, cep, estado, cidade, rua, numero, complemento);

            entrega.setDescricaoProduto(descricaoReq);
            if (entregaDAO.atualizarDescricao(entrega) != 1) erro = true;

            entrega.setEndereco(endereco);
            if (entregaDAO.atualizarEndereco(entrega) != 1) erro = true;

            entrega.setDtEntrega(dataConclusao);
            entrega.setDtPedido(dataConclusao);
            if (entregaDAO.atualizarDatas(entrega) != 1) erro = true;

            if (! erro){
                resp.sendRedirect("/listar-entregas?msg=Sucesso");
                return;
            }

            req.setAttribute("erro", "Erro ao atualizar entrega!");
            req.getRequestDispatcher("/WEB-INF/entrega/atualizar-entrega.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("erro", "Erro ao atualizar entrega!");
            req.getRequestDispatcher("/WEB-INF/entrega/atualizar-entrega.jsp").forward(req, resp);
        }

    }
}
