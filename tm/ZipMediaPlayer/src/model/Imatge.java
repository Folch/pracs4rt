/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Classe que emmagatzema un BufferedImage i el seu nom
 * @author Albert Folch i Xavi Moreno
 */
public class Imatge implements Cloneable{
    private String name;
    private BufferedImage image;
    
    public Imatge(){
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    /**
     * Mètode que retorna un clon del buffered image que conté aquesta imatge
     * @return 
     */
    public BufferedImage deepCopy() {
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
    
    /**
     * Mètode de sobreescritura del clone
     * @return
     * @throws CloneNotSupportedException 
     */
    @Override
    protected BufferedImage clone() throws CloneNotSupportedException {
        super.clone();
        return deepCopy();
    }
    
    public Integer[] getPosTesela(int t, int size_t) {
        
        return null;
    }
    
    public int getNumTeseles(int size_t) {
        
        return 0;
    }
    
    
}
