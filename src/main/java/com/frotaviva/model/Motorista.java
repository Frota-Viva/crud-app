package com.frotaviva.model;

public class Motorista {
    private long id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private long idEmpresa;

    public Motorista(long id,String nome,String email,String cpf, String senha, long idEmpresa){
        this.id=id;
        this.nome=nome;
        this.email=email;
        this.cpf=cpf;
        this.senha=senha;
        this.idEmpresa=idEmpresa;
    }

    public Motorista(String nome,String email,String cpf, String senha, long idEmpresa){
        this.nome=nome;
        this.email=email;
        this.cpf=cpf;
        this.senha=senha;
        this.idEmpresa=idEmpresa;
    }

    public Motorista(String nome,String email,String cpf, String senha){
        this.nome=nome;
        this.email=email;
        this.cpf=cpf;
        this.senha=senha;
    }

    public Motorista(long id, String nome,String email,String cpf, String senha){
        this.id = id;
        this.nome=nome;
        this.email=email;
        this.cpf=cpf;
        this.senha=senha;
    }

    public long getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getEmail(){
        return this.email;
    }

    public String getCpf(){
        return this.cpf;
    }

    public String getSenha(){
        return this.senha;
    }

    public long getIdEmpresa(){
        return this.idEmpresa;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setSenha(String senha){
        this.senha=senha;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString(){
        return "Id: "+this.id+"\nNome: "+this.nome+"\nCpf: "+this.cpf+"\nEmail: "+this.email+
                "\nSenha: "+this.senha+"\nId Empresa: "+this.idEmpresa;
    }
}
