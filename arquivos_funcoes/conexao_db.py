# Função para trazer o banco de dados
def trazer_banco(nome_Tabela):
    import psycopg2
    import pandas as pd
    from dotenv import load_dotenv
    import os

    try:
        # Realiza a conecção com as informações dadas
        load_dotenv()
        conn = psycopg2.connect(
            # ----- INFORMAÇÕES PARA ACESSAR O DB -----
            host = os.getenv('HOST'),
            database = os.getenv('DATABASE'),
            user = os.getenv('USER'),
            password = os.getenv('PASSWORD'),
            port = int(os.getenv('PORT')),
            # -----------------------------------------
        )

        df = pd.read_sql_query(f"SELECT * FROM {nome_Tabela.lower()}", conn)
        conn.close()

        return pd.DataFrame(df)
    except psycopg2.Error as e:
        print("Erro ao se conectar: ", e)
        return None
# (Traz em formato de Lista de Tuplas -> [(1,2),(2,3)])