package com.frotaviva.servlet.frota;

import com.frotaviva.dao.FrotaDAO;
import com.frotaviva.exception.ErroAoAtualizar;
import com.frotaviva.exception.ErroAoConsultar;
import com.frotaviva.model.Frota;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

        HttpSession session = request.getSession(true); //Pega a sessão
        Object id_session = session.getAttribute("idEmpresa"); //Pega o id_session da empresa na sessão

        //Verifica se o id_session existe
        if (id_session == null){
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id_session;

        id = Long.parseLong(request.getParameter("id"));
        tamanhoFrota = Integer.parseInt(request.getParameter("tamanhoFrota"));
        tipoFrota = request.getParameter("tipoFrota");
        regiao = request.getParameter("regiao");

        if (tipoFrota == null || tipoFrota.isEmpty()){
            request.setAttribute("erro", "Tipo de frota inválido! Não pode ser nulo.");
        }

        if (regiao == null || regiao.isEmpty()){
            request.setAttribute("erro", "Região inválida! Não pode ser nula.");
        }

        if (tamanhoFrota <= 0){
            request.setAttribute("erro", "Tamanho da frota inválida! Não pode ser menor ou igual a 0.");
        }

        try{
            FrotaDAO dao = new FrotaDAO();
            Frota frota = dao.buscarPorId(id);

            frota.setTamanhoFrota(tamanhoFrota);
            frota.setTipoFrota(tipoFrota);
            frota.setRegiao(regiao);
            frota.setIdEmpresa(idEmpresa);

            if (dao.atualizar(frota) == 1) {
                response.sendRedirect("/listar-frota");
                return;
            }

            request.setAttribute("mensagem", "Erro ao atualizar frota. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);

        } catch (ErroAoAtualizar e) {
            request.setAttribute("mensagem", "Erro ao atualizar frota. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}
