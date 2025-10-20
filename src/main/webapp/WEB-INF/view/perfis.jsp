<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Map<String, String>> perfisMotoristas = (List<Map<String, String>>) request.getAttribute("perfisMotoristas");
  int paginaAtual = (int) request.getAttribute("paginaAtual");
  int totalPaginas = 10;

  int maxBotoes = 5;
  int inicio, fim;

  if (totalPaginas <= maxBotoes) {
      inicio = 1;
      fim = totalPaginas;
  } else {
      inicio = Math.max(1, paginaAtual - 2);
      fim = Math.min(totalPaginas, inicio + maxBotoes - 1);
      inicio = Math.max(1, fim - maxBotoes + 1);
}
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina Inicial - Frota Viva</title>
    <link rel="stylesheet" href="../../assets/CSS/homeStyle.css">
</head>

<body>
    <aside class="menu-lateral">
        <img src="../../assets/imgs/img-home/logo2.png" alt="logo" class="logo">
        <nav>
            <a href="/home">
                <img src="../../assets/imgs/img-home/casa.png" alt="Pagina inicial">
                Página Inicial
            </a>

            <a href="home/perfis">
                <img src="../../assets/imgs/img-home/pessoa.png" alt="Perfis">
                Perfis
            </a>
        </nav>
    </aside>

    <main>
        <section class="funcionarios-container">

            <!-- Barra superior -->
            <header class="top-bar">
                <div class="procurar-container">
                    <img src="../../assets/imgs/img-home/lupa.png" alt="Buscar" class="icon-acomp">
                    <input type="text" placeholder="Buscar Usuario" class="input-search">
                </div>

                <div class="perfil-container">
                    <button class="bt-edit-perfil">
                        <img src="../../assets/imgs/img-home/caneta-edit.png" alt="Editar" class="icon-acomp">
                        Personalizar Perfil
                    </button>
                    <img src="../../assets/imgs/img-home/perfil.png" alt="Usuário" class="icon-user">
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
                        <div>
                            <button class="add-user">+ Adicionar Usuário</button>
                        </div>
                    </div>

                    <!-- Linhas da tabela -->
                    <%for (Map<String, String> perfil : perfisMotoristas) {%>
                    <div class="table-row">
                        <div class="table-left-row">
                            <div><%=perfil.get("placa")%></div>
                            <div><%=perfil.get("nome")%></div>
                            <div><%=perfil.get("email")%></div>
                            <div><%=perfil.get("telefone_principal")%></div>
                            <div><%=perfil.get("tipo_frota")%></div>
                        </div>
                        <div class="actions">
                            <button title="Visualizar"><img src="../../assets/imgs/img-home/olho.png" alt=""
                                    class="icon-eye"></button>
                            <button title="Editar"><img src="../../assets/imgs/img-home/editar.png" alt=""
                                    class="icon-edit"></button>
                            <button title="Excluir"><img src="../../assets/imgs/img-home/lixo.png" alt=""
                                    class="icon-trash"></button>
                        </div>
                    </div>
                    <%}%>

                </article>
                <!-- ------------ -->

                <!-- Mudança de pagina (Barra inferior) -->
                <div class="inf-bar">
                    <%if (paginaAtual > 1){%>
                    <button class="bt-prev" onclick="window.location.href='/home/perfis?pagina=<%=paginaAtual - 1%>'">
                        <img src="../../assets/imgs/img-home/seta-esq-tabela.png" alt="Anterior" class="icon-in-table">
                        Anterior
                    </button>
                    <%}%>

                    <div class="paginas">
                        <% for (int i = inicio; i <= fim; i++) { %>
                        <button class="<%= (i == paginaAtual) ? "pag-active" : "pag" %>">
                            <%= i %>
                        </button>
                        <% } %>
                        <% if (fim < totalPaginas) { %>
                        <span>...</span>
                        <% } %>
                    </div>

                    <%if (paginaAtual < totalPaginas){%>
                    <button class="bt-prox" onclick="window.location.href='/home/perfis?pagina=<%=paginaAtual + 1%>'">
                        Próxima
                        <img src="../../assets/imgs/img-home/seta-dir-tabela.png" alt="Próxima" class="icon-seta">
                    </button>
                    <%} else {%>
                    <div></div>
                    <%}%>
                </div>
                <!-- ------------------ -->

            </article>
            <!-- ------ -->
        </section>
    </main>
</body>
<style>
</style>

</html>