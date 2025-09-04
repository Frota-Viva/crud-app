from arquivos_funcoes import anonimizacao as an, conexao_db as cnx, pseudonimizacao as ps
import pandas as pd
from dotenv import load_dotenv 
import os

# ----- INFORMAÇÕES PARA ACESSAR O DB -----
load_dotenv()
host = os.getenv('HOST')
database = os.getenv('DATABASE')
user = os.getenv('USER')
password = os.getenv('PASSWORD')
port = int(os.getenv('PORT'))
# -----------------------------------------

# Preparando os dados
df_database_SQL = cnx.trazer_banco(host, port, user, password, database, "empresa") # Traz lista de tuplas com os dados.

# Anonimiza e salva em .csv
df_anonima = pd.DataFrame(an.anonimzar_tabela(df_database_SQL, [])) # Transforma em um novo dataframe
df_anonima.to_csv('arquivosCSV/dados-anonimizados.csv',sep=';' ,index=False) # Salva em csv separado por ';' e considerando estar vazio

# Pseudonimiza e salva em .csv
df_pseudonima = pd.DataFrame(ps.pseudonimzar_tabela(df_database_SQL))
# print(df_pseudonima)
df_pseudonima.to_csv('arquivosCSV/dados-pseudonimizados.csv',sep=';' ,index=False)