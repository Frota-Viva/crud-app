package com.frotaviva.servlet.caminhao;

import com.frotaviva.dao.CaminhaoDAO;
import com.frotaviva.dao.FrotaDAO;
import com.frotaviva.exception.ErroAoDeletar;
import com.frotaviva.model.Caminhao;

import com.frotaviva.model.Frota;
import com.frotaviva.util.Validar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AtualizarCaminhao", value = "/atualizar-caminhao")
public class AtualizarCaminhaoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;
        CaminhaoDAO dao =  new CaminhaoDAO();

        HttpSession session = req.getSession(true);
        Object id_empresa = session.getAttribute("idEmpresa");

        if (id_empresa == null) {
            resp.sendRedirect("/");
            return;
        }

        try {
            id = Long.parseLong(req.getParameter("id"));

            if (dao.buscarPorId(id) == null) {
                resp.sendRedirect("/listar-caminhao");
            }

            req.setAttribute("caminhao", dao.buscarPorId(id));
            req.getRequestDispatcher("WEB-INF/view/caminhao/atualizar-caminhao.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendRedirect("/listar-caminhao");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id;
        String placa;
        int kmRodados;
        String modelo;
        String status;
        int capacidade;
        long idFrota;

        HttpSession session = request.getSession(true);
        Object id_empresa = session.getAttribute("idEmpresa");

        if (id_empresa == null) {
            response.sendRedirect("/");
            return;
        }

        long idEmpresa =  (long) id_empresa;

        try{
            CaminhaoDAO dao = new CaminhaoDAO();

            id = Long.parseLong(request.getParameter("id"));
            placa = request.getParameter("placa");
            status = request.getParameter("status");
            kmRodados = Integer.parseInt(request.getParameter("kmRodados"));
            modelo = request.getParameter("modelo");
            capacidade = Integer.parseInt(request.getParameter("capacidade"));
            idFrota = Long.parseLong(request.getParameter("idFrota"));

            if (!Validar.placa(placa)){
                request.setAttribute("erroPlaca", "Placa inválida! Deve seguir o padrão XXX1X11");
            }

            if (!Validar.status(status)){
                request.setAttribute("erroStatus", "Status inválido! Deve ser 'I' (Inativo), 'A' (Ativo) ou 'M'(Em manutenção).");
            }

            if (modelo ==  null || modelo.isEmpty()){
                request.setAttribute("erroModelo", "Modelo inválido! Não pode ser nulo.");
            }
            
            if (capacidade <= 0){
                request.setAttribute("erroCapacidade", "Capacidade inválida! Deve ser maior que 0.");
            }

            if (kmRodados < 0){
                request.setAttribute("erroKmRodados", "Kilometragem inválida! Não pode ser menor que 0.");
            }

            /*
              Para verificar a frota,
              primeiro eu verifico as frotas que o usuário possui,
              depois verifico se a frota inserida está dentro delas
             */

            FrotaDAO frotaDAO = new FrotaDAO();

            List<Frota> frotas = frotaDAO.buscarPorEmpresa(idEmpresa);
            Frota frota = frotaDAO.buscarPorId(idFrota);

            if (!frotas.contains(frota)){
                request.setAttribute("erroFrota", "Frota inválida! Selecione uma frota existente.");
            }

            Caminhao caminhao = dao.buscarPorId(id);

            caminhao.setPlaca(placa);
            caminhao.setStatus(status);
            caminhao.setKmRodados(kmRodados);
            caminhao.setModelo(modelo);
            caminhao.setCapacidade(capacidade);
            caminhao.setIdFrota(idFrota);

            if (dao.atualizar(caminhao) == 1) {
                response.sendRedirect("/listar-caminhao?msg=Caminhão+atualizado+com+sucesso");
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
