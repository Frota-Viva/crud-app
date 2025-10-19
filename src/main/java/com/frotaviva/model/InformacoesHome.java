package com.frotaviva.model;

public class InformacoesHome {
    private String nome;
    private long idEmpresa;
    private int tamanhoFrota;
    private int ativos;
    private int inativos;
    private int manutecao;
    private int atrasadas;
    private int pendente;
    private int qtEntrega;
    public InformacoesHome(String nome, long idEmpresa, int tamanhoFrota, int ativos, int inativos, int manutecao,
                           int atrasadas, int pendente, int qtEntrega){
        this.nome=nome;
        this.idEmpresa=idEmpresa;
        this.tamanhoFrota=tamanhoFrota;
        this.ativos=ativos;
        this.inativos=inativos;
        this.manutecao=manutecao;
        this.atrasadas=atrasadas;
        this.pendente=pendente;
        this.qtEntrega=qtEntrega;
    }
    public String getNome(){
        return this.nome;
    }
    public long getIdEmpresa(){
        return this.idEmpresa;
    }
    public int getTamanhoFrota(){
        return this.tamanhoFrota;
    }
    public int getAtivos(){
        return this.ativos;
    }
    public int getInativos(){
        return this.inativos;
    }
    public int getManutecao(){
        return this.manutecao;
    }
    public int getAtrasadas(){
        return this.atrasadas;
    }
    public int getPendente(){
        return this.pendente;
    }
    public int getQtEntrega(){
        return this.qtEntrega;
    }
    public String toString() {
        return "InformacoesHome{" +
                "nome='" + nome + '\'' +
                ", idEmpresa=" + idEmpresa +
                ", tamanhoFrota=" + tamanhoFrota +
                ", ativos=" + ativos +
                ", inativos=" + inativos +
                ", manutecao=" + manutecao +
                '}';
    }
}
