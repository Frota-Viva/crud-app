<%@ page import="com.frotaviva.model.Frota" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("tabela","frota");
    Frota f = (Frota) request.getAttribute("frota");
%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edição de Frotas - Frota Viva</title>
    <link rel="stylesheet" href="../../../assets/CSS/tabelaEditStyle.css?<%=System.currentTimeMillis()%>">
</head>

<body>
<jsp:include page="../componentes/aside.jsp"/>
<main>
    <section class="conteudo-container">

        <form action="/atualizar-frota" method="post">
            <header class="cabecalho-info">
                <div class="table-header">
                    <div class="th-esquerda">
                        <div class="header-cell">ID</div>
                        <div class="header-cell">Tamanho da frota</div>
                        <div class="header-cell">Tipo da frota</div>
                        <div class="header-cell">Região de atuação</div>
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
                        <div class="table-cell cell"><%=f.getId()%></div>
                        <div class="table-cell cell"><%=f.getTamanhoFrota()%></div>
                        <div class="table-cell cell"><%=f.getTipoFrota()%></div>
                        <div class="table-cell cell"><%=f.getRegiao()%></div>
                    </div>
                    <button class="btn-sair" type="button" onclick="window.location.href='/listar-frota'">
                        <img src="../../../assets/icons/icon-sair-edicao.svg" alt="Sair" id="sair-edicao">
                        Sair da Edição
                    </button>
                </div>
            </header>

            <section class="area-edicao">

                <input type="hidden" name="id" value="<%=f.getId()%>">

                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="regiao">Região:</label>
                        <input type="text" value="<%=f.getRegiao()%>" required id="regiao" name="regiao">
                        <% if (request.getAttribute("erroRegiao") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroRegiao")%></p>
                        <% } %>
                    </div>
                </div>

                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="tamanhoFrota">Tamanho Frota:</label>
                        <input type="number" value="<%=f.getTamanhoFrota()%>" min="1" required id="tamanhoFrota" name="tamanhoFrota">
                        <% if (request.getAttribute("erroTamanhoFrota") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroTamanhoFrota")%></p>
                        <% } %>
                    </div>
                </div>

                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="tipoFrota">Tipo da Frota:</label>
                        <input type="text" value="<%=f.getTipoFrota()%>" required id="tipoFrota" name="tipoFrota">
                        <% if (request.getAttribute("erroTipoFrota") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroTipoFrota")%></p>
                        <% } %>
                    </div>
                </div>

            </section>
        </form>
    </section>
</main>
</body>
</html>