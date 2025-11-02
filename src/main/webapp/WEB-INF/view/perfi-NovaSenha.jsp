<%@ page import="com.frotaviva.model.Empresa" %><%--
  Created by IntelliJ IDEA.
  User: lucasporto-ieg
  Date: 31/10/2025
  Time: 21:45
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
  <form action="alterar-senha" method="post">
    <div class="div-forms-altSenha">
      <p>Para alterar sua senha, primeiro insira sua senha atual para confirmar sua identidade.</p>
      <label for="senha-atual">
        Senha Atual:
        <input placeholder="Digite aqui..." type="password" id="senha-atual" name="senha-atual"
               class="input-forms" required>
      </label>
    </div>

    <div class="div-forms-altSenha">
      <p>Crie uma nova senha segura. Ela deve ter pelo menos 8 caracteres e combinar letras, números e
        símbolos.</p>
      <label for="nova-senha">
        Nova Senha:
        <input placeholder="Digite aqui..." type="password" id="nova-senha" name="nova-senha"
               class="input-forms" required>
      </label>
    </div>

    <div id="ultima-linhaSenha">
      <div class="div-forms-altSenha">
        <p>Digite novamente a nova senha para confirmar que está correta.</p>
        <label for="confirmar-senha">
          Confirmar Nova Senha:
          <input placeholder="Digite aqui..." type="password" id="confirmar-senha" name="confirmar-senha"
                 class="input-forms" required>
        </label>
      </div>
      <% if (request.getAttribute("erroDiferentes") != null) { %>
      <div class="erroCaixa">
        <span><%= request.getAttribute("erroDiferentes") %></span>
      </div>
      <% } else if (request.getAttribute("erroSenha") != null) { %>
      <div class="erroCaixa">
        <span><%= request.getAttribute("erroSenha") %></span>
      </div>
      <% } else if (request.getAttribute("erroVazio") != null) { %>
      <div class="erroCaixa">
        <span><%= request.getAttribute("erroVazio") %></span>
      </div>
      <% } else if (request.getAttribute("erroSenhaIncorreta") != null) { %>
      <div class="erroCaixa">
        <span><%= request.getAttribute("erroSenhaIncorreta") %></span>
      </div>
      <% } else if (request.getAttribute("erroIguais") != null) { %>
      <div class="erroCaixa">
        <span><%= request.getAttribute("erroIguais") %></span>
      </div>
      <% } else if (request.getAttribute("erroAlterar") != null) { %>
      <div class="erroCaixa">
        <span><%= request.getAttribute("erroAlterar") %></span>
      </div>
      <% } else if (request.getAttribute("alteracaoFeita") != null) { %>
      <div id="realizado">
        <span><%= request.getAttribute("alteracaoFeita") %></span>
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