package com.frotaviva.model;

public class Caminhao {
    private long id;
    private String placa;
    private String status;
    private int kmRodados;
    private String modelo;
    private int capacidade;
    private long idFrota;
    public Caminhao(long id,String placa,String status,int kmRodados,String modelo,int capacidade,
                    long idFrota){
        this.id=id;
        this.status=status;
        this.modelo=modelo;
        this.placa=placa;
        this.kmRodados=kmRodados;
        this.capacidade=capacidade;
        this.idFrota=idFrota;
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
    public void setPlaca(String placa){
        this.placa=placa;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setKmRodados(int kmRodados) {
        this.kmRodados = kmRodados;
    }

    public void setIdFrota(long idFrota){
        this.idFrota=idFrota;
    }

    @Override
    public String toString() {
        return "Caminhao{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                ", status='" + status + '\'' +
                ", kmRodados=" + kmRodados +
                ", modelo='" + modelo + '\'' +
                ", capacidade=" + capacidade +
                ", idFrota=" + idFrota +
                '}';
    }
}
