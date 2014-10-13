/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub.model;

import java.io.Serializable;

/**
 *
 * @author zenbook
 */
public class SubFamilia implements Serializable{
    private String nom;
    private int idSubFamilia;
    
    private Familia familia;

    public SubFamilia() {}
    
    public SubFamilia(String nom, int idSubFamilia, Familia familia) {
        this.nom = nom;
        this.idSubFamilia = idSubFamilia;
        this.familia = familia;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdSubFamilia() {
        return idSubFamilia;
    }

    public void setIdSubFamilia(int idSubFamilia) {
        this.idSubFamilia = idSubFamilia;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    @Override
    public String toString() {
        return "SubFamilia{" + "nom=" + nom + ", idSubFamilia=" + idSubFamilia + ", familia=" + familia + '}';
    }
    
    
    
    
}
