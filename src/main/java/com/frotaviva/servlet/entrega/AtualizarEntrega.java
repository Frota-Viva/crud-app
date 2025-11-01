package com.frotaviva.servlet.entrega;

import com.frotaviva.dao.EntregaDAO;
import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.exception.ErroAoDeletar;
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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "AtualizarEntrega", value = "/atualizar-entrega")
public class AtualizarEntrega extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long cod_entrega;
        EntregaDAO dao = new EntregaDAO();

        HttpSession session = req.getSession(true);
        Object id_empresa = session.getAttribute("idEmpresa");

        if (id_empresa == null) {
            resp.sendRedirect("/");
            return;
        }

        try {
            cod_entrega = Long.parseLong(req.getParameter("cod_entrega"));
            Entrega entrega = dao.buscarPorId(cod_entrega);

            if (entrega == null) {
                resp.sendRedirect("/listar-entrega");
            }

            req.setAttribute("entrega", entrega);
            req.getRequestDispatcher("WEB-INF/view/entrega/atualizar-entrega.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendRedirect("/listar-entrega");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long cod_entrega;
        String descricaoProduto;
        Date dtPedido;
        Date dtEntrega;
        Endereco endereco;
        long idMotorista;

        HttpSession session = request.getSession(true);
        Object id_empresa = session.getAttribute("idEmpresa");

        if (id_empresa == null) {
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id_empresa;

        try {
            EntregaDAO dao = new EntregaDAO();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String cod_entregaReq = request.getParameter("cod_entrega");
            cod_entrega = (! Validar.testeVazio(cod_entregaReq)) ? Long.parseLong(cod_entregaReq) : -1;

            descricaoProduto = request.getParameter("descricao_produto");
            dtPedido = new Date(sdf.parse(request.getParameter("dt_pedido")).getTime());
            //Continuar as validações a partir daqui (validar a data)
            System.out.println(dtPedido);
            dtEntrega = new Date(sdf.parse(request.getParameter("dt_entrega")).getTime());
            System.out.println(dtEntrega);
            endereco = (Endereco) session.getAttribute("endereco");
            System.out.println(endereco);
            idMotorista = Long.parseLong(request.getParameter("idMotorista"));
            System.out.println(idMotorista);

            if (descricaoProduto == null || descricaoProduto.isEmpty()) request.setAttribute("erroDescricao", "Descrição inválida! Não pode ser nula.");

            if (!Validar.data(String.valueOf(dtPedido))) request.setAttribute("erroDtPedido", "Data de pedido inválida!");

            if (!Validar.data(String.valueOf(dtEntrega))) request.setAttribute("erroDtEntrega", "Data de entrega inválida!");

            if (dtEntrega.before(dtPedido)) {
                request.setAttribute("erroDtEntrega", "Data de entrega não pode ser anterior à data do pedido!");
            }

            if (endereco == null) request.setAttribute("erroEndereco", "Endereço inválido! Não pode ser nulo.");

            /*
              Para verificar o motorista,
              primeiro eu verifico os motoristas que o usuário possui,
              depois verifico se o motorista inserido está dentro deles
             */

            MotoristaDAO motoristaDAO = new MotoristaDAO();

            List<Motorista> motoristas = motoristaDAO.buscarPorEmpresa(idEmpresa);
            Motorista motorista = motoristaDAO.buscarPorId(idMotorista);

            if (!motoristas.contains(motorista)) {
                request.setAttribute("erroMotorista", "Motorista inválido! Selecione um motorista existente.");
            }

            Entrega entrega = dao.buscarPorId(cod_entrega);

            entrega.setDescricaoProduto(descricaoProduto);
            entrega.setDtPedido(dtPedido);
            entrega.setDtEntrega(dtEntrega);
            entrega.setEndereco(endereco);
            entrega.setIdMotorista(idMotorista);

            if (dao.atualizar(entrega) == 1) {
                response.sendRedirect("/listar-entrega?msg=Entrega+atualizada+com+sucesso");
            } else {
                request.setAttribute("mensagem", "Erro ao atualizar entrega. Tente novamente mais tarde.");
                request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
            }

        } catch (ErroAoDeletar e) {
            request.setAttribute("msg", "Erro ao atualizar entrega. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/listar-motorista.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}