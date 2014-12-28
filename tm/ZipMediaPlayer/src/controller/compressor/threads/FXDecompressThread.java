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
public class FXDecompressThread implements Callable {

    private final FXContent content;
    private final OnLoading loading;

    public FXDecompressThread(FXContent cont, OnLoading load) {
        this.content = cont;
        this.loading = load;
    }

    @Override
    public Object call() throws Exception {
        return decompress(content);
    }

    private ArrayList<Imatge> decompress(FXContent content) {
        ArrayList<Imatge> imgs = content.getImatges(); // imatges amb forats
        FXFile fx = content.getFx();
        int numImatges = imgs.size();
        int GoP_tmp = content.getFx().getGoP();
        int size_t_tmp = content.getFx().getSize_t();
        int refs = numImatges / GoP_tmp;
        Imatge ref = imgs.get(0);
        boolean timeCalculated = false;
        DatatypeFactory datafactory;
        long start, end, res, init = System.currentTimeMillis();

        try {

            datafactory = DatatypeFactory.newInstance();
            for (int i = 1; i < numImatges; i++) {
                Imatge img = imgs.get(i);
                if (i % refs == 0) {
                    ref = img;
                    continue;
                }
                HashMap posicionsTeseles = fx.frames.get(i);
                for (int tesela = 0; tesela < ref.getNumTeseles(size_t_tmp); tesela++) {
                    Integer[] posTesela = (Integer[]) posicionsTeseles.get(tesela);
                    Integer[] refPosTesela = ref.getPosTesela(tesela, size_t_tmp);

                    BufferedImage imgToFill = img.getImage();

                    BufferedImage imgRef = ref.getImage();
                    Duration timeleft;
                    start = System.currentTimeMillis();
                    //pos[0]=x,columnes   pos[1]=y,files
                    for (int col = 0; col < size_t_tmp; col++) {
                        for (int fila = 0; fila < size_t_tmp; fila++) {
                            /*
                            System.out.println("width = "+imgRef.getWidth()+" height="+imgRef.getHeight());
                            System.out.println(" col ="+(col + refPosTesela[0])+" fila= "+(fila + refPosTesela[1]));
                            System.out.println();
                                    */
                            int rgb = imgRef.getRGB(col + refPosTesela[0], fila + refPosTesela[1]);
                            if (posTesela != null) {
                                imgToFill.setRGB(col + posTesela[0], fila + posTesela[1], rgb);
                            }
                        }

                    }
                    end = System.currentTimeMillis();
                    res = ((numImatges/(i+1))-1)*(end-init) + (numImatges%(i+1))*(end-start);
                    timeleft = datafactory.newDuration(res);
                    this.loading.updateProgressBar((short) (i * 100 / numImatges), timeleft);
                }

            }

        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(FXDecompressThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imgs;

    }

}
