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
 *
 * @author albert
 */
public class HsbThread implements Runnable {

    private final FilterController filter;
    private final int start, end;

    public HsbThread(FilterController filter, int start, int end) {
        this.filter = filter;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        float hu, sa, br;
        for (int k = start; k < end; k++) {
            BufferedImage img = this.filter.getImatges().get(k).getImage();
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    int rgbCurrent = img.getRGB(i, j);
                    Color c = new Color(rgbCurrent);
                    int g, b, r;
                    r = c.getRed();
                    g = c.getGreen();
                    b = c.getBlue();
                    float[] hsb = Color.RGBtoHSB(r, g, b, null);

                    hu = hsb[0];
                    if (this.filter.getLastHue() != 0) { //si algun dels 3 valors es -1, deixem el mateix valor
                        hu += this.filter.getLastHue();
                        hu = Math.min(hu, 1);
                    }
                    sa = hsb[1];
                    if (this.filter.getLastSaturation() != 0) {
                        sa += this.filter.getLastSaturation();
                        sa = Math.min(sa, 1);
                    }
                    br = hsb[2];
                    if (this.filter.getLastValue() != 0) {
                        br += this.filter.getLastValue();
                        br = Math.min(br, 1);

                    }

                    int rgb = Color.HSBtoRGB(hu, sa, br);
                    img.setRGB(i, j, rgb);

                }
            }
        }

    }
}
