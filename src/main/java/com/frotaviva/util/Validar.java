package com.frotaviva.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;import java.time.format.DateTimeParseException;

/**
 * Classe responsável por validar determinados parâmetros utilizando, na sua maioria, REGEX para a validação
 * <p>Principais métodos:</p>
 * <ul>
 * <li>email -> valida se o email coincide com o REGEX</li>
 * <li>senha -> valida se o senha coincide com o REGEX</li>
 * <li>telefone -> valida se o telefone coincide com o REGEX</li>
 * <li>telefoneValidado -> retorna o telefone somente com números</li>
 * <li>cep -> valida se o cep coincide com o REGEX</li>
 * <li>cepValidado -> retorna o cep somente com números</li>
 * <li>data -> valida se a data coincide com o REGEX</li>
 * <li>cpf -> valida se o cpf coincide com o REGEX</li>
 * <li>cpfValidado -> retorna o cpf somente com números</li>
 * <li>placa -> valida se a placa coincide com o REGEX</li>
 * <li>status -> valida se o status do caminhão coincide com o REGEX</li>
 * <li>testeVazio -> valida se a String recebida não contém caracteres (sem ser espaço ' ')</li>
 * </ul>
 * 
 * @author Ricardo e Lucas Cayres
 */
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

    public static boolean data(String data){
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(data, formato);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean cpf(String cpf){
        if (cpf == null) return false;

        String regex = "^[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9]{2}$";
        return cpf.matches(regex);
    }

    public static String cpfValidado(String cpf){
        if (cpf == null) return null;

        if (! Validar.cpf(cpf)) return null;

        return cpf.replaceAll("[^0-9]", "");
    }

    public static boolean placa(String placa){
        if (placa == null) return false;

        String regex = "[A-Z]{3}[0-9][A-Z][0-9]{2}";
        return placa.matches(regex);
    }

    public static boolean status(String status){
        if (status == null) return false;

        String regex = "(I)|(A)|(M)";
        return status.matches(regex);
    }
    public static boolean testeVazio(String texto){
        if (texto == null || texto.isEmpty()) return true;

        String regex = "^ +$";
        return texto.matches(regex);
    }
}
