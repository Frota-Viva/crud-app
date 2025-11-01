<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("tabela","frota");
%>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inserir Frota - Frota Viva</title>
    <link rel="stylesheet" href="../../../assets/CSS/tabelaIserirStyle.css">
</head>
<body>
<jsp:include page="../componentes/aside.jsp"/>
<main>
    <section class="conteudo-container">
        <header class="cabecalho-info">
            <div class="table-header">
                <h1>Inserir Frota</h1>
            </div>
        </header>
        <form action="/inserir-frota" method="post">
            <section class="area-edicao">
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="regiao">Região:</label>
                        <input type="text" placeholder="Região que a frota opera." id="regiao" name="regiao" required>
                        <%if (request.getAttribute("erroRegiao") != null){%>
                        <p style="color: red"><%=request.getAttribute("erroRegiao")%></p>
                        <%}%>
                    </div>
                    <div class="campo-edicao">
                        <label for="tamanhoFrota">Tamanho Frota:</label>
                        <input type="number" placeholder="Quantidade de caminhões da frota." min="1" id="tamanhoFrota" name="tamanhoFrota" required>
                        <%if (request.getAttribute("erroTamanhoFrota") != null){%>
                        <p style="color: red"><%=request.getAttribute("erroTamanhoFrota")%></p>
                        <%}%>
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="tipoFrota">Tipo da Frota:</label>
                        <input type="text" placeholder="Digite o tipo da frota." id="tipoFrota" name="tipoFrota" required>
                        <%if (request.getAttribute("erroTipoFrota") != null){%>
                        <p style="color: red"><%=request.getAttribute("erroTipoFrota")%></p>
                        <%}%>
                    </div>
                </div>
            </section>
            <section id="botoes">
                <button id="button-cancelar" type="button" onclick="window.location.href='/listar-frota'">
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