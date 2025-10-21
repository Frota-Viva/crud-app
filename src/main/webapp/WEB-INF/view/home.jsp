<%@ page import="com.frotaviva.model.InformacoesHome" %>
<%@ page import="com.frotaviva.model.Empresa" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object reqInformacoesHome = request.getAttribute("informacoesHome");
    InformacoesHome informacoesHome = null;
    Empresa empresa = (Empresa) request.getAttribute("empresa");
    if (reqInformacoesHome != null){
        informacoesHome = (InformacoesHome) reqInformacoesHome;
    }
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Funcionários - Frota Viva</title>
    <link rel="stylesheet" href="../../assets/CSS/homeStyle.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="../../assets/JS/graficos.js"></script>
</head>

<body>
<aside class="menu-lateral">
    <img src="../../assets/imgs/img-home/logo2.png" alt="logo" class="logo">
    <nav>
        <a href="home">
            <img src="../../assets/imgs/img-home/casa.png" alt="Pagina inicial">
            Página Inicial
        </a>

        <a href="home/perfis">
            <img src="../../assets/imgs/img-home/pessoa.png" alt="Funcionários">
            Perfis
        </a>
    </nav>
</aside>

<main>
    <section class="informacoes">
        <h1>Seja bem-vindo "<%=informacoesHome != null ? informacoesHome.getNome() : empresa.getNome()%>"!</h1>
        <section class="cards">
            <article class="card-info" id="card-frota">
                <h2>Caminhões</h2>
                <div class="infos">
                    <p id="totalCaminhoes">Total:<br><%= informacoesHome != null ? informacoesHome.getTamanhoFrota() : 0%></p>
                    <p id="inativosCaminhoes">Inativos:<br><%= informacoesHome != null ? informacoesHome.getInativos() : 0 %></p>
                    <p id="manutencaoCaminhoes">Manutenção:<br><%= informacoesHome != null ? informacoesHome.getManutecao() : 0 %></p>
                    <p id="ativosCaminhoes">Ativos:<br><%= informacoesHome != null ? informacoesHome.getAtivos() : 0 %></p>
                </div>
                <canvas id="graficoCaminhoes"></canvas>

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
                </div>
            </article>
            <article class="card-info" id="card-entregas">
                <h2>Entregas</h2>
                <div class="infos">
                    <p id="totalEntregas">Total:<br><%= informacoesHome != null ? informacoesHome.getQtEntrega() : 0 %></p>
                    <p id="aCaminhoEntregas">À Caminho:<br><%= informacoesHome != null ? informacoesHome.getPendente() : 0 %></p>
                    <p id="realizadasEntregas">Realizadas:<br><%= informacoesHome != null ? informacoesHome.getRealizada() : 0%></p>
                </div>

                <canvas id="graficoEntregas"></canvas>

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
            <article class="card-info" id="card-manu">
                <h2>Manutenção</h2>
                <p>Tipos</p>
                <section class="infos-manu">
                    <canvas class="graficos-manu"></canvas>
                    <div id="infos-tipo-manu">
                        <div class="descricao-grafico-manu">
                            <div class="cor-grafico cor3 cor-manu"></div>
                            <p>Corretivas: <%= informacoesHome != null?informacoesHome.getCorretivas():0%></p>
                        </div>
                        <div class="descricao-grafico-manu">
                            <div class="cor-grafico cor1 cor-manu"></div>
                            <p>Preventivas: <%= informacoesHome != null?informacoesHome.getPreventivas():0%></p>
                        </div>
                        <p>Total: <%= informacoesHome != null?informacoesHome.getCorretivas()+ informacoesHome.getPreventivas():0%></p>

                    </div>
                </section>
                <p>Custos</p>
                <section class="infos-manu">
                    <canvas class="graficos-manu"></canvas>
                    <div id="infos-custos-manu">
                        <div class="descricao-grafico-manu">
                            <div class="cor-grafico cor3 cor-manu-custos"></div>
                            <p>Corretivas:<br>R$<%= informacoesHome != null?informacoesHome.getCustoCorretivas():0%></p>
                        </div>
                        <div class="descricao-grafico-manu">
                            <div class="cor-grafico cor1 cor-manu-custos"></div>
                            <p>Preventivas:<br>R$<%= informacoesHome != null?informacoesHome.getCustoPreventivas():0%></p>
                        </div>
                        <p>Total: R$<%= informacoesHome != null?informacoesHome.getCustoCorretivas()+informacoesHome.getCustoPreventivas():0%></p>

                    </div>
                </section>
            </article>
        </section>
    </section>
</main>
</body>

</html>