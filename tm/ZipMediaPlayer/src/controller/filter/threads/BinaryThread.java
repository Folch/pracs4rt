/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter.threads;

import controller.filter.FilterController;
import static controller.filter.FilterController.BORDES_0;
import java.awt.Color;
import java.awt.image.BufferedImage;
import model.FilterDim3;

/**
 *
 * @author albert
 */
public class BinaryThread implements Runnable {

    private final FilterController filter;
    private final int start, end;

    public BinaryThread(FilterController filter, int start, int end) {
        this.filter = filter;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int k = start; k < end; k++) {
            BufferedImage img = this.filter.getImatges().get(k).getImage();
            this.filter.grayScale(img);
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

    }
}
