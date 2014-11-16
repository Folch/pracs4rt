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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Classe controladora dels filtrs principal que implementa el InternalFilter
 * @author Albert Folch i Xavi Moreno
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
    /**
     * Constructor
     */
    public FilterController() {
        this.threshold = Config.DEFAULT_THRESHOLD;
        this.lastFilterApplied = Config.DEFAULT_FILTER;
        this.lastHue = Config.DEFAULT_HUE;
        this.lastSaturation = Config.DEFAULT_SATURATION;
        this.lastValue = Config.DEFAULT_VALUE;
    }
    /**
     * Mètode que aplica el filtre negatiu amb multithread
     * @param imatges
     * @return 
     */
    @Override
    public ArrayList<Imatge> negativeFilter(ArrayList<Imatge> imatges) {
        multithreadingFilter(imatges, NEGATIVE);
        return this.imatges;
    }
    /**
     * Mètode que aplica el filtre binari amb multithread
     * @param imatges
     * @param threshold
     * @return 
     */
    @Override
    public ArrayList<Imatge> binaryFilter(ArrayList<Imatge> imatges, int threshold) {
        this.threshold = threshold;
        multithreadingFilter(imatges, BINARY);
        return this.imatges;
    }
    /**
     * Mètode que canvia el HSB de les imatges, si no es vol canviar s'ha de passar 0 ja que serà additiu.
     * @param imatges
     * @param hue
     * @param saturation
     * @param brightness
     * @return 
     */
    @Override
    public ArrayList<Imatge> changeHSB(ArrayList<Imatge> imatges, float hue, float saturation, float brightness) {
        lastHue = hue;
        lastSaturation = saturation;
        lastValue = brightness;
        multithreadingFilter(imatges, HSB);
        return this.imatges;
    }
    /**
     * Mètode que convoluciona les imatges segons el filtre passat per paràmetre. Retorna null si el filtre no s'ha pogut aplicar
     * @param imatges
     * @param filter
     * @return 
     */
    @Override
    public ArrayList<Imatge> convolveImages(ArrayList<Imatge> imatges, FilterDim3 filter) {
        this.lastFilterApplied = filter;
        return multithreadingFilter(imatges, CONVOLVE) ? this.imatges : null;
    }
    /**
     * Mètode privat que crea els threads (tants threads com threads tinguin les cpus), els executa cadascun per un conjunt d'imatges i espera a que acabin. Retorna true si ha anat tot bé.
     * @param imatges
     * @param filter
     * @return 
     */
    private boolean multithreadingFilter(ArrayList<Imatge> imatges, int filter) {
        try {
            int numProcessors = Runtime.getRuntime().availableProcessors();
            int step = imatges.size() < numProcessors ? 1 : imatges.size() / numProcessors;
            int start = 0, end = imatges.size() < numProcessors ? 1 : step;
            this.imatges = imatges;
            int iter = imatges.size() < numProcessors ? imatges.size() : numProcessors;

            ArrayList<Future> submits = new ArrayList<>();

            for (int i = 0; i < iter; i++) {

                FilterThread f = null;
                switch (filter) {
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
                Callable c =  (Callable) f;
                ExecutorService executor = Executors.newFixedThreadPool(1);
                Future b = executor.submit(c);
                submits.add(b);

                start += step;
                end += step;
            }
            for (int i = 0; i < iter; i++) {
                submits.get(i).get();

            }

        } catch (InterruptedException | ExecutionException ex) {
            return false;
        }
        return true;
    }
    /**
     * Getter que retorna l'últim threshold utilitzat
     * @return 
     */
    public int getThreshold() {
        return this.threshold;
    }
    /**
     * Getter que retorna l'últim hue utilitzat
     * @return 
     */
    public float getLastHue() {
        return lastHue;
    }
    /**
     * Getter que retorna l'últim saturation utilitzat
     * @return 
     */
    public float getLastSaturation() {
        return lastSaturation;
    }
    /**
     * Getter que retorna l'últim brightness/value utilitzat
     * @return 
     */
    public float getLastValue() {
        return lastValue;
    }
    /**
     * Getter que retorna les imatges
     * @return 
     */
    public ArrayList<Imatge> getImatges() {
        return imatges;
    }
    /**
     * Getter que retorna l'últim filtre utilitzat
     * @return 
     */
    public FilterDim3 getLastFilterApplied() {
        return lastFilterApplied;
    }
}
