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

/**
 *
 * @author albert
 */
public class FilterController implements InternalIFilter {

    private int threshold;
    private FilterDim3 lastFilterApplied;

    public FilterController() {
        this.threshold = -1;
        this.lastFilterApplied = Config.DEFAULT_FILTER;
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

    @Override
    public void binaryFilter(ArrayList<Imatge> imatges, int threshold) {
        this.threshold = threshold;
        for (Imatge imatge : imatges) {
            BufferedImage img = imatge.getImage();
            ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
            op.filter(img, img);
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
    }

    @Override
    public void changeHSB(ArrayList<Imatge> imatges, float hue, float saturation, float brightness) {
        float hu,sa,br;
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
                    if (hue != -1) { //si algun dels 3 valors es -1, deixem el mateix valor
                        hu += hue;
                        hu = Math.max(hu, 1);
                    }
                    sa = hsb[1];
                    if (saturation != -1) {
                        sa += saturation;
                        sa = Math.max(sa, 1);
                    }
                    br = hsb[2];
                    if (brightness != -1) {
                        br += brightness;
                        br = Math.max(br, 1);

                    }

                    int rgb = Color.HSBtoRGB(hu, sa, br);
                    imatge.getImage().setRGB(i, j, rgb);

                }
            }
        }
    }

    @Override
    public void convolveImages(ArrayList<Imatge> imatges, FilterDim3 filter
    ) {
        this.lastFilterApplied = filter;
        double[][] filtre = filter.getFilter();
        for (int k = 0; k < imatges.size(); k++) {
            Imatge img = imatges.get(k);
            int[][] imgR = new int[img.getImage().getWidth()][img.getImage().getHeight()];
            int[][] imgG = new int[img.getImage().getWidth()][img.getImage().getHeight()];
            int[][] imgB = new int[img.getImage().getWidth()][img.getImage().getHeight()];

            for (int i = 0; i < img.getImage().getWidth(); i++) {
                for (int j = 0; j < img.getImage().getHeight(); j++) {
                    Color c = new Color(img.getImage().getRGB(i, j));
                    int red = c.getRed();
                    int green = c.getGreen();
                    int blue = c.getBlue();
                    imgR[i][j] = red;
                    imgG[i][j] = green;
                    imgB[i][j] = blue;
                }
            }
            imgR = convolve(imgR, filtre);
            imgG = convolve(imgG, filtre);
            imgB = convolve(imgB, filtre);
            for (int i = 0; i < img.getImage().getWidth(); i++) {
                for (int j = 0; j < img.getImage().getHeight(); j++) {
                    byte red2 = (byte) imgR[i][j];
                    byte green2 = (byte) imgG[i][j];
                    byte blue2 = (byte) imgB[i][j];
                    //System.out.println("red ="+red);
                    int red = red2, green = green2, blue = blue2;
                    if (red2 < 0) {
                        red += 128;
                    }
                    if (green2 < 0) {
                        green += 128;
                    }
                    if (blue2 < 0) {
                        blue += 128;
                    }
                    //System.out.println(red);
                    Color c2 = new Color(red, green, blue);
                    int rgb = c2.getRGB();
                    img.getImage().setRGB(i, j, rgb);
                }
            }
        }
    }

    public int getThreshold() {
        return this.threshold;
    }

    public int[][] convolve(int[][] img, double[][] kernel) {

        int xn, yn;
        float average;

        int w = img.length;
        int h = img[0].length;
        int[][] output = new int[w][h];

        //--- IMAGE: Iterate through image pixels ---//
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {

                //--- KERNEL: Iterate through kernel ---//
                average = 0;
                for (int u = 0; u < kernel.length; u++) {
                    for (int v = 0; v < kernel[0].length; v++) {

                        //--- Get associated neighbor pixel coordinates ---//
                        xn = x + u - kernel.length / 2;
                        yn = y + v - kernel[0].length / 2;

                        //--  Make sure we don't go off of an edge of the image ---//
                        xn = constrain(xn, 0, w - 1);
                        yn = constrain(yn, 0, h - 1);
                        //--- Add weighted neighbor to average ---//
                        average += img[xn][yn] * kernel[u][v];
                    }
                } /*--- KERNEL ---*/

                //System.out.println("average="+(int)average);
                //--- Set output pixel to weighted average value ---//

                output[x][y] = (int) average;
            }
        } /*--- IMAGE ---*/

        return output;
    }

    private int constrain(int x, int a, int b) {
        if (x < a) {
            return a;
        } else if (b < x) {
            return b;
        } else {
            return x;
        }
    }

}
