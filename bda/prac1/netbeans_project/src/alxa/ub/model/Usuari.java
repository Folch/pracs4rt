/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub.model;

import java.util.Set;

/**
 *
 * @author Albert i Xavi
 */
public class Usuari {
    private String nom;
    private String contrasenya;
    private String direccio;
    
    private Set<Article> articles;
    private Set<Campanya> campanyes;
    /**
     * Constructor
     */
    public Usuari() {
    }
    /**
     * Constructor
     * @param nom
     * @param contrasenya
     * @param direccio
     * @param articles
     * @param campanyes 
     */
    public Usuari(String nom, String contrasenya, String direccio, Set<Article> articles, Set<Campanya> campanyes) {
        this.nom = nom;
        this.contrasenya = contrasenya;
        this.direccio = direccio;
        this.campanyes = campanyes;
        this.articles = articles;
    }
    /**
     * Getter
     * @return 
     */
    public String getNom() {
        return nom;
    }
    /**
     * Setter
     * @param nom 
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     * Getter
     * @return 
     */
    public String getContrasenya() {
        return contrasenya;
    }
    /**
     * Setter
     * @param contrasenya 
     */
    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
    /**
     * Getter
     * @return 
     */
    public String getDireccio() {
        return direccio;
    }
    /**
     * Setter
     * @param direccio 
     */
    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }
    /**
     * Getter
     * @return 
     */
    public Set<Article> getArticles() {
        return articles;
    }
    /**
     * Setter
     * @param articles 
     */
    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
    /**
     * Getter
     * @return 
     */
    public Set<Campanya> getCampanyes() {
        return campanyes;
    }
    /**
     * Setter
     * @param campanyes 
     */
    public void setCampanyes(Set<Campanya> campanyes) {
        this.campanyes = campanyes;
    }
    /**
     * Mètode per mostrar un usuari per pantalla
     * @return 
     */
    @Override
    public String toString() {
        return "Usuari{" + "nom=" + nom + ", contrasenya=" + contrasenya + ", direccio=" + direccio + ", articles=" + articles + ", campanyes=" + campanyes + '}';
    }
    
    
    
}
