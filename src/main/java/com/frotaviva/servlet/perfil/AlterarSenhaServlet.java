package com.frotaviva.servlet.perfil;

import com.frotaviva.exception.ErroAoAtualizar;
import com.frotaviva.util.Senhas;
import jakarta.servlet.http.HttpServlet;
import com.frotaviva.dao.EmpresaDAO;
import com.frotaviva.model.Empresa;
import com.frotaviva.util.Validar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Responsável por gerenciar a alteração de senha das empresas logadas.
 * <p>
 * Realiza a verificação da senha atual, valida a nova senha e atualiza o registro
 * no banco de dados caso todas as condições sejam atendidas.
 * </p>
 * 
 * @author Lucas Cayres
 */
@WebServlet(value = "/home/perfil/alterar-senha")
public class AlterarSenhaServlet extends HttpServlet {

    /**
     * Processa uma requisição POST para alterar a senha da empresa logada.
     * <p>
     * O método realiza várias verificações:
     * <ul>
     *     <li>Se o usuário está logado;</li>
     *     <li>Se as senhas novas coincidem;</li>
     *     <li>Se não há campos vazios;</li>
     *     <li>Se a nova senha segue o padrão definido;</li>
     *     <li>Se a nova senha é diferente da atual;</li>
     *     <li>Se a senha atual informada está correta.</li>
     * </ul>
     * Em caso de sucesso, a senha é atualizada no banco de dados e uma mensagem de confirmação é exibida.
     * Caso contrário, mensagens de erro específicas são enviadas à página de redefinição de senha.
     * </p>
     *
     * @param request objeto da requisição HTTP
     * @param response objeto da resposta HTTP
     * @throws ServletException se ocorrer um erro de servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Definindo DAO usado e pegando o idEmpresa
            EmpresaDAO empresaDAO = new EmpresaDAO();
            HttpSession session = request.getSession(true);

            Object idSession = session.getAttribute("idEmpresa");

            if (idSession == null) {
                response.sendRedirect("/");
                return;
            }

            long idEmpresa = (long) idSession;
            Object empresaSession = session.getAttribute("empresa");
            Empresa empresaPagina = (Empresa) empresaSession;
            request.setAttribute("empresa", empresaPagina);

            // Pegando as informações dos inputs da página
            String senhaAtual = request.getParameter("senha-atual");
            String senhaNova = request.getParameter("nova-senha");
            String confirmarSenha = request.getParameter("confirmar-senha");

            // Realizando a verificação se as senhas estão certas
            if (!(senhaNova.equals(confirmarSenha))) {
                request.setAttribute("erroDiferentes", "Senhas novas estão diferente!");
                request.getRequestDispatcher("/WEB-INF/view/perfi-NovaSenha.jsp").forward(request, response);
                return;
            } else if (Validar.testeVazio(senhaNova) && Validar.testeVazio(confirmarSenha) && Validar.testeVazio(senhaAtual)) {
                request.setAttribute("erroVazio", "Não deixe campos vazios!");
                request.getRequestDispatcher("/WEB-INF/view/perfi-NovaSenha.jsp").forward(request, response);
                return;
            } else if (!Validar.senha(senhaNova) && !Validar.senha(confirmarSenha)) {
                request.setAttribute("erroSenha", "A senha nova é inválida!");
                request.getRequestDispatcher("/WEB-INF/view/perfi-NovaSenha.jsp").forward(request, response);
                return;
            } else if (senhaNova.equals(senhaAtual) && confirmarSenha.equals(senhaAtual)) {
                request.setAttribute("erroIguais", "Digite uma senha diferente da atual!");
                request.getRequestDispatcher("/WEB-INF/view/perfi-NovaSenha.jsp").forward(request, response);
                return;
            }

            // Verificação se a senha atual está correta
            String senhaBD = Senhas.getSenhaHash(idEmpresa);
            if (!Senhas.verificarSenha(senhaAtual, senhaBD)) {
                request.setAttribute("erroSenhaIncorreta", "Senha atual incorreta!");
                request.getRequestDispatcher("/WEB-INF/view/perfi-NovaSenha.jsp").forward(request, response);
                return;
            }

            Empresa empresa = new Empresa(idEmpresa, senhaNova);

            // Alterando a senha
            int retorno = empresaDAO.atualizarSenha(empresa);

            if (retorno == 1) {
                request.setAttribute("alteracaoFeita", "Senha alterada com sucesso!");
                request.getRequestDispatcher("/WEB-INF/view/perfi-NovaSenha.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "Erro ao atualizar a senha. Tente novamente mais tarde.");
                request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
            }

        } catch (ErroAoAtualizar eaa) {
            request.setAttribute("mensagem", "Erro ao atualizar manutenção. Tente novamente mais tarde.");
            request.getRequestDispatcher("/WEB-INF/view/erro.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("/home?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }
    }

    /**
     * Recebe uma requisição GET e encaminha o usuário para a página de alteração de senha.
     * <p>
     * O método também adiciona o objeto da empresa logada aos atributos da requisição.
     * </p>
     *
     * @param request objeto da requisição HTTP
     * @param res objeto da resposta HTTP
     * @throws ServletException se ocorrer um erro de servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        Object empresaSession = session.getAttribute("empresa");
        Empresa empresaPagina = (Empresa) empresaSession;
        request.setAttribute("empresa", empresaPagina);
        request.getRequestDispatcher("/WEB-INF/view/perfi-NovaSenha.jsp").forward(request, res);
    }
}
