<%--
  Created by IntelliJ IDEA.
  User: davifranco-ieg
  Date: 23/10/2025
  Time: 07:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setAttribute("tabela","motorista");%>
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
        <form action="">
            <section class="area-edicao">
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="nome">Nome:</label>
                        <input type="text" required id="nome" name="nome" placeholder="Digite o nome do motorista.">
                    </div>
                    <div class="campo-edicao">
                        <label for="senha">Senha:</label>
                        <input type="password" required id="senha" name="senha" placeholder="Exemplo: Abc1$def">
                    </div>
                </div>

                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="cpf">CPF:</label>
                        <input type="text" maxlength="14" required id="cpf" name="cpf" placeholder="Exemplo: 000.000.000-00">
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="email">Email:</label>
                        <input type="email" placeholder="Exemplo: exemplo123@email.com" required name="email" id="email">
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
