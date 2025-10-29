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
    <link rel="stylesheet" href="../../assets/CSS/tabelasStyle.css">
</head>

<body>
<jsp:include page="../componentes/aside.jsp"/>
<main>
    <section class="conteudo-container">

        <header class="top-bar">
            <div class="procurar-container">
                <img src="../../assets/imgs/img-home/lupa.png" alt="Buscar" class="icon-acomp">
                <input type="text" placeholder="Buscar nome ou placa" class="input-search">
            </div>
        </header>

        <article class="table-container">

            <article class="tabela">
                <div class="table-header">
                    <div>ID</div>
                    <div>Nome</div>
                    <div>E-mail</div>
                    <div>CPF</div>
                    <div>Senha</div>
                    <div>
                        <a href="inserir-motorista.jsp" methods="post">
                            <button class="bt-adicionar">
                                <img src="../../assets/imgs/img-home/" alt="Adicionar" >
                                Adicionar Usuário
                            </button>
                        </a>

                    </div>
                </div>

                <%
                    if (motoristas != null && !motoristas.isEmpty()){
                        for (Motorista m : motoristas) {
                %>

                <div class="table-row">
                    <div class="table-left-row">
                        <div> <%= m.getId() %></div>
                        <div><%= m.getNome() %></div>
                        <div><%= m.getEmail() %></div>
                        <div><%= m.getCpf()%></div>
                        <div><%= m.getSenha()%></div>
                    </div>

                    <div class="table-right-row">
                        <a href="atualizar-motorista.jsp">
                            <button class="bt-editar">
                                <img src="../../assets/imgs/img-home/caneta-edit.png" alt="Editar">
                                Editar
                            </button>
                        </a>

                        <a href="deletar-motorista.jsp">
                            <button class="bt-excluir">
                                <img src="../../assets/imgs/img-home/lixo.png" alt="Excluir">
                                Excluir
                            </button>
                        </a>

                    </div>
                </div>

                <%}
                } else {
                %>
                <!-- Linhas da tabela -->
                <div class="table-row">
                    <div class="table-left-row">
                        <p>Nenhum motorista encontrado</p>
                    </div>
                </div>
                <%
                    }
                %>

            </article>
        </article>
    </section>
</main>
</body>
</html>