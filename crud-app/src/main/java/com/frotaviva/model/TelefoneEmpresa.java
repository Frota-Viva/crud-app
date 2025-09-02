package com.frotaviva.model;


public class TelefoneEmpresa {
    private long id;
    private String telefoneEmpresa;
    private String idEmpresa;
    public TelefoneEmpresa(long id, String telefoneEmpresa, String idEmpresa){
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
    public String getIdEmpresa(){
        return this.idEmpresa;
    }
    public String toString(){
        return "Id: "+this.idEmpresa+"\nTelefone: "+this.telefoneEmpresa +"\nId Empresa: "+this.idEmpresa;
    }
}
