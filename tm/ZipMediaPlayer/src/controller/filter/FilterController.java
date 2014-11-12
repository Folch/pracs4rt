/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter;

import model.FilterDim3;
import model.Imatge;
import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.util.ArrayList;
import model.config.Config;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 *
 * @author albert
 */
public class FilterController implements InternalIFilter {

    private int threshold;
    private FilterDim3 lastFilterApplied;
    private float lastHue;
    private float lastSaturation;
    private float lastValue;

    public FilterController() {
        this.threshold = Config.DEFAULT_THRESHOLD;
        this.lastFilterApplied = Config.DEFAULT_FILTER;
        this.lastHue = Config.DEFAULT_HUE;
        this.lastSaturation = Config.DEFAULT_SATURATION;
        this.lastValue = Config.DEFAULT_VALUE;
    }

    public FilterDim3 getLastFilterApplied() {
        return lastFilterApplied;
    }

    @Override
    public void negativeFilter(ArrayList<Imatge> imatges) {
        for (Imatge imatge : imatges) {
            BufferedImage img = imatge.getImage();
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

    private void grayScale(BufferedImage img) {
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(img, img);
    }

    @Override
    public ArrayList<Imatge> binaryFilter(ArrayList<Imatge> imatges, int threshold) {
        this.threshold = threshold;
        for (Imatge imatge : imatges) {
            BufferedImage img = imatge.getImage();
            grayScale(img);
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    Color c = new Color(img.getRGB(i, j));
                    int g, b, r;
                    r = c.getRed();
                    if (r < threshold) {
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
        return imatges;
    }

    @Override
    public ArrayList<Imatge> changeHSB(ArrayList<Imatge> imatges, float hue, float saturation, float brightness) {
        lastHue = hue;
        lastSaturation = saturation;
        lastValue = brightness;
        float hu, sa, br;
        for (Imatge imatge : imatges) {
            for (int i = 0; i < imatge.getImage().getWidth(); i++) {
                for (int j = 0; j < imatge.getImage().getHeight(); j++) {
                    int rgbCurrent = imatge.getImage().getRGB(i, j);
                    Color c = new Color(rgbCurrent);
                    int g, b, r;
                    r = c.getRed();
                    g = c.getGreen();
                    b = c.getBlue();
                    float[] hsb = Color.RGBtoHSB(r, g, b, null);

                    hu = hsb[0];
                    if (hue != 0) { //si algun dels 3 valors es -1, deixem el mateix valor
                        hu += hue;
                        hu = Math.min(hu, 1);
                    }
                    sa = hsb[1];
                    if (saturation != 0) {
                        sa += saturation;
                        sa = Math.min(sa, 1);
                    }
                    br = hsb[2];
                    if (brightness != 0) {
                        br += brightness;
                        br = Math.min(br, 1);

                    }

                    int rgb = Color.HSBtoRGB(hu, sa, br);
                    imatge.getImage().setRGB(i, j, rgb);
                    

                }
            }
        }
        return imatges;
    }

    @Override
    public ArrayList<Imatge> convolveImages(ArrayList<Imatge> imatges, FilterDim3 filter
    ) {
        this.lastFilterApplied = filter;

        for (Imatge imatge : imatges) {
            if (filter == FilterDim3.SOBEL_X || filter == FilterDim3.SOBEL_Y || filter == FilterDim3.HIGH_PASS || filter == FilterDim3.LAPLACIAN) {
                grayScale(imatge.getImage());
            }
            imatge.setImage(this.convolve(filter.getFilter(), imatge.getImage(), BORDES_0));
        }
        return imatges;
    }
    /**
     * Als bordes que surten de la imatge no s'els aplica el filtre
     */
    public static final int SENSE_BORDES = ConvolveOp.EDGE_NO_OP;
    /**
     * Els borders que es surten de la imatge s'avaluen com si tinguéssin valor
     * 0
     */
    public static final int BORDES_0 = ConvolveOp.EDGE_ZERO_FILL;

    public BufferedImage convolve(float filtre[][], BufferedImage imatge, int tratBordes) {
        BufferedImage res;

        if (imatge == null) {
            throw new IllegalArgumentException("La imatge no pot ser nula");
        }
        if (filtre == null || filtre.length == 0) {
            throw new IllegalArgumentException("S'ha de passar algun filtre vàlid");
        }

        if (tratBordes != SENSE_BORDES || tratBordes != BORDES_0) {
            tratBordes = SENSE_BORDES;
        }

        int width = filtre.length;
        int height = filtre[0].length;
        int tam = width * height;
        float filtroK[] = new float[tam];

        //Creem el filtre
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                filtroK[i * width + j] = filtre[i][j];
            }
        }

        //Creem l'operació de convolució
        Kernel kernel = new Kernel(width, height, filtroK);
        ConvolveOp cop = new ConvolveOp(kernel, tratBordes, null);

        //Creem la imatge nova semblant a l'antiga
        res = new BufferedImage(imatge.getWidth(), imatge.getHeight(), imatge.getType());

        //Apliquem el filtre
        cop.filter(imatge, res);

        return res;
    }

    public int getThreshold() {
        return this.threshold;
    }

    public float getLastHue() {
        return lastHue;
    }

    public float getLastSaturation() {
        return lastSaturation;
    }

    public float getLastValue() {
        return lastValue;
    }

}
