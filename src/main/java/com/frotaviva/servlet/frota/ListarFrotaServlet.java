package com.frotaviva.servlet.frota;

import com.frotaviva.dao.FrotaDAO;
import com.frotaviva.exception.ErroAoConsultar;
import com.frotaviva.model.Frota;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * Responsável por listar as frotas de uma empresa no sistema.
 * <p>
 * Este servlet exibe todas as frotas da empresa logada e permite também filtrar
 * a lista pelo tipo de frota.
 * </p>
 * <p>
 * Funcionalidades principais:
 * <ul>
 *     <li>Busca todas as frotas de uma empresa;</li>
 *     <li>Busca frotas filtradas pelo tipo;</li>
 *     <li>Redireciona para a página de listagem com mensagens de erro caso ocorra falha.</li>
 * </ul>
 * </p>
 * <p>
 * Se não houver empresa logada, o usuário é redirecionado para a landing page.
 * </p>
 * 
 * @author Davi
 */
@WebServlet(name = "ListaFrota", value = "/listar-frota")
public class ListarFrotaServlet extends HttpServlet {

    /**
     * Processa a requisição GET para exibir a lista de frotas.
     * <p>
     * Se existir o parâmetro "buscar", realiza a filtragem das frotas pelo tipo.
     * Se ocorrer algum erro durante a consulta, redireciona o usuário com mensagem apropriada.
     * </p>
     * 
     * <p>
     * Se o usuário não estiver logado ele é redirecionado para a landing page
     * </p> 
     *
     * @param request  requisição HTTP recebida
     * @param response resposta HTTP enviada ao cliente
     * @throws ServletException se ocorrer erro no processamento do servlet
     * @throws IOException se ocorrer erro de entrada ou saída
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Object id = session.getAttribute("idEmpresa");

        if (id == null){
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id;
        String buscar = request.getParameter("buscar");

        try {
            FrotaDAO dao = new FrotaDAO();
            List<Frota> frotas;

            if (buscar != null && !buscar.isBlank()) {
                frotas = dao.buscarPorEmpresaComTipoFrota(idEmpresa, buscar);
            } else {
                frotas = dao.buscarPorEmpresa(idEmpresa);
            }

            request.setAttribute("frotas", frotas);
            request.getRequestDispatcher("/WEB-INF/view/frota/listar-frota.jsp").forward(request, response);

        } catch (ErroAoConsultar e) {
            response.sendRedirect("/home?msg=Erro ao consultar frotas");
        } catch (Exception e) {
            response.sendRedirect("/home?msg=Ocorreu um erro desconhecido");
        }
    }
}