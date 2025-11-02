package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.exception.ErroAoConsultar;
import com.frotaviva.model.Motorista;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * Responsável por listar os motoristas cadastrados de uma empresa.
 * <p>
 * Este servlet exibe a lista completa de motoristas ou realiza uma busca filtrada pelo nome,
 * retornando os resultados para a página JSP de listagem.
 * </p>
 * <p>
 * Principais funcionalidades:
 * <ul>
 *     <li>Verifica se existe uma empresa logada, senão redireciona para a landing page;</li>
 *     <li>Se o parâmetro de busca estiver presente, filtra os motoristas pelo nome;</li>
 *     <li>Se não houver filtro, lista todos os motoristas da empresa;</li>
 *     <li>Envia os dados para a JSP responsável por exibir a lista;</li>
 *     <li>Se ocorrer algum erro de consulta ou inesperado, encaminha para a página de erro com a mensagem apropriada.</li>
 * </ul>
 * </p>
 * 
 * @author Davi Alcanfor
 */
@WebServlet(name = "ListaMotoristas", value = "/listar-motoristas")
public class ListarMotoristasServlet extends HttpServlet {

    /**
     * Exibe a lista de motoristas da empresa.
     * <p>
     * Etapas principais:
     * <ul>
     *     <li>Recupera a sessão e verifica se existe uma empresa logada;</li>
     *     <li>Recebe o parâmetro opcional "buscar" para filtro de nome;</li>
     *     <li>Se houver filtro, busca motoristas pelo nome associado à empresa;</li>
     *     <li>Se não houver filtro, busca todos os motoristas da empresa;</li>
     *     <li>Envia a lista de motoristas para a JSP de listagem;</li>
     *     <li>Se ocorrer {@link ErroAoConsultar} ou qualquer outro erro, encaminha para página de erro.</li>
     * </ul>
     * </p>
     *
     * @param request  requisição HTTP recebida
     * @param response resposta HTTP enviada ao cliente
     * @throws ServletException se ocorrer um erro interno no servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Object id = session.getAttribute("idEmpresa");
        String buscar = request.getParameter("buscar");

        // Se não existir empresa logada, redireciona para landing page
        if (id == null){
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id;

        try {
            MotoristaDAO dao = new MotoristaDAO();
            List<Motorista> motoristas;

            if (buscar != null && !buscar.isBlank()) {
                motoristas = dao.buscarPorEmpresaComNome(idEmpresa, buscar);
            } else {
                motoristas = dao.buscarPorEmpresa(idEmpresa);
            }

            request.setAttribute("motoristas", motoristas);
            request.getRequestDispatcher("/WEB-INF/view/motorista/listar-motorista.jsp").forward(request, response);

        } catch (ErroAoConsultar e) {
            response.sendRedirect("/home?msg=Erro ao encontrar motoristas. Tente novamente mais tarde.");
        } catch (Exception e) {
            response.sendRedirect("/home?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");

        }
    }
}
