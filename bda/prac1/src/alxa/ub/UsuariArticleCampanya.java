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

    public float getImpostes() {
        return impostos;
    }

    public void setImpostes(float impostes) {
        this.impostos = impostes;
    }
}
