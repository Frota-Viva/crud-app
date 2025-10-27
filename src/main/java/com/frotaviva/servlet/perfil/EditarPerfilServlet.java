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

@WebServlet(value = "/editar-perfil")
public class EditarPerfilServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession(true);
            Object idEmpresaSession = session.getAttribute("idEmpresa");

            Long idEmpresa = (long) idEmpresaSession;
            EmpresaDAO empresaDAO = new EmpresaDAO();

            String cepBD = "";
            String cnpjBD = "";
            String email = req.getParameter("email");
            String tipo = req.getParameter("tipo");
            String cnpj = req.getParameter("cnpj");
            String cep = req.getParameter("cep");
            String rua = req.getParameter("rua");
            String complemento = req.getParameter("complemento");
            String pais = req.getParameter("pais");
            String cidade = req.getParameter("cidade");
            String estado  = req.getParameter("estado");
            String nome = req.getParameter("nome");

            int numero = Integer.parseInt(req.getParameter("numero"));

            if (Empresa.cnpjValidado(cnpj) == null) {
                req.setAttribute("erroCnpj", "CNPJ invalido!");
                res.sendRedirect("/home/perfil");
            } else {
                cnpjBD = Empresa.cnpjValidado(cnpj);
            }

            if (!Validar.email(email)) {
                req.setAttribute("erroEmail", "Email Invalido!");
                res.sendRedirect("/home/perfil");
            }
            if (Validar.cepValidado(cep) == null) {
                req.setAttribute("erroCep", "CEP Invalido!");
                res.sendRedirect("/home/perfil");
            } else {
                cepBD = Validar.cepValidado(cep);
            }
            Empresa empresa = new Empresa(idEmpresa,tipo,cnpjBD,
                    email,nome,new Endereco(pais,cepBD,estado,cidade,rua,numero,complemento));

            empresaDAO.atualizar(empresa);



        }catch (NumberFormatException nfe){
            req.setAttribute("erroNumero", "Digite apenas números inteiros!");
            res.sendRedirect("/home/perfil");
        } catch (Exception e){
            res.sendRedirect("/");
        }


    }
}
