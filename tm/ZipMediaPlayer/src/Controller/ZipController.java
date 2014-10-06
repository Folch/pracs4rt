/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
public class ZipController {

    private ArrayList<BufferedImage> images;
    private OnImageListener listener;
    private ScheduledExecutorService executor;
    private int index;
    private String path;

    public enum FileType {

        IMAGE, ZIP
    };

    public ZipController(String path, FileType f, OnImageListener listener) {
        this.listener = listener;
        this.setPath(path, f);
    }

    public void first() {
        index = 0;
        if (listener != null) {
            listener.onImage(images.get(index));
        }
    }

    public void next() {
        index++;
        if (index == (images.size() - 1)) {
            index = 0;
        }
        System.out.println("Imatge index= " + index);
        if (listener != null) {
            listener.onImage(images.get(index));
        }
    }

    public void previous() {
        if (index == 0) {
            index = images.size() - 1;
        }
        if (listener != null) {
            listener.onImage(images.get(index));
        }
    }

    public void auto(int time) {
        Runnable nextRunnable = new Runnable() {
            public void run() {
                next();
            }
        };

        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(nextRunnable, 0, time, TimeUnit.MILLISECONDS);

    }

    public void manual() {
        executor.shutdown();
    }

    public void setPath(String path, FileType f) {
        this.path = path;
        this.index = 0;
        this.images = new ArrayList<>();

        File file = new File(this.path);
        if (f == FileType.ZIP) {
            try {
                ZipFile zFl = new ZipFile(file);
                Enumeration<? extends ZipEntry> entries = zFl.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    String imgName = entry.getName();
                    InputStream is = zFl.getInputStream(entry);
                    ImageInputStream iis = ImageIO.createImageInputStream(is);
                    BufferedImage bufImg = ImageIO.read(iis);
                    images.add(bufImg);

                }
                System.out.println("Imatges carregades");
            } catch (IOException ex) {
                Logger.getLogger(ZipController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { //image
            try {

                BufferedImage bufImg = ImageIO.read(file);
                images.add(bufImg);
            } catch (IOException ex) {
                Logger.getLogger(ZipController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //comprimmeix les imatges i les guarda al mateix path d'entrada amb una altra extensio

    public void saveImages() {

    }
}
