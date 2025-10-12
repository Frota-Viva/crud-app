package com.frotaviva.exception;

public class DAOException extends Exception{

    public DAOException(String message){
        super(message);
    }
    
    public static DAOException erroAoInserir(String entidade, Throwable cause){
        return new DAOException("Erro ao inserir dados na tabela "+ entidade + " : " + cause);
    }

    public static DAOException erroAoConectar(Throwable cause){
        return new DAOException("Erro ao conectar com o banco de dados." + cause);
    }

    public static DAOException erroAoAtualizar(String entidade, Throwable cause){
        return new DAOException("Erro ao atualizar dados na tabela "+ entidade + " : "+ cause);
    }

    public static DAOException erroAoDeletar(String entidade, Throwable cause){
        return new DAOException("Erro ao deletar dados na tabela "+ entidade + " : "+ cause);
    }

    public static DAOException erroAoConsultar(String entidade, Throwable cause){
        return new DAOException("Erro ao consultar dados na tabela "+ entidade + " : "+ cause);
    }

}