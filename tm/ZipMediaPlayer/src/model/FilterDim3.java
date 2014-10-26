/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author albert
 */
public class FilterDim3 {

    public final static FilterDim3 AVERAGE = new FilterDim3(1 / 9.0, 1 / 9.0, 1 / 9.0, 1 / 9.0, 1 / 9.0, 1 / 9.0, 1 / 9.0, 1 / 9.0, 1 / 9.0);
    public final static FilterDim3 SOBEL_X = new FilterDim3(-1, 0, 1, -2, 0, 2, -1, 0, 1);
    public final static FilterDim3 SOBEL_Y = new FilterDim3(1, 2, 1, 0, 0, 0, -1, -2, -1);
    public final static FilterDim3 LOW_PASS = new FilterDim3(1, 1, 1, 1, 1, 1, 1, 1, 1);
    public final static FilterDim3 HIGH_PASS = new FilterDim3(-1, -1, -1, -1, 8, -1, -1, -1, -1);
    public final static FilterDim3 LAPLACIAN = new FilterDim3(0, -1, 0, -1, 4, -1, 0, -1, 0);
    public final static FilterDim3 IDENTITY = new FilterDim3(0, 0, 0, 0, 1, 0, 0, 0, 0);

    private final double[][] filter;

    public FilterDim3(double a, double b, double c, double d, double e, double f, double g, double h, double i) {
        this.filter = new double[][]{{a, b, c}, {d, e, f}, {g, h, i}};
    }

    public double[][] getFilter() {
        return filter;
    }

}
