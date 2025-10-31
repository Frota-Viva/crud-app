<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.frotaviva.model.Entrega" %>
<%
    List<Entrega> entregas = (List<Entrega>) request.getAttribute("entregas");
    String msg = request.getParameter("msg");
    request.setAttribute("tabela","entrega");

%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Frota Viva - Entrega</title>
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
                <form action="listar-entregas" class="form-buscar">
                    <button type="submit" class="bt-buscar">
                        <img src="../../../assets/imgs/img-home/lupa.png" alt="Buscar" class="icon-acomp">
                    </button>
                    <input type="text" placeholder="Buscar data (yyyy-mm-dd)" class="input-search" name="buscar">
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
                        <div class="header-cell">Pedido</div>
                        <div class="header-cell">Entrega</div>
                        <div class="header-cell">CEP</div>
                        <div class="header-cell">Motorista</div>
                    </div>
                    <div class="header-cell header-action">
                        <button class="bt-adicionar">
                            <img src="../../../assets/icons/icon-adicionar.svg" alt="Adicionar">
                            Adicionar Entrega
                        </button>
                    </div>
                </div>

                <%
                    if ( (entregas != null) && (! entregas.isEmpty()) ) {
                        for (Entrega e : entregas){
                            boolean entregaNull = e.getDtEntrega() == null;
                %>

                <div class="table-row">
                    <div class="t-esquerda">
                        <div class="table-cell cell"><%=e.getCod_entrega()%></div>
                        <div class="table-cell cell"><%=e.getDtPedido()%></div>
                        <div class="table-cell cell"><%=entregaNull ? "Não entregue!" : e.getDtEntrega()%></div>
                        <div class="table-cell cell"><%=e.getEndereco().getCep()%></div>
                        <div class="table-cell cell"><%=e.getIdMotorista()%></div>
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
                        <td class="table-cell cell">Nenhum entrega encontrado</td>
                    </div>
                </div>
                <%}%>
            </article>
    </section>
    <jsp:include page="../componentes/pop-up-excluir.jsp">
        <jsp:param name="servletExclusao" value="/deletar-entrega"/>
    </jsp:include>
</main>
</body>
