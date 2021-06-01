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
            stmt = con.prepareStatement("SELECT * FROM tbusuario"
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
    public void create(Usuario u) throws NoSuchAlgorithmException {
        PreparedStatement stmt = null;
        BigInteger cripto;
        try {
            stmt = con.prepareStatement("INSERT INTO tbfabricante   (marca,"
                    + "telefone,site,email) VALUE (?,?,?,?)");
            stmt.setString(1, u.getmarca());
            stmt.setString(2, u.gettelefone());
            cripto = u.criptografarSenha(u.getSenha());
            stmt.setString(3, cripto.toString());
            stmt.setString(4, u.getsite());
            stmt.setString(5, u.getemail());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Marca " + u.getmarca()
                    + " Salvo com Sucesso!!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    //ALTERAR O USUARIO NO BANCO DE DADOS   -- U 
    public void update(Usuario u) throws NoSuchAlgorithmException {
        PreparedStatement stmt = null;
        BigInteger cripto;
        try {
            stmt = con.prepareStatement("UPDATE tbfabricante SET marca = ?,"
                    + "telefone = ?, site = ? ,email = ? WHERE id = ?");
            stmt.setString(1, u.getmarca());
            stmt.setString(2, u.gettelefone());
            cripto = u.criptografarSenha(u.getSenha());
            stmt.setString(3, cripto.toString());
            stmt.setString(4, u.getemail());
            stmt.setInt(5, u.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "fabrica " + u.getMarca()
                    + " Modificado com Sucesso!!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //listagem de usuarios na tabela do formulario   ---   R

    public ArrayList<Usuario> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbfabricante ORDER BY nome ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("marca"));
                usuario.setNome(rs.getString("telefone"));
                usuario.setEmail(rs.getString("site"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("email"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return (ArrayList<Usuario>) usuarios;
    }

    public ArrayList<Usuario> readPesq(String nome) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            stmt = con.prepareStatement("SELECT * FROM tbfabricante WHERE nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setMarca(rs.getString("marca"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setSite(rs.getString("site"));
                usuario.setEmail(rs.getString("email"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return (ArrayList<Usuario>) usuarios;
    }

        
    //excluir do banco de dados   --- D
    public void delete(Usuario u) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM tbfabricante WHERE id = ?");

            stmt.setInt(1, u.getId());

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
