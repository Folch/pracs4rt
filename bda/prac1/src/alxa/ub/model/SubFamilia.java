/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub.model;

import java.io.Serializable;

/**
 *
 * @author Albert i Xavi
 */
public class SubFamilia implements Serializable{
    private String nom;
    private int idSubFamilia;
    
    private Familia familia;
    /**
     * Constructor
     */
    public SubFamilia() {}
    /**
     * Constructor
     * @param nom
     * @param familia 
     */
    public SubFamilia(String nom, Familia familia) {
        this.nom = nom;
        this.familia = familia;
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
    public int getIdSubFamilia() {
        return idSubFamilia;
    }
    /**
     * Setter
     * @param idSubFamilia 
     */
    public void setIdSubFamilia(int idSubFamilia) {
        this.idSubFamilia = idSubFamilia;
    }
    /**
     * Getter
     * @return 
     */
    public Familia getFamilia() {
        return familia;
    }
    /**
     * Setter
     * @param familia 
     */
    public void setFamilia(Familia familia) {
        this.familia = familia;
    }
    /**
     * Mètode per mostrar per pantalla una subfamilia
     * @return 
     */
    @Override
    public String toString() {
        return "SubFamilia{" + "nom=" + nom + ", idSubFamilia=" + idSubFamilia + ", familia=" + (familia!=null?familia.getNom():"No té una familia referenciada") + '}';
    }
    
    
    
    
}
