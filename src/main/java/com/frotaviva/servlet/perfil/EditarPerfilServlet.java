package com.frotaviva.servlet.perfil;

import com.frotaviva.dao.EmpresaDAO;
import com.frotaviva.model.Empresa;
import com.frotaviva.model.Endereco;
import com.frotaviva.util.Validar;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Responsável por gerenciar a edição de informações do perfil da empresa logada.
 * <p>
 * Este servlet verifica os dados enviados pelo formulário de edição de perfil, valida os valores recebidos
 * e atualiza os dados da empresa no banco de dados se todas as
 * verificações derem certo.
 * </p>
 * <p>
 * Caso ocorra algum erro de validação, o usuário é redirecionado de volta para a página de perfil com
 * mensagens de erro específicas.
 * </p>
 * 
 * @author Lucas Cayres
 */
@WebServlet(name = "EditarPerfilServlet", value = "/home/editar-perfil")
public class EditarPerfilServlet extends HttpServlet {

    /**
     * Processa uma requisição GET para atualizar as informações do perfil da empresa logada.
     * <p>
     * O método realiza as seguintes operações:
     * <ul>
     *     <li>Obtém os dados enviados pelo formulário de perfil;</li>
     *     <li>Valida campos obrigatórios e formatos (email, CEP, número, etc.);</li>
     *     <li>Verifica se houve alteração nos dados da empresa;</li>
     *     <li>Atualiza os dados da empresa no banco de dados caso não haja erros;</li>
     *     <li>Exibe mensagens de erro apropriadas se as validações falharem.</li>
     * </ul>
     * Em caso de erro inesperado, o usuário é redirecionado para a página inicial com uma mensagem de falha.
     * </p>
     *
     * @param request objeto da requisição HTTP
     * @param response objeto da resposta HTTP
     * @throws ServletException se ocorrer um erro de servlet
     * @throws IOException se ocorrer um erro de entrada ou saída
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean testeVazio = false;
            HttpSession session = request.getSession(true);

            Object empresaSession = session.getAttribute("empresa");
            Empresa empresaSalva = (Empresa) empresaSession;
            EmpresaDAO empresaDAO = new EmpresaDAO();

            String cepBD = "";
            String numForms = request.getParameter("numero");
            String email = request.getParameter("email");
            String tipo = request.getParameter("tipo");
            String cep = request.getParameter("cep");
            String rua = request.getParameter("rua");
            String complemento = request.getParameter("complemento");
            String pais = request.getParameter("pais");
            String cidade = request.getParameter("cidade");
            String estado  = request.getParameter("estado");
            String nome = request.getParameter("nome");

            // Verifica campos obrigatórios
            if (Validar.testeVazio(email) || Validar.testeVazio(tipo) || Validar.testeVazio(cep) ||
                Validar.testeVazio(rua) || Validar.testeVazio(pais) || Validar.testeVazio(cidade) ||
                Validar.testeVazio(estado) || Validar.testeVazio(nome) || Validar.testeVazio(numForms)) {
                testeVazio = true;
            }

            if (Validar.testeVazio(complemento)) {
                complemento = "Complemento não informado";
            }

            if (testeVazio) {
                request.setAttribute("erroVazio", "Não deixe campos vazios!");
                request.getRequestDispatcher("/home/perfil").forward(request, response);
                return;
            }

            // Valida número
            int num = Integer.parseInt(numForms);
            if (num <= 0) {
                request.setAttribute("erroNumero", "Digite apenas números inteiros!");
            }

            // Valida email
            if (!Validar.email(email)) {
                request.setAttribute("erroEmail", "Email inválido!");
            }

            // Valida CEP
            if (Validar.cepValidado(cep) == null) {
                request.setAttribute("erroCep", "CEP inválido!");
            } else {
                cepBD = Validar.cepValidado(cep);
            }

            // Verifica se há erros de validação
            if (request.getAttribute("erroCep") != null ||
                request.getAttribute("erroEmail") != null ||
                request.getAttribute("erroNumero") != null) {
                request.getRequestDispatcher("/home/perfil").forward(request, response);
                return;
            }

            // Cria novo objeto Empresa com os dados atualizados
            Empresa empresa = new Empresa(
                    empresaSalva.getId(),
                    tipo,
                    empresaSalva.getCnpj(),
                    email,
                    empresaSalva.getSenha(),
                    nome,
                    new Endereco(pais, cepBD, estado, cidade, rua, num, complemento)
            );

            // Verifica se houve alterações e atualiza se necessário
            if (empresaSalva.equals(empresa)) {
                request.setAttribute("erroIgualdade", "Nenhum campo foi modificado!");
                request.getRequestDispatcher("/home/perfil").forward(request, response);
            } else if (empresaSalva.getEmail().equals(empresa.getEmail()) || (empresaDAO.buscarPorEmail(email) == 0)) {
                request.setAttribute("realizado", "Mudança realizada com sucesso!");
                empresaDAO.atualizar(empresa);
                session.setAttribute("empresa", empresa);
                request.getRequestDispatcher("/home/perfil").forward(request, response);
            } else if (empresaDAO.buscarPorEmail(email) == 1) {
                request.setAttribute("erroEmail", "Este email já está em uso!");
                request.getRequestDispatcher("/home/perfil").forward(request, response);
            }

        } catch (NumberFormatException nfe) {
            request.setAttribute("erroNumero", "Digite apenas números inteiros!");
            request.getRequestDispatcher("/home/perfil").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("/home?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }
    }
}
