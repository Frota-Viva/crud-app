package com.frotaviva.servlet.entrega;

import com.frotaviva.dao.EntregaDAO;
import com.frotaviva.exception.ErroAoConsultar;
import com.frotaviva.model.Entrega;
import com.frotaviva.util.Validar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "ListarEntregas", value = "/listar-entregas")
public class ListarEntregas extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        Object id = session.getAttribute("idEmpresa");

        String buscar = req.getParameter("buscar");

        if (id == null){
            resp.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id;

        try {
            EntregaDAO entregaDAO = new EntregaDAO();
            List<Entrega> entregas;

            if (buscar != null && !buscar.isBlank() && Validar.data(buscar)){
                Date data = Date.valueOf(buscar);
                entregas = entregaDAO.buscarPorIdEmpresaComDatas(idEmpresa, data);
            } else {
                entregas = entregaDAO.buscarPorIdEmpresa(idEmpresa);
            }

            req.setAttribute("entregas", entregas);
            req.getRequestDispatcher("/WEB-INF/view/entrega/listar-entrega.jsp").forward(req, resp);

        } catch (ErroAoConsultar e) {
            req.setAttribute("mensagem", "Erro ao acessar o encontrar entregas. Tente novamente mais tarde.");
            req.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
            req.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(req, resp);
        }


    }
}
