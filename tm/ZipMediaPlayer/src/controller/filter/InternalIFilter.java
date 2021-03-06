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
 * Interfície que implementa el FilterController per implementar el funcionament dels filtres
 * @author Albert Folch
 */
public interface InternalIFilter {
    public ArrayList<Imatge> negativeFilter(ArrayList<Imatge> imatges);
    public ArrayList<Imatge> binaryFilter(ArrayList<Imatge> imatges, int threshold);
    public ArrayList<Imatge> changeHSB(ArrayList<Imatge> imatges, float hue, float saturation, float brightness);
    public ArrayList<Imatge> convolveImages(ArrayList<Imatge> imatges, FilterDim3 filter);
}
