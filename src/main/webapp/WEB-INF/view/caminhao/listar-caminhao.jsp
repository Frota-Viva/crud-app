<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.frotaviva.model.Caminhao" %>
<%
    List<Caminhao> caminhoes = (List<Caminhao>) request.getAttribute("caminhoes");
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Frota Viva - Caminhões</title>
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
                <form action="listar-caminhao" class="form-buscar">
                    <button type="submit" class="bt-buscar">
                        <img src="../../../assets/imgs/img-home/lupa.png" alt="Buscar" class="icon-acomp">
                    </button>
                    <input type="text" placeholder="Buscar placa" class="input-search" name="buscar">
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
                        <div class="header-cell">Placa</div>
                        <div class="header-cell">Status</div>
                        <div class="header-cell">Kms rodados</div>
                        <div class="header-cell">Modelo</div>
                        <div class="header-cell">Capacidade</div>
                    </div>
                    <div class="header-cell header-action">
                        <button class="bt-adicionar">
                            <img src="../../../assets/icons/icon-adicionar.svg" alt="Adicionar">
                            Adicionar Caminhão
                        </button>
                    </div>
                </div>


                <%
                    if ( (caminhoes != null) && (! caminhoes.isEmpty()) ) {
                        for (Caminhao c : caminhoes){
                %>

                <div class="table-row">
                    <div class="t-esquerda">
                        <div class="table-cell cell"><%=c.getId()%></div>
                        <div class="table-cell cell"><%=c.getPlaca()%></div>
                        <div class="table-cell cell"><%=c.getStatus()%></div>
                        <div class="table-cell cell"><%=c.getKmRodados()%></div>
                        <div class="table-cell cell"><%=c.getModelo()%></div>
                        <div class="table-cell cell"><%=c.getCapacidade()%></div>
                    </div>
                    <div class="table-cell cell table-actions">
                        <button class="bt-editar">
                            <img src="../../../assets/icons/icon-editar.svg" alt="Editar">
                            Editar
                        </button>
                        <button class="bt-excluir">
                            <img src="../../../assets/icons/icon-excluir.svg" alt="Excluir">
                            Excluir
                        </button>
                    </div>
                </div>
                <%}
                } else {%>
                <div class="table-row">
                    <div class="t-esquerda">
                        <td class="table-cell cell">Nenhum caminhão encontrado</td>
                    </div>
                </div>
                <%}%>
            </article>
    </section>
    <jsp:include page="../componentes/pop-up-excluir.jsp">
        <jsp:param name="servletExclusao" value="/deletar-caminhao"/>
    </jsp:include>
</main>
</body>
