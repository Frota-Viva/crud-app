
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setAttribute("tabela","motorista");%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edição de Motoristas - Frota Viva</title>
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
                                <div class="header-cell">Nome</div>
                                <div class="header-cell">CPF</div>
                                <div class="header-cell">Email</div>
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
                                <div class="table-cell cell">2</div>
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
                            <label for="nome">Nome:</label>
                            <input type="text" value="José Miguel Pires"  required id="nome" name="nome">
                        </div>
                    </div>
    
                    <div class="linha-info">
                        <div class="campo-edicao">
                            <label for="cpf">CPF:</label>
                            <input type="text" value="91768345066" maxlength="14" required id="cpf" name="cpf">
                        </div>
                    </div>
                    <div class="linha-info">
                        <div class="campo-edicao">
                            <label for="email">Email:</label>
                            <input type="text" value="da-mataenzo@example.org" required name="email" id="email">
                        </div>
                    </div>
                </section>
            </form>
        </section>
    </main>
</body>

</html>