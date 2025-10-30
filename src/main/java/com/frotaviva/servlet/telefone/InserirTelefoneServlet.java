package com.frotaviva.servlet.telefone;

import com.frotaviva.dao.TelefoneMotoristaDAO;
import com.frotaviva.model.TelefoneMotorista;
import com.frotaviva.util.Validar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "InserirTelefones", value = "/inserir-telefone")
public class InserirTelefoneServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long idMotorista;
        String telefone;

        try{

            TelefoneMotoristaDAO dao = new TelefoneMotoristaDAO();

            telefone = request.getParameter("telefone");
            idMotorista = Long.parseLong(request.getParameter("idMotorista"));


            if (idMotorista <= 0){
                request.setAttribute("erro", "ID Motorista inválido!");
                request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
            }

            if (!Validar.telefone(telefone)){
                request.setAttribute("erro", "Telefone inválido! Deve conter 11 dígitos");
                request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
            } else{
                telefone = Validar.telefoneValidado(telefone);
            }

            TelefoneMotorista telefoneMotorista = new TelefoneMotorista(telefone, idMotorista);

            if (dao.inserir(telefoneMotorista) == 1){
                response.sendRedirect("/listar-motoristas");
            }

            request.setAttribute("erro", "Erro ao inserir Telefone inválido!");
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);

        } catch (Exception e){
            request.getRequestDispatcher("WEB-INF/view/erro.jsp").forward(request, response);
        }
    }
}