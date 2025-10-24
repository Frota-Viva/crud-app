package com.frotaviva.servlet.entrega;

import com.frotaviva.dao.EntregaDAO;
import com.frotaviva.model.Endereco;
import com.frotaviva.model.Entrega;
import com.frotaviva.util.Validar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;


@WebServlet(name = "InserirEntrega", value = "/inserir-entrega")
public class InserirEntrega extends HttpServlet {
    @Override
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
        if (descricaoReq.equals("") || descricaoReq == null){
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
        if (! Validar.data(dataConclusaoReq) && ! dataConclusaoReq.equals("")){
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
            req.getRequestDispatcher("/WEB-INF/entrega/inserir-entrega.jsp").forward(req, resp);
            return;
        }

        //Verifica se já existe alguma entrega com o código informado
        if (entregaDAO.buscarPorId(codEntrega) != null){
            req.setAttribute("existeEntrega", "Já existe uma entrega com esse código!");
            req.getRequestDispatcher("/WEB-INF/entrega/inserir-entrega.jsp").forward(req, resp);
            return;
        }

        Endereco endereco = new Endereco(pais, cep, estado, cidade, rua, numero, complemento);
        Entrega entrega = new Entrega(codEntrega, descricaoReq, dataPedido, dataConclusao, endereco, idMotorista);

        //Insere a entrega no banco de dados
        switch (entregaDAO.inserir(entrega)){
            case 1 -> resp.sendRedirect("/listar-entregas");
            case 0 -> {
                req.setAttribute("erroCadastrar", "Erro ao cadastrar entrega");
                req.getRequestDispatcher("/WEB-INF/entrega/inserir-entrega.jsp").forward(req, resp);
            }
            default ->{
                req.setAttribute("erroBD", "Erro no banco de dados");
                req.getRequestDispatcher("/WEB-INF/entrega/inserir-entrega.jsp").forward(req, resp);
            }
        }
    }
}
