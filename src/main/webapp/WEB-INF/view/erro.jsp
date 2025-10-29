<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String msg = request.getParameter("erro"); %>
<html>
<head>
    <title>Erro</title>
    <link rel="stylesheet" href="../../assets/CSS/homeStyle.css">
</head>
<body>
    <h1>Erro: <%=msg%></h1>
</body>
</html>
