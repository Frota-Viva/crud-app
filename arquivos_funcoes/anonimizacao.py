# Função para anonimizar elemento
def anonimzar_elemento(elemento):
    aValor = '*' * len(str(elemento))
    return aValor
# anonimiza O ELEMENTO. Sem indice, sem nada. cruo.

# Função para anonimizar tabela
def anonimzar_tabela(tabela, colunas_visiveis):
    aDados = {} 

    for titulo_coluna, coluna in tabela.items(): 
        aColuna = {} # Inicializa uma nova coluna como dict 

#       PARTE QUE MODIFICA OS DADOS
        # --------------------------------------
        for id, valor in coluna.items(): # Percorre as colunas (que com pandas é outro dict)
            aValor = '*' * len(str(valor)) # Anonimiza com '*'

            aColuna[id] = aValor # Coloca na coluna nova com o mesmo id da original
        # --------------------------------------

        # Coloca o mesmo nome/titulo antigo de cada coluna porem com os dados anonimizados
        aDados[titulo_coluna] = aColuna if titulo_coluna not in colunas_visiveis else coluna

    return aDados # Por fim, devolve anonimizado em formato de dict
# (Funciona com dicionarios com indicie (do pandas) ou DFs do pandas)