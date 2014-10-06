/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Image;
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

    private ArrayList<Image> images;
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
        
        if (index == images.size() || index < 0) {
            index = 0;
        }
        
        if (listener != null) {
            listener.onImage(images.get(index));
        }
        index++;
    }

    public void previous() {
        if (index == images.size() || index < 0) {
            index = images.size() -1;
        }
        if (listener != null) {
            listener.onImage(images.get(index));
        }
        index--;
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
                    Image image = new Image();
                    String imgName = entry.getName();
                    image.setName(imgName);
                    InputStream is = zFl.getInputStream(entry);
                    ImageInputStream iis = ImageIO.createImageInputStream(is);
                    BufferedImage bufImg = ImageIO.read(iis);
                    image.setImage(bufImg);
                    images.add(image);

                }
                
            } catch (IOException ex) {
                Logger.getLogger(ZipController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else { //image
            try {

                BufferedImage bufImg = ImageIO.read(file);
                Image image = new Image();
                image.setImage(bufImg);
                image.setName(file.getName());
                images.add(image);
            } catch (IOException ex) {
                Logger.getLogger(ZipController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void jpgToPng(){//no funciona encara
        for (Image image : images) {
            try {
                ImageIO.write(image.getImage(), "png", new File(image.getName()));
            } catch (IOException ex) {
                Logger.getLogger(ZipController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //comprimmeix les imatges i les guarda al mateix path d'entrada amb una altra extensio

    public void saveImages() {

    }
}
