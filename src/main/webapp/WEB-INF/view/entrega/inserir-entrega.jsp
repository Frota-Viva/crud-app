<%--
  Created by IntelliJ IDEA.
  User: davifranco-ieg
  Date: 30/10/2025
  Time: 09:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setAttribute("tabela","entrega");%>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inserir Entrega - Frota Viva</title>
    <link rel="stylesheet" href="../../../assets/CSS/tabelaIserirStyle.css">
</head>

<body>
<jsp:include page="../componentes/aside.jsp"/>
<main>
    <section class="conteudo-container">
        <header class="cabecalho-info">
            <div class="table-header">
                <h1>Inserir Entrega</h1>
            </div>
        </header>
        <form action="">
            <section class="area-edicao">
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="descricao">Descrição:</label>
                        <input type="text" placeholder="Digite a descrição da entrega." id="descricao" name="descricao" required>
                    </div>
                    <div class="campo-edicao">
                        <label for="cep">CEP:</label>
                        <input type="text" placeholder="Exemplo: 00000-000" maxlength="9" required name="cep" id="cep">
                    </div>
                    <div class="campo-edicao">
                        <label for="numero">Número:</label>
                        <input type="number" placeholder="Digite o número da rua/avenida." mim="1" required id="numero" name="numero">
                    </div>
                    <div class="campo-edicao">
                        <label for="cidade">Cidade:</label>
                        <input type="text" placeholder="Digite o nome da cidade." required id="cidade" name="cidade">
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="pedido">Pedido:</label>
                        <input type="date" required id="pedido" name="pedido">
                    </div>
                    <div class="campo-edicao">
                        <label for="rua">Rua:</label>
                        <input type="text" placeholder="Digite o nome da rua/avenida." required id="rua" name="rua">
                    </div>
                    <div class="campo-edicao">
                        <label for="pais">País:</label>
                        <input type="text" placeholder="Digite o nome do país." required id="pais" name="pais">
                    </div>
                    <div class="campo-edicao">
                        <label for="idMotorista">ID Motorista:</label>
                        <input type="number" placeholder="Digite o ID do motorista que fez a entrega." min="1" required id="idMotorista" name="idMotorista">
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="entrega">Entrega:</label>
                        <input type="date" id="entrega" name="entrega">
                    </div>
                    <div class="campo-edicao">
                        <label for="complemento">Complemento:</label >
                        <input type="text" placeholder="Complemento do endereço." id="complemento" name="complemento">
                    </div>
                    <div class="campo-edicao">
                        <label for="estado">Estado:</label>
                        <input type="text" placeholder="Digite o estado que a entrega foi feita." required id="estado" name="estado">
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
