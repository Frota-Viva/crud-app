# -- FUNÇÕES PARA PSEUDONIMIZAR --
# NUMEROS
def psCPF(cpf):
    import re
    newCPF = None
    if re.match(r"^(\d{3}\.\d{3}\.\d{3}\-\d{2}|\d{11})", str(cpf)): # Identifica se realmente é um CPF
        newCPF = str(cpf).replace(cpf[0:3], '***') # Transforma so os 3 primeiros

    return newCPF
def psIdade(idade):
    idade = int(idade)
    if idade <= 25:
        return "18-30"
    elif idade <= 48:
        return "31-48"
    elif idade <= 60:
        return "49-60"
    else:
        return "60+"
def psTelefone(telefone):
    import re

    regiao = re.search(r"^(\(\d{2}\)|\d{2})", telefone).group() if re.search(r"^(\(\d{2}\)|\d{2})", telefone) else '' # Tenta pegar o numero da região
    telefone = str(telefone).replace(regiao, '').strip() # Tira o numero da região e espaços add
    newTelefone = f"({regiao.replace('(','').replace(')','')}) {telefone[0]}***{telefone[3:]}" # Transforma os 3 digitos depois do 1 do telefone

    return newTelefone
def psEndereco(endereco):
    import re

    cidade_estado = None # Inicializa para retornar alguma coisa
    try:
        cidade_estado = (re.search(r'\w+/[A-ZÀ-Ý]{2}', str(endereco))).group() # Procura por cidade e estado desse jeito -> Cidade/ES
    except AttributeError as e:
        print("Não tem")

    return cidade_estado
def psDAdim(data):
    import re
    newData = None # Inicializa para retornar alguma coisa
    if re.search(r'\d{4}(-|/)\d{2}(-|/)\d{2}', str(data)): # Procura pela data desse jeito -> yyyy-mm-dd
        newData = re.search(r"\d{4}", str(data)).group() # E pega apenas o ano
    return newData
def psSalario(salario):
    faixa = None

    if 1200 <= salario <= 2400:
        faixa = "1200 - 2400"
    elif salario <= 3200:
        faixa = "2400 - 3200"
    elif salario <= 5000:
        faixa = "3200 - 5000"
    elif salario > 5000:
        faixa = ">5000"
    
    return faixa
def psCPNJ(cnpj):
    import re

    newCNPJ = re.sub(r"[./-]", '', str(cnpj)) # Transforma so os 3 primeiros

    # 05.311.244/0001-09
    if re.match(r"^\d{14}", str(newCNPJ)): # Identifica se realmente é um CNPJ
        # newCNPJ = newCNPJ.replace(newCNPJ[2:5], '***')
        newCNPJ = f"{newCNPJ[:2]}.***.***/{newCNPJ[8:12]}-{newCNPJ[12:]}"

    return newCNPJ
def psCEP(cep):
    import re
    cep = re.sub(r"[-.]","",str(cep))
    if len(cep) == 8:
        cep = f"{cep[:3]}***-{cep[6:]}"
    else:
        cep = None
    return cep
# -------

# --------------------------------

# Função para pseudonimzar tabela
def pseudonimzar_tabela(tabela):

    import re

    pDados = {} # Inicializa um novo dict para os Dados Pseudominimizados

    # Percorre a tabela como um DICIONARIO, trazendo (chave, Valor)
    for titulo_coluna, coluna in tabela.items():
        quant_linhas = len(coluna) # Conta o tamanho do ultimo indice (deixa o pseudonimo de nome melhor)
        pColuna = {} # Inicializa uma nova coluna como dict 

    # Chama as funções com suas palavras chaves/combinação(regex) (nos nomes das colunas)
        func = {
            r"\bcpf\b":psCPF,
            r"\bidade\b":psIdade,
            r"\btelefone\b":psTelefone,
            r"\bendere(ço|co)\b":psEndereco,
            r"\badmiss(ao|ão)\b":psDAdim,
            r"\bsal(a|á)rio\b":psSalario,
            r"\bcnpj\b":psCPNJ,
            r"\bcep\b":psCEP
        }
        
#       PARTE QUE MODIFICA OS DADOS
        # --------------------------------------
        for id, valor in coluna.items(): # Percorre as colunas (que com pandas é outro dict)

            pValor = valor # Inicializa a variavel para colocar algo (no caso o valor original)
            # Agora percorre o dicionario das funções de pseudonimização
            for reg, fun in func.items(): 
                if re.search(reg, titulo_coluna.lower()): # ve se o nome da coluna é compativel com uma função para pseudonimizar
                    pValor = fun(valor)
                    break
            else:
                if type(valor) == str: 
                    pValor = f'{titulo_coluna}_' + (str(id+1).zfill(quant_linhas)) # Apenas para numerar e não mostrar os nomes originais
            
            # Coloca na coluna nova com o mesmo id da original
            pColuna[id] = pValor 
        # --------------------------------------

        pDados[titulo_coluna] = pColuna

    return pDados
# (Funciona com dicionarios com indicie (do pandas) ou DFs do pandas
