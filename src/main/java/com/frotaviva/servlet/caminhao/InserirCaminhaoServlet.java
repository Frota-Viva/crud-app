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
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String placa = request.getParameter("placa");
        String status = request.getParameter("status");
        int kmRodados = Integer.parseInt(request.getParameter("kmRodados"));
        String modelo = request.getParameter("modelo");
        int capacidade = Integer.parseInt(request.getParameter("capacidade"));
        long idFrota = Long.parseLong(request.getParameter("idFrota"));

        if (!Validar.placa(placa)){
            request.setAttribute("erro", "Placa inválida! Deve seguir o padrão XXX1X11");
        }

        if (!Validar.status(status)){
            request.setAttribute("erro", "Status inválido! Deve ser 'I' (Inativo), 'A' (Ativo) ou 'M'(Em manutenção).");
        }

        if (modelo ==  null || modelo.isEmpty()){
            request.setAttribute("erro", "Modelo inválido! Não pode ser nulo.");
        }


        try{

            CaminhaoDAO dao = new CaminhaoDAO();
            Caminhao caminhao = new Caminhao(placa, status, kmRodados, modelo, capacidade, idFrota);

            if (dao.inserir(caminhao) == 1){
                response.sendRedirect("listar-caminhao");
                return;
            }

            request.setAttribute("mensagem", "Erro ao inserir caminhao. Tente novamente mais tarde.");
            request.getRequestDispatcher("WEB-INF/view/listar-frota.jsp").forward(request, response);

        } catch (ErroAoInserir e) {
            request.setAttribute("mensagem", "Erro ao inserir caminhao. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}