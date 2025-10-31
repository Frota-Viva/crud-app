<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setAttribute("tabela","caminhao");%>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inserir Caminhão - Frota Viva</title>
    <link rel="stylesheet" href="../../../assets/CSS/tabelaIserirStyle.css">
</head>
<body>
<jsp:include page="../componentes/aside.jsp"/>
<main>
    <section class="conteudo-container">
        <header class="cabecalho-info">
            <div class="table-header">
                <h1>Inserir Caminhão</h1>
            </div>
        </header>
        <form action="">
            <section class="area-edicao">
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="idFrota">ID Frota:</label>
                        <input type="number" placeholder="Digite o ID da frota do caminhão." min="1" id="idFrota" name="idFrota" required>
                    </div>
                    <div class="campo-edicao">
                        <label for="kmsRodados">Kms Rodados:</label>
                        <input type="number" placeholder="Digite a quantidade de Km rodados do caminhão." min="0" id="kmsRodados" name="kmsRodados" required>
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="placa">Placa:</label>
                        <input type="text" placeholder="Digite a placa do caminhão." id="placa" name="placa" required>
                    </div>
                    <div class="campo-edicao">
                        <label for="modelo">Modelo:</label>
                        <input type="text" placeholder="Digite o modelo do caminhão." id="modelo" name="modelo" required>
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="estado">Estado:</label>
                        <input type="text" placeholder="A/I/M" id="estado" name="estado" required>
                    </div>
                    <div class="campo-edicao">
                        <label for="capacidade">Capacidade:</label>
                        <input type="number" placeholder="Digite a capacidade em Kg do caminhão." id="capacidade" name="capacidade" min="1">
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