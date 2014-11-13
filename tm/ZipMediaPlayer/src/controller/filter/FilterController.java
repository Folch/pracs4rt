/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter;

import controller.filter.threads.BinaryThread;
import controller.filter.threads.ConvolveThread;
import controller.filter.threads.FilterThread;
import controller.filter.threads.HsbThread;
import controller.filter.threads.NegativeThread;
import model.FilterDim3;
import model.Imatge;
import java.util.ArrayList;
import model.config.Config;
import java.awt.image.ConvolveOp;
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
    private ArrayList<Imatge> imatges;
    private final int NEGATIVE = 0;
    private final int BINARY = 1;
    private final int HSB = 2;
    private final int CONVOLVE = 3;

    /**
     * Als bordes que surten de la imatge no s'els aplica el filtre
     */
    public static final int SENSE_BORDES = ConvolveOp.EDGE_NO_OP;
    /**
     * Els borders que es surten de la imatge s'avaluen com si tinguéssin valor
     * 0
     */
    public static final int BORDES_0 = ConvolveOp.EDGE_ZERO_FILL;

    public FilterController() {
        this.threshold = Config.DEFAULT_THRESHOLD;
        this.lastFilterApplied = Config.DEFAULT_FILTER;
        this.lastHue = Config.DEFAULT_HUE;
        this.lastSaturation = Config.DEFAULT_SATURATION;
        this.lastValue = Config.DEFAULT_VALUE;
    }

    @Override
    public ArrayList<Imatge> negativeFilter(ArrayList<Imatge> imatges) {
        multithreadingFilter(imatges, NEGATIVE);
        return this.imatges;
    }


    @Override
    public ArrayList<Imatge> binaryFilter(ArrayList<Imatge> imatges, int threshold) {
        this.threshold = threshold;
        multithreadingFilter(imatges, BINARY);
        return this.imatges;
    }

    @Override
    public ArrayList<Imatge> changeHSB(ArrayList<Imatge> imatges, float hue, float saturation, float brightness) {
        lastHue = hue;
        lastSaturation = saturation;
        lastValue = brightness;
        multithreadingFilter(imatges, HSB);
        return this.imatges;
    }
    
    @Override
    public ArrayList<Imatge> convolveImages(ArrayList<Imatge> imatges, FilterDim3 filter) {
        this.lastFilterApplied = filter;
        multithreadingFilter(imatges, CONVOLVE);
        return this.imatges;
    }

    private void multithreadingFilter(ArrayList<Imatge> imatges, int filter) {
        try {
            int numProcessors = Runtime.getRuntime().availableProcessors();
            int step = imatges.size() / numProcessors;
            int start = 0, end = step;
            this.imatges = imatges;

                    
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < numProcessors; i++) {
                FilterThread f = null;
                switch(filter){
                    case NEGATIVE:
                        f = new NegativeThread();
                        break;
                    case BINARY:
                        f = new BinaryThread();
                        break;
                    case HSB:
                        f = new HsbThread();
                        break;
                    case CONVOLVE:
                        f = new ConvolveThread();
                        break;
                }
                
                f.set(this, start, end);
                Thread a = new Thread((Runnable) f);
                a.start();
                threads.add(a);
                start += step;
                end += step;
            }
            for (int i = 0; i < numProcessors; i++) {
                threads.get(i).join();

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(FilterController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public FilterDim3 getLastFilterApplied() {
        return lastFilterApplied;
    }
}
