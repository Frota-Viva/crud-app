package com.frotaviva.dao;

import java.util.List;

public interface DAO<T> {
    int inserir(T entidade);
    int alterar(T entidade);
    int deletar(int id);
    List<T> buscarTodos();
    T buscarPorId(int id);
}
