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
    <img src="../../assets/imgs/img-login/logo3.png" alt="" id="logo-login">
    <img src="../../assets/imgs/img-login/divisor.png" alt="" id="meio">
  </section>

  <section class="login-section">
    <form action="cadastro" method="post" class="cadastro-forms">
      <h1>Cadastro da Empresa</h1>

      <div>
        <label for="nome">
          Nome da empresa (*):
          <input placeholder="Digite aqui..." type="text" id="nome" name="nome" class="input-forms"
                 required>
        </label>

        <label for="tipoEmpresa">
          Tipo da empresa (*):
          <input type="text" name="tipoEmpresa" placeholder="Digite aqui..." id="tipoEmpresa" class="input-forms"
                 required>
        </label>

        <label for="telefone">
          Telefone (*):
          <input placeholder="Digite aqui..." type="tel" id="telefone" name="telefone" class="input-forms"
                 required>
          <%if (request.getAttribute("erroTelefone") != null){%>
          <span class="erro" style="color: red; font-size: medium"><%=request.getAttribute("erroTelefone")%></span>
          <%}%>
        </label>

        <label for="email">
          Email (*):
          <input placeholder="Digite aqui..." type="email" id="email" name="email" class="input-forms"
                 required>
          <%if (request.getAttribute("erroEmail") != null){%>
          <span class="erro" style="color: red; font-size: medium"><%=request.getAttribute("erroEmail")%></span>
          <%}%>
        </label>

        <label for="cnpj">
          CNPJ (*):
          <input placeholder="Digite aqui..." type="text" id="cnpj" name="cnpj" class="input-forms"
                 required>
          <%if (request.getAttribute("erroCnpj") != null){%>
          <span class="erro" style="color: red; font-size: medium"><%=request.getAttribute("erroCnpj")%></span>
          <%}%>
        </label>

        <label for="cep">
          CEP (*):
          <input placeholder="Digite aqui..." type="text" id="cep" name="cep" class="input-forms"
                 required>
          <%if (request.getAttribute("erroCep") != null){%>
          <span class="erro" style="color: red; font-size: medium"><%=request.getAttribute("erroCep")%></span>
          <%}%>
        </label>

        <label for="pais">
          Pais (*):
          <input placeholder="Digite aqui..." type="text" id="pais" name="pais" class="input-forms"
                 required>

        </label>

        <label for="estado">
          Estado (*):
          <input placeholder="Digite aqui..." type="text" id="estado" name="estado" class="input-forms"
                 required>
        </label>

        <label for="cidade">
          Cidade (*):
          <input placeholder="Digite aqui..." type="text" id="cidade" name="cidade" class="input-forms"
                 required>
        </label>

        <label for="rua">
          Rua (*):
          <input placeholder="Digite aqui..." type="text" id="rua" name="rua" class="input-forms"
                 required>
        </label>

        <label for="numero">
          Numero (*):
          <input placeholder="Digite aqui..." type="text" id="numero" name="numero" class="input-forms"
                 required>
          <%if (request.getAttribute("erroNumero") != null){%>
          <span class="erro" style="color: red; font-size: medium"><%=request.getAttribute("erroNumero")%></span>
          <%}%>
        </label>

        <label for="complemento">
          Complemento:
          <input placeholder="Digite aqui..." type="text" id="complemento" name="complemento" class="input-forms">

        </label>

        <label for="senha">
          Senha (*):
          <input placeholder="Digite aqui..." type="password" id="senha" name="senha" class="input-forms"
                 required>
          <%if (request.getAttribute("erroSenha") != null){%>
          <span class="erro" style="color: red; font-size: medium"><%=request.getAttribute("erroSenha")%></span>
          <%}%>
        </label>
      </div>
        <%if (request.getAttribute("dadoFaltando") != null){%>
        <span class="erro" style="color: red; font-size: medium"><%=request.getAttribute("dadoFaltando")%></span>
        <%}%>

      <!-- Enviando / voltando -->
      <hr>
      <button type="submit" class="botao-forms" id="botao1">Enviar</button>
    </form>
    <a href="inicio"><button class="botao-forms" id="botao2">Voltar</button></a>
  </section>

</main>
</body>

</html>