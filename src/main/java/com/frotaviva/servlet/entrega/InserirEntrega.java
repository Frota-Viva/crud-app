package com.frotaviva.servlet.entrega;

import com.frotaviva.dao.EntregaDAO;
import com.frotaviva.exception.ErroAoConsultar;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean erro = false;
        boolean dadoFaltando = false;
        Date dataConclusao = null;
        Date dataPedido = null;
        EntregaDAO entregaDAO = new EntregaDAO();

        //Faz a validação do código da entrega
        long codEntrega = 0;
        String codEntregaReq = request.getParameter("codEntrega");
        if (codEntregaReq == null || codEntregaReq.equals("")){
            dadoFaltando = true;
        }
        try {
            codEntrega = Long.parseLong(codEntregaReq);
        } catch (NumberFormatException e) {
            request.setAttribute("erroCodEntrega", "Apenas valores numéricos são permitidos!");
        }

        //Faz a validação da descrição do produto da entrega
        String descricaoReq = request.getParameter("descricao");
        if (descricaoReq.equals("") || descricaoReq == null){
            dadoFaltando = true;
        }

        //Faz a validação da data de pedido da entrega
        String dataPedidoReq = request.getParameter("dataPedido");
        if (! Validar.data(dataPedidoReq)){
            erro = true;
            request.setAttribute("erroDataPedido", "A data deve estar no formato yyyy-MM-dd!");
        } else {
            dataPedido = Date.valueOf(dataPedidoReq);
        }

        //Faz a validação da data de conclusão da entrega
        String dataConclusaoReq = request.getParameter("dataConclusao");
        if (! Validar.data(dataConclusaoReq) && ! dataConclusaoReq.equals("")){
            erro = true;
            request.setAttribute("erroDataPedido", "A data deve estar no formato yyyy-MM-dd!");
        }
        if (! (dataConclusaoReq.equals("")) && ! (dataConclusaoReq == null)){
            dataConclusao = Date.valueOf(dataConclusaoReq);
        }

        //Faz a validação do cep
        String cep = request.getParameter("cep");
        cep = Validar.cepValidado(cep);
        if (cep == null){
            request.setAttribute("erroCep", "Formato não compatível!");
            erro = true;
        }

        //Faz a validação da rua
        String rua = request.getParameter("rua");
        if (rua == null || rua.equals("")) dadoFaltando = true;

        //Pega o complemento (pode ser nulo)
        String complemento = request.getParameter("complemento");

        //Faz a validação do número
        int numero = 0;
        try {
            numero = Integer.parseInt(request.getParameter("numero"));
        } catch (NumberFormatException e){
            request.setAttribute("erroNumero", "Apenas valores numéricos são permitidos!");
            erro = true;
        }

        //Faz a validação do país
        String pais = request.getParameter("pais");
        if (pais == null || pais.equals("")) dadoFaltando = true;

        //Faz a validação do estado
        String estado = request.getParameter("estado");
        if (estado == null || estado.equals("")) dadoFaltando = true;

        //Faz a validação da cidade
        String cidade = request.getParameter("cidade");
        if (cidade == null || cidade.equals("")) dadoFaltando = true;

        //Faz a validação do idMotorista
        long idMotorista = 0;
        String idMotoristaReq = request.getParameter("idMotorista");
        try {
            idMotorista = Long.parseLong(idMotoristaReq);
        } catch (NumberFormatException e) {
            request.setAttribute("erroIdMotorista", "Apenas valores numéricos são permitidos!");
            erro = true;
        }

        //Verifica se existe algum erro nos dados
        if (dadoFaltando) request.setAttribute("dadoFaltando", "Todos os campos com (*) devem ser preenchidos!");
        if (dadoFaltando || erro) {
            request.getRequestDispatcher("/WEB-INF/entrega/inserir-entrega.jsp").forward(request, response);
            return;
        }

        //Verifica se já existe alguma entrega com o código informado
        if (entregaDAO.buscarPorId(codEntrega) != null){
            request.setAttribute("existeEntrega", "Já existe uma entrega com esse código!");
            request.getRequestDispatcher("/WEB-INF/entrega/inserir-entrega.jsp").forward(request, response);
            return;
        }

        try{
            Endereco endereco = new Endereco(pais, cep, estado, cidade, rua, numero, complemento);
            Entrega entrega = new Entrega(codEntrega, descricaoReq, dataPedido, dataConclusao, endereco, idMotorista);

            //Insere a entrega no banco de dados
            switch (entregaDAO.inserir(entrega)){
                case 1 -> response.sendRedirect("/listar-entregas");
                case 0 -> {
                    request.setAttribute("erroCadastrar", "Erro ao cadastrar entrega");
                    request.getRequestDispatcher("/WEB-INF/entrega/inserir-entrega.jsp").forward(request, response);
                }
                default ->{
                    request.setAttribute("erroBD", "Erro no banco de dados");
                    request.getRequestDispatcher("/WEB-INF/entrega/inserir-entrega.jsp").forward(request, response);
                }
            }
        } catch (ErroAoConsultar e) {
            request.setAttribute("mensagem", "Erro ao acessar o encontrar motoristas. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }

    }
}
