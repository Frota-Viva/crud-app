<%--
  Created by IntelliJ IDEA.
  User: ricardojunior-ieg
  Date: 27/10/2025
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="../../../assets/CSS/aside.css?<%=System.currentTimeMillis()%>">
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
</body>
</html>
