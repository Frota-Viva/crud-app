<%--Apenas TESTE--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Página de Login</title>
</head>
<body>
<h2>Login</h2>
<form action="login" method="post">
    <label for="email">Digite o email da empresa:</label> <br>
    <input type="text" id="email" name="usuario" placeholder="email@dominio.com" required><br><br>

    <label for="cnpj">Digite o CNPJF da empresa:</label> <br>
    <input type="text" id="cnpj" name="cnpj" placeholder="12.345.678/0001-11" required> <br><br>

    <label for="senha">Digite a senha da empresa:</label> <br>
    <input type="password" id="senha" name="senha" placeholder="*********" required><br><br>

    <button type="submit">Entrar</button>
</form>
</body>
</html>
