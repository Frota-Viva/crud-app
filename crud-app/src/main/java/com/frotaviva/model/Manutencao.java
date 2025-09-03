package com.frotaviva.model;


import java.math.BigDecimal;
import java.time.LocalDate;

public class Manutencao {
    private long id;
    private LocalDate dtCadastro;
    private LocalDate dtConclusao;
    private String tipoManutencao;
    private BigDecimal custo;
    private long ultimoMotorista;
    private String descricaoServico;
    private long idCaminhao;
    public Manutencao(long id,LocalDate dataCadastro,LocalDate dataConclusao,String tipoManutencao,BigDecimal custo,long ultimoMotorista,String descricaoServico,long idCaminhao){
        this.id=id;
        this.dtCadastro =dataCadastro;
        this.dtConclusao =dataConclusao;
        this.tipoManutencao=tipoManutencao;
        this.custo=custo;
        this.ultimoMotorista=ultimoMotorista;
        this.descricaoServico=descricaoServico;
        this.idCaminhao=idCaminhao;
    }
    public Manutencao(long id,LocalDate dataCadastro,String tipoManutencao,BigDecimal custo,long ultimoMotorista,String descricaoServico,long idCaminhao){
        this.id=id;
        this.dtCadastro =dataCadastro;
        this.tipoManutencao=tipoManutencao;
        this.custo=custo;
        this.ultimoMotorista=ultimoMotorista;
        this.descricaoServico=descricaoServico;
        this.idCaminhao=idCaminhao;
    }
    public long getId(){
        return this.id;
    }
    public LocalDate getDtCadastro(){
        return this.dtCadastro;
    }
    public LocalDate getDtConclusao(){
        return this.dtConclusao;
    }
    public String getTipoManutencao(){
        return this.tipoManutencao;
    }
    public BigDecimal getCusto(){
        return this.custo;
    }
    public long getUltimoMotorista(){
        return this.ultimoMotorista;
    }
    public String getDescricaoServico(){
        return this.descricaoServico;
    }
    public long getIdCaminhao() {
        return this.idCaminhao;
    }
    public void setCusto(BigDecimal custo){
        this.custo=custo;
    }
    public void setDescricaoServico(String descricaoServico){
        this.descricaoServico=descricaoServico;
    }

    public String toString(){
        return "Id: "+this.id+"\nData Cadastro: "+this.dtCadastro +"\nData Conclusão: "+this.dtConclusao +"\nCusto: "+this.custo+
                "\nTipo Manutenção: "+this.tipoManutencao+"\nUltimo Motorista: "+this.ultimoMotorista+
                "\nDescrição do Serviço: "+this.descricaoServico+"Id Caminhão: "+this.idCaminhao;
    }
}
