<%@ page import="com.frotaviva.model.Empresa" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Empresa empresa = (Empresa) request.getAttribute("empresa");%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Funcionários - Frota Viva</title>
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

            <a href="funcionarios.html">
                <img src="../../assets/imgs/img-home/pessoa.png" alt="Funcionários">
                Funcionários
            </a>
        </nav>
    </aside>

    <main>
        <section class="informacoes">
            <h1>Seja bem-vindo "<%=empresa.getNome()%>"!</h1>
            <section class="cards">
                <article class="card-info" id="card-frota">
                    <h2>Frota</h2>
                    <div class="infos">
                        <p>Total:<br><%= 0 %></p>
                        <p>Inativos:<br><%= 0 %></p>
                        <p>Manutenção:<br><%= 0 %></p>
                        <p>Ativos:<br><%= 0 %></p>
                    </div>
                    <canvas id="#"></canvas>

                    <div class="descricao-grafco">
                        <div>
                            <div class="cor-grafico cor1"></div>
                            <div>Ativos</div>
                        </div>
                        <div>
                            <div class="cor-grafico cor2"></div>
                            <div>Manutenção</div>
                        </div>
                        <div>
                            <div class="cor-grafico cor3"></div>
                            <div>inativos</div>
                        </div>
                    </div>
                </article>
                <article class="card-info" id="card-entregas">
                    <h2>Entregas</h2>
                    <div class="infos">
                        <p>Total:<br><%= 0 %></p>
                        <p>Pendentes:<br><%= 0 %></p>
                        <p>Realizadas:<br><%= 0 %></p>
                        <p>À Caminho:<br><%= 0 %></p>
                    </div>

                    <canvas id="#"></canvas>

                    <div class="descricao-grafco">
                        <div>
                            <div class="cor-grafico cor1"></div>
                            <div>Realizadas</div>
                        </div>
                        <div>
                            <div class="cor-grafico cor2"></div>
                            <div>À Caminho</div>
                        </div>
                        <div>
                            <div class="cor-grafico cor3"></div>
                            <div>Pendentes</div>
                        </div>
                    </div>
                </article>
                <article class="card-info" id="card-meta">
                    <h2>Meta de Entregas</h2>

                    <canvas id="#"></canvas>

                    <div class="descricao-grafco">
                        <div>Tempo restante:<br>
                            08 D - 12 H - 22 M
                        </div>
                    </div>
                </article>
            </section>
        </section>
    </main>
</body>

</html>