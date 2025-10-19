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
            <form action="cadastro.jsp" method="post" class="cadastro-forms">
                <h1>Cadastro da Empresa</h1>

                <div>
                    <label for="nome-empresa">
                        Nome da empresa
                        <input placeholder="Digite aqui..." type="text" id="nome-empresa" name="nome-empresa" class="input-forms" 
                        required>
                    </label>

                    <label for="telefone">
                        Telefone
                        <input placeholder="Digite aqui..." type="tel" id="telefone" name="telefone" class="input-forms" 
                        required>
                    </label>
                    

                    <label for="email">
                        Email
                        <input placeholder="Digite aqui..." type="email" id="email" name="email" class="input-forms"
                        required>
                    </label>
                    

                    <label for="cnpj">
                        CNPJ
                        <input placeholder="Digite aqui..." type="text" id="cnpj" name="cnpj" class="input-forms"
                        required>
                    </label>
                    
                    <label for="endereco">
                        Endereço
                        <input placeholder="Digite aqui..." type="text" id="endereco" name="endereco" class="input-forms"
                        required>
                    </label>

                    <label for="password">
                        Senha
                        <input placeholder="Digite aqui..." type="password" id="password" name="password" class="input-forms" 
                        required>
                    </label>
                    
                </div>

                <!-- Enviando / voltando -->
                <hr>
                <button type="submit" class="botao-forms" id="botao1">Enviar</button>
                <a href="../../inicio.html"><button class="botao-forms" id="botao2">Voltar</button></a>
            </form>
        </section>

    </main>
</body>

</html>