/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Imatge;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author albert
 */
public class ZipController implements IPlayer, IFilter, IDisk {

    private ArrayList<Imatge> images;
    private OnImageListener listener;
    private ScheduledExecutorService executor;
    private int index;
    private int time;
    private DirectionType dir;
    private CompressorController compressor;
    private DiscController disk;

    public enum DirectionType {

        BACKWARD, FORWARD
    };

    public enum FileType {

        IMAGE, ZIP
    };

    public ZipController(String path, FileType f, OnImageListener listener) {
        this.listener = listener;
        this.dir = Config.DEFAULT_DIRECTION;
        this.time = Config.DEFAULT_FRAME_RATE;
        this.compressor = new CompressorController();
        this.disk = new DiscController();
        this.images = new ArrayList<>();
    }

    @Override
    public void setFrameRate(int time) {
        this.time = time;
    }

    @Override
    public void pause() {
        executor.shutdown();
    }

    @Override
    public void play() {
        Runnable nextRunnable = new Runnable() {
            public void run() {
                if (dir == DirectionType.FORWARD) {
                    next();
                } else {
                    previous();
                }
            }
        };

        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(nextRunnable, 0, this.time, TimeUnit.MILLISECONDS);
    }

    @Override
    public void setDirection(DirectionType type) {
        this.dir = type;
    }

    @Override
    public void openZip(String path) {
        ZipFile zip = disk.openZip(path);
        this.images = compressor.decompressZip(zip);
    }

    @Override
    public void openImage(String path) {
        Imatge img = disk.openImage(path);
        this.images.add(img);
    }

    @Override
    public void saveImage(String path) {
        disk.saveImage(path,this.images.get(0));
    }

    @Override
    public void saveZip(String path) {
        disk.saveZip(path, this.images);
    }

    @Override
    public void saveGZip(String path) {
        disk.saveGZip(path, this.images);
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
}
