package com.frotaviva.dao;

import com.frotaviva.Conexao;
import com.frotaviva.Senhas;
import com.frotaviva.model.Empresa;
import com.frotaviva.model.Endereco;

import java.sql.*;

public class EmpresaDAO {
    public static void cadastrarEmpresa(Empresa empresa){
        String sql = "INSERT INTO empresa(tipo_empresa, cnpj, email, senha, nome, cep, rua, complemento, numero, pais," +
                " estado, cidade) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String senhaHash = Senhas.hashSenha(empresa.getSenha());
        Endereco endereco = empresa.getEndereco();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, empresa.getTipoEmpresa());
            stmt.setString(2, empresa.getCnpj());
            stmt.setString(3, empresa.getEmail());
            stmt.setString(4, senhaHash);
            stmt.setString(5, empresa.getNome());
            stmt.setString(6, endereco.getCep());
            stmt.setString(7, endereco.getRua());
            if (endereco.getComplemento() != null){
                stmt.setString(8, endereco.getComplemento());
            } else {
                stmt.setNull(8, Types.VARCHAR);
            }
            stmt.setInt(9, endereco.getNumero());
            stmt.setString(10, endereco.getPais());
            stmt.setString(11, endereco.getEstado());
            stmt.setString(12, endereco.getCidade());

            stmt.execute();
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
    public static void atualizarNome(Empresa empresa){
        String sql = "UPDATE empresa SET nome = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, empresa.getNome());
            stmt.setLong(2, empresa.getId());

            stmt.execute();
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
    public static void atualizarEndereco(Empresa empresa){
        String sql = "UPDATE empresa SET pais = ?, cep = ?, estado = ?, cidade = ?, rua = ?, numero = ?," +
                " complemento = ? WHERE id = ?";
        Endereco endereco = empresa.getEndereco();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, endereco.getPais());
            stmt.setString(2, endereco.getCep());
            stmt.setString(3, endereco.getEstado());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getRua());
            stmt.setInt(6, endereco.getNumero());
            if (endereco.getComplemento() != null){
                stmt.setString(7, endereco.getComplemento());
            } else {
                stmt.setNull(7, Types.VARCHAR);
            }
            stmt.setLong(8, empresa.getId());

            stmt.executeUpdate();
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
    public static void atualizarEmail(Empresa empresa){
        String sql = "UPDATE empresa SET email = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, empresa.getEmail());
            stmt.setLong(2, empresa.getId());

            stmt.executeUpdate();
        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
    public static boolean atualizarSenha(Empresa empresa){
        String senhaArmazenada = Senhas.getSenhaHash(empresa.getId());
        if (Senhas.verificarSenha(empresa.getSenha(), senhaArmazenada)) return false;

        String sql = "UPDATE empresa SET senha = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            String senhaHash = Senhas.hashSenha(empresa.getSenha());
            stmt.setString(1, senhaHash);
            stmt.setLong(2, empresa.getId());

            stmt.executeUpdate();
            return true;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static void deletarEmpresa(Empresa empresa){
        String sql = "DELETE FROM empresa WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, empresa.getId());
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
