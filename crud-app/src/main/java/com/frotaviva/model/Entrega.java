package com.frotaviva.model;


import java.time.LocalDate;

public class Entrega {
    private long cod_entrega;
    private String descricaoProduto;
    private LocalDate dtPedido;
    private LocalDate dtEntrega;
    private Endereco endereco;
    private long idMotorista;
    public Entrega(long cod_entrega,String descricaoProduto,LocalDate dtPedido,LocalDate dtEntrega,Endereco endereco,long idMotorista){
        this.cod_entrega=cod_entrega;
        this.dtPedido=dtPedido;
        this.dtEntrega=dtEntrega;
        this.endereco=endereco;
        this.descricaoProduto=descricaoProduto;
        this.idMotorista=idMotorista;
    }
    public Entrega(long cod_entrega,String descricaoProduto,LocalDate dtPedido,Endereco endereco,long idMotorista){
        this.cod_entrega=cod_entrega;
        this.dtPedido=dtPedido;
        this.endereco=endereco;
        this.descricaoProduto=descricaoProduto;
        this.idMotorista=idMotorista;
    }
    public long getCod_entrega(){
        return this.cod_entrega;
    }
    public String getDescricaoProduto(){
        return this.descricaoProduto;
    }
    public LocalDate getDtPedido(){
        return this.dtPedido;
    }
    public LocalDate getDtEntrega(){
        return this.dtEntrega;
    }
    public Endereco getEndereco(){
        return this.endereco;
    }
    public long getIdMotorista(){
        return this.idMotorista;
    }
    public String toString(){
        return "Codigo Entrega: "+this.cod_entrega+"\nData Pedido: "+this.dtPedido+"\nData Entrega: "+this.dtEntrega+
                "\nEndereço: "+this.endereco+"\nDescrição do Produto: "+this.descricaoProduto+"\n Motorista: "+this.idMotorista;
    }
}
