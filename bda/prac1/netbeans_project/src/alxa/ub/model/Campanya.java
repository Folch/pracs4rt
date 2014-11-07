/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub.model;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Albert i Xavi
 */
public class Campanya {

    private int idCampanya;
    private String nom;
    private Date data_fi;
    private Date data_inici;
    private int numArticles;
    private float imprt;

    private Set<Article> articles;
    private Set<Usuari> usuaris;
    /**
     * Constructor
     */
    public Campanya() {
    }
    /**
     * Constructor
     * @param nom
     * @param data_fi
     * @param data_inici
     * @param numArticles
     * @param imprt 
     */
    public Campanya(String nom, Date data_fi, Date data_inici, int numArticles, float imprt) {
        this.nom = nom;
        this.data_fi = data_fi;
        this.data_inici = data_inici;
        this.numArticles = numArticles;
        this.imprt = imprt;
        this.usuaris = new TreeSet<>();
        this.articles = new TreeSet<>();
    }
    /**
     * Getter
     * @return 
     */
    public int getIdCampanya() {
        return idCampanya;
    }
    /**
     * Setter
     * @param idCampanya 
     */
    public void setIdCampanya(int idCampanya) {
        this.idCampanya = idCampanya;
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
    public Date getData_fi() {
        return data_fi;
    }
    /**
     * Setter
     * @param data_fi 
     */
    public void setData_fi(Date data_fi) {
        this.data_fi = data_fi;
    }
    /**
     * Getter
     * @return 
     */
    public Date getData_inici() {
        return data_inici;
    }
    /**
     * Setter
     * @param data_inici 
     */
    public void setData_inici(Date data_inici) {
        this.data_inici = data_inici;
    }
    /**
     * Getter
     * @return 
     */
    public int getNumArticles() {
        return numArticles;
    }
    /**
     * Setter
     * @param numArticles 
     */
    public void setNumArticles(int numArticles) {
        this.numArticles = numArticles;
    }
    /**
     * Getter
     * @return 
     */
    public float getImprt() {
        return imprt;
    }
    /**
     * Setter
     * @param imprt 
     */
    public void setImprt(float imprt) {
        this.imprt = imprt;
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
    public Set<Usuari> getUsuaris() {
        return usuaris;
    }
    /**
     * Setter
     * @param usuaris 
     */
    public void setUsuaris(Set<Usuari> usuaris) {
        this.usuaris = usuaris;
    }
    /**
     * Mètode per mostrar per pantalla una campanya
     * @return 
     */
    @Override
    public String toString() {
        return "Campanya{" + "idCampanya=" + idCampanya + ", nom=" + nom + ", data_fi=" + formatDate(data_fi) + ", data_inici=" + formatDate(data_inici) + ", numArticles=" + numArticles + ", imprt=" + imprt + '}';
    }
    /**
     * Mètode per mostrar la data en format dd/mm/aaaa
     * @param data
     * @return 
     */
    private String formatDate(Date data){
        return data.getDate()+"/"+(data.getMonth()+1)+"/"+(data.getYear()+1900);
    }

}
