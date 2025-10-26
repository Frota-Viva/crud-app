<%--
  Created by IntelliJ IDEA.
  User: lucasporto-ieg
  Date: 25/10/2025
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Frota Viva - Perfil</title>
  <link rel="stylesheet" href="../../assets/CSS/perfilStyle.css">
</head>
<body>
  <aside class="menu-lateral">
        <img src="../../assets/imgs/img-home/logo2.png" alt="Frota Viva" id="logo">
        <nav>
            <a href="home.html">
                <img src="../../assets/imgs/img-home/casa.png" alt="Pagina Inicial" class="icones-aside">
                Pagina Inicial
            </a>
            <a href="">
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

        <a href="perfil.jsp">
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
                <h1>Teixeira Macedo - ME</h1>
                <h2>Transportadora</h2>
            </div>
        </section>
        <form action="editar-perfil">
            <article id="edicoes">
                <section class="conjunto-input">
                    <div class="div-inputs">
                        <label for="email">Email:</label>
                        <input type="text" value="" id="email" name="email" required>
                    </div>
                    <div class="div-inputs">
                        <label for="telefone">Telefone:</label>
                        <input type="text" value="" id="telefone" name="telefone" required>
                    </div>
                    <div class="div-inputs">
                        <label for="cnpj">CNPJ:</label>
                        <input type="text" value="" id="cnpj" name="cnpj" required>
                    </div>
                </section>
                <section class="conjunto-input">
                    <div class="div-inputs">
                        <label for="cep">CEP:</label>
                        <input type="text" value="" id="cep" name="cep" required>
                    </div>
                    <div class="div-inputs">
                        <label for="rua">Rua:</label>
                        <input type="text" value="" id="rua" name="rua" required>
                    </div>
                    <div class="div-inputs">
                        <label for="complemento">Complemento:</label>
                        <input type="text" value="" id="complemento" name="complemento">
                    </div>
                </section>
                <section class="conjunto-input">
                    <div class="div-inputs">
                        <label for="pais">País:</label>
                        <input type="text" value="" id="pais" name="pais" required>
                    </div>
                    <div class="div-inputs">
                        <label for="cidade">Cidade:</label>
                        <input type="text" value="" id="cidade" name="cidade" required>
                    </div>
                    <div class="div-inputs">
                        <label for="estado">Estado:</label>
                        <input type="text" value="" id="estado" name="estado" required>
                    </div>
                </section>
                <section class="conjunto-input">
                    <div class="div-inputs">
                        <label for="numero">Número:</label>
                        <input type="text" value="" id="numero" name="numero" required>
                    </div>
                    <div class="div-inputs">
                        <label for="nome">Nome:</label>
                        <input type="text" value="" id="nome" name="nome" required>
                    </div>
                    <div class="div-inputs">
                        <label for="tipo">Tipo:</label>
                        <input type="text" value="" id="tipo" name="tipo" required>
                    </div>
                </section>
            </article>
            <section id="ultima-linha">
                <div class="div-inputs">
                    <label for="senha">Senha:</label>
                    <input type="text" value="" id="senha" name="senha" required>
                </div>
                <section id="botoes">
                    <button id="button-cancelar" type="button">
                        <img src="../../assets/icons/icon-cancelar.svg" alt="" class="icones-botao">
                        <p><b>Cancelar</b></p>
                    </button>
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
