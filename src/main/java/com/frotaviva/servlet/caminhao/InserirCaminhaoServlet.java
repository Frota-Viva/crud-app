package com.frotaviva.servlet.caminhao;

import com.frotaviva.dao.CaminhaoDAO;

import com.frotaviva.exception.ErroAoInserir;
import com.frotaviva.model.Caminhao;

import com.frotaviva.util.Validar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "InserirCaminhao", value = "/inserir-caminhao")
public class InserirCaminhaoServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/caminhao/inserir-caminhao.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean erro = false;

        try{

            CaminhaoDAO dao = new CaminhaoDAO();

            String placa = request.getParameter("placa");
            String status = request.getParameter("status");
            int kmRodados = Integer.parseInt(request.getParameter("kmRodados"));
            String modelo = request.getParameter("modelo");
            int capacidade = Integer.parseInt(request.getParameter("capacidade"));
            long idFrota = Long.parseLong(request.getParameter("idFrota"));


            if (!Validar.placa(placa)){
                request.setAttribute("erro", "Placa inválida! Deve seguir o padrão XXX1X11");
                erro = true;
            }

            if (!Validar.status(status)){
                request.setAttribute("erro", "Status inválido! Deve ser 'I' | 'A' | 'M'.");
                erro = true;
            }

            if (modelo ==  null || modelo.isEmpty()){
                request.setAttribute("erro", "Modelo inválido! Não pode ser nulo.");
                erro = true;
            }

            if (erro){
                request.getRequestDispatcher("WEB-INF/view/caminhao/inserir-caminhao.jsp").forward(request, response);
            }


            Caminhao caminhao = new Caminhao(placa, status, kmRodados, modelo, capacidade, idFrota);

            if (dao.inserir(caminhao) == 1){
                response.sendRedirect("/listar-caminhao?msg=Caminhão+inserido+com+sucesso");
                return;
            }

            response.sendRedirect("/listar-caminhao?msg=Erro+ao+inserir+caminhao.+Tente+novamente+mais+tarde");

        } catch (ErroAoInserir e) {
            response.sendRedirect("/listar-caminhao?msg=Erro+ao+inserir+caminhao.+Tente+novamente+mais+tarde");
        } catch (NumberFormatException e) {
            response.sendRedirect("/listar-caminhao?msg=Formato+inválido+inserido.+Tente+novamente+mais+tarde");
        } catch (Exception e) {
            response.sendRedirect("/listar-caminhao?msg=Ocorreu+um+erro+inesperado.+Tente+novamente+mais+tarde");
        }
    }
}