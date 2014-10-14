/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub.model;

/**
 *
 * @author zenbook
 */
public class Producte {
    
    private int idProducte;
    private String nom;
    private String color;
    
    private SubFamilia subFamilia;
    /**
     * Constructor
     */
    public Producte() {
    }
    /**
     * Constructor
     * @param idProducte
     * @param nom
     * @param color
     * @param subFamilia 
     */
    public Producte(int idProducte, String nom, String color, SubFamilia subFamilia) {
        this.idProducte = idProducte;
        this.nom = nom;
        this.color = color;
        this.subFamilia = subFamilia;
    }
    /**
     * Getter
     * @return 
     */
    public int getIdProducte() {
        return idProducte;
    }
    /**
     * Setter
     * @param idProducte 
     */
    public void setIdProducte(int idProducte) {
        this.idProducte = idProducte;
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
    public String getColor() {
        return color;
    }
    /**
     * Setter
     * @param color 
     */
    public void setColor(String color) {
        this.color = color;
    }
    /**
     * Getter
     * @return 
     */
    public SubFamilia getSubFamilia() {
        return subFamilia;
    }
    /**
     * Setter
     * @param subFamilia 
     */
    public void setSubFamilia(SubFamilia subFamilia) {
        this.subFamilia = subFamilia;
    }
    /**
     * Mètode per mostrar per pantalla un producte
     * @return 
     */
    @Override
    public String toString() {
        return "Producte{" + "idProducte=" + idProducte + ", nom=" + nom + ", color=" + color + ", subFamilia=" + subFamilia + '}';
    }
    
    
}
