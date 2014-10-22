/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter;

import model.FilterDim3;

/**
 *
 * @author albert
 */
public interface IFilter {
    public void applyFilter(FilterDim3 filter);
    public void negativeFilter();
    public void binaryFilter(int threshold);
    public void changeHSB(float hue, float saturation, float brightness);
    public void removeFilter();
}
