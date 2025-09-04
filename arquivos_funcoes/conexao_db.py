# Função para trazer o banco de dados
def trazer_banco(host, port, user, password, database, nome_Tabela):
    import psycopg2
    import pandas as pd

    db_list = None # Inicia a lista que vai vir o banco
    nome_colunas = None # Inicializa os nomes das colunas

    try:
        # Realiza a conecção com as informações dadas
        conn = psycopg2.connect(
            host=host,
            database=database,
            user=user,
            password=password,
            port=port
        )

        df = pd.read_sql_query(f"SELECT * FROM {nome_Tabela.lower()}")
        cursor = conn.cursor() # Esse é quem permite executar coma  ndos SQL no banco
        # cursor.execute(f"SELECT * FROM {nome_Tabela.lower()}") # executa o comando escrito
        # db_list = cursor.fetchall() # Retorna dados (fetch ALL)
        nome_colunas = [desc[0] for desc in cursor.description]

        # Feche o cursor e a conexão no final
        cursor.close()
        conn.close()

    except psycopg2.Error as e:
        print("Erro ao se conectar: ", e)
    
    return pd.DataFrame(df, columns= nome_colunas)
        # (Traz em formato de Lista de Tuplas -> [(1,2),(2,3)])