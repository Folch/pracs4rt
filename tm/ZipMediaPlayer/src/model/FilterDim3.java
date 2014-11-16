/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Classe que fa servir el patró Singleton per tenir el filtre que es vol aplicar: average, sobel_x, sobel_y, low_pass, high_pass, laplacian i identity
 * @author Albert Folch i Xavi Moreno
 */
public class FilterDim3 {

    public final static FilterDim3 AVERAGE = new FilterDim3(1 / 9.0f, 1 / 9.0f, 1 / 9.0f, 1 / 9.0f, 1 / 9.0f, 1 / 9.0f, 1 / 9.0f, 1 / 9.0f, 1 / 9.0f);
    public final static FilterDim3 SOBEL_X = new FilterDim3(-1, 0, 1, -2, 0, 2, -1, 0, 1);
    public final static FilterDim3 SOBEL_Y = new FilterDim3(1, 2, 1, 0, 0, 0, -1, -2, -1);
    public final static FilterDim3 LOW_PASS = new FilterDim3(1, 1, 1, 1, 1, 1, 1, 1, 1);
    public final static FilterDim3 HIGH_PASS = new FilterDim3(-1, -1, -1, -1, 8, -1, -1, -1, -1);
    public final static FilterDim3 LAPLACIAN = new FilterDim3(0, -1, 0, -1, 4, -1, 0, -1, 0);
    public final static FilterDim3 IDENTITY = new FilterDim3(0, 0, 0, 0, 1, 0, 0, 0, 0);

    private final float[][] filter;

    public FilterDim3(float a, float b, float c, float d, float e, float f, float g, float h, float i) {
        this.filter = new float[][]{{a, b, c}, {d, e, f}, {g, h, i}};
    }

    public float[][] getFilter() {
        return filter;
    }
}
