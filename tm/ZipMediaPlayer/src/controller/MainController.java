/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.player.OnImageListener;
import controller.compressor.CompressorController;
import controller.compressor.FXContent;
import controller.compressor.IFXParameters;
import controller.player.IPlayer;
import controller.filter.FilterController;
import controller.filter.IFilter;
import controller.disk.IDisk;
import controller.disk.DiskController;
import java.io.File;
import model.config.Config;
import model.config.DirectionType;
import model.FilterDim3;
import model.Imatge;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipFile;

/**
 * Classe controladora principal del video, fa de clase comunicadora entre la vista i els altres controladors
 * @author albert
 */
public class MainController implements IPlayer, IFilter, IDisk, IFXParameters {

    private ArrayList<Imatge> images, imagesCopia;
    private ScheduledExecutorService executor;
    private int index, time;
    private DirectionType dir;
    private final OnImageListener listener;
    private final CompressorController compressor;
    private final DiskController disk;
    private final FilterController filter;
    private ZipFile zip;
    /**
     * Constructor
     * @param listener 
     */
    public MainController(OnImageListener listener) {
        this.listener = listener;
        this.dir = Config.DEFAULT_DIRECTION;
        this.time = Config.DEFAULT_FRAME_RATE;
        this.compressor = new CompressorController();
        this.disk = new DiskController();
        this.filter = new FilterController();
        this.images = new ArrayList<>();
        this.imagesCopia = new ArrayList<>();
    }
    /**
     * Mètode que serveix per configurar el frame rate.
     * @param time 
     */
    @Override
    public void setFrameRate(int time) {
        this.time = time;
        boolean isPlaying = isPlaying();
        pause();
        if (isPlaying) {
            play();
        }
    }
    /**
     * Mètode que para el thread d'execució del video
     */
    @Override
    public void pause() {
        if (executor != null) {
            executor.shutdown();
        }
    }
    /**
     * Mètode que executa el thread de reproducció del video cada 1/fps
     */
    @Override
    public void play() {
        Runnable nextRunnable = new Runnable() {
            @Override
            public void run() {
                if (dir == DirectionType.FORWARD) {
                    next();
                } else {
                    previous();
                }
            }
        };

        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(nextRunnable, 0, (long) ((1.0f / this.time) * 1000), TimeUnit.MILLISECONDS);
    }
    /**
     * Mètode que configura la direcció de la reproducció
     * @param type 
     */
    @Override
    public void setDirection(DirectionType type) {
        this.dir = type;
    }
    /**
     * Mètode que pausa la reproducció del video, crida als mètodes necessaris per descomprimir i retorna si s'ha pogut obrir el zip i aconseguir les imatges correctament.
     * @param path
     * @return 
     */
    @Override
    public boolean openZip(String path) {
        if (executor != null) {
            executor.shutdown();
        }
        this.zip = disk.openZip(path);
        if (zip != null) {
            this.images = compressor.decompressZip(zip);
            this.imagesCopia = deepCopyArrayList(images);
            return true;
        }
        return false;

    }
    /**
     * Mètode crida als mètodes necessaris per obrir la imatge i retorna si s'ha pogut aconseguir la imatge correctament.
     * @param path
     * @return 
     */
    @Override
    public boolean openImage(String path) {
        Imatge img = disk.openImage(path);
        if (img != null) {
            this.images.clear();
            this.imagesCopia.clear();
            this.images.add(img);
            this.imagesCopia.add(img);
            return true;
        }
        return false;
    }
    /**
     * Mètode que crida guarda una imatge en el path passat per paràmetre.
     * @param path 
     */
    @Override
    public void saveImage(String path) {
        disk.saveImage(path, this.images.get(0));
    }
    /**
     * Mètode que guarda el conjunt d'imatges en un zip en el path passat per paràmetre
     * @param path 
     */
    @Override
    public void saveZip(String path) {
        disk.saveZip(path, images);
    }
    /**
     * Mètode que guarda 1 gzip per cada imatge en el path que es passa per paràmetre
     * @param path 
     */
    @Override
    public void saveGZip(String path) {
        if (zip != null) {
            ArrayList<File> files = compressor.getFilesFromZip(path, this.zip);
            disk.saveGZip(path, files);
        }

    }
    /**
     * Mètode que mostra la primera imatge que hi ha carregada
     */
    @Override
    public void first() {
        if (!images.isEmpty()) {
            index = 0;
            if (listener != null) {
                listener.onImage(images.get(index));
            }
        }
    }
    /**
     * Mètode que mostra la següent imatge
     */
    @Override
    public void next() {
        if (!images.isEmpty()) {
            index++;
            if (index == images.size() || index < 0) {
                index = 0;
            }

            if (listener != null) {
                listener.onImage(images.get(index));
            }
        }

    }
    /**
     * Mètode que mostra l'anterior imatge respecte l'actual
     */
    @Override
    public void previous() {
        if (!images.isEmpty()) {
            index--;
            if (index == images.size() || index < 0) {
                index = images.size() - 1;
            }
            if (listener != null) {
                listener.onImage(images.get(index));
            }
        }

    }
    /**
     * Mètode que restaura les imatges originals i mostra la primera
     */
    @Override
    public void removeFilter() {
        this.images = deepCopyArrayList(imagesCopia);
        first();
    }
    /**
     * Mètode que aplica un filtre a totes les imatges, retorna true si ha anat bé.
     * @param filter
     * @return 
     */
    @Override
    public boolean applyFilter(FilterDim3 filter) {

        ArrayList<Imatge> resultat = this.filter.convolveImages(deepCopyArrayList(imagesCopia), filter);
        if (resultat != null) {
            this.images = resultat;
            first();
        }
        return resultat != null;

    }
    /**
     * Mètode que fa una copia de les imatges per a poder-les restaurar més tard
     * @param original
     * @return 
     */
    private ArrayList<Imatge> deepCopyArrayList(ArrayList<Imatge> original) {
        ArrayList<Imatge> copia = new ArrayList<>(original.size());
        for (Imatge img : original) {
            Imatge im = new Imatge();
            im.setName(img.getName());
            im.setImage(img.deepCopy());
            copia.add(im);
        }
        return copia;
    }
    /**
     * Mètode que aplica el filtre negatiu
     */
    @Override
    public void negativeFilter() {
        this.images = this.filter.negativeFilter(deepCopyArrayList(imagesCopia));
        first();
    }
    /**
     * Mètode que aplica el filtre binari segons un threshold
     * @param threshold 
     */
    @Override
    public void binaryFilter(int threshold) {
        this.images = this.filter.binaryFilter(deepCopyArrayList(imagesCopia), threshold);
        first();
    }

