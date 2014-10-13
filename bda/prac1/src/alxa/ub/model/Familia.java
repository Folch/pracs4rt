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
public class Familia {
    private String nom;
    private int idFamilia;
    
    public Familia() { }
    
    public Familia(int idFamilia, String nom) {
        this.idFamilia = idFamilia;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(int idFamilia) {
        this.idFamilia = idFamilia;
    }
    
    
}
