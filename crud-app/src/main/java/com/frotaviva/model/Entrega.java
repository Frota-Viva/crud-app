package com.frotaviva.model;


import java.sql.Date;

public class Entrega {
    private long cod_entrega;
    private String descricaoProduto;
    private Date dtPedido;
    private Date dtEntrega;
    private Endereco endereco;
    private long idMotorista;
    public Entrega(long cod_entrega,String descricaoProduto,Date dtPedido,Date dtEntrega,Endereco endereco,long idMotorista){
        this.cod_entrega=cod_entrega;
        this.dtPedido=dtPedido;
        this.dtEntrega=dtEntrega;
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
    public Date getDtPedido(){
        return this.dtPedido;
    }
    public Date getDtEntrega(){
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
