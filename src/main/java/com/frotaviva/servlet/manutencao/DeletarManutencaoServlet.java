package com.frotaviva.servlet.manutencao;

import com.frotaviva.dao.ManutencaoDAO;
import com.frotaviva.exception.ErroAoDeletar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Responsável por excluir uma manutenção do sistema.
 * <p>
 * Este servlet realiza a exclusão de uma manutenção a partir do ID recebido via requisição.
 * </p>
 * <p>
 * Principais funcionalidades:
 * <ul>
 *     <li>Recebe o ID da manutenção a ser excluída através do parâmetro da requisição;</li>
 *     <li>Utiliza o {@link ManutencaoDAO} para remover o registro correspondente no banco de dados;</li>
 *     <li>Redireciona para a listagem de manutenções com mensagens de sucesso ou erro conforme o resultado.</li>
 * </ul>
 * </p>
 * 
 * @author Davi Alcanfor
 */
@WebServlet(name = "DeleteManutencao", value = "/deletar-manutencao")
public class DeletarManutencaoServlet extends HttpServlet {

    /**
     * Realiza a exclusão de uma manutenção de acordo com o ID passado como parâmetro.
     * <p>
     * Se a exclusão for bem-sucedida, redireciona para a listagem com uma mensagem de sucesso.
     * Se não for possível deletar ou ocorrer algum erro inesperado, redireciona para a listagem com a mensagem apropriada.
     * </p>
     *
     * @param request  requisição HTTP contendo o ID da manutenção a ser excluída
     * @param response resposta HTTP a ser enviada ao cliente
     * @throws ServletException se ocorrer erro interno no servlet
     * @throws IOException se ocorrer erro de entrada ou saída durante o redirecionamento
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        try {
            ManutencaoDAO dao = new ManutencaoDAO();

            if (dao.deletar(id) == 1) {
                response.sendRedirect("/listar-manutencao?msg=Manutencao deletada com sucesso");
                return;
            }

            response.sendRedirect("/listar-manutencao?msg=Nao foi possivel deletar manutencao.");

        } catch (ErroAoDeletar e) {
            response.sendRedirect("/listar-manutencao?msg=Ocorreu um erro ao deletar manutencao.");

        } catch (Exception e) {
            response.sendRedirect("/listar-manutencao?msg=Ocorreu um erro inesperado ao deletar manutencao.");

        }
    }

}
