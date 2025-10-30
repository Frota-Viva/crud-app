package com.frotaviva.servlet.caminhao;

import com.frotaviva.dao.CaminhaoDAO;
import com.frotaviva.exception.ErroAoDeletar;
import com.frotaviva.model.Caminhao;

import com.frotaviva.util.Validar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AtualizarCaminhao", value = "/atualizar-caminhao")
public class AtualizarCaminhaoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id;
        String placa;
        int kmRodados;
        String modelo;
        String status;
        int capacidade;
        long idFrota;

        try{
            id = Long.parseLong(request.getParameter("id"));
            placa = request.getParameter("placa");
            status = request.getParameter("status");
            kmRodados = Integer.parseInt(request.getParameter("kmRodados"));
            modelo = request.getParameter("modelo");
            capacidade = Integer.parseInt(request.getParameter("capacidade"));
            idFrota = Long.parseLong(request.getParameter("idFrota"));

            if (!Validar.placa(placa)){
                request.setAttribute("erro", "Placa inválida! Deve seguir o padrão XXX1X11");
            }

            if (!Validar.status(status)){
                request.setAttribute("erro", "Status inválido! Deve ser 'I' (Inativo), 'A' (Ativo) ou 'M'(Em manutenção).");
            }

            if (modelo ==  null || modelo.isEmpty()){
                request.setAttribute("erro", "Modelo inválido! Não pode ser nulo.");
            }

            CaminhaoDAO dao = new CaminhaoDAO();
            Caminhao caminhao = dao.buscarPorId(id);

            caminhao.setPlaca(placa);
            caminhao.setStatus(status);
            caminhao.setKmRodados(kmRodados);
            caminhao.setModelo(modelo);
            caminhao.setCapacidade(capacidade);
            caminhao.setIdFrota(idFrota);

            if (dao.atualizar(caminhao) == 1) {
                response.sendRedirect("/listar-motorista?msg=Motorista+deletado+com+sucesso");
            } else{
                request.setAttribute("mensagem", "Erro ao atualizar caminhao. Tente novamente mais tarde.");
                request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
            }


        } catch (ErroAoDeletar e) {
            request.setAttribute("mensagem", "Erro ao atualizar caminhao. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}
