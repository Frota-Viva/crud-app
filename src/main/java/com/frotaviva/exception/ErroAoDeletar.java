package com.frotaviva.exception;

public class ErroAoDeletar extends DAOException {

    public static final String MENSAGEM = "Erro ao deletar ";

    public ErroAoDeletar(String message, String entidade) {
        super(MENSAGEM + entidade + " " + message);
    }

    public ErroAoDeletar(String message, Throwable cause, String entidade) {
        super(MENSAGEM + entidade + " " + message + cause);
    }

    public ErroAoDeletar(Throwable cause, String entidade) {
        super(MENSAGEM + entidade + " " + cause);
    }
}
