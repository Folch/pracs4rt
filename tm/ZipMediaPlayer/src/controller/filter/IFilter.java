/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter;

import model.FilterDim3;

/**
 * Interfície que implementa el MainController que serà utilitzada per la vista pel tema de filtres
 * @author Albert Folch i Xavi Moreno
 */
public interface IFilter {
    
    public boolean applyFilter(FilterDim3 filter);
    public void negativeFilter();
    public void binaryFilter(int threshold);
    public void changeHSB(float hue, float saturation, float brightness);
    public void removeFilter();
    

    public float getHue();
    public float getSaturation();
    public float getBrightness();
    public int getThreshold();
    public FilterDim3 getFilterDim3();
    
}