    /**
     * Mètode per canviar els valors de HSB de totes les imatges, si algun dels
     * 3 paràmetres no es volen modificar s'ha de passar 0.
     *
     * @param hue
     * @param saturation
     * @param brightness
     */
    @Override
    public void changeHSB(float hue, float saturation, float brightness) {
        this.images = this.filter.changeHSB(deepCopyArrayList(imagesCopia), hue, saturation, brightness);
        first();
    }
    /**
     * Mètode que retorna l'últim threshold aplicat
     * @return 
     */
    @Override
    public int getThreshold() {
        return this.filter.getThreshold();
    }
    /**
     * Mètode que retorna l'últim filtre aplicat
     * @return 
     */
    @Override
    public FilterDim3 getFilterDim3() {
        return this.filter.getLastFilterApplied();
    }
    /**
     * Mètode que retorna si s'està reproduint el video o no (false)
     * @return 
     */
    @Override
    public boolean isPlaying() {
        if (executor != null) {
            return !this.executor.isShutdown();
        }
        return false;
    }
    /**
     * Mètode que retorna el frame rate que està aplicat
     * @return 
     */
    @Override
    public int getFrameRate() {
        return this.time;
    }
    /**
     * Mètode que retorna la direcció amb la qual s'està reproduint
     * @return 
     */
    @Override
    public DirectionType getDirection() {
        return this.dir;
    }
    /**
     * Mètode que retorna l'últim hue aplicat
     * @return 
     */
    @Override
    public float getHue() {
        return this.filter.getLastHue();
    }
    /**
     * Mètode que l'última saturation que s'ha aplicat
     * @return 
     */
    @Override
    public float getSaturation() {
        return this.filter.getLastSaturation();
    }
    /**
     * Mètode que retorna l'última brightness que s'ha aplicat
     * @return 
     */
    @Override
    public float getBrightness() {
        return this.filter.getLastValue();
    }
    
    @Override
    public void saveFX(String path) {
        FXContent content = compressor.compressFX(deepCopyArrayList(imagesCopia), getGoP(), getSizeTesela(), getPC(), getFQ());
        content.save(path, disk);
    }

    @Override
    public boolean openFX(String path) {
        FXContent fx = FXContent.open(path , disk, compressor);
        if(fx == null)
            return false;
        images = compressor.decompressFX(fx);
        imagesCopia = deepCopyArrayList(images);
        return true;
    }

    @Override
    public void setGoP(int GoP) {
        this.compressor.setGoP(GoP);
    }

    @Override
    public int getGoP() {
        return this.compressor.getGoP();
    }

    @Override
    public void setSizeTesela(int size_t) {
        this.compressor.setSize_t(size_t);
    }

    @Override
    public int getSizeTesela() {
        return this.compressor.getSize_t();
    }

    @Override
    public void setPC(int pc) {
        this.compressor.setPc(pc);
    }

    @Override
    public int getPC() {
        return this.compressor.getPc();
    }

    @Override
    public void setFQ(float fq) {
        this.compressor.setFq(fq);
    }

    @Override
    public float getFQ() {
        return this.compressor.getFq();
    }
}
