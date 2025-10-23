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
    <link rel="stylesheet" href="../../assets/CSS/homeStyle.css?<%=System.currentTimeMillis()%>">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="../../assets/JS/graficos.js?<%=System.currentTimeMillis()%>"></script>
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
                    <p>Total:<br><span id="totalCaminhoes"><%= informacoesHome != null ? informacoesHome.getTamanhoFrota() : 0 %></span></p>
                    <p>Inativos:<br><span id="inativosCaminhoes"><%= informacoesHome != null ? informacoesHome.getInativos() : 0 %></span></p>
                    <p>Manutenção:<br><span id="manutencaoCaminhoes"><%= informacoesHome != null ? informacoesHome.getManutecao() : 0 %></span></p>
                    <p>Ativos:<br><span id="ativosCaminhoes"><%= informacoesHome != null ? informacoesHome.getAtivos() : 0 %></span></p>

                </div>
                <canvas id="graficoCaminhoes"></canvas>

<%--                <div class="descricao-grafco">--%>
<%--                    <div>--%>
<%--                        <div class="cor-grafico cor1"></div>--%>
<%--                        <div>Ativos</div>--%>
<%--                    </div>--%>
<%--                    <div>--%>
<%--                        <div class="cor-grafico cor2"></div>--%>
<%--                        <div>Manutenção</div>--%>
<%--                    </div>--%>
<%--                    <div>--%>
<%--                        <div class="cor-grafico cor3"></div>--%>
<%--                        <div>inativos</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
            </article>
            <article class="card-info" id="card-entregas">
                <h2>Entregas</h2>
                <div class="infos">
                    <p>Total:<br><span id="totalEntregas"><%= informacoesHome != null ? informacoesHome.getQtEntrega() : 0 %></span></p>
                    <p>À Caminho:<br><span id="aCaminhoEntregas"><%= informacoesHome != null ? informacoesHome.getPendente() : 0 %></span></p>
                    <p>Realizadas:<br><span id="realizadasEntregas"><%= informacoesHome != null ? informacoesHome.getRealizada() : 0 %></span></p>
                </div>


                <canvas id="graficoEntregas"></canvas>

<%--                <div class="descricao-grafco">--%>
<%--                    <div>--%>
<%--                        <div class="cor-grafico cor1"></div>--%>
<%--                        <div>Realizadas</div>--%>
<%--                    </div>--%>
<%--                    <div>--%>
<%--                        <div class="cor-grafico cor2"></div>--%>
<%--                        <div>À Caminho</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
            </article>
            <article class="card-info" id="card-manu">
                <h2>Manutenção</h2>
                <div class="infos">
                    <p>Total: R$<span id="totalPrecoManutencao"><%= informacoesHome != null?informacoesHome.getCustoCorretivas()+informacoesHome.getCustoPreventivas(): 0.0%></span></p>
                    <p>Corretivas:<br>R$<span id="corretivasPrecoManutencao"><%= informacoesHome != null?informacoesHome.getCustoCorretivas():0.0%></span></p>
                    <p>Preventivas:<br>R$<span id="preventivasPrecoManutencao"><%= informacoesHome != null?informacoesHome.getCustoPreventivas():0.0%></span></p>
                </div>
                <canvas id="graficoPrecosManutencoes"></canvas>
            </article>
        </section>
    </section>
</main>
</body>

</html>