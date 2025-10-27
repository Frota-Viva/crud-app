package com.frotaviva.model;


public class Frota {
    private long id;
    private int tamanhoFrota;
    private String tipoFrota;
    private String regiao;
    private long idEmpresa;

    public Frota(long id,int tamanhoFrota,String tipoFrota,String regiao,long idEmpresa){
        this.id=id;
        this.tamanhoFrota=tamanhoFrota;
        this.tipoFrota=tipoFrota;
        this.regiao=regiao;
        this.idEmpresa=idEmpresa;
    }

    public Frota(int tamanhoFrota,String tipoFrota,String regiao,long idEmpresa){
        this.tamanhoFrota=tamanhoFrota;
        this.tipoFrota=tipoFrota;
        this.regiao=regiao;
        this.idEmpresa=idEmpresa;
    }

    public long getId(){
        return this.id;
    }
    public int getTamanhoFrota(){
        return this.tamanhoFrota;
    }
    public String getTipoFrota(){
        return this.tipoFrota;
    }
    public String getRegiao(){
        return this.regiao;
    }
    public long getIdEmpresa(){
        return this.idEmpresa;
    }
    public void setTamanhoFrota(int tamanhoFrota){
        this.tamanhoFrota=tamanhoFrota;
    }
    public void setRegiao(String regiao){
        this.regiao=regiao;
    }
    public void setTipoFrota(String tipoFrota){
        this.tipoFrota=tipoFrota;
    }
    public void setIdEmpresa(int idEmpresa){
        this.idEmpresa =idEmpresa;
    }

    public String toString(){
        return "Id: "+this.id+"\nTamanho da Frota: "+this.tamanhoFrota+"\nTipo da Frota: "+this.tipoFrota+"\nRegião: "+this.regiao;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
