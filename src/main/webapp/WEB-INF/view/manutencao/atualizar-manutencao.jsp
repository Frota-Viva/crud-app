<%@ page import="com.frotaviva.model.Manutencao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setAttribute("tabela","manutencao");
    Manutencao m = (Manutencao) request.getAttribute("manutencao");
    boolean conclusaoNull = m.getDtConclusao() == null;
%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../../assets/imgs/Logo16x16.png">
    <title>Edição de Manutenção - Frota Viva</title>
    <link rel="stylesheet" href="../../../assets/CSS/tabelaEditStyle.css?<%=System.currentTimeMillis()%>">
</head>

<body>
<jsp:include page="../componentes/aside.jsp"/>
<main>
    <section class="conteudo-container">

        <form action="/atualizar-manutencao" method="post">
            <header class="cabecalho-info">
                <div class="table-header">
                    <div class="th-esquerda">
                        <div class="header-cell">ID</div>
                        <div class="header-cell">Cadastro</div>
                        <div class="header-cell">Conclusão</div>
                        <div class="header-cell">Tipo</div>
                        <div class="header-cell">Custo</div>
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
                        <div class="table-cell cell"><%=m.getId()%></div>
                        <div class="table-cell cell"><%=m.getDtCadastro()%></div>
                        <div class="table-cell cell"><%=conclusaoNull ? "Não concluída" : m.getDtConclusao()%></div>
                        <div class="table-cell cell"><%=m.getTipoManutencao()%></div>
                        <div class="table-cell cell"><%=m.getCusto()%></div>
                    </div>
                    <button class="btn-sair" type="button" onclick="window.location.href='/listar-manutencao'">
                        <img src="../../../assets/icons/icon-sair-edicao.svg" alt="Sair" id="sair-edicao">
                        Sair da Edição
                    </button>
                </div>
            </header>

            <section class="area-edicao">

                <input type="hidden" name="id" value="<%=m.getId()%>">

                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="descricaoServico">Descrição Serviço:</label>
                        <input type="text" value="<%=m.getDescricaoServico()%>" required id="descricaoServico" name="descricaoServico">
                        <% if (request.getAttribute("erroDescricao") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroDescricao")%></p>
                        <% } %>
                    </div>
                    <div class="campo-edicao">
                        <label for="dtCadastro">Cadastro:</label>
                        <input type="date" value="<%=m.getDtCadastro()%>" required id="dtCadastro" name="dtCadastro">
                        <% if (request.getAttribute("erroDtCadastro") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroDtCadastro")%></p>
                        <% } %>
                    </div>
                    <div class="campo-edicao">
                        <label for="dtConclusao">Conclusão:</label>
                        <input type="date" value="<%=conclusaoNull ? "" : m.getDtConclusao()%>" id="dtConclusao" name="dtConclusao">
                        <% if (request.getAttribute("erroDtConclusao") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroDtConclusao")%></p>
                        <% } %>
                    </div>
                </div>

                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="tipoManutencao">Tipo Manutenção:</label>
                        <input type="text" value="<%=m.getTipoManutencao()%>" required id="tipoManutencao" name="tipoManutencao">
                        <% if (request.getAttribute("erroTipoManutencao") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroTipoManutencao")%></p>
                        <% } %>
                    </div>
                    <div class="campo-edicao">
                        <label for="custo">Custo:</label>
                        <input type="number" value="<%=m.getCusto()%>" min="0.01" required id="custo" name="custo" step="0.01">
                        <% if (request.getAttribute("erroCusto") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroCusto")%></p>
                        <% } %>
                    </div>
                </div>

                <div class="linha-info">
                    <div class="campo-edicao">
                        <label for="idCaminhao">Id Caminhão:</label>
                        <input type="number" value="<%=m.getIdCaminhao()%>" min="1" required id="idCaminhao" name="idCaminhao">
                        <% if (request.getAttribute("erroCaminhao") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroCaminhao")%></p>
                        <% } %>
                    </div>
                    <div class="campo-edicao">
                        <label for="ultimoMotorista">Ultimo Motorista:</label>
                        <input type="number" value="<%=m.getUltimoMotorista()%>" min="1" required id="ultimoMotorista" name="ultimoMotorista">
                        <% if (request.getAttribute("erroMotorista") != null) { %>
                        <p style="color: red; font-size: 16px"><%=request.getAttribute("erroMotorista")%></p>
                        <% } %>
                    </div>
                </div>

            </section>
        </form>
    </section>
</main>
</body>

</html>