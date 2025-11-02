<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("tabela","entrega");
%>
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
        <form action="/inserir-entregas" method="post">
            <section class="area-edicao">
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="codEntrega">Código Entrega:</label>
                        <input type="number" placeholder="Digite o código da entrega." min="1" id="codEntrega" name="codEntrega" required>
                        <%if (request.getAttribute("erroCodEntrega") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroCodEntrega")%></p>
                        <%}%>
                    </div>
                    <div class="campo-edicao">
                        <label for="idMotorista">ID Motorista:</label>
                        <input type="number" placeholder="Digite o ID do motorista." min="1" id="idMotorista" name="idMotorista" required>
                        <%if (request.getAttribute("erroMotorista") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroMotorista")%></p>
                        <%}%>
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="descricao">Descrição:</label>
                        <input type="text" placeholder="Digite a descrição da entrega." id="descricao" name="descricao" required>
                        <%if (request.getAttribute("erroDescricao") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroDescricao")%></p>
                        <%}%>
                    </div>
                    <div class="campo-edicao">
                        <label for="dataPedido">Data Pedido:</label>
                        <input type="date" id="dataPedido" name="dataPedido" required>
                        <%if (request.getAttribute("erroDataPedido") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroDataPedido")%></p>
                        <%}%>
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="dataConclusao">Data Conclusão:</label>
                        <input type="date" id="dataConclusao" name="dataConclusao">
                        <%if (request.getAttribute("erroDataConclusao") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroDataConclusao")%></p>
                        <%}%>
                    </div>
                    <div class="campo-edicao">
                        <label for="cep">CEP:</label>
                        <input type="text" placeholder="Exemplo: 00000-000" maxlength="9" id="cep" name="cep" required>
                        <%if (request.getAttribute("erroCep") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroCep")%></p>
                        <%}%>
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="rua">Rua:</label>
                        <input type="text" placeholder="Digite o nome da rua/avenida." id="rua" name="rua" required>
                        <%if (request.getAttribute("erroRua") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroRua")%></p>
                        <%}%>
                    </div>
                    <div class="campo-edicao">
                        <label for="numero">Número:</label>
                        <input type="number" placeholder="Digite o número da rua/avenida." min="1" id="numero" name="numero" required>
                        <%if (request.getAttribute("erroNumero") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroNumero")%></p>
                        <%}%>
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="complemento">Complemento:</label>
                        <input type="text" placeholder="Complemento do endereço." id="complemento" name="complemento">
                    </div>
                    <div class="campo-edicao">
                        <label for="cidade">Cidade:</label>
                        <input type="text" placeholder="Digite o nome da cidade." id="cidade" name="cidade" required>
                        <%if (request.getAttribute("erroCidade") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroCidade")%></p>
                        <%}%>
                    </div>
                </div>
                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="estado">Estado:</label>
                        <input type="text" placeholder="Digite o estado." id="estado" name="estado" required>
                        <%if (request.getAttribute("erroEstado") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroEstado")%></p>
                        <%}%>
                    </div>
                    <div class="campo-edicao">
                        <label for="pais">País:</label>
                        <input type="text" placeholder="Digite o nome do país." id="pais" name="pais" required>
                        <%if (request.getAttribute("erroPais") != null){%>
                        <p style="color: red; font-size: 20px"><%=request.getAttribute("erroPais")%></p>
                        <%}%>
                    </div>
                </div>
            </section>
            <section id="botoes">
                <button id="button-cancelar" type="button" onclick="window.location.href='/listar-entregas'">
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