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

    public Producte() {
    }

    public Producte(int idProducte, String nom, String color, SubFamilia subFamilia) {
        this.idProducte = idProducte;
        this.nom = nom;
        this.color = color;
        this.subFamilia = subFamilia;
    }

    public int getIdProducte() {
        return idProducte;
    }

    public void setIdProducte(int idProducte) {
        this.idProducte = idProducte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public SubFamilia getSubFamilia() {
        return subFamilia;
    }

    public void setSubFamilia(SubFamilia subFamilia) {
        this.subFamilia = subFamilia;
    }

    @Override
    public String toString() {
        return "Producte{" + "idProducte=" + idProducte + ", nom=" + nom + ", color=" + color + ", subFamilia=" + subFamilia + '}';
    }
    
    
}
