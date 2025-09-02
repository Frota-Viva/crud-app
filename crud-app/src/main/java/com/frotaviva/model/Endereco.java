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

// Sem o complemento

//    public Endereco(String pais,String cep,String estado,String cidade,String rua,int numero){
//        this.cep=cep;
//        this.rua=rua;
//        this.numero=numero;
//        this.pais=pais;
//        this.estado=estado;
//        this.cidade=cidade;
//    }

// Sem o complemento, sem estado e sem cep, estes os dois ultimos pode ser que outros países não tenham

//    public Endereco(String pais,String cidade,String rua,int numero){
//        this.rua=rua;
//        this.numero=numero;
//        this.pais=pais;
//        this.cidade=cidade;
//    }

// Sem cep, seguindo a mesma lógica da de cima

//    public Endereco(String pais,String estado,String cidade,String rua,int numero,String complemento){
//        this.rua=rua;
//        this.complemento=complemento;
//        this.numero=numero;
//        this.pais=pais;
//        this.estado=estado;
//        this.cidade=cidade;
//    }

// Sem estado e sem cep, seguindo a mesma lógica de cima

//    public Endereco(String pais,String cidade,String rua,int numero,String complemento){
//        this.rua=rua;
//        this.complemento=complemento;
//        this.numero=numero;
//        this.pais=pais;
//        this.cidade=cidade;
//    }

// Sem cep e sem complemento, seguindo a mesma lógica de cima

//    public Endereco(String pais,String estado,String cidade,String rua,int numero){
//        this.rua=rua;
//        this.numero=numero;
//        this.pais=pais;
//        this.estado=estado;
//        this.cidade=cidade;
//    }

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

// Método toString
    public String toString(){
        return "País: "+this.pais+"\nCEP: "+this.cep+"\nEstado: "+this.estado+"\nCidade: "+this.cidade+"\nRua: "+this.rua+
                "\nNúmero: "+this.numero;
    }
}
