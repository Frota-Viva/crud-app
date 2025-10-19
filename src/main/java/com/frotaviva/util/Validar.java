package com.frotaviva.util;

public class Validar {
    public Validar(){}

    public static boolean email(String email){
        if (email == null) return false;

        String regex = "^[A-Za-z0-9\\.\\_\\%\\+\\-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }
    public static boolean senha(String senha){
        if (senha == null) return false;

        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\$\\*\\&\\@\\#\\!\\?])[0-9a-zA-Z\\$\\*\\&\\@\\#\\!\\?]{8,}$";
        return senha.matches(regex);
    }

    public static boolean telefone(String telefone){
        if (telefone == null) return false;

        String regex = "^(\\(?\\d{2}\\)?\\s?)(\\d{4,5}\\-?\\d{4})$";
        return telefone.matches(regex);
    }

    public static String telefoneValidado(String telefone){
        if (telefone == null) return null;

        if (! Validar.telefone(telefone)) return null;

        return telefone.replaceAll("[^0-9]", "");
    }
    public static boolean cep(String cep){
        if (cep == null) return false;

        String regex = "^[0-9]{5}\\-?[0-9]{3}$";
        return cep.matches(regex);
    }
    public static String cepValidado(String cep){
        if (cep == null) return null;

        if (! Validar.cep(cep)) return null;

        return cep.replaceAll("[^0-9]", "");
    }
}
