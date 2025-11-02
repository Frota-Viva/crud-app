package com.frotaviva.servlet.motorista;

import com.frotaviva.dao.MotoristaDAO;
import com.frotaviva.exception.ErroAoAtualizar;
import com.frotaviva.model.Motorista;
import com.frotaviva.util.Validar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Responsável por atualizar os dados de um motorista cadastrado.
 * <p>
 * Principais funcionalidades:
 * <ul>
 *     <li><b>GET:</b> Busca e exibe os dados do motorista selecionado para atualização.</li>
 *     <li><b>POST:</b> Processa as alterações feitas pelo usuário, valida os dados e atualiza o registro no banco.</li>
 * </ul>
 * </p>
 * <p>
 * O servlet valida se existe uma empresa logada, senão o usuário é redirecionado para landing page.
 * </p>
 *
 * @author Davi Alcanfor
 */
@WebServlet(name = "AtualizarMotorista", value = "/atualizar-motorista")
public class AtualizarMotoristaServlet extends HttpServlet {

    /**
     * Exibe o formulário de atualização com os dados atuais do motorista.
     * <p>
     * Principais funcionalidades:
     * <ul>
     *     <li>Verifica se existe uma empresa logada (sessão válida);</li>
     *     <li>Busca o ID do motorista a partir do parâmetro da requisição;</li>
     *     <li>Busca o motorista no banco de dados através do {@link MotoristaDAO};</li>
     *     <li>Redireciona os dados para a página JSP de atualização;</li>
     *     <li>Caso o ID seja inválido ou o motorista não seja encontrado, redireciona para a listagem de motoristas
     * com os determinados erros.</li>
     * </ul>
     * </p>
     *
     * @param req  requisição HTTP recebida
     * @param resp resposta HTTP enviada
     * @throws ServletException se ocorrer um erro no processamento do servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Recuperando o motorista que será atualizado
        HttpSession session = req.getSession(true);
        Object idEmpresaSession = session.getAttribute("idEmpresa");

        if (idEmpresaSession == null) {
            resp.sendRedirect("/");
            return;
        }

        try {
            long id = Long.parseLong(req.getParameter("id"));
            MotoristaDAO dao = new MotoristaDAO();

            Motorista motorista = dao.buscarPorId(id);

            if (motorista == null) {
                resp.sendRedirect("/listar-motoristas");
                return;
            }

            req.setAttribute("motorista", motorista);
            req.getRequestDispatcher("WEB-INF/view/motorista/atualizar-motorista.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendRedirect("/listar-motoristas?msg=Nao foi possível encontrar o ID do Motorista!");
        }
    }

    /**
     * Processa a atualização dos dados do motorista enviados pelo formulário.
     * <p>
     * Principais funcionalidades:
     * <ul>
     *     <li>Verifica a sessão e valida se há uma empresa logada;</li>
     *     <li>Busca e valida os campos de entrada (nome, email, CPF, senha);</li>
     *     <li>Exibe mensagens de erro em se haver entradas inválidas;</li>
     *     <li>Atualiza o motorista no banco de dados se todos os dados forem válidos;</li>
     *     <li>Redireciona para a listagem com uma mensagem de sucesso ou erro.</li>
     * </ul>
     * </p>
     *
     * @param request  requisição HTTP com os dados enviados pelo formulário
     * @param response resposta HTTP a ser enviada ao cliente
     * @throws ServletException se ocorrer um erro no processamento do servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Sessão
        HttpSession session = request.getSession(true);
        Object idEmpresaSession = session.getAttribute("idEmpresa");

        if (idEmpresaSession == null) {
            response.sendRedirect("/");
            return;
        }

        long idEmpresa = (long) idEmpresaSession;
        boolean erro = false;

        try {
            MotoristaDAO dao = new MotoristaDAO();

            long id = Long.parseLong(request.getParameter("id"));
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");

            // Validação dos campos
            if (!Validar.senha(senha)) {
                request.setAttribute("erroSenha", "Senha inválida! Deve conter números, maiúsculas, minúsculas e 8+ caracteres.");
                erro = true;
            }

            if (!Validar.email(email)) {
                request.setAttribute("erroEmail", "Email inválido! Deve seguir o padrão exemplo@exemplo.com");
                erro = true;
            }

            if (!Validar.cpf(cpf)) {
                request.setAttribute("erroCpf", "CPF inválido! Deve conter 11 dígitos.");
                erro = true;
            } else {
                cpf = Validar.cpfValidado(cpf);
            }

            if (nome == null || nome.trim().isEmpty()) {
                request.setAttribute("erroNome", "Nome inválido! Não pode ser nulo.");
                erro = true;
            }

            Motorista motorista = dao.buscarPorId(id);

            // Caso haja erros de validação, retorna para a página com mensagens
            if (erro) {
                request.setAttribute("motorista", motorista);
                request.getRequestDispatcher("WEB-INF/view/motorista/atualizar-motorista.jsp").forward(request, response);
                return;
            }

            // Atualização do objeto
            motorista.setNome(nome);
            motorista.setEmail(email);
            motorista.setSenha(senha);
            motorista.setCpf(cpf);

            if (dao.atualizar(motorista) == 1) {
                response.sendRedirect("/listar-motoristas?msg=Motorista atualizado com sucesso");
                return;
            }

            response.sendRedirect("/listar-motoristas?msg=Ocorreu um erro ao atualizar motorista");

        } catch (ErroAoAtualizar e) {
            response.sendRedirect("/listar-motoristas?msg=Erro ao atualizar motorista. Tente novamente mais tarde");
        } catch (Exception e) {
            response.sendRedirect("/listar-motoristas?msg=Ocorreu um erro inesperado. Tente novamente mais tarde");
        }
    }
}
