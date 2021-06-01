/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class fabricante {
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
    
    public String getmarca() {
        return marca;
    }
    
    public void setmarca(String nome) {
        this.marca = marca.toUpperCase();
    }
    
    public String gettelefoe() {
        return telefone;
    }
    
    public void setEmail(String email) {
        this.telefone = telefone.toLowerCase();
    }
    
    public String getsite() {
        return site;
    }
    
    public void setsite(String  site) {
        this.site = site;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setemail (String email ) {
        this.email  = email .toUpperCase();
    }
    
    @Override
    public String toString() {
        return "Fabricante{" + "id=" + id + ", marca=" + marca + ", telefone=" + telefone + ", site=" + site + ", email=" + email + '}';
    }
    
}
  
}
