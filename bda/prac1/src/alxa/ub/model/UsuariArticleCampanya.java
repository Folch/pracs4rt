/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub.model;

import java.io.Serializable;

/**
 * Aquesta classe representa una compra que un usari a fet en una campanya comprant un article
 * @author Albert i Xavi
 */
public class UsuariArticleCampanya implements Serializable{
    private int unitatsComprades;
    private float preuTotal;
    private float impostos;
    
    
    private Usuari usuari;
    private Article article;
    private Campanya campanya;
    /**
     * Constructor
     */
    public UsuariArticleCampanya() {
    }
    /**
     * Constructor
     * @param unitatsComprades
     * @param preuTotal
     * @param impostes 
     */
    public UsuariArticleCampanya(int unitatsComprades, float preuTotal, float impostes) {
        this.unitatsComprades = unitatsComprades;
        this.preuTotal = preuTotal;
        this.impostos = impostes;
    }
    /**
     * Getter
     * @return 
     */
    public int getUnitatsComprades() {
        return unitatsComprades;
    }
    /**
     * Setter
     * @param unitatsComprades 
     */
    public void setUnitatsComprades(int unitatsComprades) {
        this.unitatsComprades = unitatsComprades;
    }
    /**
     * Getter
     * @return 
     */
    public float getPreuTotal() {
        return preuTotal;
    }
    /**
     * Setter
     * @param preuTotal 
     */
    public void setPreuTotal(float preuTotal) {
        this.preuTotal = preuTotal;
    }
    /**
     * Getter
     * @return 
     */
    public float getImpostos() {
        return impostos;
    }
    /**
     * Setter
     * @param impostos 
     */
    public void setImpostos(float impostos) {
        this.impostos = impostos;
    }
    /**
     * Getter
     * @return 
     */
    public Usuari getUsuari() {
        return usuari;
    }
    /**
     * Setter
     * @param usuari 
     */
    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }
    /**
     * Getter
     * @return 
     */
    public Article getArticle() {
        return article;
    }
    /**
     * Setter
     * @param article 
     */
    public void setArticle(Article article) {
        this.article = article;
    }
    /**
     * Getter
     * @return 
     */
    public Campanya getCampanya() {
        return campanya;
    }
    /**
     * Setter
     * @param campanya 
     */
    public void setCampanya(Campanya campanya) {
        this.campanya = campanya;
    }
    /**
     * Mètode per mostrar per pantalla un usuariarticlecampanya
     * @return 
     */
    @Override
    public String toString() {
        return "UsuariArticleCampanya{" + "unitatsComprades=" + unitatsComprades + ", preuTotal=" + preuTotal + ", impostos=" + impostos + ", usuari=" + usuari + ", article=" + article + ", campanya=" + campanya + '}';
    }
}
