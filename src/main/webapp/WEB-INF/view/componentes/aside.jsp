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
        <a href="/listar-caminhao">
            <img src="../../assets/icons/icon-manutencao.svg" alt="Manutenções" class="icones-aside">
            Manutenções
        </a>

        <a href="/listar-caminhao">
            <img src="../../assets/icons/icon-caminhao.svg" alt="Caminhões" class="icones-aside">
            Caminhões
        </a>

        <a href="/listar-frota">
            <img src="../../assets/icons/icon-frota.svg" alt="Frota" class="icones-aside">
            Frota
        </a>

        <a href="/listar-motoristas">
            <img src="../../assets/icons/icon-motorista.svg" alt="Motorista" class="icones-aside">
            Motoristas
        </a>

        <a href="/listar-entregas">
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
