<%--
  Created by IntelliJ IDEA.
  User: davifranco-ieg
  Date: 30/10/2025
  Time: 09:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setAttribute("tabela","frota");%>
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
        <form action="">
            <section class="area-edicao">
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="regiao">Região:</label>
                        <input type="text" placeholder="Região que a frota opera."  required id="regiao" name="regiao">
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="tamanhoFrota">Tamanho Frota:</label>
                        <input type="number" placeholder="Quantidade de caminhões da frota." min="1" required id="tamanhoFrota" name="tamanhoFrota">
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="tipoFrota">Tipo da Frota:</label>
                        <input type="text" placeholder="Digite o tipo da frota." required id="tipoFrota" name="tipoFrota">
                    </div>
                </div>
            </section>
            <section id="botoes">
                <button id="button-cancelar" type="button">
                    <img src="../../../assets/icons/icon-cancelar.svg" alt="" class="icones-botao">
                    <p><b>Cancelar</b></p>
                </button>
                <button id="button-confirmar" type="submit">
                    <img src="../../../assets/icons/icon-confirmar.svg" alt="" class="icones-botao">
                    <p><b>Confirmar</b></p>
                </button>
            </section>
        </form>
    </section>
</main>
</body>

</html>
