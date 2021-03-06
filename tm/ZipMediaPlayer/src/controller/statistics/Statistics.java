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

    public float[] dataR;
    public float[] dataG;
    public float[] dataB;
    private int size;

    public Statistics(float[] data) {
        this.dataR = data;
        this.size = data.length;
    }

    public Statistics(BufferedImage img) {
        this.size = img.getWidth() * img.getHeight();
        this.dataR = new float[size];
        this.dataG = new float[size];
        this.dataB = new float[size];
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                this.dataR[i * img.getHeight() + j] = new Color(img.getRGB(i, j)).getRed();
                this.dataG[i * img.getHeight() + j] = new Color(img.getRGB(i, j)).getGreen();
                this.dataB[i * img.getHeight() + j] = new Color(img.getRGB(i, j)).getBlue();
            }
        }
    }

    public float getMeanR() {
        float sum = 0.0f;
        for (float a : dataR) {
            sum += a;
        }
        return sum / size;
    }

    public float getMeanG() {
        float sum = 0.0f;
        for (float a : dataG) {
            sum += a;
        }
        return sum / size;
    }

    public float getMeanB() {
        float sum = 0.0f;
        for (float a : dataB) {
            sum += a;
        }
        return sum / size;
    }
    public Color getMeanCanals() {
        return new Color ((int)getMeanR(),(int)getMeanG(),(int)getMeanB());
    }

    public double getVariance() {
        double mean = getMeanR();
        double temp = 0;
        for (double a : dataR) {
            temp += (mean - a) * (mean - a);
        }
        return temp / size;
    }

    public double getStdDev() {
        return Math.sqrt(getVariance());
    }

    public static double normalizedCrossCorrelation(BufferedImage img1, BufferedImage img2) {
        assert img1.getWidth() == img2.getWidth();
        assert img1.getHeight() == img2.getHeight();

        double outR = 0,outG=0,outB=0;
        double meanR,meanG,meanB;
        int size;
        Statistics s1;
        Statistics s2;

        size = img1.getWidth() * img1.getHeight();
        s1 = new Statistics(img1);
        s2 = new Statistics(img2);

        meanR = s1.getMeanR() * s2.getMeanR();
        meanG = s1.getMeanG() * s2.getMeanG();
        meanB = s1.getMeanB() * s2.getMeanB();
        for (int i = 0; i < size; i++) {
            outR += s1.dataR[i] * s2.dataR[i] - meanR;
            outG += s1.dataG[i] * s2.dataG[i] - meanG;
            outB += s1.dataB[i] * s2.dataB[i] - meanB;
        }

        outR /= (size * s1.getStdDev() * s2.getStdDev());
        outG /= (size * s1.getStdDev() * s2.getStdDev());
        outB /= (size * s1.getStdDev() * s2.getStdDev());

        return (outR+outG+outB)/3;
    }
}
