/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub;

import java.io.Serializable;

/**
 *
 * @author zenbook
 */
public class UsuariArticleCampanya implements Serializable{
    private int unitatsComprades;
    private float preuTotal;
    private float impostos;
    
    
    private Usuari usuari;
    private Article article;
    private Campanya campanya;
    
    public UsuariArticleCampanya() {
    }

    public UsuariArticleCampanya(int unitatsComprades, float preuTotal, float impostes) {
        this.unitatsComprades = unitatsComprades;
        this.preuTotal = preuTotal;
        this.impostos = impostes;
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

    public float getImpostos() {
        return impostos;
    }

    public void setImpostos(float impostos) {
        this.impostos = impostos;
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
