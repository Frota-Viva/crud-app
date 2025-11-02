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

@WebServlet(name = "EditarPerfilServlet", value = "/home/editar-perfil")
public class EditarPerfilServlet extends HttpServlet {
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


            if (Validar.testeVazio(email)){
                testeVazio=true;
            } else if (Validar.testeVazio(tipo)) {
                testeVazio=true;
            } else if (Validar.testeVazio(cep)) {
                testeVazio=true;
            }else if (Validar.testeVazio(rua)) {
                testeVazio=true;
            } else if (Validar.testeVazio(pais)) {
                testeVazio=true;
            }else if (Validar.testeVazio(cidade)) {
                testeVazio=true;
            } else if (Validar.testeVazio(estado)) {
                testeVazio=true;
            }else if (Validar.testeVazio(nome)) {
                testeVazio=true;
            } else if (Validar.testeVazio(numForms)){
                testeVazio=true;
            }
            if (Validar.testeVazio(complemento)){
                complemento = "Complemento não informado";
            }

            if (testeVazio){
                request.setAttribute("erroVazio","Não deixe campos vazios!");
                request.getRequestDispatcher("/home/perfil").forward(request, response);
                return;
            }

            int num = Integer.parseInt(numForms);

            if (num<=0){
                request.setAttribute("erroNumero", "Digite apenas números inteiros!");
            }
            if ((!Validar.email(email))) {
                request.setAttribute("erroEmail", "Email Invalido!");
            }
            if (Validar.cepValidado(cep) == null) {
                request.setAttribute("erroCep", "CEP Invalido!");
            } else {
                cepBD = Validar.cepValidado(cep);
            }
            if ((request.getAttribute("erroCep") != null) || (request.getAttribute("erroEmail") != null) || request.getAttribute("erroNumero") != null){
                request.getRequestDispatcher("/home/perfil").forward(request, response);
                return;
            }

            Empresa empresa = new Empresa(empresaSalva.getId(),tipo,empresaSalva.getCnpj(),email,
                    empresaSalva.getSenha(),nome,new Endereco(pais,cepBD,estado,cidade,rua,num,complemento));

            if (empresaSalva.equals(empresa)){
                request.setAttribute("erroIgualdade", "Nenhum campo foi modificado!");
                request.getRequestDispatcher("/home/perfil").forward(request, response);
            } else if (empresaSalva.getEmail().equals(empresa.getEmail()) || (empresaDAO.buscarPorEmail(email) == 0)){
                request.setAttribute("realizado", "Mudança realizada com sucesso!");
                empresaDAO.atualizar(empresa);
                session.setAttribute("empresa",empresa);
                request.getRequestDispatcher("/home/perfil").forward(request, response);
            } else if ((empresaDAO.buscarPorEmail(email) == 1)) {
                request.setAttribute("erroEmail", "Email Invalido!");
                request.getRequestDispatcher("/home/perfil").forward(request, response);
            }

        }catch (NumberFormatException nfe){
            request.setAttribute("erroNumero", "Digite apenas números inteiros!");
            request.getRequestDispatcher("/home/perfil").forward(request, response);
        } catch (Exception e){
            response.sendRedirect("/home?msg=Ocorreu um erro inesperado. Tente novamente mais tarde.");
        }



    }
}
