<%--Apenas TESTE--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Frota Viva</title>
    <link rel="stylesheet" href="../../assets/CSS/loginStyle.css">
</head>

<body>

<main>

    <section class="img-section">
        <img src="/assets/imgs/img-login/logo3.png" alt="" id="logo-login">
        <img src="/assets/imgs/img-login/divisor.png" alt="" id="meio">
    </section>

    <section class="login-section">
        <form action="login" method="post" class="login-form">
            <h1>Login</h1>

            <div>
                <label for="email">
                    Email
                    <input placeholder="Digite aqui..." type="email" id="email" name="email" class="input-forms"
                           required>
                </label>


                <label for="senha">
                    Senha
                    <input placeholder="Digite aqui..." type="password" id="senha" name="senha" class="input-forms"
                           required>
                </label>
                <%if (request.getAttribute("erro") != null){%>
                <span class="erro" style="color: red; font-size: medium"><%=request.getAttribute("erro")%></span>
                <%}%>
            </div>
            <hr>
            <button type="submit" class="botao-forms" id="botao1">Entrar</button>
            <button class="botao-forms" id="botao2" onclick="window.location.href='inicio.html'">Voltar</button>
        </form>
    </section>

</main>
</body>

</html>
