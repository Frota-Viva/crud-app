<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setAttribute("tabela","caminhao");%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Edição de Caminhões - Frota Viva</title>
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
            <div class="header-cell">Placa</div>
            <div class="header-cell">Status</div>
            <div class="header-cell">Kms rodados</div>
            <div class="header-cell">Modelo</div>
            <div class="header-cell">Capacidade</div>
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
            <label for="idFrota">ID Frota:</label>
            <input type="number" value="4" min="1" id="idFrota" name="idFrota" required>
          </div>
          <div class="campo-edicao">
            <label for="kmsRodados">Kms Rodados:</label>
            <input type="number" value="4789" min="0" id="kmsRodados" name="kmsRodados" required>
          </div>
        </div>
        <div class="linha-info">
          <div class="campo-edicao">
            <label for="placa">Placa:</label>
            <input type="text" value="BEE 4R22" id="placa" name="placa" required>
          </div>
          <div class="campo-edicao">
            <label for="modelo">Modelo:</label>
            <input type="text" value="Scania R450" id="modelo" name="modelo" required>
          </div>
        </div>
        <div class="linha-info">
          <div class="campo-edicao">
            <label for="estado">Estado:</label>
            <input type="text" value="Ativo" id="estado" name="estado" required>
          </div>
          <div class="campo-edicao">
            <label for="capacidade">Capacidade:</label >
            <input type="number" value="16350" id="capacidade" name="capacidade" min="1">
          </div>
        </div>
      </section>
  </section>
  </form>
</main>
</body>

</html>
