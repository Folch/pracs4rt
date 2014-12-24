/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compressor;

import controller.MainController;
import controller.filter.threads.ConvolveThread;
import controller.statistics.Statistics;
import model.Imatge;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import model.config.Config;
import java.awt.Color;

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

    public CompressorController() {
        this.GoP = Config.DEFAULT_GOP;
        this.size_t = Config.DEFAULT_SIZE_TESELA;
        this.pc = Config.DEFAULT_PC;
        this.fq = Config.DEFAULT_FQ;
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
        try {
            Enumeration<? extends ZipEntry> entries = zFl.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                Imatge image = new Imatge();
                String imgName = entry.getName();
                image.setName(imgName);
                InputStream is = zFl.getInputStream(entry);
                ImageInputStream iis = ImageIO.createImageInputStream(is);
                BufferedImage bufImg = ImageIO.read(iis);
                image.setImage(bufImg);
                images.add(image);

            }

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
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

        int refs = imatges.size() / GoP;
        Imatge ref = imatges.get(0);

        FXFile fxf = new FXFile(GoP, size_t);

        for (int i = 1; i < imatges.size(); i++) {
            Imatge img = imatges.get(i);
            fxf.frames.add(new HashMap<Integer, Integer[]>());
            if (i % refs == 0) {
                ref = img;
                continue;
            }
            for (int j = 0; j < img.getNumTeseles(size_t); j++) {
                Integer[] pos = searchTesela(ref, img, j, size_t, pc, fq);
                deleteTesela(img, pos, size_t);
                fxf.frames.get(i).put(j, pos);
            }
        }

        FXContent content = new FXContent(imatges, fxf);

        return content;
    }

    @Override
    public ArrayList<Imatge> decompressFX(FXContent content) {

        return null;
    }
    /*
     private Integer[] searchTesela(Imatge src, Imatge dest, int tesela, int size_t, int pc, float fq) {
     int width = src.getImage().getWidth();
     int height = src.getImage().getHeight();
     Integer[] posD = new Integer[2];
     for (int i = 0; i < src.getNumTeseles(size_t); i++) {
     Integer[] pos = src.getPosTesela(i, size_t);//pos[0]=x,columnes   pos[1]=y,files
     BufferedImage subimatge = src.getImage().getSubimage(pos[0].intValue(), pos[1].intValue(), size_t, size_t);
     int initColumna = pos[0] - pc < 0 ? 0 : pos[0] - pc;
     int initFila = pos[1] - pc < 0 ? 0 : pos[1] - pc;
     int fiColumna = pos[0] + pc >= width ? width - 1 : pos[0] + pc;
     int fiFila = pos[1] + pc >= height ? height - 1 : pos[1] + pc;
     boolean trobat = false;
     for (int fila = initFila; fila < fiFila && !trobat; fila++) {//cerca cicular!!
     for (int col = initColumna; col < fiColumna && !trobat; col++) {
     if (fila + size_t > fiFila || col + size_t > fiColumna) {
     break;
     } else {
     BufferedImage desti = dest.getImage().getSubimage(col, fila, size_t, size_t);
     double diff = Statistics.normalizedCrossCorrelation(subimatge, desti);
     if (diff < pc) {
     posD[0] = col;
     posD[1] = fila;
     trobat = true;
     }

     }

     }

     }

     }
     return posD;
     }*/

    private Integer[] searchTesela(Imatge src, Imatge dest, int tesela, int size_t, int pc, float fq) {
        int width = src.getImage().getWidth();
        int height = src.getImage().getHeight();
        Integer[] posD = new Integer[2];
        boolean trobat = false;
        Integer[] pos = src.getPosTesela(tesela, size_t);//pos[0]=x,columnes   pos[1]=y,files
        BufferedImage subimatge = src.getImage().getSubimage(pos[0].intValue(), pos[1].intValue(), size_t, size_t);

        for (int l = 0; l < pc && !trobat; l++) {
            for (int fila = pos[1] - l; fila <= pos[1] + l - 2 && !trobat; fila++) {
                if (fila >= 0 && fila < height - size_t) {

                    for (int col = pos[0] - l; col <= pos[0] + l - 2 && !trobat;) {
                        if (col >= 0 && col < width - size_t) {
                            BufferedImage desti = dest.getImage().getSubimage(col, fila, size_t, size_t);
                            double diff = Statistics.normalizedCrossCorrelation(subimatge, desti);
                            if (diff < fq) {
                                posD[0] = pos[0];
                                posD[1] = pos[1];
                                trobat = true;
                            } else {
                                if (fila == pos[1] - l || fila == pos[1] + l - 2) {
                                    col++;
                                } else {
                                    col = pos[0] + l - 2;
                                }
                            }
                        } else if (col < 0) {
                            col = 0;
                        } else {
                            break;
                        }

                    }
                } else if (fila < 0) {
                    fila = 0;
                } else {
                    break;
                }
            }

        }
        return posD;
    }

    //el valor que es posa en el moment d'eliminar es la mitja de TOTA la imatge
    private void deleteTesela(Imatge imatge, Integer[] pos, int size_t) {
        BufferedImage img = imatge.getImage();
        Statistics s = new Statistics(img);
        float mean = s.getMean();
        Color colorMig = new Color(mean, mean, mean);

        for (int i = pos[0]; i < pos[0] + size_t; i++) {
            for (int j = pos[1]; j < pos[1] + size_t; j++) {
                img.setRGB(i, j, colorMig.getRGB());
            }

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

}
