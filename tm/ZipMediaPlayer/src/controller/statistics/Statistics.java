/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.statistics;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author zenbook
 */

public class Statistics {
    public double[] data;
    private int size;    

    public Statistics(double[] data) {
        this.data = data;
        this.size = data.length;
    }
    
    public Statistics(BufferedImage img) {
        this.size = img.getWidth()*img.getHeight();
        this.data = new double[size];
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                this.data[i*img.getHeight()+j] = new Color(img.getRGB(i, j)).getRed();
            }
        }
    }

    public double getMean() {
        double sum = 0.0;
        for(double a : data)
            sum += a;
        return sum/size;
    }

    public double getVariance() {
        double mean = getMean();
        double temp = 0;
        for(double a :data)
            temp += (mean-a)*(mean-a);
        return temp/size;
    }

    public double getStdDev() {
        return Math.sqrt(getVariance());
    }
}


