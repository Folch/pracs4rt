/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub;

/**
 *
 * @author zenbook
 */
public class UsuariArticleCampanya {
    private int unitatsComprades;
    private float preuTotal;
    private float impostes;
    
    private Usuari usuari;
    private Article article;
    private Campanya campanya;

    public UsuariArticleCampanya() {
    }

    public UsuariArticleCampanya(int unitatsComprades, float preuTotal, float impostes, Usuari usuari, Article article, Campanya campanya) {
        this.unitatsComprades = unitatsComprades;
        this.preuTotal = preuTotal;
        this.impostes = impostes;
        this.usuari = usuari;
        this.article = article;
        this.campanya = campanya;
    }

    public int getUnitatsComprades() {
        return unitatsComprades;
    }

    public void setUnitatsComprades(int unitatsComprades) {
        this.unitatsComprades = unitatsComprades;
    }

    public float getPreuTotal() {
        return preuTotal;
    }

    public void setPreuTotal(float preuTotal) {
        this.preuTotal = preuTotal;
    }

    public float getImpostes() {
        return impostes;
    }

    public void setImpostes(float impostes) {
        this.impostes = impostes;
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

    public Campanya getCampanya() {
        return campanya;
    }

    public void setCampanya(Campanya campanya) {
        this.campanya = campanya;
    }
    
    
}
