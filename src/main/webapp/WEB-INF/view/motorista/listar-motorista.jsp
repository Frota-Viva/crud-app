<%@ page import="com.frotaviva.model.Motorista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%
    List<Motorista> motoristas = (List<Motorista>) request.getAttribute("motoristas");
    String msg = request.getParameter("msg");
    request.setAttribute("tabela","motorista");
%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                <form action="listar-motoristas" class="form-buscar">
                    <button type="submit" class="bt-buscar">
                        <img src="../../../assets/imgs/img-home/lupa.png" alt="Buscar" class="icon-acomp">
                    </button>
                    <input type="text" placeholder="Buscar nome" class="input-search" name="buscar">
                </form>
                <div class="mensagem">
                    <%if (msg != null){%>
                    <p><%=msg%></p>
                    <%}%>
                </div>
            </div>
        </header>

        <article class="tabela">
            <div class="table-header">
                <div class="th-esquerda">
                    <div class="header-cell">ID</div>
                    <div class="header-cell">Nome</div>
                    <div class="header-cell">CPF</div>
                    <div class="header-cell">Email</div>
                </div>
                <div class="header-cell header-action">
                    <button class="bt-adicionar" onclick="window.location.href='/inserir-motoristas'">
                        <img src="../../../assets/icons/icon-adicionar.svg" alt="Adicionar">
                        Adicionar Usuário
                    </button>
                </div>
            </div>

            <%
                if ( (motoristas != null) && (! motoristas.isEmpty()) ) {
                    for (Motorista motorista : motoristas){
            %>
            <div class="table-row">
                <div class="t-esquerda">
                    <div class="table-cell cell"><%=motorista.getId()%></div>
                    <div class="table-cell cell"><%=motorista.getNome()%></div>
                    <div class="table-cell cell"><%=motorista.getCpf()%></div>
                    <div class="table-cell cell"><%=motorista.getEmail()%></div>
                </div>
                <div class="table-cell cell table-actions">
                    <button class="bt-editar" onclick="window.location.href='/atualizar-motorista?id=<%=motorista.getId()%>'">
                        <img src="../../../assets/icons/icon-editar.svg" alt="Editar">
                        Editar
                    </button>
                    <button class="bt-excluir" data-id="<%=motorista.getId()%>">
                        <img src="../../../assets/icons/icon-excluir.svg" alt="Excluir">
                        Excluir
                    </button>
                </div>
            </div>
            <%}
            } else {%>
            <div class="table-row">
                <div class="t-esquerda">
                    <td class="table-cell cell">Nenhum motorista encontrado</td>
                </div>
            </div>
            <%}%>
        </article>
    </section>
    <jsp:include page="../componentes/pop-up-excluir.jsp">
        <jsp:param name="servletExclusao" value="/deletar-motorista"/>
    </jsp:include>
</main>
</body>
</html>