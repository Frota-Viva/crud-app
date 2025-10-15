package com.frotaviva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frotaviva.exception.DAOException;
import com.frotaviva.model.Caminhao;
import com.frotaviva.util.Conexao;

public class CaminhaoDAO implements DAO<Caminhao>{

    /*
    * Logger está sendo usado para melhor tratamento e rastreamento de exceções.
    * Ele registra mensagens de erro personalizadas com diagnóstico completo.
    */

    /*
     * A classe CaminhaoDAO conta com os métodos:
     * inserir
     * atualizarPlaca|KmRodados|Status|idFrota
     * deletar
     * buscarPorId
     * buscarTodos
    */

    private static final Logger log = LoggerFactory.getLogger(CaminhaoDAO.class);

    public static boolean cadastrarCaminhao(Caminhao caminhao){
        Conexao conexao = new Conexao();
        Connection con = null;

        String sql = "INSERT INTO caminhao(placa, status, km_rodados, modelo, capacidade, id_frota) " +
                "VALUES(?, ?, ?, ?, ? ,?)";

        try {
            con = conexao.conectar();
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, caminhao.getPlaca());
            stmt.setString(2, caminhao.getStatus());
            stmt.setInt(3, caminhao.getKmRodados());
            stmt.setString(4, caminhao.getModelo());
            stmt.setInt(5, caminhao.getCapacidade());
            stmt.setLong(6, caminhao.getIdFrota());

            if (stmt.executeUpdate() > 0){
                stmt.close(); 
                return 1;
            } 
            return 0;

        } 
        catch (SQLException sqle){
            log.error("Erro ao cadastrar caminhão.", sqle);
            return false;
        } 
        finally{
            conexao.desconectar(con);
        }
    }

    public static boolean atualizarPlaca(Caminhao caminhao){
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "UPDATE caminhao SET placa = ? WHERE id = ?";

        try {
            
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, caminhao.getPlaca());
            stmt.setLong(2, caminhao.getId());

            if (stmt.executeUpdate() > 0){
                stmt.close(); 
                return 1;
            } 
            return 0;

        } 
        catch (SQLException sqle){
            log.error("Erro ao atualizar placa do caminhão", sqle);
            return false;
        } 
        finally {
            conexao.desconectar(conn);
        }
    }


    public static boolean atualizarStatus(Caminhao caminhao){
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "UPDATE caminhao SET status = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, caminhao.getStatus());
            stmt.setLong(2, caminhao.getId());

            if (stmt.executeUpdate() > 0){
                stmt.close(); 
                return 1;
            } 
            return 0;
        } 
        catch (SQLException e){
            log.error("Erro ao atualizar o status do caminhão", e);
            return false;
        }
        finally{
            conexao.desconectar(conn);
        }
    }


    public static boolean atualizarKmRodados(Caminhao caminhao){
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "UPDATE caminhao SET kms_rodados = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, caminhao.getKmRodados());
            stmt.setLong(2, caminhao.getId());

            if (stmt.executeUpdate() > 0){
                stmt.close();
                return 1;
            }
            return 0;

        } catch (SQLException sqle){
            
            return false;
        } 
        finally {
            conexao.desconectar(conn);
        }
    }

    public static boolean atualizarIdFrota(Caminhao caminhao){
        Conexao conexao = new Conexao();
        Connection conn = null;
        
        String sql = "UPDATE caminhao SET id_frota = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, caminhao.getIdFrota());
            stmt.setLong(2, caminhao.getId());

            if (stmt.executeUpdate() > 0){
                stmt.close(); 
                return 1;
            } 
            return 0;

        } catch (SQLException e){
            log.error("Erro ao atualizar o ID da frota.", e);
            return false;
        }
        finally {
            conexao.desconectar(conn);
        }
    }


    public static boolean deletarCaminhao(Caminhao caminhao){
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "DELETE FROM caminhao WHERE id = ?";

        try {
            
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);

            if (stmt.executeUpdate() > 0) return true;
            return false;

        } catch (SQLException e) {
            log.error("Erro ao deletar caminhão. ",e);
            return false;
        }
        finally{
            conexao.desconectar(conn);
        }
    }

    public static Caminhao getCaminhao(long id){
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "SELECT * FROM caminhao WHERE id = ?";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){

                long idCaminhao = rs.getLong("id");
                String placa = rs.getString("placa");
                String status = rs.getString("status");
                int km_rodados = rs.getInt("kms_rodados");
                String modelo = rs.getString("modelo");
                int capacidade = rs.getInt("capacidade");
                long id_frota = rs.getLong("id_frota");

                stmt.close();
                rs.close();

                return new Caminhao(idCaminhao, placa, status, km_rodados, modelo, capacidade, id_frota);
            }
            return null;

        } 
        catch (SQLException e){
            log.error("Erro ao recuperar o caminhão. ", e);
            throw DAOException.erroAoConsultar( "caminhao", e);
        }
        finally{
            conexao.desconectar(conn);
        }
    }

    public List<Caminhao> buscarTodos() {
        List<Caminhao> caminhoes = new ArrayList<>();
        Conexao conexao = new Conexao();
        Connection conn = null;

        String sql = "SELECT * FROM caminhao";

        try {
            conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){

                long idCaminhao = rs.getLong("id");
                String placa = rs.getString("placa");
                String status = rs.getString("status");
                int km_rodados = rs.getInt("kms_rodados");
                String modelo = rs.getString("modelo");
                int capacidade = rs.getInt("capacidade");
                long id_frota = rs.getLong("id_frota");

                Caminhao caminhao = new Caminhao(idCaminhao, placa, status, km_rodados, modelo,
                        capacidade, id_frota);
                caminhoes.add(caminhao);
            }

            stmt.close();
            rs.close();

            return caminhoes;

        }
        catch (SQLException e){
            log.error("Erro ao resgatar o caminhão. ", e);
            return null;
        }
        finally{
            conexao.desconectar(conn);
        }

    }
}

