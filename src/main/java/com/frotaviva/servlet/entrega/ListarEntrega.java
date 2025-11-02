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

/**
 * Servlet responsável por listar todas as entregas de uma empresa.
 * <p>
 * Este servlet permite consultar entregas cadastradas, podendo filtrar por data.
 * </p>
 * <p>
 * Principais funcionalidades:
 * <ul>
 *     <li>Verifica se há uma empresa logada;</li>
 *     <li>Consulta todas as entregas da empresa;</li>
 *     <li>Permite busca filtrada por data, se o parâmetro 'buscar' for fornecido;</li>
 *     <li>Exibe a lista de entregas na página correspondente;</li>
 *     <li>Redireciona com mensagens de erro em caso de falha na consulta.</li>
 * </ul>
 * </p>
 * 
 * @author Ricardo
 */
@WebServlet(name = "ListarEntregas", value = "/listar-entregas")
public class ListarEntrega extends HttpServlet {

    /**
     * Processa a requisição GET para listar as entregas.
     * <p>
     * Se não houver empresa logada, o usuário é redirecionado para a landing page.
     * Se for o parâmetro 'buscar' existir contendo uma data válida, a lista será filtrada.
     * Caso contrário, retorna todas as entregas da empresa.
     * </p>
     *
     * @param req  requisição HTTP recebida
     * @param resp resposta HTTP enviada ao cliente
     * @throws ServletException se ocorrer erro interno no servlet
     * @throws IOException se ocorrer erro de entrada/saída durante o processamento
     */
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
            resp.sendRedirect("/home?msg=Erro ao consultar entregas");
        } catch (Exception e) {
            resp.sendRedirect("/home?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }
    }
}
