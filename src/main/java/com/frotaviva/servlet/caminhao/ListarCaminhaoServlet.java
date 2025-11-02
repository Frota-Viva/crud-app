package com.frotaviva.servlet.caminhao;

import com.frotaviva.dao.CaminhaoDAO;
import com.frotaviva.exception.ErroAoConsultar;
import com.frotaviva.model.Caminhao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 * Responsável por listar os caminhões cadastrados da empresa logada.
 * <p>
 * Este servlet recupera os caminhões vinculados à empresa do usuário logado e os envia
 * para a página JSP correspondente. Também permite realizar buscas filtrando os resultados
 * pela placa informada no parâmetro de requisição.
 * </p>
 *
 * <p>
 * Funcionalidades principais:
 * <ul>
 *     <li>Lista todos os caminhões da empresa logada;</li>
 *     <li>Permite buscar caminhões por parte da placa informada;</li>
 *     <li>Redireciona para a página de login se não houver sessão válida;</li>
 *     <li>Encaminha a lista de caminhões para a view JSP responsável pela exibição.</li>
 * </ul>
 * </p>
 *
 * @author Davi Alcanfor
 */
@WebServlet(name = "ListarCaminhao", value = "/listar-caminhao")
public class ListarCaminhaoServlet extends HttpServlet {

    /**
     * Processa as requisições do tipo GET para listar os caminhões.
     * <p>
     * Se o parâmetro <b>buscar</b> estiver presente, a listagem é filtrada com base na placa informada.
     * Caso contrário, são retornados todos os caminhões da empresa logada.
     * </p>
     *
     * <p>
     * O método executa as seguintes etapas:
     * <ul>
     *     <li>Busca o ID da empresa armazenado na sessão;</li>
     *     <li>Verifica se o usuário possui sessão ativa;</li>
     *     <li>Consulta o banco de dados usando o {@link CaminhaoDAO};</li>
     *     <li>Encaminha os resultados para o JSP de listagem;</li>
     *     <li>Redireciona para a página inicial se ocorrer algum erro.</li>
     * </ul>
     * </p>
     *
     * @param request  requisição HTTP recebida
     * @param response resposta HTTP enviada ao cliente
     * @throws ServletException se ocorrer erro durante o encaminhamento do JSP
     * @throws IOException se ocorrer erro de entrada ou saída
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true); // Pega a sessão
        Object id = session.getAttribute("idEmpresa");  // Pega o id da empresa na sessão

        String buscar = request.getParameter("buscar");

        if (id == null) {
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id;

        try {
            CaminhaoDAO dao = new CaminhaoDAO();
            List<Caminhao> caminhoes;

            if (buscar != null && !buscar.isBlank()) {
                caminhoes = dao.buscarPorEmpresaComPlaca(idEmpresa, buscar);
            } else {
                caminhoes = dao.buscarPorEmpresa(idEmpresa);
            }

            request.setAttribute("caminhoes", caminhoes);
            request.getRequestDispatcher("/WEB-INF/view/caminhao/listar-caminhao.jsp").forward(request, response);

        } catch (ErroAoConsultar e) {
            response.sendRedirect("/home?msg=Erro ao consultar motoristas");
        } catch (Exception e) {
            response.sendRedirect("/home?msg=Ocorreu um erro desconhecido");
        }
    }
}
