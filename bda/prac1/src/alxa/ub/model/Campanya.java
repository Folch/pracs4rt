/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub.model;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author zenbook
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
    
    public Campanya() { }

    public Campanya(int idCampanya, String nom, Date data_fi, Date data_inici, int numArticles, float imprt, Set<Usuari> usuari, Set<Article> article) {
        this.idCampanya = idCampanya;
        this.nom = nom;
        this.data_fi = data_fi;
        this.data_inici = data_inici;
        this.numArticles = numArticles;
        this.imprt = imprt;
        this.usuaris = usuari;
        this.articles = article;
    }

    public int getIdCampanya() {
        return idCampanya;
    }

    public void setIdCampanya(int idCampanya) {
        this.idCampanya = idCampanya;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getData_fi() {
        return data_fi;
    }

    public void setData_fi(Date data_fi) {
        this.data_fi = data_fi;
    }

    public Date getData_inici() {
        return data_inici;
    }

    public void setData_inici(Date data_inici) {
        this.data_inici = data_inici;
    }

    public int getNumArticles() {
        return numArticles;
    }

    public void setNumArticles(int numArticles) {
        this.numArticles = numArticles;
    }

    public float getImprt() {
        return imprt;
    }

    public void setImprt(float imprt) {
        this.imprt = imprt;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public Set<Usuari> getUsuaris() {
        return usuaris;
    }

    public void setUsuaris(Set<Usuari> usuaris) {
        this.usuaris = usuaris;
    }

    @Override
    public String toString() {
        return "Campanya{" + "idCampanya=" + idCampanya + ", nom=" + nom + ", data_fi=" + data_fi + ", data_inici=" + data_inici + ", numArticles=" + numArticles + ", imprt=" + imprt + ", articles=" + articles + ", usuaris=" + usuaris + '}';
    }

    


}
