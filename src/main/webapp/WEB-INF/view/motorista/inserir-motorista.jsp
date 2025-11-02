<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("tabela","motorista");
%>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inserir Motorista - Frota Viva</title>
    <link rel="stylesheet" href="../../../assets/CSS/tabelaIserirStyle.css">
</head>
<body>
<jsp:include page="../componentes/aside.jsp"/>
<main>
    <section class="conteudo-container">
        <header class="cabecalho-info">
            <div class="table-header">
                <h1>Inserir Motorista</h1>
            </div>
        </header>
        <form action="/inserir-motoristas" method="post">
            <section class="area-edicao">
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="nome">Nome:</label>
                        <input type="text" placeholder="Digite o nome do motorista." id="nome" name="nome" required>
                        <%if (request.getAttribute("erroNome") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroNome")%></p>
                        <%}%>
                    </div>
                    <div class="campo-edicao">
                        <label for="cpf">CPF:</label>
                        <input type="text" placeholder="Exemplo: 000.000.000-00" maxlength="14" id="cpf" name="cpf" required>
                        <%if (request.getAttribute("erroCpf") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroCpf")%></p>
                        <%}%>
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="email">Email:</label>
                        <input type="email" placeholder="Exemplo: exemplo123@email.com" id="email" name="email" required>
                        <%if (request.getAttribute("erroEmail") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroEmail")%></p>
                        <%}%>
                    </div>
                    <div class="campo-edicao">
                        <label for="senha">Senha:</label>
                        <input type="password" placeholder="Exemplo: Abc1$def" id="senha" name="senha" required>
                        <%if (request.getAttribute("erroSenha") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroSenha")%></p>
                        <%}%>
                    </div>
                </div>
            </section>
            <section id="botoes">
                <button id="button-cancelar" type="button" onclick="window.location.href='/listar-motoristas'">
                    <img src="../../../assets/icons/icon-cancelar.svg" alt="" class="icones-botao">
                    <b>Cancelar</b>
                </button>
                <button id="button-confirmar" type="submit">
                    <img src="../../../assets/icons/icon-confirmar.svg" alt="" class="icones-botao">
                    <b>Confirmar</b>
                </button>
            </section>
        </form>
    </section>
</main>
</body>

</html>