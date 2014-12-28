/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compressor.threads;

import controller.compressor.CompressorController;
import controller.compressor.FXFile;
import controller.player.OnLoading;
import controller.statistics.Statistics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import model.Imatge;

/**
 *
 * @author albert
 */
public class FXCompressThread implements Callable {

    private final int size_t;
    private final int pc;
    private final float fq;
    private final int inici;
    private final ArrayList<Imatge> imatges;
    private final int GoP;
    private final int fi;
    private final CompressorController compressor;
    private final OnLoading loading;

    public FXCompressThread(int inici, int fi, int size, int pc, float fq, int GoP, ArrayList<Imatge> imatges, CompressorController compr, OnLoading load) {
        this.imatges = imatges;
        this.inici = inici;
        this.fi = fi;
        this.size_t = size;
        this.pc = pc;
        this.fq = fq;
        this.GoP = GoP;
        this.compressor = compr;
        this.loading = load;
    }

    @Override
    public Object call() throws Exception {
        return compressFX();
    }

    private FXFile compressFX() {
        FXFile fxf = new FXFile(GoP, size_t);
        Imatge ref = imatges.get(inici);
        fxf.frames.add(new HashMap<Integer, Integer[]>());//sincronitzar
        int k = 0;
        long start, end, res, init = System.currentTimeMillis();
        Duration timeleft;
        int numImatges = imatges.size();
        try {

            DatatypeFactory datafactory;
            datafactory = DatatypeFactory.newInstance();
            for (int i = inici + 1; i < fi; i++) {
                start = System.currentTimeMillis();
                Imatge img = imatges.get(i);

                fxf.frames.add(new HashMap<Integer, Integer[]>());//sincronitzar
                HashMap hm = fxf.frames.get(k);
                k++;
                for (int j = 0; j < img.getNumTeseles(size_t); j++) {
                    Integer[] pos = searchTesela(ref, img, j, size_t, pc, fq);
                    if (pos != null) {
                        deleteTesela(img, pos, size_t);
                        hm.put(j, pos);
                    }
                }
                synchronized (compressor) {
                    System.out.println(compressor.getI());//borrar
                    int max = Math.max(compressor.getI(), i);
                    compressor.setI(max);
                    end = System.currentTimeMillis();
                    res = ((numImatges / (max + 1)) - 1) * (end - init) + (numImatges % (max + 1)) * (end - start);
                    timeleft = datafactory.newDuration(res);
                    if (loading != null) {
                        this.loading.updateProgressBar((short) ((max + 1) * 100 / numImatges), timeleft);
                    }
                }
            }

        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(FXCompressThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fxf;

    }

    //el valor que es posa en el moment d'eliminar es la mitja de TOTA la imatge
    private void deleteTesela(Imatge imatge, Integer[] pos, int size_t) {
        BufferedImage img = imatge.getImage();
        Statistics s = new Statistics(img);
        Color colorMig = s.getMeanCanals();

        for (int i = pos[0]; i < pos[0] + size_t; i++) {
            for (int j = pos[1]; j < pos[1] + size_t; j++) {
                img.setRGB(i, j, colorMig.getRGB());
            }

        }
    }

    private Integer[] searchTesela(Imatge src, Imatge dest, int tesela, int size_t, int pc, float fq) {
        int width = src.getImage().getWidth();
        int height = src.getImage().getHeight();

        Integer[] pos = src.getPosTesela(tesela, size_t);//pos[0]=x,columnes   pos[1]=y,files

        if (pos[0] + size_t >= width || pos[1] + size_t >= height) {
            return null;
        }
        BufferedImage subimatge = src.getImage().getSubimage(pos[0].intValue(), pos[1].intValue(), size_t, size_t);

        for (int l = 0; l < pc; l++) {
            int lastRow = pos[1] + l + size_t;
            int lastColumn = pos[0] + l + size_t;
            /*
             System.out.println("Inici "+(pos[1]-l)+"  "+(pos[0]-l));
             System.out.println("Fi "+lastRow+"  "+lastColumn);
             */
            for (int fila = pos[1] - l; fila < lastRow; fila++) {
                if (fila >= 0 && fila < height - size_t) {

                    for (int col = pos[0] - l; col < lastColumn;) {
                        //System.out.println("fila = "+fila+" columna = "+col);
                        if (col >= 0 && col < width - size_t) {
                            BufferedImage desti = dest.getImage().getSubimage(col, fila, size_t, size_t);
                            double diff = Statistics.normalizedCrossCorrelation(subimatge, desti);
                            if (diff > fq) {
                                Integer[] posD = new Integer[2];
                                posD[0] = pos[0];
                                posD[1] = pos[1];
                                return posD;
                            } else {
                                if (fila == pos[1] - l || fila == lastRow) {
                                    col++;
                                } else {
                                    col = lastColumn;
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
        return null;
    }
}
