package com.frotaviva.model;


public class TelefoneEmpresa {
    private long id;
    private String telefoneEmpresa;
    private long idEmpresa;
    public TelefoneEmpresa(long id, String telefoneEmpresa, long idEmpresa){
        this.id=id;
        this.telefoneEmpresa = telefoneEmpresa;
        this.idEmpresa=idEmpresa;
    }
    public long getId(){
        return this.id;
    }
    public String getTelefoneEmpresa(){
        return this.telefoneEmpresa;
    }
    public long getIdEmpresa(){
        return this.idEmpresa;
    }
    public void setTelefoneEmpresa(String telefoneEmpresa){
        this.telefoneEmpresa=telefoneEmpresa;
    }
    public String toString(){
        return "Id: "+this.idEmpresa+"\nTelefone: "+this.telefoneEmpresa +"\nId Empresa: "+this.idEmpresa;
    }
}
