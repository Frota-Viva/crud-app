<%--
  Created by IntelliJ IDEA.
  User: davifranco-ieg
  Date: 30/10/2025
  Time: 09:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setAttribute("tabela","manutencao");%>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inserir Manutenção - Frota Viva</title>
    <link rel="stylesheet" href="../../../assets/CSS/tabelaIserirStyle.css">
</head>

<body>
<jsp:include page="../componentes/aside.jsp"/>
<main>
    <section class="conteudo-container">
        <header class="cabecalho-info">
            <div class="table-header">
                <h1>Inserir Manutenção</h1>
            </div>
        </header>
        <form action="">
            <section class="area-edicao">
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="descricao">Descrição Serviço:</label>
                        <input type="text" placeholder="Digite a descrição do serviço." required id="descricao" name="descricao">
                    </div>
                    <div class="campo-edicao">
                        <label for="cadastro">Cadastro:</label>
                        <input type="date" required id="cadastro" name="cadastro">
                    </div>
                    <div class="campo-edicao">
                        <label for="conclusao">Conclusão:</label>
                        <input type="date" id="conclusao" name="conclusao">
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="tipoManutencao">Tipo Manutenção:</label>
                        <input type="text" placeholder="Corretiva/Preventiva" required id="tipoManutencao" name="tipoManutencao">
                    </div>
                    <div class="campo-edicao">
                        <label for="custo">Custo:</label>
                        <input type="number" placeholder="Digite o custo da manutenção." min="1" required id="custo" name="custo" step="0.01">
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="idCaminhao">Id Caminhão:</label>
                        <input type="number" placeholder="ID do Caminhão que fez a manutenção." min="1" required id="idCaminhao" name="idCaminhao">
                    </div>
                    <div class="campo-edicao">
                        <label for="ultimoMotorista">Ultimo Motorista:</label>
                        <input type="number" placeholder="ID do último motorista." required id="ultimoMotorista" name="idMotorista">
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
