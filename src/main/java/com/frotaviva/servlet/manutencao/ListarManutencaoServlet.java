package com.frotaviva.servlet.manutencao;

import com.frotaviva.dao.ManutencaoDAO;
import com.frotaviva.exception.ErroAoConsultar;
import com.frotaviva.model.Manutencao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * Responsável por listar as manutenções cadastradas no sistema.
 * <p>
 * Este servlet exibe todas as manutenções da empresa logada ou realiza
 * uma busca filtrada por tipo de manutenção se o parâmetro "buscar" estiver presente.
 * </p>
 * <p>
 * Principais funcionalidades:
 * <ul>
 *     <li>Verifica se há uma empresa logada;</li>
 *     <li>Busca a lista de manutenções da empresa;</li>
 *     <li>Permite filtrar manutenções por tipo usando o parâmetro "buscar";</li>
 *     <li>Redireciona os dados para a JSP de listagem;</li>
 *     <li>Trata exceções exibindo mensagens de erro apropriadas.</li>
 * </ul>
 * </p>
 * 
 * @author Davi Alcanfor
 */
@WebServlet(name = "ListaManutencao", value = "/listar-manutencao")
public class ListarManutencaoServlet extends HttpServlet {

    /**
     * Processa a requisição GET para listar manutenções da empresa logada.
     * <p>
     * Se não houver empresa logada, redireciona para a landing page.
     * Se houver erro no servidor ou na consulta, redireciona para a home com a mensagem apropriada.
     * </p>
     * 
     * @param request  requisição HTTP recebida
     * @param response resposta HTTP enviada ao cliente
     * @throws ServletException se ocorrer um erro no processamento do servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Object id = session.getAttribute("idEmpresa");

        String buscar = request.getParameter("buscar");

        if (id == null) {
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id;

        try {
            ManutencaoDAO dao = new ManutencaoDAO();
            List<Manutencao> manutencoes;

            if (buscar != null && !buscar.isBlank()) {
                manutencoes = dao.buscarPorEmpresaComTipo(idEmpresa, buscar);
            } else {
                manutencoes = dao.buscarPorEmpresa(idEmpresa);
            }

            request.setAttribute("manutencoes", manutencoes);
            request.getRequestDispatcher("WEB-INF/view/manutencao/listar-manutencao.jsp").forward(request, response);

        } catch (ServletException e) {
            response.sendRedirect("/home?msg=Ocorreu um erro no servidor. Tente novamente mais tarde.");
        } catch (ErroAoConsultar e) {
            response.sendRedirect("/home?msg=Erro ao acessar ou encontrar manutenções. Tente novamente mais tarde.");
        } catch (Exception e) {
            response.sendRedirect("/home?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }
    }
}
