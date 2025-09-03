package com.frotaviva.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Informacoes {
    private long id;
    private BigDecimal pressaoPneu;
    private BigDecimal sensoresOxigenio;
    private BigDecimal qtdCombustivel;
    private BigDecimal tempArrefecimento;
    private BigDecimal pressaoColetorAdmissao;
    private LocalDateTime dtHoraLeitura;
    private BigDecimal rmpMotor;
    private BigDecimal cargaMotor;
    private LocalTime tempoMotorLigado;
    private BigDecimal posicaoAcelerador;
    private BigDecimal tempturaArAdmissao;
    private long idCaminhao;
    private Informacoes(long id, BigDecimal tempturaArAdmissao, BigDecimal posicaoAcelerador, LocalTime tempoMotorLigado,
                        BigDecimal cargaMotor, BigDecimal rmpMotor, LocalDateTime dtHoraLeitura, BigDecimal pressaoPneu,
                        BigDecimal sensoresOxigenio, BigDecimal qtdCombustivel, BigDecimal tempArrefecimento,
                        BigDecimal pressaoColetorAdmissao, long idCaminhao){
        this.id=id;
        this.pressaoPneu=pressaoPneu;
        this.sensoresOxigenio=sensoresOxigenio;
        this.qtdCombustivel = qtdCombustivel;
        this.tempArrefecimento = tempArrefecimento;
        this.pressaoColetorAdmissao=pressaoColetorAdmissao;
        this.dtHoraLeitura = dtHoraLeitura;
        this.rmpMotor=rmpMotor;
        this.cargaMotor=cargaMotor;
        this.tempoMotorLigado=tempoMotorLigado;
        this.posicaoAcelerador=posicaoAcelerador;
        this.tempturaArAdmissao = tempturaArAdmissao;
        this.idCaminhao=idCaminhao;
    }
    public long getId(){
        return this.id;
    }
    public BigDecimal getPressaoPneu(){
        return this.pressaoPneu;
    }
    public BigDecimal getSensoresOxigenio(){
        return this.sensoresOxigenio;
    }
    public BigDecimal getQtdCombustivel(){
        return this.qtdCombustivel;
    }
    public BigDecimal getTempArrefecimento(){
        return this.tempArrefecimento;
    }
    public BigDecimal getPressaoColetorAdmissao(){
        return this.pressaoColetorAdmissao;
    }
    public LocalDateTime getDataHoraLeitura(){
        return this.dtHoraLeitura;
    }
    public BigDecimal getRmpMotor(){
        return this.rmpMotor;
    }
    public BigDecimal getCargaMotor(){
        return this.cargaMotor;
    }
    public LocalTime getTempoMotorLigado(){
        return this.tempoMotorLigado;
    }
    public BigDecimal getPosicaoAcelerador(){
        return this.posicaoAcelerador;
    }
    public BigDecimal getTempturaArAdmissao(){
        return this.tempturaArAdmissao;
    }
    public long getIdCaminhao(){
        return idCaminhao;
    }
    public void setPressaoPneu(BigDecimal pressaoPneu){
        this.pressaoPneu=pressaoPneu;
    }
    public void setSensoresOxigenio(BigDecimal sensoresOxigenio){
        this.sensoresOxigenio=sensoresOxigenio;
    }
    public void setQtdCombustivel(BigDecimal qtdCombustivel){
        this.qtdCombustivel=qtdCombustivel;
    }
    public void setTempArrefecimento(BigDecimal tempArrefecimento){
        this.tempArrefecimento=tempArrefecimento;
    }
    public void setPressaoColetorAdmissao(BigDecimal pressaoColetorAdmissao){
        this.pressaoColetorAdmissao=pressaoColetorAdmissao;
    }
    public void setDtHoraLeitura(LocalDateTime dtHoraLeitura){
        this.dtHoraLeitura=dtHoraLeitura;
    }
    public void setRmpMotor(BigDecimal rmpMotor){
        this.rmpMotor=rmpMotor;
    }
    public void setCargaMotor(BigDecimal cargaMotor){
        this.cargaMotor=cargaMotor;
    }
    public void setTempoMotorLigado(LocalTime tempoMotorLigado){
        this.tempoMotorLigado=tempoMotorLigado;
    }
    public void setPosicaoAcelerador(BigDecimal posicaoAcelerador){
        this.posicaoAcelerador=posicaoAcelerador;
    }
    public void setTempturaArAdmissao(BigDecimal tempturaArAdmissao){
        this.tempturaArAdmissao=tempturaArAdmissao;
    }
    public String toString(){
        return "Id: "+this.id+"\nPressão Pneu: "+this.pressaoPneu+"\nSensores Oxigênio: "+this.sensoresOxigenio+
                "\nQuantidade Combustível: "+this.qtdCombustivel +"\nTemperatura Arrefeciemnto: "+this.tempArrefecimento +
                "\nPressão Coletor Admissão: "+this.pressaoColetorAdmissao+"\nData e Hora da Leitura: "+this.dtHoraLeitura +
                "\nRpm Motor: "+this.rmpMotor+"\nCarga Motor: "+this.cargaMotor+"\nTempo Motor Ligado: "+this.tempoMotorLigado+
                "\nPosição Acelerador: "+this.posicaoAcelerador+"\nTemperatura Ar Admissão: "+ this.tempturaArAdmissao +
                "Id Caminhão: "+this.idCaminhao;
    }

}
