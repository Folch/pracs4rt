/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter.threads;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

/**
 * Classe que representa un thread que aplicarà un filtre negatiu
 * @author Albert Folch i Xavi Moreno
 */
public class NegativeThread extends FilterThread implements Callable {

    public NegativeThread() {
        super();
    }
    /**
     * Mètode que aplica el filtre negatiu a un subconjunt d'imatges
     * @return
     * @throws Exception 
     */
    @Override
    public Object call() throws Exception {
        for (int k = start; k < end; k++) {
            BufferedImage img = this.filter.getImatges().get(k).getImage();
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    Color c = new Color(img.getRGB(i, j));
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();
                    r = 255 - r;
                    g = 255 - g;
                    b = 255 - b;
                    img.setRGB(i, j, new Color(r, g, b).getRGB());
                }
            }
        }
        return null;

    }

}
