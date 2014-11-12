/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.player.OnImageListener;
import controller.compressor.CompressorController;
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
 *
 * @author albert
 */
public class MainController implements IPlayer, IFilter, IDisk {

    private ArrayList<Imatge> images, imagesCopia;
    private ScheduledExecutorService executor;
    private int index, time;
    private DirectionType dir;
    private final OnImageListener listener;
    private final CompressorController compressor;
    private final DiskController disk;
    private final FilterController filter;
    private ZipFile zip;

    public MainController(OnImageListener listener) {
        this.listener = listener;
        this.dir = Config.DEFAULT_DIRECTION;
        this.time = Config.DEFAULT_FRAME_RATE;
        this.compressor = new CompressorController();
        this.disk = new DiskController();
        this.filter = new FilterController();
        this.images = new ArrayList<>();
    }

    @Override
    public void setFrameRate(int time) {
        this.time = time;
        boolean isPlaying = isPlaying();
        pause();
        if (isPlaying) {
            play();
        }
    }

    @Override
    public void pause() {
        if (executor != null) {
            executor.shutdown();
        }
    }

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

    @Override
    public void setDirection(DirectionType type) {
        this.dir = type;
    }

    @Override
    public void openZip(String path) {
        if (executor != null) {
            executor.shutdown();
        }
        this.zip = disk.openZip(path);
        this.images = compressor.decompressZip(zip);
        this.imagesCopia = (ArrayList<Imatge>) this.images.clone();
    }

    @Override
    public void openImage(String path) {
        Imatge img = disk.openImage(path);
        this.images.clear();
        this.imagesCopia.clear();
        this.images.add(img);
        this.imagesCopia.add(img);
    }

    @Override
    public void saveImage(String path) {
        disk.saveImage(path, this.images.get(0));
    }

    @Override
    public void saveZip(String path) {
        disk.saveZip(path, images);
    }

    @Override
    public void saveGZip(String path) {
        if (zip != null) {
            ArrayList<File> files = compressor.getFilesFromZip(path, this.zip);
            disk.saveGZip(path, files);
        }

    }

    @Override
    public void first() {
        index = 0;
        if (listener != null) {
            listener.onImage(images.get(index));
        }
    }

    @Override
    public void next() {
        index++;
        if (index == images.size() || index < 0) {
            index = 0;
        }

        if (listener != null) {
            listener.onImage(images.get(index));
        }

    }

    @Override
    public void previous() {
        index--;
        if (index == images.size() || index < 0) {
            index = images.size() - 1;
        }
        if (listener != null) {
            listener.onImage(images.get(index));
        }

    }

    @Override
    public void removeFilter() {
        this.images = deepCopyArrayList(imagesCopia);
    }

    @Override
    public void applyFilter(FilterDim3 filter) {

        this.images = this.filter.convolveImages(deepCopyArrayList(imagesCopia), filter);
        first();
    }

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

    @Override
    public void negativeFilter() {
        this.filter.negativeFilter(images);
        first();
    }

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

    @Override
    public int getThreshold() {
        return this.filter.getThreshold();
    }

    @Override
    public FilterDim3 getFilterDim3() {
        return this.filter.getLastFilterApplied();
    }

    @Override
    public boolean isPlaying() {
        if (executor != null) {
            return !this.executor.isShutdown();
        }
        return false;
    }

    @Override
    public int getFrameRate() {
        return this.time;
    }

    @Override
    public DirectionType getDirection() {
        return this.dir;
    }

    @Override
    public float getHue() {
        return this.filter.getLastHue();
    }

    @Override
    public float getSaturation() {
        return this.filter.getLastSaturation();
    }

    @Override
    public float getBrightness() {
        return this.filter.getLastValue();
    }
}
