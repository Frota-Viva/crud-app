<%--
  Created by IntelliJ IDEA.
  User: lucasporto-ieg
  Date: 30/10/2025
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setAttribute("tabela","entrega");%>
<html lang="pt-BR">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edição de Entregas - Frota Viva</title>
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
            <div class="header-cell">Pedido</div>
            <div class="header-cell">Entrega</div>
            <div class="header-cell">CEP</div>
            <div class="header-cell">Motorista</div>
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
            <div class="table-cell cell">1</div>
            <div class="table-cell cell">text</div>
            <div class="table-cell cell">text</div>
            <div class="table-cell cell">text</div>
            <div class="table-cell cell">text</div>
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
            <label for="descricao">Descrição:</label>
            <input type="text" value="Nisi voluptates provident et." id="descricao" name="descricao" required>
          </div>
          <div class="campo-edicao">
            <label for="cep">CEP:</label>
            <input type="text" value="08148589" maxlength="9" required name="cep" id="cep">
          </div>
          <div class="campo-edicao">
            <label for="numero">Número:</label>
            <input type="number" value="84825" mim="1" required id="numero" name="numero">
          </div>
          <div class="campo-edicao">
            <label for="cidade">Cidade:</label>
            <input type="text" value="Pereira Verde" required id="cidade" name="cidade">
          </div>
        </div>
        <div class="linha-info">
          <div class="campo-edicao">
            <label for="pedido">Pedido:</label>
            <input type="date" value="2025-01-03" required id="pedido" name="pedido">
          </div>
          <div class="campo-edicao">
            <label for="rua">Rua:</label>
            <input type="text" value="Lago de Moura" required id="rua" name="rua">
          </div>
          <div class="campo-edicao">
            <label for="pais">País:</label>
            <input type="text" value="Brasil" required id="pais" name="pais">
          </div>
          <div class="campo-edicao">
            <label for="idMotorista">ID Motorista:</label>
            <input type="number" value="3" min="1" required id="idMotorista" name="idMotorista">
          </div>
        </div>
        <div class="linha-info">
          <div class="campo-edicao">
            <label for="entrega">Entrega:</label>
            <input type="date" value="20125-03-17" id="entrega" name="entrega">
          </div>
          <div class="campo-edicao">
            <label for="complemento">Complemento:</label >
            <input type="text" value="Molestiae repellendus veritatis." id="complemento" name="complemento">
          </div>
          <div class="campo-edicao">
            <label for="estado">Estado:</label>
            <input type="text" value="SP" required id="estado" name="estado">
          </div>
        </div>
      </section>
    </form>
  </section>
</main>
</body>

</html>
