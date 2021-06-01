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
    private String tipo;
    
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
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo.toUpperCase();
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", tipo=" + tipo + '}';
    }
    
}
