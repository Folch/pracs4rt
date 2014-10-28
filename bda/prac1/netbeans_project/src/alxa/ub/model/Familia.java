/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub.model;

/**
 *
 * @author Albert i Xavi
 */
public class Familia {
    private String nom;
    private int idFamilia;
    /**
     * Constructor
     */
    public Familia() { }
    /**
     * Constructor
     * @param nom 
     */
    public Familia(String nom) {
        this.nom = nom;
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
    public int getIdFamilia() {
        return idFamilia;
    }
    /**
     * Setter
     * @param idFamilia 
     */
    public void setIdFamilia(int idFamilia) {
        this.idFamilia = idFamilia;
    }

    /**
     * Mètode per mostrar per pantalla una familia
     * @return 
     */
    @Override
    public String toString() {
        return "Familia{" + "nom=" + nom + ", idFamilia=" + idFamilia + '}';
    }
    
    
}
