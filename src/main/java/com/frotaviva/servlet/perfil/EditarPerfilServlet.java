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
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            boolean testeVazio = false;
            HttpSession session = req.getSession(true);

            Object empresaSession = session.getAttribute("empresa");

            Empresa empresaSalva = (Empresa) empresaSession;
            EmpresaDAO empresaDAO = new EmpresaDAO();

            String cepBD = "";
            String numForms = req.getParameter("numero");
            String email = req.getParameter("email");
            String tipo = req.getParameter("tipo");
            String cep = req.getParameter("cep");
            String rua = req.getParameter("rua");
            String complemento = req.getParameter("complemento");
            String pais = req.getParameter("pais");
            String cidade = req.getParameter("cidade");
            String estado  = req.getParameter("estado");
            String nome = req.getParameter("nome");


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
                req.setAttribute("erroVazio","Não deixe campos vazios!");
                req.getRequestDispatcher("/home/perfil").forward(req, res);
                return;
            }

            int num = Integer.parseInt(numForms);

            if (num<=0){
                req.setAttribute("erroNumero", "Digite apenas números inteiros!");
            }
            if ((!Validar.email(email))) {
                req.setAttribute("erroEmail", "Email Invalido!");
            }
            if (Validar.cepValidado(cep) == null) {
                req.setAttribute("erroCep", "CEP Invalido!");
            } else {
                cepBD = Validar.cepValidado(cep);
            }
            if ((req.getAttribute("erroCep") != null) || (req.getAttribute("erroEmail") != null) || req.getAttribute("erroNumero") != null){
                req.getRequestDispatcher("/home/perfil").forward(req, res);
                return;
            }

            Empresa empresa = new Empresa(empresaSalva.getId(),tipo,empresaSalva.getCnpj(),email,
                    empresaSalva.getSenha(),nome,new Endereco(pais,cepBD,estado,cidade,rua,num,complemento));

            if (empresaSalva.equals(empresa)){
                req.setAttribute("erroIgualdade", "Nenhum campo foi modificado!");
                req.getRequestDispatcher("/home/perfil").forward(req, res);
            } else if (empresaSalva.getEmail().equals(empresa.getEmail()) || (empresaDAO.buscarPorEmail(email) == 0)){
                req.setAttribute("realizado", "Mudança realizada com sucesso!");
                empresaDAO.atualizar(empresa);
                session.setAttribute("empresa",empresa);
                req.getRequestDispatcher("/home/perfil").forward(req, res);
            } else if ((empresaDAO.buscarPorEmail(email) == 1)) {
                req.setAttribute("erroEmail", "Email Invalido!");
                req.getRequestDispatcher("/home/perfil").forward(req, res);
            }

        }catch (NumberFormatException nfe){
            req.setAttribute("erroNumero", "Digite apenas números inteiros!");
            req.getRequestDispatcher("/home/perfil").forward(req, res);
        } catch (Exception e){
            res.sendRedirect("/");
        }



    }
}
