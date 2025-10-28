<%--
  Created by IntelliJ IDEA.
  User: lucasporto-ieg
  Date: 25/10/2025
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.frotaviva.model.Empresa" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%Object reqEmpresa = request.getAttribute("empresa");
Empresa empresa = (Empresa) reqEmpresa;%>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Frota Viva - Perfil</title>
    <link rel="stylesheet" href="../../assets/CSS/perfilStyle.css?<%=System.currentTimeMillis()%>">
</head>
<body>
  <aside class="menu-lateral">
        <img src="../../assets/imgs/img-home/logo2.png" alt="Frota Viva" id="logo">
        <nav>
            <a href="/home">
                <img src="../../assets/imgs/img-home/casa.png" alt="Pagina Inicial" class="icones-aside">
                Pagina Inicial
            </a>
            <a href="/home/perfis">
                <img src="../../assets/icons/icon-perfis.svg" alt="" class="icones-aside">
                Perfis
            </a>
            <a href="manutencoes.html">
                <img src="../../assets/icons/icon-manutencao.svg" alt="Manutenções" class="icones-aside">
                Manutenções
            </a>

            <a href="caminhoes.html">
                <img src="../../assets/icons/icon-caminhao.svg" alt="Caminhões" class="icones-aside">
                Caminhões
            </a>

            <a href="frota.html">
                <img src="../../assets/icons/icon-frota.svg" alt="Frota" class="icones-aside">
                Frota
            </a>

            <a href="motorista.html">
                <img src="../../assets/icons/icon-motorista.svg" alt="Motorista" class="icones-aside">
                Motorista
            </a>

            <a href="entregas.html">
                <img src="../../assets/icons/icon-entregas.svg" alt="Entregas" class="icones-aside">
                Entregas
            </a>
        </nav>

        <a href="/home/perfil">
            <div class="perfil-menu">
                <img src="../../assets/icons/icon-perfil-empresa.svg" alt="Perfil" class="icon-perfil">
                Perfil
            </div>
        </a>

    </aside>
    <main>
        <section id="infos-inicial">
            <img src="../../assets/icons/icon-perfil-empresa.svg" alt="" id="perfil-main">
            <div>
                <h1><%=empresa.getNome()%></h1>
                <h2><%=empresa.getTipoEmpresa()%></h2>
            </div>
        </section>
        <form action="editar-perfil">
            <article id="edicoes">
                <section class="conjunto-input">
                    <div class="div-inputs">
                        <label for="nome">Nome:</label>
                        <input type="text" value="<%=empresa.getNome()%>" id="nome" name="nome" required>
                    </div>
                    <div class="div-inputs">
                        <label for="email">Email:</label>
                        <input type="email" value="<%=empresa.getEmail()%>" id="email" name="email" required>
                        <%if (request.getAttribute("erroEmail") != null){;%>
                        <span class="erro"><%=request.getAttribute("erroEmail")%></span>
                        <%}%>

                    </div>
                    <div class="div-inputs">
                        <label for="tipo">Tipo:</label>
                        <input type="text" value="<%=empresa.getTipoEmpresa()%>" id="tipo" name="tipo" required>
                    </div>
                </section>
                <section class="conjunto-input">
                    <div class="div-inputs">
                        <label for="cep">CEP:</label>
                        <input type="text" value="<%=empresa.getEndereco().getCep()%>" id="cep" name="cep" required>
                        <%if(request.getAttribute("erroCep")!=null){%>
                        <span class="erro"><%=request.getAttribute("erroCep")%></span>
                        <%}%>
                    </div>
                    <div class="div-inputs">
                        <label for="rua">Rua:</label>
                        <input type="text" value="<%=empresa.getEndereco().getRua()%>" id="rua" name="rua" required>
                    </div>
                    <div class="div-inputs">
                        <label for="complemento">Complemento:</label>
                        <input type="text" value="<%=empresa.getEndereco().getComplemento()%>" id="complemento" name="complemento">
                    </div>
                </section>
                <section class="conjunto-input">
                    <div class="div-inputs">
                        <label for="pais">País:</label>
                        <input type="text" value="<%=empresa.getEndereco().getPais()%>" id="pais" name="pais" required>
                    </div>
                    <div class="div-inputs">
                        <label for="cidade">Cidade:</label>
                        <input type="text" value="<%=empresa.getEndereco().getCidade()%>" id="cidade" name="cidade" required>
                    </div>
                    <div class="div-inputs">
                        <label for="estado">Estado:</label>
                        <input type="text" value="<%=empresa.getEndereco().getEstado()%>" id="estado" name="estado" required>
                    </div>
                </section>
                <section class="conjunto-input">
                    <div class="div-inputs">
                        <label for="numero">Número:</label>
                        <input type="number" value="<%=empresa.getEndereco().getNumero()%>" id="numero" name="numero" required min="1">
                        <%if(request.getAttribute("erroNumero")!=null){%>
                        <span class="erro"><%= request.getAttribute("erroNumero") %></span>
                        <%}%>
                    </div>
                </section>
            </article>
            <section id="ultima-linha">
                <div id="caixa-alterarSenha">
                    <a>Alterar senha</a>
                </div>
                    <% if (request.getAttribute("erroIgualdade") != null) { %>
                    <div class="erroCaixa">
                        <span><%= request.getAttribute("erroIgualdade") %></span>
                    </div>
                    <% } else if (request.getAttribute("realizado") != null) { %>
                    <div id="realizado">
                        <span><%= request.getAttribute("realizado") %></span>
                    </div>
                    <% } else if (request.getAttribute("erroVazio") != null) { %>
                    <div class="erroCaixa">
                        <span><%= request.getAttribute("erroVazio") %></span>
                    </div>
                    <% } %>

                </div>
                <section id="botoes">
                    <a href="/home">
                        <button id="button-cancelar" type="button">
                            <img src="../../assets/icons/icon-cancelar.svg" alt="" class="icones-botao">
                            <p><b>Cancelar</b></p>
                        </button>
                    </a>

                    <button id="button-confirmar" type="submit">
                        <img src="../../assets/icons/icon-confirmar.svg" alt="" class="icones-botao">
                        <p><b>Confirmar</b></p>
                    </button>
                </section>
            </section>
        </form>
    </main>
</body>
</html>
