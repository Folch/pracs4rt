/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter;

import model.FilterDim3;
import model.Imatge;
import java.util.ArrayList;

/**
 *
 * @author albert
 */
public interface InternalIFilter {
    public void negativeFilter(ArrayList<Imatge> imatges);
    public void binaryFilter(ArrayList<Imatge> imatges, int threshold);
    public void changeHSB(ArrayList<Imatge> imatges, float hue, float saturation, float brightness);
    public void convolveImages(ArrayList<Imatge> imatges, FilterDim3 filter);
    public float getHue(Imatge img);
    public float getSaturation(Imatge img);
    public float getBrightness(Imatge img);
}
