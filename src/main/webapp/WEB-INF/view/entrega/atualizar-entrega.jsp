<%@ page import="com.frotaviva.model.Entrega" %>
<%@ page import="com.frotaviva.model.Endereco" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("tabela","entrega");
    Entrega e = (Entrega) request.getAttribute("entrega");
    boolean dtEntregaNull = e.getDtEntrega() == null;
%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edição de Entregas - Frota Viva</title>
    <link rel="stylesheet" href="../../../assets/CSS/tabelaEditStyle.css?<%=System.currentTimeMillis()%>">
</head>

<body>
<jsp:include page="../componentes/aside.jsp"/>
<main>
    <section class="conteudo-container">

        <form action="/atualizar-entrega" method="post">
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
                        <div class="table-cell cell"><%=e.getCod_entrega()%></div>
                        <div class="table-cell cell"><%=e.getDtPedido()%></div>
                        <div class="table-cell cell"><%=dtEntregaNull ? "Não foi entregue" : e.getDtEntrega()%></div>
                        <div class="table-cell cell"><%=e.getEndereco().getCep()%></div>
                        <div class="table-cell cell"><%=e.getIdMotorista()%></div>
                    </div>
                    <button class="btn-sair" type="button" onclick="window.location.href='/listar-entregas'">
                        <img src="../../../assets/icons/icon-sair-edicao.svg" alt="Sair" id="sair-edicao">
                        Sair da Edição
                    </button>
                </div>
            </header>

            <section class="area-edicao">

                <input type="hidden" name="cod_entrega" value="<%=e.getCod_entrega()%>">

                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="descricao_produto">Descrição:</label>
                        <input type="text" value="<%=e.getDescricaoProduto()%>" id="descricao_produto" name="descricao_produto" required>
                        <% if (request.getAttribute("erroDescricao") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroDescricao")%></p>
                        <% } %>
                    </div>
                    <div class="campo-edicao">
                        <label for="cep">CEP:</label>
                        <input type="text" value="<%=e.getEndereco().getCep()%>" maxlength="9" required name="cep" id="cep">
                        <% if (request.getAttribute("erroCep") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroCep")%></p>
                        <% } %>
                    </div>
                    <div class="campo-edicao">
                        <label for="numero">Número:</label>
                        <input type="number" value="<%=e.getEndereco().getNumero()%>" min="1" required id="numero" name="numero">
                        <% if (request.getAttribute("erroNumero") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroNumero")%></p>
                        <% } %>
                    </div>
                    <div class="campo-edicao">
                        <label for="cidade">Cidade:</label>
                        <input type="text" value="<%=e.getEndereco().getCidade()%>" required id="cidade" name="cidade">
                        <% if (request.getAttribute("erroCidade") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroCidade")%></p>
                        <% } %>
                    </div>
                </div>

                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="dt_pedido">Data pedido:</label>
                        <input type="date" value="<%=e.getDtPedido()%>" required id="dt_pedido" name="dt_pedido">
                        <% if (request.getAttribute("erroDtPedido") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroDtPedido")%></p>
                        <% } %>
                    </div>
                    <div class="campo-edicao">
                        <label for="rua">Rua:</label>
                        <input type="text" value="<%=e.getEndereco().getRua()%>" required id="rua" name="rua">
                        <% if (request.getAttribute("erroRua") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroRua")%></p>
                        <% } %>
                    </div>
                    <div class="campo-edicao">
                        <label for="pais">País:</label>
                        <input type="text" value="<%=e.getEndereco().getPais()%>" required id="pais" name="pais">
                        <% if (request.getAttribute("erroPais") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroPais")%></p>
                        <% } %>
                    </div>
                    <div class="campo-edicao">
                        <label for="idMotorista">ID Motorista:</label>
                        <input type="number" value="<%=e.getIdMotorista()%>" min="1" required id="idMotorista" name="idMotorista">
                        <% if (request.getAttribute("erroMotorista") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroMotorista")%></p>
                        <% } %>
                    </div>
                </div>

                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="dt_entrega">Data entrega:</label>
                        <input type="date" value="<%=dtEntregaNull ? "" : e.getDtEntrega()%>" id="dt_entrega" name="dt_entrega">
                        <% if (request.getAttribute("erroDtEntrega") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroDtEntrega")%></p>
                        <% } %>
                    </div>
                    <div class="campo-edicao">
                        <label for="complemento">Complemento:</label>
                        <input type="text" value="<%=e.getEndereco().getComplemento()%>" id="complemento" name="complemento">
                        <% if (request.getAttribute("erroComplemento") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroComplemento")%></p>
                        <% } %>
                    </div>
                    <div class="campo-edicao">
                        <label for="estado">Estado:</label>
                        <input type="text" value="<%=e.getEndereco().getEstado()%>" required id="estado" name="estado">
                        <% if (request.getAttribute("erroEstado") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroEstado")%></p>
                        <% } %>
                    </div>
                </div>

            </section>
        </form>
    </section>
</main>
</body>
</html>
