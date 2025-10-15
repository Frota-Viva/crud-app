package com.frotaviva.dao;

import java.util.List;

public interface DAO<T> {
    int inserir(T entidade);
    int deletar(long id);
    List<T> buscarTodos();
    T buscarPorId(long id);
}
