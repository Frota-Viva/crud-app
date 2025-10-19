package com.frotaviva.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDAO {
    /*
     * Logger está sendo usado para melhor tratamento e rastreamento de exceções.
     * Ele registra mensagens de erro personalizadas com diagnóstico completo.
     */

    protected static final Logger log = LoggerFactory.getLogger(AbstractDAO.class);
}
