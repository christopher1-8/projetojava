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
public class ModeloDao {
 Connection con;
    
    public ModeloDao() throws SQLException{
        con = ConnectionFactory.getConnection();
    }
    
   
    
     //listagem de usuarios na tabela do formulario   ---   R
    
    public ArrayList<Modelo> read(){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Modelo> Modelos = new ArrayList<Modelo>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbmodelo ORDER BY marca ASC");
            rs = stmt.executeQuery();
            while(rs.next()){
                Modelo m = new Modelo();
                m.setId(rs.getInt("id"));
               m.setModelo(rs.getString("modelo"));
                m.setMarca(rs.getString("marca"));
                m.setAno(rs.getInt("ano"));
                      
                
                Modelos.add(m);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro:"+e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return (ArrayList<Modelo>) Modelos;
    }
    
     public ArrayList<Modelo> readPesq(){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Modelo> Modelos = new ArrayList<Modelo>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbmodelo WHERE marca LIKE ?");
            rs = stmt.executeQuery();
            while(rs.next()){
                Modelo m = new Modelo();
                    m.setId(rs.getInt("id"));
               m.setModelo(rs.getString("modelo"));
                m.setMarca(rs.getString("marca"));
                m.setAno(rs.getInt("ano"));
                              
                
                Modelos.add(m);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro:"+e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return (ArrayList<Modelo>) Modelos;
    }
    
    
   // SALVA O USUARIO NO BANCO DE DADOS   ---- C
    public void create(Modelo m){
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO tbmodelo (marca,"
                    + "modelo,ano) VALUE (?,?,?)");
            stmt.setString(1, m.getMarca());
            stmt.setString(2, m.getModelo());
            stmt.setInt(3, m.getAno());
        
            
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "O "+m.getModelo()
                    +" Salvo com Sucesso!!");
   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    //ALTERAR O USUARIO NO BANCO DE DADOS   -- U 
    public void update(Modelo m){
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE tbmodelo SET marca = ? ,modelo = ?, ano = ?");
           stmt.setString(1, m.getMarca());
            stmt.setString(2, m.getModelo());
            stmt.setInt(3, m.getAno());
         
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Modelo "+m.getModelo()
                    +" Modificado com Sucesso!!");
   
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    //excluir do banco de dados   --- D
    public void delete(Modelo m){
            PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbveiculo WHERE id = ?");
           
            stmt.setInt   (1, m.getId());
            
            if (JOptionPane.showConfirmDialog(null, "Tem certeza que"
                    + " deseja excluir este Modelo","Exclusão",
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
