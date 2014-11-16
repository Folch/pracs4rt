/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter.threads;

import controller.filter.FilterController;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Classe pare de tots els threads que apliquen filtres
 * @author Albert Folch i Xavi Moreno
 */
public class FilterThread {

    protected FilterController filter;
    protected int start, end;
    /**
     * Constructor
     */
    public FilterThread() {
        this.filter = null;
        this.start = -1;
        this.end = -1;
    }
    /**
     * Aquest setter configura el subconjunt de les imatges de FilterController que un thread concret aplicarà un filtre segons el inici i end
     * @param aThis
     * @param inici
     * @param end 
     */
    public void set(FilterController aThis, int inici, int end) {
        this.filter = aThis;
        this.start = inici;
        this.end = end;
    }
    /**
     * Mètode que converteix una imatge en escala de grisos
     * @param img 
     */
    protected void grayScale(BufferedImage img) {
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color c = new Color(img.getRGB(i, j));
                int g, b, r;
                r = c.getRed();
                g = c.getGreen();
                b = c.getBlue();
                int mean = (int) (((float) r) + g + b) / 3;
                r = mean;
                g = mean;
                b = mean;

                int rgb = new Color(r, g, b).getRGB();
                img.setRGB(i, j, rgb);
            }

        }

    }
}
