<%--
  Created by IntelliJ IDEA.
  User: lucasporto-ieg
  Date: 30/10/2025
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setAttribute("tabela","manutencao");%>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edição de Manutenção - Frota Viva</title>
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
                            <label for="descricao">Descrição Serviço:</label>
                            <input type="text" value="Nisi voluptates provident et." required id="descricao" name="descricao">
                        </div>
                        <div class="campo-edicao">
                            <label for="cadastro">Cadastro:</label>
                            <input type="date" value="2024-03-04" required id="cadastro" name="cadastro">
                        </div>
                        <div class="campo-edicao">
                            <label for="conclusao">Conclusão:</label>
                            <input type="date" value="2024-12-20" id="conclusao" name="conclusao">
                        </div>
                    </div>
                    <div class="linha-info">
                        <div class="campo-edicao">
                            <label for="tipoManutencao">Tipo Manutenção:</label>
                            <input type="text" value="Corretiva" required id="tipoManutencao" name="tipoManutencao">
                        </div>
                        <div class="campo-edicao">
                            <label for="custo">Custo:</label>
                            <input type="number" value="4735" min="1" required id="custo" name="custo" step="0.01">
                        </div>
                    </div>
                    <div class="linha-info">
                        <div class="campo-edicao">
                            <label for="idCaminhao">Id Caminhão:</label>
                            <input type="number" value="5" min="1" required id="idCaminhao" name="idCaminhao">
                        </div>
                        <div class="campo-edicao">
                            <label for="ultimoMotorista">Ultimo Motorista:</label>
                            <input type="number" value="2" required id="ultimoMotorista" name="idMotorista">
                        </div>
                    </div>
                </section>
            </form>
        </section>
    </main>
</body>

</html>
