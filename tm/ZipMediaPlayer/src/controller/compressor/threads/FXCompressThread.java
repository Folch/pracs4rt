/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compressor.threads;

import controller.compressor.CompressorController;
import controller.compressor.FXContent;
import controller.compressor.FXFile;
import controller.player.OnLoading;
import controller.statistics.Statistics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
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

    private final ArrayList<Imatge> imatges;
    private final int size_t;
    private final int GoP;
    private final int pc;
    private final float fq;
    private final OnLoading loading;

    public FXCompressThread( OnLoading load, ArrayList<Imatge> imatges, int GoP, int size_t, int pc, float fq) {
        this.loading = load;
        this.imatges = imatges;
        this.GoP = GoP;
        this.size_t = size_t;
        this.pc = pc;
        this.fq = fq;
    }

    @Override
    public Object call() throws Exception {
        return compressFX(imatges, GoP, size_t, pc, fq);
    }

    private FXContent compressFX(ArrayList<Imatge> imatges, int GoP, int size_t, int pc, float fq) {
        int numImatges = imatges.size();
        int refs = numImatges / GoP;
        Imatge ref = imatges.get(0);
        Collections.sort(imatges);
        boolean timeCalculated = false;
        DatatypeFactory datafactory;

        FXFile fxf = new FXFile(GoP, size_t);
        try {

            datafactory = DatatypeFactory.newInstance();
            fxf.frames.add(new HashMap<Integer, Integer[]>());
            for (int i = 1; i < numImatges; i++) {
                Imatge img = imatges.get(i);
                fxf.frames.add(new HashMap<Integer, Integer[]>());
                System.out.println(i + " Imatge " + img.getName());
                if (i % refs == 0) {
                    ref = img;
                    continue;
                }
                long start = -1, res = -1;
                Duration timeleft;
                if (!timeCalculated) {
                    start = System.currentTimeMillis();
                }
                HashMap hm = fxf.frames.get(i);
                for (int j = 0; j < img.getNumTeseles(size_t); j++) {

                    Integer[] pos = searchTesela(ref, img, j, size_t, pc, fq);

                    if (pos != null) {
                        //System.out.println("elimina la tesela " + j + " imatge " + img.getName());
                        deleteTesela(img, pos, size_t);
                        hm.put(j, pos);
                    }

                }
                if (!timeCalculated) {
                    long end = System.currentTimeMillis();
                    res = end - start;
                    timeleft = datafactory.newDuration(res);
                } else {
                    timeleft = datafactory.newDuration(res * (numImatges - i));
                }
                this.loading.updateProgressBar((short) (i * 100 / numImatges), timeleft);
            }

        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(CompressorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new FXContent(imatges, fxf);
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

}
