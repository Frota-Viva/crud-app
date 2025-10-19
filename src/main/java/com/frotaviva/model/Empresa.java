package com.frotaviva.model;

public class Empresa {
    private long id;
    private String nome;
    private Endereco endereco;
    private String email;
    private String cnpj;
    private String tipoEmpresa;
    private String senha;

    public Empresa(long id,String tipoEmpresa,String cnpj,String email,String senha,String nome,
                   Endereco endereco){
        this.id=id;
        this.nome=nome;
        this.email=email;
        this.cnpj=cnpj;
        this.endereco =endereco;
        this.tipoEmpresa=tipoEmpresa;
        this.senha=senha;
    }
    public Empresa(String tipoEmpresa,String cnpj,String email,String senha,String nome,
                   Endereco endereco){
        this.nome=nome;
        this.email=email;
        this.cnpj=cnpj;
        this.endereco =endereco;
        this.tipoEmpresa=tipoEmpresa;
        this.senha=senha;
    }

    public long getId(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public Endereco getEndereco(){
        return this.endereco;
    }
    public String getEmail(){
        return this.email;
    }
    public String getCnpj(){
        return this.cnpj;
    }
    public String getTipoEmpresa(){
        return this.tipoEmpresa;
    }
    public String getSenha(){
        return this.senha;
    }
    public void setNome(String nome){
        this.nome=nome;
    }
    public void setEndereco(Endereco endereco){
        this.endereco=endereco;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setSenha(String senha){
        this.senha=senha;
    }

    public static String cnpjValidado(String cnpj){
        String regexCnpj = "^[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\/?[0-9]{4}\\-?[0-9]{2}$"; //Regex para validar CNPJ

        if (! cnpj.matches(regexCnpj)) return null;

        cnpj = cnpj.replaceAll("[^0-9]", ""); //Pega apenas o número no CNPJ

//        Verifica o tamanho do CNPJ e se ele apenas repete o mesmo número
        if (cnpj.length() != 14 || cnpj.matches("^([0-9])\\1{13}$")) return null;

        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        String base = cnpj.substring(0, 12);
        String digitos = cnpj.substring(12);

        int soma1 = 0;
        int soma2 = 0;

//        Soma todos os valores multiplicados pelos respectivos pesos
        for (int i = 0; i < 12; i++) {
            int num = Character.getNumericValue(base.charAt(i)); //Pega o valor numérico do caractere
            soma1 += num * pesos1[i];
            soma2 += num * pesos2[i];
        }

//        Calcula o 1° dígito
        int resto = soma1 % 11;
        int digito1 = (resto < 2) ? 0 : 11 - resto;

//        Soma o digito1 multiplicado por seu peso e calcula o 2° dígito
        soma2 += digito1 * pesos2[12];
        resto = soma2 % 11;
        int digito2 = (resto < 2) ? 0 : 11 - resto;

        return digitos.equals("" + digito1 + digito2) ? cnpj : null;
    }
    public String toString(){
        return "Id: "+this.id+"\nNome: "+this.nome+"\nEndereco: "+this.endereco+"\nEmail: "+this.email+
                "\nCnpj: "+this.cnpj+"\nTipo da Empresa: "+this.tipoEmpresa;
    }
}
