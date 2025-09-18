package com.frotaviva.dao;

import com.frotaviva.util.Conexao;
import com.frotaviva.model.TelefoneEmpresa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class TelefoneEmpresaDAO {
    public static boolean cadastrarTelefoneEmpresa(TelefoneEmpresa telefoneEmpresa){
        String sql = "INSERT INTO telefone_empresa(telefone_empresa, id_empresa) VALUES(? , ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt =  conn.prepareStatement(sql)){
            stmt.setString(1, telefoneEmpresa.getTelefoneEmpresa());
            stmt.setLong(2, telefoneEmpresa.getIdEmpresa());

            if (stmt.executeUpdate() > 0) return true;
            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean atualizarTelefoneEmpresa(TelefoneEmpresa telefoneEmpresa){
        String sql = "UPDATE telefone_empresa SET telefone_empresa = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, telefoneEmpresa.getTelefoneEmpresa());
            stmt.setLong(2, telefoneEmpresa.getId());

            if (stmt.executeUpdate() > 0) return true;

            return false;
        } catch (SQLException sqle){
            sqle.printStackTrace();
            return false;
        }
    }
    public static boolean deletarTelefoneEmpresa(TelefoneEmpresa telefoneEmpresa){
        String sql = "DELETE FROM telefone_empresa WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setLong(1, telefoneEmpresa.getId());

            if (stmt.executeUpdate() > 0) return true;

            return false;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            return false;
        }

    }
    public static TelefoneEmpresa getTelefoneEmpresa(long id){
        String sql = "SELECT * FROM telefone_empresa WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareCall(sql)){
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                long idTelefoneEmpresa = rs.getLong("id");
                String telefone_empresa = rs.getString("telefone_empresa");
                long id_empresa = rs.getLong("id_empresa");
                return new TelefoneEmpresa(idTelefoneEmpresa, telefone_empresa, id_empresa);
            }
            return null;

        } catch (SQLException sqle){
            sqle.printStackTrace();
            return null;
        }
    }
}
