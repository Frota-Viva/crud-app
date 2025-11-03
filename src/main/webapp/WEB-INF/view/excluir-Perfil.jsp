<%@ page import="com.frotaviva.model.Empresa" %><%--
  Created by IntelliJ IDEA.
  User: lucasporto-ieg
  Date: 02/11/2025
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Object empresaReq = request.getAttribute("empresa");
    Empresa empresa = (Empresa) empresaReq;%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../assets/imgs/Logo16x16.png">
    <link rel="stylesheet" href="../../assets/CSS/perfilStyle.css?<%=System.currentTimeMillis()%>">
    <title>Frota Viva - Perfil</title>
</head>

<body>
<jsp:include page="componentes/aside.jsp"/>
<main id="main-senha">
    <section id="infos-inicial">
        <img src="../../assets/icons/icon-perfil-empresa.svg" alt="" id="perfil-main">
        <div>
            <h1><%= empresa.getNome()%></h1>
            <h2><%= empresa.getTipoEmpresa()%></h2>
        </div>
    </section>
    <form action="excluir-conta" method="post">
        <div id="ultima-linhaSenha">
            <div class="div-forms-altSenha">
                <p>Confirme sua senha para excluir sua conta</p>
                <label for="senha">
                    Senha:
                    <input placeholder="Digite aqui..." type="password" id="senha" name="senha"
                           class="input-forms" required>
                </label>
            </div>
            <% if (request.getAttribute("erro") != null) { %>
            <div class="erroCaixa">
                <span><%= request.getAttribute("erro") %></span>
            </div>
            <% } else if (request.getAttribute("erroSenha") != null) { %>
            <div class="erroCaixa">
                <span><%= request.getAttribute("erroSenha") %></span>
            </div>
            <% } %>

            <div id="botoes-forms-altSenha">
                <a href="/home/perfil">
                    <button id="button-cancelar" type="button">
                        <img src="../../assets/icons/icon-cancelar.svg" alt="" class="icones-botao">
                        <p><b>Cancelar</b></p>
                    </button>
                </a>

                <button id="button-confirmar" type="submit">
                    <img src="../../assets/icons/icon-confirmar.svg" alt="" class="icones-botao">
                    <p><b>Confirmar</b></p>
                </button>
            </div>
        </div>
    </form>
</main>
</body>

</html>
