/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jeferson
 */
public class VeiculoDao {
 Connection con;
    
    public VeiculoDao() throws SQLException{
        con = ConnectionFactory.getConnection();
    }
    
   
    
     //listagem de usuarios na tabela do formulario   ---   R
    
    public ArrayList<Veiculo> read(){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbveiculo ORDER BY marca ASC");
            rs = stmt.executeQuery();
            while(rs.next()){
                Veiculo v = new Veiculo();
                v.setId(rs.getInt("id"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setAno(rs.getInt("ano"));
                v.setCor(rs.getString("cor"));
                v.setPlaca(rs.getString("placa"));
                v.setMotor(rs.getString("motor"));
                v.setKm(rs.getString("km"));
                v.setValor(rs.getDouble("valor"));
                v.setValorFipe(rs.getDouble("valorfipe"));                
                
                veiculos.add(v);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro:"+e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return (ArrayList<Veiculo>) veiculos;
    }
    
     public ArrayList<Veiculo> readPesq(){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbveiculo WHERE marca LIKE ?");
            rs = stmt.executeQuery();
            while(rs.next()){
                Veiculo v = new Veiculo();
                v.setId(rs.getInt("id"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setAno(rs.getInt("ano"));
                v.setCor(rs.getString("cor"));
                v.setPlaca(rs.getString("placa"));
                v.setMotor(rs.getString("motor"));
                v.setKm(rs.getString("km"));
                v.setValor(rs.getDouble("valor"));
                v.setValorFipe(rs.getDouble("valorfipe"));                
                
                veiculos.add(v);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro:"+e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return (ArrayList<Veiculo>) veiculos;
    }
    
    
   // SALVA O USUARIO NO BANCO DE DADOS   ---- C
    public void create(Veiculo v){
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO tbveiculo (marca,"
                    + "modelo,ano,cor,placa,motor,km,valor,valorfipe) VALUE (?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, v.getMarca());
            stmt.setString(2, v.getModelo());
            stmt.setInt(3, v.getAno());
            stmt.setString(4, v.getCor());
            stmt.setString(5, v.getPlaca());
            stmt.setString(6, v.getMotor());
            stmt.setString(7, v.getKm());
            stmt.setDouble(8, v.getValor());
            stmt.setDouble(9, v.getValorFipe());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "O "+v.getModelo()
                    +" Salvo com Sucesso!!");
   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    //ALTERAR O USUARIO NO BANCO DE DADOS   -- U 
    public void update(Veiculo v){
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbusuario SET marca = ?,"
                    + "modelo = ?, ano = ? ,cor = ?, placa = ?, motor = ?, km = ?, valor = ?, valorfipe = ? WHERE id = ?");
           stmt.setString(1, v.getMarca());
            stmt.setString(2, v.getModelo());
            stmt.setInt(3, v.getAno());
            stmt.setString(4, v.getCor());
            stmt.setString(5, v.getPlaca());
            stmt.setString(6, v.getMotor());
            stmt.setString(7, v.getKm());
            stmt.setDouble(8, v.getValor());
            stmt.setDouble(9, v.getValorFipe());
            stmt.setInt(10, v.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario "+v.getModelo()
                    +" Modificado com Sucesso!!");
   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    //excluir do banco de dados   --- D
    public void delete(Veiculo v){
            PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbveiculo WHERE id = ?");
           
            stmt.setInt   (1, v.getId());
            
            if (JOptionPane.showConfirmDialog(null, "Tem certeza que"
                    + " deseja excluir este Veiculo","Exclusão",
                    JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null, "Veiculo excluído com Sucesso!!");
                stmt.executeUpdate();
            }else{
                JOptionPane.showMessageDialog(null, "A exclusão do Veiculo Cancelado(a)com Sucesso!!");
            }
   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
   
}
