<%@ page import="com.frotaviva.model.Motorista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("tabela","motorista");
    Motorista m = (Motorista) request.getAttribute("motorista");
%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edição de Motoristas - Frota Viva</title>
    <link rel="stylesheet" href="../../../assets/CSS/tabelaEditStyle.css?<%=System.currentTimeMillis()%>">
</head>

<body>
<jsp:include page="../componentes/aside.jsp"/>
<main>
    <section class="conteudo-container">

        <form action="/atualizar-motorista" method="post">
            <header class="cabecalho-info">

                <div class="table-header">
                    <div class="th-esquerda">
                        <div class="header-cell">ID</div>
                        <div class="header-cell">Nome</div>
                        <div class="header-cell">CPF</div>
                        <div class="header-cell">Email</div>
                    </div>

                    <div class="header-cell header-action">
                        <button class="btn-salvar" type="submit">
                            <img src="../../../assets/icons/icon-salvar-alteracoes.svg" alt="salvar" id="salvar-edicao">
                            Salvar Alterações
                        </button>
                    </div>
                </div>

                <div class="table-row">
                    <div class="t-esquerda">
                        <div class="table-cell cell"><%=m.getId()%></div>
                        <div class="table-cell cell"><%=m.getNome()%></div>
                        <div class="table-cell cell"><%=m.getCpf()%></div>
                        <div class="table-cell cell"><%=m.getEmail()%></div>
                    </div>

                    <button class="btn-sair" type="button" onclick="window.location.href='/listar-motorista'">
                        <img src="../../../assets/icons/icon-sair-edicao.svg" alt="Sair" id="sair-edicao">
                        Sair da Edição
                    </button>
                </div>

            </header>

            <section class="area-edicao">

                <!-- ID oculto -->
                <input type="hidden" name="id" value="<%=m.getId()%>">

                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="nome">Nome:</label>
                        <input type="text" value="<%=m.getNome()%>" required id="nome" name="nome">
                        <% if (request.getAttribute("erroNome") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroNome")%></p>
                        <% } %>
                    </div>
                </div>

                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="cpf">CPF:</label>
                        <input type="text" value="<%=m.getCpf()%>" maxlength="14" required id="cpf" name="cpf">
                        <% if (request.getAttribute("erroCpf") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroCpf")%></p>
                        <% } %>
                    </div>
                </div>

                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="email">Email:</label>
                        <input type="text" value="<%=m.getEmail()%>" required id="email" name="email">
                        <% if (request.getAttribute("erroEmail") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroEmail")%></p>
                        <% } %>
                    </div>
                </div>

                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="senha">Senha:</label>
                        <input type="password" required id="senha" name="senha" value="<%=m.getSenha()%>">
                        <% if (request.getAttribute("erroSenha") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroSenha")%></p>
                        <% } %>
                    </div>
                </div>

            </section>
        </form>

    </section>
</main>
</body>
</html>
