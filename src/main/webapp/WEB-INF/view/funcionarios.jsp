<%@ page import="com.frotaviva.dao.MotoristaDAO" %>
<%@ page import="com.frotaviva.model.Motorista" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%@ page import="com.frotaviva.model.TelefoneMotorista" %>
<%@ page import="com.frotaviva.dao.TelefoneMotoristaDAO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
            <a href="home.jsp">
                <img src="../../assets/imgs/img-home/casa.png" alt="Pagina inicial">
                Página Inicial
            </a>

            <a href="funcionarios.jsp">
                <img src="../../assets/imgs/img-home/pessoa.png" alt="Funcionários">
                Funcionários
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

                        <div>ID</div>
                        <div>Nome</div>
                        <div>E-mail</div>
                        <div>CPF</div>
                        <div>Senha</div>
                        <div>Telefone</div>
                        <div>ID Empresa</div>
                        <div>Telefone</div>

                        <div>
                            <button class="add-user">+ Adicionar Usuário</button>
                        </div>
                    </div>

                    <%
                        List<Motorista> motoristas = (List<Motorista>) request.getAttribute("motoristas");
                        //List<TelefoneMotorista> telefones = (List<TelefoneMotorista>) request.getAttribute("telefones")
                        TelefoneMotoristaDAO teleDao = new TelefoneMotoristaDAO();

                        if (motoristas != null && !motoristas.isEmpty()){
                            for (Motorista m : motoristas) {
                                List<TelefoneMotorista> telefones = teleDao.buscarPorIdMotorista(m.getId());
                                if ( telefones == null || telefones.isEmpty()){
                    %>

                        <!-- Linhas da tabela -->
                        <div class="table-row">
                            <div class="table-left-row">
                                <div> <%= m.getId() %></div>
                                <div><%= m.getNome() %></div>
                                <div><%= m.getEmail() %></div>
                                <div><%= m.getCpf()%></div>
                                <div><%= m.getSenha()%></div>
                                <div><%= m.getIdEmpresa()%></div>
                                <div> Sem telefone</div>
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
                <% } else{ for(TelefoneMotorista t : telefones){
                %>
                    <div class="table-row">
                        <div class="table-left-row">
                            <div> <%= m.getId() %></div>
                            <div><%= m.getNome() %></div>
                            <div><%= m.getEmail() %></div>
                            <div><%= m.getCpf()%></div>
                            <div><%= m.getSenha()%></div>
                            <div><%= m.getIdEmpresa()%></div>
                            <div> <%= t.getTelefoneMotorista()%></div>
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
                    <%
                            }
                        }
                    %>
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

                <!-- ------------ -->

                <!-- Mudança de pagina (Barra inferior) -->
                <div class="inf-bar">
                    <button class="bt-prev">
                        <img src="../../assets/imgs/img-home/seta-esq-tabela.png" alt="Anterior" class="icon-in-table">
                        Anterior
                    </button>

                    <div class="paginas">
                        <button class="pag-active">1</button>
                        <button class="pag">2</button>
                        <button class="pag">3</button>
                        <button class="pag">4</button>
                        <button class="pag">5</button>
                        <span>...</span>
                    </div>

                    <button class="bt-prox">
                        Próxima
                        <img src="../../assets/imgs/img-home/seta-dir-tabela.png" alt="Próxima" class="icon-seta">
                    </button>
                </div>
            <!-- ------ -->
        </section>
    </main>
</body>
<style>
</style>
