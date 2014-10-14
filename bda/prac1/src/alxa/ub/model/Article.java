/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alxa.ub.model;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Albert i Xavi
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
    /**
     * Constructor
     */
    public Article() {
    }
    /**
     * Constructor
     * @param talla
     * @param color
     * @param stock
     * @param preu
     * @param iva
     * @param marca
     * @param producte 
     */
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
    /**
     * Getter
     * @return 
     */
    public int getIdArticle() {
        return idArticle;
    }
    /**
     * Setter
     * @param idArticle 
     */
    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }
    /**
     * Getter
     * @return 
     */
    public String getTalla() {
        return talla;
    }
    /**
     * Setter
     * @param talla 
     */
    public void setTalla(String talla) {
        this.talla = talla;
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
    public int getStock() {
        return stock;
    }
    /**
     * Setter
     * @param stock 
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    /**
     * Getter
     * @return 
     */
    public float getPreu() {
        return preu;
    }
    /**
     * Setter
     * @param preu 
     */
    public void setPreu(float preu) {
        this.preu = preu;
    }
    /**
     * Getter
     * @return 
     */
    public float getIva() {
        return iva;
    }
    /**
     * Setter
     * @param iva 
     */
    public void setIva(float iva) {
        this.iva = iva;
    }
    /**
     * Getter
     * @return 
     */
    public Producte getProducte() {
        return producte;
    }
    /**
     * Setter
     * @param producte 
     */
    public void setProducte(Producte producte) {
        this.producte = producte;
    }
    /**
     * Getter
     * @return 
     */
    public Marca getMarca() {
        return marca;
    }
    /**
     * Setter
     * @param marca 
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    /**
     * Getter
     * @return 
     */
    public Set<Usuari> getUsuaris() {
        return usuaris;
    }
    /**
     * Setter
     * @param usuaris 
     */
    public void setUsuaris(Set<Usuari> usuaris) {
        this.usuaris = usuaris;
    }
    /**
     * Getter
     * @return 
     */
    public Set<Campanya> getCampanyes() {
        return campanyes;
    }
    /**
     * Setter
     * @param campanyes 
     */
    public void setCampanyes(Set<Campanya> campanyes) {
        this.campanyes = campanyes;
    }

    /**
     * Mètode per mostrar per pantalla un article
     * @return 
     */
    @Override
    public String toString() {
        
        return "Article{" + "idArticle=" + idArticle + ", talla=" + talla + ", color=" + color + ", stock=" + stock + ", preu=" + preu + ", iva=" + iva + ", marca=" + (marca!=null?marca.getNom():"No té marca") + ", producte=" + (producte!=null?producte.getNom():"No té un producte referenciat") + '}';
    }
    
    
}
