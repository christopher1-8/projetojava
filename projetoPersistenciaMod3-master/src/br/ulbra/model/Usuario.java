package br.ulbra.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jeferson
 */
public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String recado;
    
    public Usuario() {
        
    }
    
    public BigInteger criptografarSenha(String senha) throws NoSuchAlgorithmException {
        MessageDigest md5;
        BigInteger senhacrip = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(senha.getBytes(), 0, senha.length());
            senhacrip = new BigInteger(1, md5.digest());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return senhacrip;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getRecado() {
        return recado;
    }
    
   
    
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", telefone=" + telefone +  ", recado=" + recado + '}';
    }
   public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setRecado(String recado) {
        this.recado = recado;
    }
    private static final Logger LOG = Logger.getLogger(Usuario.class.getName());

    
}
