package com.frotaviva.model;

public class InformacoesHome {
    private String nome;
    private long idEmpresa;
    private int tamanhoFrota;
    private int ativos;
    private int inativos;
    private int manutecao;
    private int realizadas;
    private int pendentes;
    private int qtEntrega;
    private int preventivas;
    private int corretivas;
    private double custoPreventivas;
    private double custoCorretivas;
    public InformacoesHome(String nome, long idEmpresa, int tamanhoFrota, int ativos, int inativos, int manutecao,
                           int realizadas, int pendentes, int qtEntrega, int preventivas, int corretivas,
                           double custoPreventivas, double custoCorretivas){
        this.nome=nome;
        this.idEmpresa=idEmpresa;
        this.tamanhoFrota=tamanhoFrota;
        this.ativos=ativos;
        this.inativos=inativos;
        this.manutecao=manutecao;
        this.realizadas = realizadas;
        this.pendentes = pendentes;
        this.qtEntrega=qtEntrega;
        this.preventivas=preventivas;
        this.corretivas=corretivas;
        this.custoPreventivas=custoPreventivas;
        this.custoCorretivas=custoCorretivas;
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
    public int getRealizada(){
        return this.realizadas;
    }
    public int getPendente(){
        return this.pendentes;
    }
    public int getQtEntrega(){
        return this.qtEntrega;
    }

    public int getPreventivas() {
        return this.preventivas;
    }
    public int getCorretivas() {
        return this.corretivas;
    }

    public double getCustoCorretivas() {
        return this.custoCorretivas;
    }

    public double getCustoPreventivas() {
        return this.custoPreventivas;
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
                ", entregue=" + realizadas +
                ", aCaminho=" + pendentes +
                ", qtEntrega=" + qtEntrega +
                '}';
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdEmpresa(long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public void setTamanhoFrota(int tamanhoFrota) {
        this.tamanhoFrota = tamanhoFrota;
    }

    public void setAtivos(int ativos) {
        this.ativos = ativos;
    }

    public void setInativos(int inativos) {
        this.inativos = inativos;
    }

    public void setManutecao(int manutecao) {
        this.manutecao = manutecao;
    }

    public int getRealizadas() {
        return realizadas;
    }

    public void setRealizadas(int realizadas) {
        this.realizadas = realizadas;
    }

    public int getPendentes() {
        return pendentes;
    }

    public void setPendentes(int pendentes) {
        this.pendentes = pendentes;
    }

    public void setQtEntrega(int qtEntrega) {
        this.qtEntrega = qtEntrega;
    }

    public void setPreventivas(int preventivas) {
        this.preventivas = preventivas;
    }

    public void setCorretivas(int corretivas) {
        this.corretivas = corretivas;
    }

    public void setCustoPreventivas(double custoPreventivas) {
        this.custoPreventivas = custoPreventivas;
    }

    public void setCustoCorretivas(double custoCorretivas) {
        this.custoCorretivas = custoCorretivas;
    }
}
