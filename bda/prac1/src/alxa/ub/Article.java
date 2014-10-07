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
public class Article {
    
    private int idArticle;
    private String talla;
    private String color;
    private int stock;
    private float preu;
    private float iva;
    
    private Marca marca;
    private Producte producte;
    private Campanya campanya;

    public Article() {
    }

    public Article(int idArticle, String talla, String color, int stock, float preu, float iva, Producte producte, Campanya campanya) {
        this.idArticle = idArticle;
        this.talla = talla;
        this.color = color;
        this.stock = stock;
        this.preu = preu;
        this.iva = iva;
        this.producte = producte;
        this.campanya = campanya;
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

    public Campanya getCampanya() {
        return campanya;
    }

    public void setCampanya(Campanya campanya) {
        this.campanya = campanya;
    }
    
}
