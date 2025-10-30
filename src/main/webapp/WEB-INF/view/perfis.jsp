<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Map<String, String>> perfisMotoristas = (List<Map<String, String>>) request.getAttribute("perfisMotoristas");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfis - Frota Viva</title>
    <link rel="stylesheet" href="../../assets/CSS/homeStyle.css?<%=System.currentTimeMillis()%>">
    <link rel="stylesheet" href="../../assets/CSS/tabelasStyle.css?<%=System.currentTimeMillis()%>">
</head>

<body>
<jsp:include page="componentes/aside.jsp"></jsp:include>
<main>
    <section class="funcionarios-container">

        <!-- Barra superior -->
        <header class="top-bar">
            <div class="procurar-container">
                <form action="/home/perfis" style="display: flex; align-items: center;">
                    <img src="../../assets/imgs/img-home/lupa.png" alt="Buscar" class="icon-acomp">
                    <input type="text" placeholder="Buscar Usuario" class="input-search" name="buscar">
                </form>
            </div>
        </header>
        <!-- -------------- -->

        <!-- Tabela -->
        <article class="table-container">

            <!-- Tabela mesmo -->
            <article class="table-users">
                <!-- Cabeçalho da tabela -->
                <div class="table-header">
                    <div>Placa</div>
                    <div>Nome</div>
                    <div>E-mail</div>
                    <div>Telefone</div>
                    <div>Frota</div>
                </div>

                <!-- Linhas da tabela -->
                <%if (! perfisMotoristas.isEmpty() || perfisMotoristas != null){%>
                <%for (Map<String, String> perfil : perfisMotoristas) {%>
                <div class="table-row">
                    <div class="table-left-row">
                        <div><%=perfil.get("placa")%></div>
                        <div><%=perfil.get("nome")%></div>
                        <div><%=perfil.get("email")%></div>
                        <div><%=perfil.get("telefone_principal")%></div>
                        <div><%=perfil.get("tipo_frota")%></div>
                    </div>
                </div>
                <%}%>
                <%}else {%>
                <div class="table-row">
                    <div class="table-left-row">
                        <div></div>
                        <div></div>
                        <div>Nenhum usuário foi encontrado</div>
                        <div></div>
                        <div></div>
                    </div>
                </div>
                <%}%>

            </article>

        </article>
        <!-- ------ -->
    </section>
</main>
</body>
<style>
</style>

</html>