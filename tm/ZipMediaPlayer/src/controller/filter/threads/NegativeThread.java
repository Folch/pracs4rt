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
public class NegativeThread implements Runnable {

    private final FilterController filter;
    private final int start, end;

    public NegativeThread(FilterController filter, int start, int end) {
        this.filter = filter;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
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

    }
}
