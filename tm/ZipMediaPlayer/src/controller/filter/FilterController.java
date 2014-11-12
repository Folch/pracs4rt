/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter;

import controller.filter.threads.BinaryThread;
import controller.filter.threads.ConvolveThread;
import controller.filter.threads.HsbThread;
import controller.filter.threads.NegativeThread;
import model.FilterDim3;
import model.Imatge;
import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.util.ArrayList;
import model.config.Config;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author albert
 */
public class FilterController implements InternalIFilter {

    private int threshold;
    private FilterDim3 lastFilterApplied;
    private float lastHue;
    private float lastSaturation;
    private float lastValue;
    private ExecutorService executorFilter;
    private int numProcessors;
    private ArrayList<Imatge> imatges;

    public FilterController() {
        this.threshold = Config.DEFAULT_THRESHOLD;
        this.lastFilterApplied = Config.DEFAULT_FILTER;
        this.lastHue = Config.DEFAULT_HUE;
        this.lastSaturation = Config.DEFAULT_SATURATION;
        this.lastValue = Config.DEFAULT_VALUE;
        this.numProcessors = Runtime.getRuntime().availableProcessors();
        this.executorFilter = Executors.newFixedThreadPool(numProcessors);
    }

    public FilterDim3 getLastFilterApplied() {
        return lastFilterApplied;
    }

    @Override
    public ArrayList<Imatge> negativeFilter(ArrayList<Imatge> imatges) {
        try {
            int cadaQuan = imatges.size() / numProcessors;
            int inici = 0, end = cadaQuan;
            this.imatges = imatges;

            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < numProcessors; i++) {
                Runnable r = new NegativeThread(this, inici, end);
                Thread a = new Thread(r);
                a.start();
                threads.add(a);
                inici += cadaQuan;
                end += cadaQuan;

            }
            for (int i = 0; i < numProcessors; i++) {
                threads.get(i).join();

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(FilterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imatges;
    }

    public void grayScale(BufferedImage img) {
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color c = new Color(img.getRGB(i, j));
                int g, b, r;
                r = c.getRed();
                g = c.getGreen();
                b = c.getBlue();
                int mean = (int) (((float) r) + g + b) / 3;
                r = mean;
                g = mean;
                b = mean;

                int rgb = new Color(r, g, b).getRGB();
                img.setRGB(i, j, rgb);
            }

        }

    }

    @Override
    public ArrayList<Imatge> binaryFilter(ArrayList<Imatge> imatges, int threshold) {
        this.threshold = threshold;
        try {
            int cadaQuan = imatges.size() / numProcessors;
            int inici = 0, end = cadaQuan;
            this.imatges = imatges;

            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < numProcessors; i++) {
                Runnable r = new BinaryThread(this, inici, end);
                Thread a = new Thread(r);
                a.start();
                threads.add(a);
                inici += cadaQuan;
                end += cadaQuan;

            }
            for (int i = 0; i < numProcessors; i++) {
                threads.get(i).join();

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(FilterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imatges;
    }

    @Override
    public ArrayList<Imatge> changeHSB(ArrayList<Imatge> imatges, float hue, float saturation, float brightness) {
        lastHue = hue;
        lastSaturation = saturation;
        lastValue = brightness;
        
        try {
            int cadaQuan = imatges.size() / numProcessors;
            int inici = 0, end = cadaQuan;
            this.imatges = imatges;

            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < numProcessors; i++) {
                Runnable r = new HsbThread(this, inici, end);
                Thread a = new Thread(r);
                a.start();
                threads.add(a);
                inici += cadaQuan;
                end += cadaQuan;

            }
            for (int i = 0; i < numProcessors; i++) {
                threads.get(i).join();

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(FilterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imatges;
    }

    @Override
    public ArrayList<Imatge> convolveImages(ArrayList<Imatge> imatges, FilterDim3 filter
    ) {
        try {
            this.lastFilterApplied = filter;
            int cadaQuan = imatges.size() / numProcessors;
            int inici = 0, end = cadaQuan;
            this.imatges = imatges;

            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < numProcessors; i++) {
                Runnable r = new ConvolveThread(this, inici, end);
                Thread a = new Thread(r);
                a.start();
                threads.add(a);
                inici += cadaQuan;
                end += cadaQuan;

            }
            for (int i = 0; i < numProcessors; i++) {
                threads.get(i).join();

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(FilterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imatges;
    }
    /**
     * Als bordes que surten de la imatge no s'els aplica el filtre
     */
    public static final int SENSE_BORDES = ConvolveOp.EDGE_NO_OP;
    /**
     * Els borders que es surten de la imatge s'avaluen com si tinguéssin valor
     * 0
     */
    public static final int BORDES_0 = ConvolveOp.EDGE_ZERO_FILL;

    public BufferedImage convolve(float filtre[][], BufferedImage imatge, int tratBordes) {
        BufferedImage res;

        if (imatge == null) {
            throw new IllegalArgumentException("La imatge no pot ser nula");
        }
        if (filtre == null || filtre.length == 0) {
            throw new IllegalArgumentException("S'ha de passar algun filtre vàlid");
        }

        if (tratBordes != SENSE_BORDES || tratBordes != BORDES_0) {
            tratBordes = SENSE_BORDES;
        }

        int width = filtre.length;
        int height = filtre[0].length;
        int tam = width * height;
        float filtroK[] = new float[tam];

        //Creem el filtre
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                filtroK[i * width + j] = filtre[i][j];
            }
        }

        //Creem l'operació de convolució
        Kernel kernel = new Kernel(width, height, filtroK);
        ConvolveOp cop = new ConvolveOp(kernel, tratBordes, null);

        //Creem la imatge nova semblant a l'antiga
        res = new BufferedImage(imatge.getWidth(), imatge.getHeight(), imatge.getType());

        //Apliquem el filtre
        cop.filter(imatge, res);

        return res;
    }

    public int getThreshold() {
        return this.threshold;
    }

    public float getLastHue() {
        return lastHue;
    }

    public float getLastSaturation() {
        return lastSaturation;
    }

    public float getLastValue() {
        return lastValue;
    }

    public ArrayList<Imatge> getImatges() {
        return imatges;
    }

}
