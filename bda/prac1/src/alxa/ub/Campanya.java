/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub;

import java.util.Date;

/**
 *
 * @author zenbook
 */
public class Campanya {

    private String nom;
    private Date data_fi;
    private Date data_inici;
    private int numArticles;
    private float imprt;
    
    private Usuari usuari;
    private Article article;
    
    public Campanya() { }

    public Campanya(String nom, Date data_fi, Date data_inici, int numArticles, float imprt, Usuari usuari, Article article) {
        this.nom = nom;
        this.data_fi = data_fi;
        this.data_inici = data_inici;
        this.numArticles = numArticles;
        this.imprt = imprt;
        this.usuari = usuari;
        this.article = article;
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

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    
}
