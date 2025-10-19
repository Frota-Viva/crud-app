package com.frotaviva.exception;

public class ErroAoAtualizar extends DAOException {

    public static final String MENSAGEM = "Erro ao atualizar ";

    public ErroAoAtualizar(String message, String entidade) {
        super(MENSAGEM + entidade + " " + message);
    }

    public ErroAoAtualizar(String message, Throwable cause, String entidade) {
        super(MENSAGEM + entidade + " " + message + cause);
    }

    public ErroAoAtualizar(Throwable cause, String entidade) {
        super(MENSAGEM + entidade + " " + cause);
    }
}
