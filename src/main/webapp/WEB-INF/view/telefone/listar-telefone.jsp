<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.frotaviva.model.TelefoneMotorista" %>
<%
    List<TelefoneMotorista> telefones = (List<TelefoneMotorista>) request.getAttribute("telefones");
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Frota Viva - Telefone</title>
    <link rel="stylesheet" href="../../assets/CSS/tabelasStyle.css">
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
                    <div class="header-cell">Motorista</div>
                    <div class="header-cell">Telefone</div>
                </div>
                <div class="header-cell header-action">
                    <button class="bt-adicionar">
                        <img src="../../assets/imgs/img-home/mais.png" alt="Adicionar">
                        Adicionar Telefone
                    </button>
                </div>
            </div>

            <%
                if (telefones != null && !telefones.isEmpty()){
                    for(TelefoneMotorista t : telefones){
            %>

            <div class="table-row">
                <div class="t-esquerda">
                    <div class="table-cell cell"><%=t.getId()%></div>
                    <div class="table-cell cell"><%=t.getIdMotorista()%></div>
                    <div class="table-cell cell"><%=t.getTelefoneMotorista()%></div>

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


            <%
                }
            }  else{
            %>
                <div class="table-row">
                    <div class="t-esquerda">
                        <div class="table-cell cell"> Nenhum telefone encontrada</div>
                    </div>
                </div>
            <%
                }
            %>
        </article>
    </section>
    <jsp:include page="../componentes/pop-up-excluir.jsp">
        <jsp:param name="servletExclusao" value="/deletar-telefone"/>
    </jsp:include>
</main>
</body>

