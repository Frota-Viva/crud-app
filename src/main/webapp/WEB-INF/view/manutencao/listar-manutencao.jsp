<%@ page import="com.frotaviva.model.Motorista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.frotaviva.model.Manutencao" %>
<%
    List<Manutencao> manutencoes = (List<Manutencao>) request.getAttribute("manutencoes");
    String msg = request.getParameter("msg");
    request.setAttribute("tabela","manutencao");
    String sucesso = ".*sucesso.*";
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Frota Viva - Manutenções</title>
    <link rel="stylesheet" href="../../../assets/CSS/tabelasStyle.css?<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="../../../assets/CSS/pop-up-excluir.css?<%=System.currentTimeMillis()%>">
    <script src="../../../assets/JS/pop-up-excluir.js?<%=System.currentTimeMillis()%>"></script></head>

<body>
<jsp:include page="../componentes/aside.jsp"/>
<main>
    <section class="conteudo-container">

        <header class="top-bar">
            <div class="procurar-container">
                <form action="listar-manutencao" class="form-buscar">
                    <button type="submit" class="bt-buscar">
                        <img src="../../../assets/imgs/img-home/lupa.png" alt="Buscar" class="icon-acomp">
                    </button>
                    <input type="text" placeholder="Buscar tipo" class="input-search" name="buscar">
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
                    <div class="header-cell">Cadastro</div>
                    <div class="header-cell">Conclusão</div>
                    <div class="header-cell">Tipo</div>
                    <div class="header-cell">Custo</div>
                    <div class="header-cell">Ultimo Motorista</div>
                </div>
                <div class="header-cell header-action">
                    <button class="bt-adicionar" onclick="window.location.href='/inserir-manutencao'">
                        <img src="../../../assets/icons/icon-adicionar.svg" alt="Adicionar">
                        Adicionar Manutenção
                    </button>
                </div>
            </div>

            <%
                if (manutencoes != null && !manutencoes.isEmpty()){
                    for(Manutencao m : manutencoes){
                        boolean conclusaoNull = m.getDtConclusao() == null;
            %>

            <div class="table-row">
                <div class="t-esquerda">
                    <div class="table-cell cell"><%=m.getId()%></div>
                    <div class="table-cell cell"><%=m.getDtCadastro()%></div>
                    <div class="table-cell cell"><%=conclusaoNull ? "Não concluída" : m.getDtConclusao()%></div>
                    <div class="table-cell cell"><%=m.getTipoManutencao()%></div>
                    <div class="table-cell cell"><%=m.getCusto()%></div>
                    <div class="table-cell cell"><%=m.getUltimoMotorista()%></div>
                </div>
                <div class="table-cell cell table-actions">
                    <button class="bt-editar" onclick="window.location.href='/atualizar-manutencao?id=<%=m.getId()%>'">
                        <img src="../../assets/icons/icon-editar.svg" alt="Editar">
                        Editar
                    </button>
                    <button class="bt-excluir" data-id="<%=m.getId()%>">
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
                    <div class="table-cell cell"> Nenhuma manutenção encontrada</div>
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
</html>
