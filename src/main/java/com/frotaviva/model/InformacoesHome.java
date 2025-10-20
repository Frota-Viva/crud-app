package com.frotaviva.model;

public class InformacoesHome {
    private String nome;
    private long idEmpresa;
    private int tamanhoFrota;
    private int ativos;
    private int inativos;
    private int manutecao;
    private int entregue;
    private int aCaminho;
    private int qtEntrega;
    public InformacoesHome(String nome, long idEmpresa, int tamanhoFrota, int ativos, int inativos, int manutecao,
                           int entregue, int aCaminho, int qtEntrega){
        this.nome=nome;
        this.idEmpresa=idEmpresa;
        this.tamanhoFrota=tamanhoFrota;
        this.ativos=ativos;
        this.inativos=inativos;
        this.manutecao=manutecao;
        this.entregue = entregue;
        this.aCaminho = aCaminho;
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
    public int getEntregue(){
        return this.entregue;
    }
    public int getaCaminho(){
        return this.aCaminho;
    }
    public int getQtEntrega(){
        return this.qtEntrega;
    }

    @Override
    public String toString() {
        return "InformacoesHome{" +
                "nome='" + nome + '\'' +
                ", idEmpresa=" + idEmpresa +
                ", tamanhoFrota=" + tamanhoFrota +
                ", ativos=" + ativos +
                ", inativos=" + inativos +
                ", manutecao=" + manutecao +
                ", entregue=" + entregue +
                ", aCaminho=" + aCaminho +
                ", qtEntrega=" + qtEntrega +
                '}';
    }
}
