package com.frotaviva.model;


public class Endereco {
    // Atributos criados para endeco
    private String cep;
    private String rua;
    private String complemento;
    private int numero;
    private String pais;
    private String estado;
    private String cidade;

// Método construtor, criei todos os padrões possiveis, porém apenas o primeiro será realmente usado
    public Endereco(String pais,String cep,String estado,String cidade,String rua,int numero,String complemento){
        this.cep=cep;
        this.rua=rua;
        this.complemento=complemento;
        this.numero=numero;
        this.pais=pais;
        this.estado=estado;
        this.cidade=cidade;
    }

<<<<<<< HEAD

=======
>>>>>>> f6d3f11c0e36128e0c31730a93c3b1d0d78f9ecb
//  Métodos getters
    public String getCef(){
        return this.cep;
    }
    public String getRua(){
        return this.rua;
    }
    public String getComplemento(){
        return this.complemento;
    }
    public int getNumero(){
        return this.numero;
    }
    public String getPais(){
        return this.pais;
    }
    public String getEstado(){
        return this.estado;
    }
    public String getCidade(){
        return this.cidade;
    }
    public void setCep(String cep){
        this.cep=cep;
    }
    public void setRua(String rua){
        this.rua=rua;
    }
    public void setComplemento(String complemento){
        this.complemento=complemento;
    }
    public void setNumero(int numero){
        this.numero=numero;
    }
    public void setCidade(String cidade){
        this.cidade=cidade;
    }
    public void setEstado (String estado){
        this.estado=estado;
    }
    public void setPais(String pais){
        this.pais=pais;
    }

// Método toString
    public String toString(){
        return "País: "+this.pais+"\nCEP: "+this.cep+"\nEstado: "+this.estado+"\nCidade: "+this.cidade+"\nRua: "+this.rua+
                "\nNúmero: "+this.numero;
    }
}
