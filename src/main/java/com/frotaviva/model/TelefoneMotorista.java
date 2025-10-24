package com.frotaviva.model;


public class TelefoneMotorista {
    private long id;
    private String telefoneMotorista;
    private long idMotorista;
    public TelefoneMotorista(long id, String telefoneMotorista, long idMotorista){
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
    public long getIdMotorista(){
        return this.idMotorista;
    }
    public void setTelefoneMotorista(String telefoneMotorista){
        this.telefoneMotorista=telefoneMotorista;
    }
    public String toString(){
        return "Id: "+this.idMotorista +"\nTelefone: "+this.telefoneMotorista +"\nId Empresa: "+this.idMotorista;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdMotorista(long idMotorista) {
        this.idMotorista = idMotorista;
    }
}
