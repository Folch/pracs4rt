/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter;

import model.FilterDim3;
import model.Imatge;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import fourier.FastFourierTransform;

/**
 *
 * @author albert
 */
public class FilterController implements InternalIFilter {
    private int threshold;
    
    public FilterController(){
        this.threshold = -1;
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
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    if (img.getRGB(i, j) < threshold) {
                        img.setRGB(i, j, 0);
                    } else {
                        img.setRGB(i, j, 255);
                    }
                }
            }
        }
    }

    @Override
    public void changeHSB(ArrayList<Imatge> imatges, float hue, float saturation, float brightness) {
        for (Imatge imatge : imatges) {
            for (int i = 0; i < imatge.getImage().getWidth(); i++) {
                for (int j = 0; j < imatge.getImage().getHeight(); j++) {
                    int rgb = Color.HSBtoRGB(hue, saturation, brightness);
                    imatge.getImage().setRGB(i, j, rgb);
                }
            }
        }
    }

    @Override
    public void convolveImages(ArrayList<Imatge> imatges, FilterDim3 filter) {
        double [][]filtre = filter.getFilter();
        FastFourierTransform.fastFT(filtre,filtre,true);
        for (Imatge imatge : imatges) {
            //FastFourierTransform.fastFT(imatge.getImage(), imatge.getImage(), true);//posar la imatge com a matriu de doubles
            //FastFourierTransform
        }
        
        
        
        
        
        
        
        
        for (Imatge imatge : imatges) {
            BufferedImage img = imatge.getImage();
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    for (int xfiltre = 0; xfiltre < filtre.length; xfiltre++) {
                        for (int yfiltre = 0; yfiltre < filtre.length; yfiltre++) {
                            int rgb = (int)(img.getRGB(i, j)*filtre[xfiltre][yfiltre]);
                            img.setRGB(i, j, rgb);
                            
                        }
                        
                    }
                    
                }
            }
        }
    }

    @Override
    public float getHue(Imatge img) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //Color c = new Color(img.getImage());
    }

    @Override
    public float getSaturation(Imatge img) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getBrightness(Imatge img) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getThreshold(){
        return this.threshold;
    }

}
