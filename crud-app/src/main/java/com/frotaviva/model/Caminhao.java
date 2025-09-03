package com.frotaviva.model;

public class Caminhao {
    private long id;
    private String placa;
    private String status;
    private int kmRodados;
    private String modelo;
    private int capacidade;
    private long idFrota;
    private long idMotorista;
    public Caminhao(long id,String placa,String status,int kmRodados,String modelo,int capacidade,
                    long idFrota,long idMotorista ){
        this.id=id;
        this.status=status;
        this.modelo=modelo;
        this.placa=placa;
        this.kmRodados=kmRodados;
        this.capacidade=capacidade;
        this.idFrota=idFrota;
        this.idMotorista=idMotorista;
    }
    public long getId(){
        return this.id;
    }
    public String getPlaca(){
        return this.placa;
    }
    public String getStatus(){
        return this.status;
    }
    public int getKmRodados(){
        return this.kmRodados;
    }
    public String getModelo(){
        return this.modelo;
    }
    public int getCapacidade(){
        return this.capacidade;
    }
    public long getIdFrota(){
        return this.idFrota;
    }
    public long getIdMotorista(){
        return this.idMotorista;
    }
    public void setPlaca(String placa){
        this.placa=placa;
    }
    public void setIdMotorista(long idMotorista){
        this.idMotorista=idMotorista;
    }
    public void setIdFrota(long idFrota){
        this.idFrota=idFrota;
    }
    public String toString(){
        return "Id: "+this.id+"\nModelo: "+this.modelo+"\nPlaca: "+this.placa+"\nKm Rodados: "+this.kmRodados+"\nStatus: "+this.status+
                "\nId Frota: "+this.idFrota+"\nId Motorista: "+this.idMotorista;
    }
}
