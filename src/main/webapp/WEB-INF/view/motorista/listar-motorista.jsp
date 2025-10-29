<%@ page import="com.frotaviva.model.Motorista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%
    List<Motorista> motoristas = (List<Motorista>) request.getAttribute("motoristas");
%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Frota Viva - Motoristas</title>
    <link rel="stylesheet" href="../../assets/CSS/tabelasStyle.css?<%=System.currentTimeMillis()%>">
</head>

<body>
<jsp:include page="../componentes/aside.jsp"/>
<main>
    <section class="conteudo-container">

        <header class="top-bar">
            <div class="procurar-container">
                <form action="">
                    <img src="../../assets/imgs/img-home/lupa.png" alt="Buscar" class="icon-acomp">
                    <input type="text" placeholder="Buscar nome ou placa" class="input-search">
                </form>
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
                        <button class="bt-adicionar">
                            <img src="../../assets/imgs/img-home/mais.png" alt="Adicionar">
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
                        <button class="bt-editar">
                            <img src="../../assets/icons/icon-editar.svg" alt="Editar">
                            Editar
                        </button>
                        <button class="bt-excluir">
                            <img src="../../assets/icons/icon-excluir.svg" alt="Excluir">
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
</main>
</body>
</html>