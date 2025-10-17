package com.frotaviva.util;

public class Validar {
    public Validar(){}

    public static boolean email(String email){
        String regex = "^[A-Za-z0-9\\.\\_\\%\\+\\-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }
    public static boolean senha(String senha){
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\$\\*\\&\\@\\#])[0-9a-zA-Z\\$\\*\\&\\@\\#]{8,}$";
        return senha.matches(regex);
    }

    public static boolean cep(String cep){
        String regex = "^[0-9]{5}\\-?[0-9]{3}$";
        return cep.matches(regex);
    }
    public static String cepValidado(String cep){
        if (! Validar.cep(cep)) return null;

        return cep.replaceAll("[^0-9]", "");
    }
}
