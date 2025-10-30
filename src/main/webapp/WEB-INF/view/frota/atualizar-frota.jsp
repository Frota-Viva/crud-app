<%--
  Created by IntelliJ IDEA.
  User: lucasporto-ieg
  Date: 30/10/2025
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setAttribute("tabela","frota");%>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edição de Frotas - Frota Viva</title>
    <link rel="stylesheet" href="../../../assets/CSS/tabelaEditStyle.css">
</head>

<body>
<jsp:include page="../componentes/aside.jsp"/>
<main>
    <section class="conteudo-container">

        <form action="">
            <header class="cabecalho-info">
                <div class="table-header">
                    <div class="th-esquerda">
                        <div class="header-cell">ID</div>
                        <div class="header-cell">Tamanho da frota</div>
                        <div class="header-cell">Tipo da frota</div>
                        <div class="header-cell">Região de atuaçao</div>
                        <div class="header-cell">Nome da frota</div>
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
                        <div class="table-cell cell">2</div>
                        <div class="table-cell cell">Texto</div>
                        <div class="table-cell cell">Texto</div>
                        <div class="table-cell cell">Texto</div>
                        <div class="table-cell cell">Texto</div>
                    </div>
                    <button class="btn-sair" type="button">
                        <img src="../../../assets/icons/icon-sair-edicao.svg" alt="Sair" id="sair-edicao">
                        Sair da Edição
                    </button>
                </div>

                </div>
            </header>
            <section class="area-edicao">
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="regiao">Região:</label>
                        <input type="text" value="Nordeste / Brasil"  required id="regiao" name="regiao">
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="tamanhoFrota">Tamanho Frota:</label>
                        <input type="number" value="47" min="1" required id="tamanhoFrota" name="tamanhoFrota">
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="tipoFrota">Tipo da Frota:</label>
                        <input type="text" value="ECO-friendly" required id="tipoFrota" name="tipoFrota">
                    </div>
                </div>
            </section>
        </form>
    </section>
</main>
</body>

</html>
