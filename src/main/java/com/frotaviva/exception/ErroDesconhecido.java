package com.frotaviva.exception;

import com.frotaviva.dao.AbstractDAO;

public class ErroDesconhecido extends DAOException {

    public static final String MENSAGEM = "Erro desconhecido ao realizar ";

    public ErroDesconhecido(AbstractDAO.Operacao operacao, String entidade, Throwable cause) {
        super(MENSAGEM + operacao + " na tabela " + entidade, cause);
    }
}
