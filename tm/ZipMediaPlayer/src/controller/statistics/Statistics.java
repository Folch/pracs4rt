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
    public float[] data;
    private int size;    

    public Statistics(float[] data) {
        this.data = data;
        this.size = data.length;
    }
    
    public Statistics(BufferedImage img) {
        this.size = img.getWidth()*img.getHeight();
        this.data = new float[size];
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                this.data[i*img.getHeight()+j] = new Color(img.getRGB(i, j)).getRed();
            }
        }
    }

    public float getMean() {
        float sum = 0.0f;
        for(float a : data)
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
    
    public static double normalizedCrossCorrelation(BufferedImage img1, BufferedImage img2) {
        assert img1.getWidth() == img2.getWidth();
        assert img1.getHeight() == img2.getHeight();
        
        double out = 0;
        double mean12;
        int size;
        Statistics s1;
        Statistics s2;
        
        size = img1.getWidth()*img1.getHeight();
        s1 = new Statistics(img1);
        s2 = new Statistics(img2);
        
        mean12 = s1.getMean() * s2.getMean();
        for (int i = 0; i < size; i++) {
            out += s1.data[i]*s2.data[i] - mean12;
        }
        
        out /= (size*s1.getStdDev() * s2.getStdDev());
        
        return out;
    }
}


