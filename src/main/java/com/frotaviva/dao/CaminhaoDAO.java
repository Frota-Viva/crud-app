package com.frotaviva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.frotaviva.exception.*;
import com.frotaviva.model.Caminhao;
import com.frotaviva.util.Conexao;

import static com.frotaviva.dao.AbstractDAO.Operacao.*;

public class CaminhaoDAO extends AbstractDAO implements DAO<Caminhao>{

    /*
     * A classe CaminhaoDAO conta com os métodos:
     * inserir
     * atualizarPlaca|KmRodados|Status|idFrota
     * deletar
     * buscarPorId|Todos
    */

    public int inserir(Caminhao caminhao){
        PreparedStatement stmt = null;
        Conexao conexao = new Conexao();
        Connection con = null;

        String sql = "INSERT INTO caminhao(placa, status, km_rodados, modelo, capacidade, id_frota) VALUES(?, ?, ?, ?, ? ,?)";

        try {
            con = conexao.conectar();
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, caminhao.getPlaca());
            stmt.setString(2, caminhao.getStatus());
            stmt.setInt(3, caminhao.getKmRodados());
            stmt.setString(4, caminhao.getModelo());
            stmt.setInt(5, caminhao.getCapacidade());
            stmt.setLong(6, caminhao.getIdFrota());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        }
        catch (SQLException e){
            log.error("Erro ao inserir caminhão", e);
            throw throwDAOException(e, INSERT);
        }
        finally{
            fechar(stmt);
            conexao.desconectar(con);
        }
    }

    public int atualizar(Caminhao caminhao){
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "UPDATE caminhao SET placa = ?, status = ?, km_rodados - ?, modelo = ?, capacidade = ?, idFrota = ? WHERE id = ?";

        try {

            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, caminhao.getPlaca());
            stmt.setString(2, caminhao.getStatus());
            stmt.setInt(3, caminhao.getKmRodados());
            stmt.setString(4, caminhao.getModelo());
            stmt.setInt(5, caminhao.getCapacidade());
            stmt.setLong(6, caminhao.getIdFrota());
            stmt.setLong(7, caminhao.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        }
        catch (SQLException e){
            log.error("Erro ao atualizar caminhão", e);
            throw throwDAOException(e, UPDATE);
        }
        finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int atualizarPlaca(Caminhao caminhao){
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "UPDATE caminhao SET placa = ? WHERE id = ?";

        try {
            
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, caminhao.getPlaca());
            stmt.setLong(2, caminhao.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        }
        catch (SQLException e){
            log.error("Erro ao atualizar placa do caminhão", e);
            throw throwDAOException(e, UPDATE);
        }
        finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }


    public int atualizarStatus(Caminhao caminhao){
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "UPDATE caminhao SET status = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, caminhao.getStatus());
            stmt.setLong(2, caminhao.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;
        }
        catch (SQLException e){
            log.error("Erro ao atualizar status do caminhão", e);
            throw throwDAOException(e, UPDATE);
        }
        finally{
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }


    public int atualizarKmRodados(Caminhao caminhao){
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "UPDATE caminhao SET kms_rodados = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, caminhao.getKmRodados());
            stmt.setLong(2, caminhao.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e){
            log.error("Erro ao atualizar kilometragem do caminhão", e);
            throw throwDAOException(e, UPDATE);
        }
        finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }

    public int atualizarIdFrota(Caminhao caminhao){
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection conn = null;
        
        String sql = "UPDATE caminhao SET id_frota = ? WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, caminhao.getIdFrota());
            stmt.setLong(2, caminhao.getId());

            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e){
            log.error("Erro ao atualizar frota do caminhão", e);
            throw throwDAOException(e, UPDATE);
        }
        finally {
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }



    public int deletar(long id){
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "DELETE FROM caminhao WHERE id = ?";

        try {
            
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            stmt.setLong(1, id);


            if (stmt.executeUpdate() > 0) return 1;
            return 0;

        } catch (SQLException e){
            log.error("Erro ao deletar caminhão", e);
            throw throwDAOException(e, DELETE);
        }
        finally{
            fechar(stmt);
            conexao.desconectar(conn);
        }
    }


    public Caminhao buscarPorId(long id) {
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        String sql = "SELECT * FROM caminhao WHERE id = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, id);

            rs = stmt.executeQuery();

            if (rs.next()){

                long idCaminhao = rs.getLong("id");
                String placa = rs.getString("placa");
                String status = rs.getString("status");
                int km_rodados = rs.getInt("kms_rodados");
                String modelo = rs.getString("modelo");
                int capacidade = rs.getInt("capacidade");
                long id_frota = rs.getLong("id_frota");

                return new Caminhao(idCaminhao, placa, status, km_rodados, modelo, capacidade, id_frota);
            }
            return null;

        } catch (SQLException e){
            log.error("Erro ao consultar caminhão", e);
            throw throwDAOException(e, SELECT);
        }
        finally{
            fechar(stmt, rs);
            conexao.desconectar(conn);
        }
    }

    public List<Caminhao> buscarTodos() {
        List<Caminhao> caminhoes = new ArrayList<>();
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        String sql = "SELECT * FROM caminhao";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()){

                long idCaminhao = rs.getLong("id");
                String placa = rs.getString("placa");
                String status = rs.getString("status");
                int kms_rodados = rs.getInt("kms_rodados");
                String modelo = rs.getString("modelo");
                int capacidade = rs.getInt("capacidade");
                long id_frota = rs.getLong("id_frota");

                Caminhao caminhao = new Caminhao(idCaminhao, placa, status, kms_rodados, modelo,
                        capacidade, id_frota);
                caminhoes.add(caminhao);
            }
            return caminhoes;

        } catch (SQLException e){
            log.error("Erro ao consultar caminhão", e);
            throw throwDAOException(e, SELECT);
        } finally{
            fechar(stmt, rs);
            conexao.desconectar(conn);
        }

    }

    public List<Caminhao> buscarPorEmpresa(long id_empresa) {
        List<Caminhao> caminhoes = new ArrayList<>();
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        String sql = "SELECT * FROM caminhao JOIN frota ON caminhao.id_frota = frota.id WHERE frota.id_empresa = ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id_empresa);

            rs = stmt.executeQuery();

            while (rs.next()){

                long idCaminhao = rs.getLong("id");
                String placa = rs.getString("placa");
                String status = rs.getString("status");
                int kms_rodados = rs.getInt("kms_rodados");
                String modelo = rs.getString("modelo");
                int capacidade = rs.getInt("capacidade");
                long id_frota = rs.getLong("id_frota");

                Caminhao caminhao = new Caminhao(idCaminhao, placa, status, kms_rodados, modelo,
                        capacidade, id_frota);
                caminhoes.add(caminhao);
            }
            return caminhoes;

        } catch (SQLException e){
            log.error("Erro ao consultar caminhão", e);
            throw throwDAOException(e, SELECT);
        } finally{
            fechar(stmt, rs);
            conexao.desconectar(conn);
        }

    }public List<Caminhao> buscarPorEmpresaComPlaca(long id_empresa, String placa) {
        List<Caminhao> caminhoes = new ArrayList<>();
        Conexao conexao = new Conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        String sql = "SELECT * FROM caminhao JOIN frota ON caminhao.id_frota = frota.id WHERE frota.id_empresa = ? " +
                "AND placa ILIKE ?";

        try {
            conn = conexao.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id_empresa);
            stmt.setString(2, "%"+placa+"%");

            rs = stmt.executeQuery();

            while (rs.next()){

                long idCaminhao = rs.getLong("id");
                String placaCaminhao = rs.getString("placa");
                String status = rs.getString("status");
                int kms_rodados = rs.getInt("kms_rodados");
                String modelo = rs.getString("modelo");
                int capacidade = rs.getInt("capacidade");
                long id_frota = rs.getLong("id_frota");

                Caminhao caminhao = new Caminhao(idCaminhao, placaCaminhao, status, kms_rodados, modelo,
                        capacidade, id_frota);
                caminhoes.add(caminhao);
            }
            return caminhoes;

        } catch (SQLException e){
            log.error("Erro ao consultar caminhão", e);
            throw throwDAOException(e, SELECT);
        } finally{
            fechar(stmt, rs);
            conexao.desconectar(conn);
        }

    }
}

