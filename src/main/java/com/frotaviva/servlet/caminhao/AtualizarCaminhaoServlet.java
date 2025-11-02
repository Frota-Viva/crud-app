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

/**
 * Responsável por atualizar os dados de um caminhão já cadastrado.
 * <p>
 * Este servlet permite buscar as informações de um caminhão existente e atualizar seus dados,
 * garantindo que as validações sejam respeitadas e que o caminhão pertença à empresa logada.
 * </p>
 * <p>
 * Funcionalidades principais:
 * <ul>
 *     <li><b>GET:</b> Exibe o formulário de atualização com os dados atuais do caminhão;</li>
 *     <li><b>POST:</b> Valida os novos dados e atualiza o caminhão no banco de dados;</li>
 *     <li>Verifica se a frota informada pertence à empresa logada antes de realizar a atualização;</li>
 *     <li>Redireciona o usuário com mensagens adequadas conforme o resultado da operação.</li>
 * </ul>
 * </p>
 *
 * @author Davi Alcanfor
 */
@WebServlet(name = "AtualizarCaminhao", value = "/atualizar-caminhao")
public class AtualizarCaminhaoServlet extends HttpServlet {

    /**
     * Exibe a página de atualização do caminhão.
     * <p>
     * Este método procura o caminhão pelo parâmetro "id" recebido na requisição e
     * encaminha seus dados para o JSP de atualização. Se o caminhão não existir ou o parâmetro
     * for inválido, o usuário é redirecionado para a lista de caminhões.
     * </p>
     *
     * @param req  requisição HTTP recebida
     * @param resp resposta HTTP enviada ao cliente
     * @throws ServletException se ocorrer erro durante o encaminhamento do JSP
     * @throws IOException se ocorrer erro de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;
        CaminhaoDAO dao = new CaminhaoDAO();

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

    /**
     * Processa a atualização dos dados do caminhão.
     * <p>
     * Este método valida os dados recebidos do formulário, garantindo que todos os campos
     * obrigatórios sejam válidos e que a frota informada pertença à empresa logada.
     * </p>
     * <p>
     * A atualização só é realizada se:
     * <ul>
     *     <li>Os dados forem válidos;</li>
     *     <li>O caminhão existir no banco de dados;</li>
     *     <li>A frota selecionada pertencer à empresa logada.</li>
     * </ul>
     * </p>
     * <p>
     * Se houver erro de validação, o formulário é exibido novamente com mensagens de erro.
     * Se ocorrer um erro inesperado, o usuário é redirecionado para a página inicial com mensagem apropriada.
     * </p>
     *
     * @param request  requisição HTTP com os dados do formulário
     * @param response resposta HTTP enviada ao cliente
     * @throws ServletException se ocorrer erro interno ao encaminhar a resposta
     * @throws IOException se ocorrer falha na comunicação com o cliente
     */
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
        boolean erro = false;

        if (id_empresa == null) {
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id_empresa;

        try {
            CaminhaoDAO dao = new CaminhaoDAO();

            id = Long.parseLong(request.getParameter("id"));
            placa = request.getParameter("placa");
            status = request.getParameter("status");

            String kmRodadosReq = request.getParameter("kmsRodados");
            kmRodados = (!Validar.testeVazio(kmRodadosReq)) ? Integer.parseInt(kmRodadosReq) : -1;

            modelo = request.getParameter("modelo");

            String capacidadeReq = request.getParameter("capacidade");
            capacidade = (!Validar.testeVazio(capacidadeReq)) ? Integer.parseInt(capacidadeReq) : -1;

            String idFrotaReq = request.getParameter("idFrota");
            idFrota = (!Validar.testeVazio(idFrotaReq)) ? Long.parseLong(idFrotaReq) : -1;

            // Validação dos campos
            if (!Validar.placa(placa)) {
                request.setAttribute("erroPlaca", "Placa inválida! Deve seguir o padrão XXX1X11");
                erro = true;
            }

            if (!Validar.status(status)) {
                request.setAttribute("erroStatus", "Status inválido! Deve ser 'I' (Inativo), 'A' (Ativo) ou 'M'(Em manutenção).");
                erro = true;
            }

            if (Validar.testeVazio(modelo)) {
                request.setAttribute("erroModelo", "Modelo inválido! Não pode ser nulo.");
                erro = true;
            }

            if (capacidade <= 0) {
                request.setAttribute("erroCapacidade", "Capacidade inválida! Deve ser maior que 0.");
                erro = true;
            }

            if (kmRodados < 0) {
                request.setAttribute("erroKmRodados", "Kilometragem inválida! Não pode ser menor que 0.");
                erro = true;
            }

            if (idFrota <= 0) {
                request.setAttribute("erroFrota", "Frota inválida! Selecione uma frota existente.");
                erro = true;
            }

            Caminhao caminhao = dao.buscarPorId(id);

            if (erro) {
                request.setAttribute("caminhao", caminhao);
                request.getRequestDispatcher("WEB-INF/view/caminhao/atualizar-caminhao.jsp").forward(request, response);
                return;
            }

            /*
              Para verificar a frota,
              primeiro são buscadas todas as frotas da empresa,
              e em seguida é verificado se a frota informada está entre elas.
             */
            FrotaDAO frotaDAO = new FrotaDAO();
            List<Frota> frotas = frotaDAO.buscarPorEmpresa(idEmpresa);

            if (!frotaExiste(idFrota, frotas)) {
                request.setAttribute("caminhao", caminhao);
                request.setAttribute("erroFrota", "Frota inválida! Selecione uma frota existente.");
                request.getRequestDispatcher("WEB-INF/view/caminhao/atualizar-caminhao.jsp").forward(request, response);
                return;
            }

            caminhao.setPlaca(placa);
            caminhao.setStatus(status);
            caminhao.setKmRodados(kmRodados);
            caminhao.setModelo(modelo);
            caminhao.setCapacidade(capacidade);
            caminhao.setIdFrota(idFrota);

            if (dao.atualizar(caminhao) == 1) {
                response.sendRedirect("/listar-caminhao?msg=Caminhao+atualizado+com+sucesso");
            } else {
                request.setAttribute("msg", "Erro ao atualizar caminhao. Tente novamente mais tarde.");
                request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
            }

        } catch (ErroAoDeletar e) {
            response.sendRedirect("/home?msg=Erro ao atualizar caminhao. Tente novamente mais tarde.");
        } catch (Exception e) {
            response.sendRedirect("/home?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }
    }

    /**
     * Verifica se uma frota com o ID especificado existe na lista fornecida.
     *
     * @param idFrota ID da frota a ser verificada
     * @param frotas  lista de frotas pertencentes à empresa
     * @return {@code true} se a frota existir, {@code false} caso contrário
     */
    private static boolean frotaExiste(long idFrota, List<Frota> frotas) {
        for (Frota frota : frotas) {
            if (frota.getId() == idFrota) return true;
        }
        return false;
    }
}
