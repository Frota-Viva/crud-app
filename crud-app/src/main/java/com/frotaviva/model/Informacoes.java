package com.frotaviva.model;


import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

public class Informacoes {
    private long id;
    private BigDecimal pressaoPneu;
    private BigDecimal sensoresOxigenio;
    private BigDecimal qtdCombustivel;
    private BigDecimal tempArrefecimento;
    private BigDecimal pressaoColetorAdmissao;
    private Timestamp dtHoraLeitura;
    private BigDecimal rpmMotor;
    private BigDecimal cargaMotor;
    private Time tempoMotorLigado;
    private BigDecimal posicaoAcelerador;
    private BigDecimal tempturaArAdmissao;
    private long idCaminhao;
    public Informacoes(long id, BigDecimal tempturaArAdmissao, BigDecimal posicaoAcelerador, Time tempoMotorLigado,
                        BigDecimal cargaMotor, BigDecimal rpmMotor, Timestamp dtHoraLeitura, BigDecimal pressaoPneu,
                        BigDecimal sensoresOxigenio, BigDecimal qtdCombustivel, BigDecimal tempArrefecimento,
                        BigDecimal pressaoColetorAdmissao, long idCaminhao){
        this.id=id;
        this.pressaoPneu=pressaoPneu;
        this.sensoresOxigenio=sensoresOxigenio;
        this.qtdCombustivel = qtdCombustivel;
        this.tempArrefecimento = tempArrefecimento;
        this.pressaoColetorAdmissao=pressaoColetorAdmissao;
        this.dtHoraLeitura = dtHoraLeitura;
        this.rpmMotor=rpmMotor;
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
    public Timestamp getDataHoraLeitura(){
        return this.dtHoraLeitura;
    }
    public BigDecimal getRpmMotor(){
        return this.rpmMotor;
    }
    public BigDecimal getCargaMotor(){
        return this.cargaMotor;
    }
    public Time getTempoMotorLigado(){
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
    public void setDtHoraLeitura(Timestamp dtHoraLeitura){
        this.dtHoraLeitura=dtHoraLeitura;
    }
    public void setRpmMotor(BigDecimal rmpMotor){
        this.rpmMotor=rmpMotor;
    }
    public void setCargaMotor(BigDecimal cargaMotor){
        this.cargaMotor=cargaMotor;
    }
    public void setTempoMotorLigado(Time tempoMotorLigado){
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
                "\nRpm Motor: "+this.rpmMotor+"\nCarga Motor: "+this.cargaMotor+"\nTempo Motor Ligado: "+this.tempoMotorLigado+
                "\nPosição Acelerador: "+this.posicaoAcelerador+"\nTemperatura Ar Admissão: "+ this.tempturaArAdmissao +
                "Id Caminhão: "+this.idCaminhao;
    }

}
