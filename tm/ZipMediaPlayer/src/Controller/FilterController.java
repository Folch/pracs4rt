/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Imatge;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author albert
 */
public class FilterController implements InternalIFilter {

    @Override
    public ArrayList<Imatge> negativeFilter(ArrayList<Imatge> imatges) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Imatge> binaryFilter(ArrayList<Imatge> imatges, int threshold) {
        for (Imatge imatge : imatges) {
            BufferedImage img = imatge.getImage();
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    if(img.getRGB(i, j) < threshold)
                        img.setRGB(i, j, 0);
                    else
                        img.setRGB(i, j, 255);
                }
            }
        }
        return imatges;
    }

    @Override
    public ArrayList<Imatge> changeHSB(ArrayList<Imatge> imatges, float hue, float saturation, float brightness) {
        for (Imatge imatge : imatges) {
            for (int i = 0; i < imatge.getImage().getWidth(); i++) {
                for (int j = 0; j < imatge.getImage().getHeight(); j++) {
                    int rgb = Color.HSBtoRGB(hue, saturation, brightness);
                    imatge.getImage().setRGB(i, j, rgb);
                }
            }
        }
        return imatges;
    }

    @Override
    public ArrayList<Imatge> convolveImages(ArrayList<Imatge> imatges, ZipController.FilterType type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
