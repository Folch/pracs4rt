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
 *
 * @author albert
 */
public class BinaryThread extends FilterThread implements Callable {

    public BinaryThread() {
        super();
    }

    @Override
    public Object call() throws Exception {
        for (int k = start; k < end; k++) {
            BufferedImage img = this.filter.getImatges().get(k).getImage();
            super.grayScale(img);
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    Color c = new Color(img.getRGB(i, j));
                    int g, b, r;
                    r = c.getRed();
                    if (r < this.filter.getThreshold()) {
                        r = 0;
                        g = 0;
                        b = 0;
                    } else {
                        r = 255;
                        g = 255;
                        b = 255;
                    }
                    int rgb = new Color(r, g, b).getRGB();
                    img.setRGB(i, j, rgb);
                }

            }
        }
        return null;
    }
}
