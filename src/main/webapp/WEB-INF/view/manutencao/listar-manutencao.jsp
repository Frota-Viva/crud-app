<%@ page import="com.frotaviva.model.Motorista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.frotaviva.model.Manutencao" %>
<%
    List<Manutencao> manutencoes = (List<Manutencao>) request.getAttribute("manutencoes");
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Frota Viva - Manutenções</title>
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
                    <div class="header-cell">Cadastro</div>
                    <div class="header-cell">Conclusão</div>
                    <div class="header-cell">Tipo</div>
                    <div class="header-cell">Custo</div>
                    <div class="header-cell">Ultimo Motorista</div>
                </div>
                <div class="header-cell header-action">
                    <button class="bt-adicionar">
                        <img src="../../assets/imgs/img-home/mais.png" alt="Adicionar">
                        Adicionar Usuário
                    </button>
                </div>
            </div>

            <%
                if (manutencoes != null && !manutencoes.isEmpty()){
                    for(Manutencao m : manutencoes){
            %>

            <div class="table-row">
                <div class="t-esquerda">
                    <div class="table-cell cell"><%=m.getId()%></div>
                    <div class="table-cell cell"><%=m.getDtCadastro()%></div>
                    <div class="table-cell cell"><%=m.getDtConclusao()%></div>
                    <div class="table-cell cell"><%=m.getTipoManutencao()%></div>
                    <div class="table-cell cell"><%=m.getCusto()%></div>
                    <div class="table-cell cell"><%=m.getUltimoMotorista()%></div>
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
        </article>

        <%
            }
        }  else{
        %>
        <div class="table-row">
            <div class="t-esquerda">
                <div class="table-cell cell"> Nenhuma manutenção encontrada</div>
            </div>
        </div>
        <%
            }
        %>

    </section>
</main>
</body>

</html>