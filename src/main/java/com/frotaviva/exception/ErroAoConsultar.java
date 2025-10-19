package com.frotaviva.exception;

public class ErroAoConsultar extends DAOException {

    public static final String MENSAGEM = "Erro ao consultar ";

    public ErroAoConsultar(String message, String entidade) {
        super(MENSAGEM + entidade + " " + message);
    }

    public ErroAoConsultar(String message, Throwable cause, String entidade) {
        super(MENSAGEM + entidade + " " + message + cause);
    }

    public ErroAoConsultar(Throwable cause, String entidade) {
        super(MENSAGEM + entidade + " " + cause);
    }
}
