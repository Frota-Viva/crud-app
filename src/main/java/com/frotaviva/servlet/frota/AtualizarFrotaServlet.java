package com.frotaviva.servlet.frota;

import com.frotaviva.dao.FrotaDAO;
import com.frotaviva.model.Frota;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "AtualizarFrota", value = "/atualizar-frota")
public class AtualizarFrotaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id;
        int tamanhoFrota;
        String tipoFrota;
        String regiao;
        long idEmpresa;

        try{

            id = Long.parseLong(request.getParameter("id"));
            tamanhoFrota = Integer.parseInt(request.getParameter("tamanhoFrota"));
            tipoFrota = request.getParameter("tipoFrota");
            regiao = request.getParameter("regiao");
            idEmpresa = Long.parseLong(request.getParameter("idEmpresa"));

            if (tipoFrota == null || tipoFrota.isEmpty()){
                request.setAttribute("erro", "Tipo de frota inválido! Não pode ser nulo.");
            }

            if (regiao == null || regiao.isEmpty()){
                request.setAttribute("erro", "Região inválida! Não pode ser nula.");
            }

            if (tamanhoFrota <= 0){
                request.setAttribute("erro", "Tamanho da frota inválida! Não pode ser menor ou igual a 0.");
            }

            FrotaDAO dao = new FrotaDAO();
            Frota frota = dao.buscarPorId(id);

            frota.setTamanhoFrota(tamanhoFrota);
            frota.setTipoFrota(tipoFrota);
            frota.setRegiao(regiao);
            frota.setIdEmpresa(idEmpresa);

            if (dao.atualizar(frota) == 1) {
                response.sendRedirect("/lista-frota?msg=Sucesso");
            } else{
                response.sendRedirect("/erro.jsp");
            }

        } catch (Exception e){ // ainda nao tem a pagina de erro
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}
