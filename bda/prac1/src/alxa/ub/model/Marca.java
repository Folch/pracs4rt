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
public class Marca {
    
    private String nom;
    /**
     * Constructor
     */
    public Marca() { }
    /**
     * Constructor
     * @param nom 
     */
    public Marca(String nom) {
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
     * Mètode per mostrar per pantalla una marca
     * @return 
     */
    @Override
    public String toString() {
        return "Marca{ " + nom + " }";
    }
    
    
    
}
