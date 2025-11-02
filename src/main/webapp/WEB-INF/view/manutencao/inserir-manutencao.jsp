<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("tabela","manutencao");
%>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../../assets/imgs/Logo16x16.png">
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
        <form action="/inserir-manutencao" method="post">
            <section class="area-edicao">
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="idCaminhao">ID Caminhão:</label>
                        <input type="number" placeholder="Digite o ID do caminhão." min="1" id="idCaminhao" name="idCaminhao" required>
                        <%if (request.getAttribute("erroCaminhao") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroCaminhao")%></p>
                        <%}%>
                    </div>
                    <div class="campo-edicao">
                        <label for="ultimoMotorista">Último Motorista:</label>
                        <input type="number" placeholder="Digite o ID do último motorista." min="1" id="ultimoMotorista" name="ultimoMotorista" required>
                        <%if (request.getAttribute("erroMotorista") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroMotorista")%></p>
                        <%}%>
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="dtCadastro">Data Cadastro:</label>
                        <input type="date" id="dtCadastro" name="dtCadastro" required>
                        <%if (request.getAttribute("erroDtCadastro") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroDtCadastro")%></p>
                        <%}%>
                    </div>
                    <div class="campo-edicao">
                        <label for="dtConclusao">Data Conclusão:</label>
                        <input type="date" id="dtConclusao" name="dtConclusao" required>
                        <%if (request.getAttribute("erroDtConclusao") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroDtConclusao")%></p>
                        <%}%>
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="tipoManutencao">Tipo Manutenção:</label>
                        <input type="text" placeholder="Corretiva/Preventiva" id="tipoManutencao" name="tipoManutencao" required>
                        <%if (request.getAttribute("erroTipoManutencao") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroTipoManutencao")%></p>
                        <%}%>
                    </div>
                    <div class="campo-edicao">
                        <label for="custo">Custo:</label>
                        <input type="number" placeholder="Digite o custo da manutenção." min="0.01" id="custo" name="custo" step="0.01" required>
                        <%if (request.getAttribute("erroCusto") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroCusto")%></p>
                        <%}%>
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="descricao">Descrição Serviço:</label>
                        <input type="text" placeholder="Digite a descrição do serviço." id="descricao" name="descricao" required>
                        <%if (request.getAttribute("erroDescricao") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroDescricao")%></p>
                        <%}%>
                    </div>
                </div>
            </section>
            <section id="botoes">
                <button id="button-cancelar" type="button" onclick="window.location.href='/listar-manutencao'">
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