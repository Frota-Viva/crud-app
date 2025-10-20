package com.frotaviva.dao;

import com.frotaviva.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO {

    /*
     * Logger está sendo usado para melhor tratamento e rastreamento de exceções.
     * Ele registra mensagens de erro personalizadas com diagnóstico completo.
     */

    protected final Logger log = LoggerFactory.getLogger(this.getClass()); // pega a classe usada

    public enum Operacao { INSERT, UPDATE, DELETE, SELECT }

    // Fechamento de ResultSet e PreparedStatement

    protected void fechar(PreparedStatement stmt) {
        if (stmt != null) {
            try{
                stmt.close();
            } catch (Exception e){
                log.warn("Erro ao fechar Ptstmt", e);
            }
        }
    }

    protected void fechar(ResultSet rs) {
        if (rs != null) {
            try{
                rs.close();
            } catch (Exception e){
                log.warn("Erro ao fechar ResultSet", e);
            }
        }
    }

    protected void fechar(PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try{
                rs.close();
            } catch (Exception e){
                log.warn("Erro ao fechar ResultSet", e);
            }
        }
        if (stmt != null) {
            try{
                stmt.close();
            } catch (Exception e){
                log.warn("Erro ao fechar Ptstmt", e);
            }
        }
    }

    protected void fechar(ResultSet rs, PreparedStatement stmt) {
        if (rs != null) {
            try{
                rs.close();
            } catch (Exception e){
                log.warn("Erro ao fechar ResultSet", e);
            }
        }
        if (stmt != null) {
            try{
                stmt.close();
            } catch (Exception e){
                log.warn("Erro ao fechar Ptstmt", e);
            }
        }
    }

    // Metodo para lancamento de excessoes

    protected DAOException throwDAOException(SQLException e, Operacao operacao){
        String entidade = this.getClass().getSimpleName();

        if (e.getSQLState() != null && e.getSQLState().startsWith("08")) {
            return new ErroAoConectar(e);
        }

        return switch (operacao) {
            case INSERT -> new ErroAoInserir(e, entidade);
            case UPDATE -> new ErroAoAtualizar(e, entidade);
            case DELETE -> new ErroAoDeletar(e, entidade);
            case SELECT -> new ErroAoConsultar(e, entidade);
            default -> new ErroDesconhecido(operacao, entidade, e);
        };
    }

}
