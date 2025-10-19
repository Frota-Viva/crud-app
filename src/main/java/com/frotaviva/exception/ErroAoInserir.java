package com.frotaviva.exception;

public class ErroAoInserir extends DAOException {

    public static final String MENSAGEM = "Erro ao inserir ";

    public ErroAoInserir(String message, String entidade) {
        super(MENSAGEM + entidade + " " + message);
    }

    public ErroAoInserir(String message, Throwable cause, String entidade) {
        super(MENSAGEM + entidade + " " + message + cause);
    }

    public ErroAoInserir(Throwable cause, String entidade) {
        super(MENSAGEM + entidade + " " + cause);
    }
}
