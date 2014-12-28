/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compressor;

import controller.MainController;
import controller.compressor.threads.FXCompressThread;
import controller.compressor.threads.FXDecompressThread;
import controller.player.OnLoading;
import controller.statistics.Statistics;
import model.Imatge;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import model.config.Config;
import java.awt.Color;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

/**
 * Classe controladora de totes les accions relacionades amb la compressió
 *
 * @author Albert Folch i Xavi Moreno
 */
public class CompressorController implements ICompressor {

    private int GoP;
    private int size_t;
    private int pc;
    private float fq;
    private final OnLoading loading;
    private final ExecutorService executor;

    public CompressorController(OnLoading loading) {
        this.GoP = Config.DEFAULT_GOP;
        this.size_t = Config.DEFAULT_SIZE_TESELA;
        this.pc = Config.DEFAULT_PC;
        this.fq = Config.DEFAULT_FQ;
        this.loading = loading;
        this.executor = Executors.newFixedThreadPool(1);
    }

    /**
     * Mètode que segons un ZipFile el descomprimeix i et retorna un ArrayList
     * de Imatge
     *
     * @param zFl
     * @return
     */
    @Override
    public ArrayList<Imatge> decompressZip(ZipFile zFl) {
        ArrayList<Imatge> images = new ArrayList<>();
        long start, end, res, init = System.currentTimeMillis();

        try {
            DatatypeFactory datafactory = DatatypeFactory.newInstance();
            Enumeration<? extends ZipEntry> entries = zFl.entries();
            int numImatges = zFl.size();
            int i = 0;
            while (entries.hasMoreElements()) {
                Duration timeleft;
                start = System.currentTimeMillis();
                ZipEntry entry = entries.nextElement();
                Imatge image = new Imatge();
                String imgName = entry.getName();
                image.setName(imgName);
                InputStream is = zFl.getInputStream(entry);
                ImageInputStream iis = ImageIO.createImageInputStream(is);
                BufferedImage bufImg = ImageIO.read(iis);
                image.setImage(bufImg);
                images.add(image);
                end = System.currentTimeMillis();
                res = ((numImatges / (i + 1)) - 1) * (end - init) + (numImatges % (i + 1)) * (end - start);
                timeleft = datafactory.newDuration(res);
                if (loading != null) {
                    this.loading.updateProgressBar((short) ((i + 1) * 100 / numImatges), timeleft);
                }
                i++;

            }

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(CompressorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return images;
    }

    /**
     * Aquest mètode fa: Descomprimir Zip,Guardar imatges com a files a disc i
     * retorna l'arraylist de files corresponent a les imatges
     *
     * @param path
     * @param zipFile
     * @return
     */
    @Override
    public ArrayList<File> getFilesFromZip(String path, ZipFile zipFile) {
        ArrayList<File> files = new ArrayList<>();
        File outputfile;
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {

            try {
                ZipEntry entry = entries.nextElement();
                Imatge image = new Imatge();
                String imgName = entry.getName();
                image.setName(imgName);
                InputStream is = zipFile.getInputStream(entry);
                ImageInputStream iis = ImageIO.createImageInputStream(is);
                BufferedImage bufImg = ImageIO.read(iis);
                image.setImage(bufImg);
                outputfile = new File((image.getName().substring(0, image.getName().length() - 4)) + ".jpg");
                ImageIO.write(image.getImage(), "jpg", outputfile);
                files.add(outputfile);

            } catch (IOException ex) {
                Logger.getLogger(CompressorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return files;
    }

    @Override
    public FXContent compressFX(ArrayList<Imatge> imatges, int GoP, int size_t, int pc, float fq) {
        try {
            Callable c = new FXCompressThread(loading, imatges, GoP, size_t, pc, fq);
            Future b = executor.submit(c);
            return (FXContent) b.get();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(CompressorController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ArrayList<Imatge> decompressFX(FXContent content) {
        try {
            Callable c = new FXDecompressThread(content, loading);
            Future b = executor.submit(c);
            return (ArrayList<Imatge>) b.get();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(CompressorController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int getGoP() {
        return GoP;
    }

    public void setGoP(int GoP) {
        this.GoP = GoP;
    }

    public int getSize_t() {
        return size_t;
    }

    public void setSize_t(int size_t) {
        this.size_t = size_t;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public float getFq() {
        return fq;
    }

    public void setFq(float fq) {
        this.fq = fq;
    }

    public void cancel() {
        executor.shutdown();
    }

}
