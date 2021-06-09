package br.ulbra.model;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author Jeferson
 */
public class fabricanteDao {

    Connection con;

    public fabricanteDao() throws SQLException {
        con = ConnectionFactory.getConnection();
    }

    public boolean checkLogin(String email, String senha) throws NoSuchAlgorithmException {

        PreparedStatement stmt = null;
        Usuario u = new Usuario();
        ResultSet rs = null;
        boolean check = false;
        BigInteger cripto;
        cripto = u.criptografarSenha(senha);

        try {
            stmt = con.prepareStatement("SELECT * FROM tbfabricante"
                    + " where email = ? AND senha = ?");
            stmt.setString(1, email);
            stmt.setString(2, cripto.toString());
            rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        return check;
    }

    // SALVA O USUARIO NO BANCO DE DADOS   ---- C
    public void create(fabricante f) throws NoSuchAlgorithmException {
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO tbfabricante   (marca,"
                    + "telefone,site,email) VALUE (?,?,?,?)");
            stmt.setString(1, f.getMarca());
            stmt.setString(2, f.getTelefone());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getSite());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Marca " + f.getMarca()
                    + " Salvo com Sucesso!!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    //ALTERAR O USUARIO NO BANCO DE DADOS   -- U 
    public void update( fabricante f) throws NoSuchAlgorithmException {
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE tbfabricante SET marca = ?,"
                    + "telefone = ?, site = ? ,email = ? WHERE id = ?");
          stmt.setString(1, f.getMarca());
            stmt.setString(2, f.getTelefone());
            stmt.setString(3, f.getEmail());
            stmt.setString(4, f.getSite());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Marca " + f.getMarca()
                    + " Salvo com Sucesso!!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //listagem de usuarios na tabela do formulario   ---   R

    public ArrayList<fabricante> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<fabricante> fabricantes = new ArrayList<fabricante>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbfabricante ORDER BY nome ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                fabricante fabricante = new fabricante();
                fabricante.setId(rs.getInt("marca"));
                fabricante.setTelefone(rs.getString("telefone"));
                fabricante.setSite(rs.getString("site"));
                fabricante.setEmail(rs.getString("email"));
                fabricantes.add(fabricante);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return (ArrayList<fabricante>) fabricantes;
    }

    public ArrayList<fabricante> readPesq(String nome) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<fabricante> Fabricantes = new ArrayList<fabricante>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbfabricante WHERE nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                fabricante Fabricante = new fabricante();
                Fabricante.setId(rs.getInt("id"));
                Fabricante.setMarca(rs.getString("marca"));
                Fabricante.setTelefone(rs.getString("telefone"));
                Fabricante.setSite(rs.getString("site"));
                Fabricante.setEmail(rs.getString("email"));
                Fabricantes.add(Fabricante);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return (ArrayList<fabricante>) Fabricantes;
    }

        
    //excluir do banco de dados   --- D
    public void delete(fabricante f) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbfabricante WHERE id = ?");

            stmt.setInt(1, f.getId());

            if (JOptionPane.showConfirmDialog(null, "Tem certeza que"
                    + " deseja excluir este marca(a)", "Exclusão",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Marca(a) excluído(a)com Sucesso!!");
                stmt.executeUpdate();
            } else {
                JOptionPane.showMessageDialog(null, "A exclusão do Marca(a) Cancelado(a)com Sucesso!!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}
