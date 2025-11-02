package com.frotaviva.servlet.entrega;

import com.frotaviva.dao.EntregaDAO;
import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.exception.ErroAoDeletar;
import com.frotaviva.model.Endereco;
import com.frotaviva.model.Entrega;
import com.frotaviva.model.Motorista;
import com.frotaviva.util.Validar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Servlet responsável por atualizar os dados de uma entrega.
 * <p>
 * Este servlet permite alterar informações da entrega, como datas,
 * descrição do produto, endereço e motorista responsável.
 * Realiza validações de campos obrigatórios e consistência de datas.
 * </p>
 * <p>
 * Se a empresa não estiver logada, redireciona para a landing page.
 * </p>
 * 
 * @author Ricardo
 */
@WebServlet(name = "AtualizarEntrega", value = "/atualizar-entrega")
public class AtualizarEntrega extends HttpServlet {

    /**
     * Exibe a página de atualização de uma entrega.
     * <p>
     * Se o ID da empresa não existir na sessão, redireciona para a landing page.
     * Se o código da entrega não existir ou estiver incorreto, redireciona para a listagem de entregas.
     * </p>
     *
     * @param req  requisição HTTP recebida
     * @param resp resposta HTTP a ser enviada
     * @throws ServletException se ocorrer erro no processamento do servlet
     * @throws IOException      se ocorrer erro de entrada/saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        Object id_empresa = session.getAttribute("idEmpresa");

        if (id_empresa == null) {
            resp.sendRedirect("/");
            return;
        }

        try {
            long cod_entrega = Long.parseLong(req.getParameter("cod_entrega"));
            EntregaDAO dao = new EntregaDAO();
            Entrega entrega = dao.buscarPorId(cod_entrega);

            if (entrega == null) {
                resp.sendRedirect("/listar-entregas");
                return;
            }

            req.setAttribute("entrega", entrega);
            req.getRequestDispatcher("WEB-INF/view/entrega/atualizar-entrega.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendRedirect("/listar-entregas");
        }
    }

    /**
     * Realiza a atualização da entrega com os dados enviados pelo formulário.
     * <p>
     * Realiza validação dos campos obrigatórios, valida datas e verifica se o motorista
     * pertence à empresa logada.
     * </p>
     *
     * @param request  requisição HTTP contendo os dados do formulário
     * @param response resposta HTTP enviada ao cliente
     * @throws ServletException se ocorrer erro no processamento do servlet
     * @throws IOException      se ocorrer erro de entrada/saída
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Object id_empresa = session.getAttribute("idEmpresa");
        boolean erro = false;

        if (id_empresa == null) {
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) id_empresa;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            EntregaDAO dao = new EntregaDAO();

            long cod_entrega = Long.parseLong(request.getParameter("cod_entrega"));
            String descricaoProduto = request.getParameter("descricao_produto");
            Date dtPedido = new Date(sdf.parse(request.getParameter("dt_pedido")).getTime());
            Date dtEntrega = !request.getParameter("dt_entrega").equals("") ?
                    new Date(sdf.parse(request.getParameter("dt_entrega")).getTime()) : null;

            String cep = request.getParameter("cep");
            int numero = Integer.parseInt(request.getParameter("numero"));
            String cidade = request.getParameter("cidade");
            String rua = request.getParameter("rua");
            String pais = request.getParameter("pais");
            String estado = request.getParameter("estado");
            String complemento = request.getParameter("complemento");

            Endereco endereco = new Endereco(pais, cep, estado, cidade, rua, numero, complemento);
            long idMotorista = Long.parseLong(request.getParameter("idMotorista"));

            // Validações
            if (descricaoProduto == null || descricaoProduto.isEmpty()){
                erro = true;
                request.setAttribute("erroDescricao", "Descrição inválida! Não pode ser nula.");
            }

            if (!Validar.data(String.valueOf(dtPedido))){
                erro = true;
                request.setAttribute("erroDtPedido", "Data de pedido inválida!");
            }

            if (!Validar.data(String.valueOf(dtEntrega))){
                erro = true;
                request.setAttribute("erroDtEntrega", "Data de entrega inválida!");
            }

            if (dtEntrega != null && dtEntrega.before(dtPedido)){
                erro = true;
                request.setAttribute("erroDtEntrega", "Data de entrega não pode ser anterior à data do pedido!");
            }

            if (endereco == null) {
                erro = true;
                request.setAttribute("erroEndereco", "Endereço inválido! Não pode ser nulo.");
            }
            
            Entrega entrega = dao.buscarPorId(cod_entrega);

            if (erro){
                request.setAttribute("entrega", entrega);
                request.getRequestDispatcher("WEB-INF/view/entrega/atualizar-entrega.jsp").forward(request, response);
                return;
            }
            
            MotoristaDAO motoristaDAO = new MotoristaDAO();
            List<Motorista> motoristas = motoristaDAO.buscarPorEmpresa(idEmpresa);

            if (!motoristaExiste(idMotorista, motoristas)){
                request.setAttribute("erroMotorista", "Motorista inválido! Selecione um motorista existente.");
                request.setAttribute("entrega", entrega);
                request.getRequestDispatcher("WEB-INF/view/entrega/atualizar-entrega.jsp").forward(request, response);
                return;
            }

            // Atualiza a entrega
            entrega.setDescricaoProduto(descricaoProduto);
            entrega.setDtPedido(dtPedido);
            entrega.setDtEntrega(dtEntrega);
            entrega.setEndereco(endereco);
            entrega.setIdMotorista(idMotorista);

            if (dao.atualizar(entrega) == 1) {
                response.sendRedirect("/listar-entregas?msg=Entrega+atualizada+com+sucesso");
            } else {
                request.setAttribute("msg", "Erro ao atualizar entrega. Tente novamente mais tarde.");
                request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
            }

        } catch (ErroAoDeletar e) {
            response.sendRedirect("/home?msg=Erro ao atualizar entrega. Tente novamente mais tarde.");
        } catch (Exception e) {
            response.sendRedirect("/home?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }
    }

    /**
     * Verifica se o motorista com o ID fornecido existe na lista de motoristas.
     * 
     * 
     * @param idMotorista ID do motorista a ser verificado
     * @param motoristas lista de motoristas da empresa
     * @return
     */
    private static boolean motoristaExiste(long idMotorista, List<Motorista> motoristas) {
        for (Motorista motorista : motoristas) {
            if (motorista.getId() == idMotorista) return true;
        }
        return false;
    }
}
