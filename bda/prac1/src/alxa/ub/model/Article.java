/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub.model;

import java.util.AbstractSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author zenbook
 */
public class Article {
    
    private int idArticle;
    private String talla;
    private String color;
    private int stock;
    private float preu;
    private float iva;
    
    private Marca marca;
    private Producte producte;
    private Set<Usuari> usuaris;
    private Set<Campanya> campanyes;

    public Article() {
    }

    public Article(String talla, String color, int stock, float preu, float iva, Marca marca, Producte producte) {
        this.talla = talla;
        this.color = color;
        this.stock = stock;
        this.preu = preu;
        this.iva = iva;
        this.marca = marca;
        this.producte = producte;
        this.campanyes = new TreeSet<>();
        this.usuaris = new TreeSet<>();
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public Producte getProducte() {
        return producte;
    }

    public void setProducte(Producte producte) {
        this.producte = producte;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Set<Usuari> getUsuaris() {
        return usuaris;
    }

    public void setUsuaris(Set<Usuari> usuaris) {
        this.usuaris = usuaris;
    }

    public Set<Campanya> getCampanyes() {
        return campanyes;
    }

    public void setCampanyes(Set<Campanya> campanyes) {
        this.campanyes = campanyes;
    }

    @Override
    public String toString() {
        
        return "Article{" + "idArticle=" + idArticle + ", talla=" + talla + ", color=" + color + ", stock=" + stock + ", preu=" + preu + ", iva=" + iva + ", marca=" + (marca!=null?marca.getNom():"No té marca") + ", producte=" + (producte!=null?producte.getNom():"No té un producte referenciat") + '}';
    }
    
    
}
