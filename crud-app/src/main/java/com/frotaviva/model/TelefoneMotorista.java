package com.frotaviva.model;


public class TelefoneMotorista {
    private long id;
    private String telefoneMotorista;
    private String idMotorista;
    public TelefoneMotorista(long id, String telefoneMotorista, String idMotorista){
        this.id=id;
        this.telefoneMotorista = telefoneMotorista;
        this.idMotorista = idMotorista;
    }
    public long getId(){
        return this.id;
    }
    public String getTelefoneMotorista(){
        return this.telefoneMotorista;
    }
    public String getIdMotorista(){
        return this.idMotorista;
    }
    public String toString(){
        return "Id: "+this.idMotorista +"\nTelefone: "+this.telefoneMotorista +"\nId Empresa: "+this.idMotorista;
    }
}
