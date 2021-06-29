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
public class UsuarioDao {

    Connection con;

    public UsuarioDao() throws SQLException {
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
            stmt = con.prepareStatement("SELECT * FROM tbagenda"
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
            stmt = con.prepareStatement("INSERT INTO tbagenda (nome,"
                    + "email,senha,telefone,recado) VALUE (?,?,?,?,?)");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            cripto = u.criptografarSenha(u.getSenha());
            stmt.setString(3, cripto.toString());
            stmt.setString(4, u.getTelefone());
            stmt.setString(5, u.getRecado());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario " + u.getNome()
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
            stmt = con.prepareStatement("UPDATE tbagenda SET nome = ?,"
                    + "email = ?, senha = ? ,telefone = ?,recado = ?  WHERE id = ?");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            cripto = u.criptografarSenha(u.getSenha());
            stmt.setString(3, cripto.toString());
            stmt.setString(4, u.getTelefone());
            stmt.setString(5, u.getRecado());
            stmt.setInt(6, u.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario " + u.getNome()
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
            stmt = con.prepareStatement("SELECT * FROM tbagenda ORDER BY nome ASC");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setRecado(rs.getString("recado"));
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
            stmt = con.prepareStatement("SELECT * FROM tbagenda WHERE nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setRecado(rs.getString("recado"));
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
            stmt = con.prepareStatement("DELETE FROM tbagenda WHERE id = ?");

            stmt.setInt(1, u.getId());

            if (JOptionPane.showConfirmDialog(null, "Tem certeza que"
                    + " deseja excluir este Usuario(a)", "Exclusão",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Usuario(a) excluído(a)com Sucesso!!");
                stmt.executeUpdate();
            } else {
                JOptionPane.showMessageDialog(null, "A exclusão do Usuario(a) Cancelado(a)com Sucesso!!");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro:" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}
