<%@ page import="com.frotaviva.dao.MotoristaDAO" %>
<%@ page import="com.frotaviva.model.Motorista" %>
<%@ page import="java.io.PrintWriter" %>
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

                        <%
                            PrintWriter writer = null;
                            MotoristaDAO dao = new MotoristaDAO();

                            for(Motorista m : dao.buscarTodos()){
                                writer.println("<div>" + m.getId() + "</div>");
                                writer.println("<div>" + m.getNome() + "</div>");
                                writer.println("<div>" + m.getEmail() + "</div>");
                                writer.println("<div>" + m.getCpf() + "</div>");
                                writer.println("<div>" + m.getSenha() + "</div>");
                                writer.println("<div>" + m.getIdEmpresa() + "</div>");
                            }
                        %>

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
                    <div class="table-row">
                        <div class="table-left-row">
                            <div>BEE 4R22</div>
                            <div>Mauricio</div>
                            <div>Maur1c10@gmail.com</div>
                            <div>11 98765-0476</div>
                            <div>NO-CARBON</div>
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

                    <div class="table-row">
                        <div class="table-left-row">
                            <div>BEE 4R22</div>
                            <div>Mauricio</div>
                            <div>Maur1c10@gmail.com</div>
                            <div>11 98765-0476</div>
                            <div>NO-CARBON</div>
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

                    <div class="table-row">
                        <div class="table-left-row">
                            <div>BEE 4R22</div>
                            <div>Mauricio</div>
                            <div>Maur1c10@gmail.com</div>
                            <div>11 98765-0476</div>
                            <div>NO-CARBON</div>
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

                    <div class="table-row">
                        <div class="table-left-row">
                            <div>BEE 4R22</div>
                            <div>Mauricio</div>
                            <div>Maur1c10@gmail.com</div>
                            <div>11 98765-0476</div>
                            <div>NO-CARBON</div>
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

                    <div class="table-row">
                        <div class="table-left-row">
                            <div>BEE 4R22</div>
                            <div>Mauricio</div>
                            <div>Maur1c10@gmail.com</div>
                            <div>11 98765-0476</div>
                            <div>NO-CARBON</div>
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
                </article>
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
                <!-- ------------------ -->

            </article>
            <!-- ------ -->
        </section>
    </main>
</body>
<style>
</style>
