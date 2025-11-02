<%@ page import="com.frotaviva.model.Caminhao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("tabela","caminhao");
    Caminhao c = (Caminhao) request.getAttribute("caminhao");
;%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../../assets/imgs/Logo16x16.png">
  <title>Edição de Caminhões - Frota Viva</title>
  <link rel="stylesheet" href="../../../assets/CSS/tabelaEditStyle.css?<%=System.currentTimeMillis()%>">
</head>

<body>
<jsp:include page="../componentes/aside.jsp"/>
<main>
  <section class="conteudo-container">

    <form action="/atualizar-caminhao" method="post">
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
            <div class="table-cell cell"><%=c.getId()%></div>
            <div class="table-cell cell"><%=c.getPlaca()%></div>
            <div class="table-cell cell"><%=c.getStatus()%></div>
            <div class="table-cell cell"><%=c.getKmRodados()%></div>
            <div class="table-cell cell"><%=c.getModelo()%></div>
            <div class="table-cell cell"><%=c.getCapacidade()%></div>
          </div>
          <button class="btn-sair" type="button" onclick="window.location.href='/listar-caminhao'">
            <img src="../../../assets/icons/icon-sair-edicao.svg" alt="Sair" id="sair-edicao">
            Sair da Edição
          </button>
        </div>

        </div>
      </header>
      <section class="area-edicao">
        <div class="linha-info">
            <input type="hidden" name="id" value="<%=c.getId()%>">
          <div class="campo-edicao">
            <label for="idFrota">ID Frota:</label>
            <input type="number" value="<%=c.getIdFrota()%>" min="1" id="idFrota" name="idFrota" required>
            <%if (request.getAttribute("erroFrota") != null){%>
            <p style="color: red; font-size: 16px"><%=request.getAttribute("erroFrota")%></p>
            <%}%>
          </div>
          <div class="campo-edicao">
            <label for="kmsRodados">Kms Rodados:</label>
            <input type="number" value="<%=c.getKmRodados()%>" min="0" id="kmsRodados" name="kmsRodados" required>
            <%if (request.getAttribute("erroKmRodados") != null){%>
            <p style="color: red; font-size: 16px"><%=request.getAttribute("erroKmRodados")%></p>
            <%}%>
          </div>
        </div>
        <div class="linha-info">
          <div class="campo-edicao">
            <label for="placa">Placa:</label>
            <input type="text" value="<%=c.getPlaca()%>" id="placa" name="placa" required>
              <%if (request.getAttribute("erroPlaca") != null){%>
              <p style="color: red; font-size: 16px"><%=request.getAttribute("erroPlaca")%></p>
              <%}%>
          </div>
          <div class="campo-edicao">
            <label for="modelo">Modelo:</label>
            <input type="text" value="<%=c.getModelo()%>" id="modelo" name="modelo" required>
              <%if (request.getAttribute("erroModelo") != null){%>
              <p style="color: red; font-size: 16px"><%=request.getAttribute("erroModelo")%></p>
              <%}%>
          </div>
        </div>
        <div class="linha-info">
          <div class="campo-edicao">
            <label for="status">Status:</label>
            <input type="text" value="<%=c.getStatus()%>" id="status" name="status" required>
              <%if (request.getAttribute("erroStatus") != null){%>
              <p style="color: red; font-size: 16px"><%=request.getAttribute("erroStatus")%></p>
              <%}%>
          </div>
          <div class="campo-edicao">
            <label for="capacidade">Capacidade:</label >
            <input type="number" value="<%=c.getCapacidade()%>" id="capacidade" name="capacidade" min="1" required>
            <%if (request.getAttribute("erroCapacidade") != null){%>
            <p style="color: red; font-size: 16px"><%=request.getAttribute("erroCapacidade")%></p>
            <%}%>
          </div>
        </div>
      </section>
  </form>
  </section>
</main>
</body>

</html>
