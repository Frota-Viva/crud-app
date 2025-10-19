package com.frotaviva.exception;

public class ErroAoConectar extends DAOException {

    public static final String MENSAGEM = "Erro na conexão com o banco de dados. ";

    public ErroAoConectar(String message) {
        super(MENSAGEM + message);
    }

    public ErroAoConectar(String message, Throwable cause) {
        super(MENSAGEM + message, cause);
    }

    public ErroAoConectar(Throwable cause) {
        super(MENSAGEM + cause);
    }
}
