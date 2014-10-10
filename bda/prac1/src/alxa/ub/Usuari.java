/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub;

import java.util.Set;

/**
 *
 * @author zenbook
 */
public class Usuari {
    private String nom;
    private String contrasenya;
    private String direccio;
    
    private Set<Article> articles;
    private Set<Campanya> campanyes;

    public Usuari() {
    }

    public Usuari(String nom, String contrasenya, String direccio) {
        this.nom = nom;
        this.contrasenya = contrasenya;
        this.direccio = direccio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }
    
}
