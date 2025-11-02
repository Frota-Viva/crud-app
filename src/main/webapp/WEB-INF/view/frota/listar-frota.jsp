<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.frotaviva.model.Frota" %>
<%
    List<Frota> frotas = (List<Frota>) request.getAttribute("frotas");
    String msg = request.getParameter("msg");
    request.setAttribute("tabela","frota");
    String sucesso = ".*sucesso.*";
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../../../assets/imgs/Logo16x16.png">
    <title>Frota Viva - Motoristas</title>
    <link rel="stylesheet" href="../../../assets/CSS/tabelasStyle.css?<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="../../../assets/CSS/pop-up-excluir.css?<%=System.currentTimeMillis()%>">
    <script src="../../../assets/JS/pop-up-excluir.js?<%=System.currentTimeMillis()%>"></script>
</head>

<body>
<jsp:include page="../componentes/aside.jsp"/>

<main>
    <section class="conteudo-container">

        <header class="top-bar">
            <div class="procurar-container">
                <form action="listar-frota" class="form-buscar">
                    <button type="submit" class="bt-buscar">
                        <img src="../../../assets/imgs/img-home/lupa.png" alt="Buscar" class="icon-acomp">
                    </button>
                    <input type="text" placeholder="Buscar tipo-frota" class="input-search" name="buscar">
                </form>
                <div class="mensagem">
                    <%
                        if (msg != null) {
                            String cor = msg.matches(sucesso) ? "green" : "red";
                    %>
                        <span id="aviso-span-tabela">
                            <p style="color: <%=cor%>"><%=msg%></p>
                        </span>
                    <%}%>
                </div>
            </div>
        </header>

            <article class="tabela">
                <div class="table-header">
                    <div class="th-esquerda">
                        <div class="header-cell">ID</div>
                        <div class="header-cell">Tamanho da frota</div>
                        <div class="header-cell">Tipo da frota</div>
                        <div class="header-cell">Região de atuação</div>
                    </div>
                    <div class="header-cell header-action">
                        <button class="bt-adicionar" onclick="window.location.href='/inserir-frota'">
                            <img src="../../../assets/icons/icon-adicionar.svg" alt="Adicionar">
                            Adicionar Frota
                        </button>
                    </div>
                </div>

                <%
                    if ( (frotas != null) && (! frotas.isEmpty()) ) {
                        for (Frota f : frotas){
                %>

                <div class="table-row">
                    <div class="t-esquerda">
                        <div class="table-cell cell"><%=f.getId()%></div>
                        <div class="table-cell cell"><%=f.getTamanhoFrota()%></div>
                        <div class="table-cell cell"><%=f.getTipoFrota()%></div>
                        <div class="table-cell cell"><%=f.getRegiao()%></div>
                    </div>
                    <div class="table-cell cell table-actions">
                        <button class="bt-editar" onclick="window.location.href='/atualizar-frota?id=<%=f.getId()%>'">
                            <img src="../../../assets/icons/icon-editar.svg" alt="Editar">
                            Editar
                        </button>
                        <button class="bt-excluir" data-id="<%=f.getId()%>">
                            <img src="../../../assets/icons/icon-excluir.svg" alt="Excluir">
                            Excluir
                        </button>
                    </div>
                </div>
                <%}
                    } else {%>
                    <div class="table-row">
                        <div class="t-esquerda">
                            <div class="table-cell cell">Nenhuma frota encontrado</div>
                        </div>
                    </div>
                    <%}%>
            </article>
    </section>
    <jsp:include page="../componentes/pop-up-excluir.jsp">
        <jsp:param name="servletExclusao" value="/deletar-frota"/>
    </jsp:include>

</main>
</body>
</html>