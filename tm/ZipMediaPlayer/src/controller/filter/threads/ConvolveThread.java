/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter.threads;

import static controller.filter.FilterController.BORDES_0;
import static controller.filter.FilterController.SENSE_BORDES;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.concurrent.Callable;
import model.FilterDim3;

/**
 * Classe que representa el thread que convolucionarà un subconjunt d'imatges
 * @author Albert Folch i Xavi Moreno
 */
public class ConvolveThread extends FilterThread implements Callable {

    public ConvolveThread() {
        super();
    }
    /**
     * Mètode que aplica la convolució a un subconjunt d'imatges. Si el filtre que es vol aplicar es Sobel_X, Sobel_Y, High_pass o laplacian s'aplicarà una conversió a escala de grisos
     * @return
     * @throws Exception 
     */
    @Override
    public Object call() throws Exception {
        for (int i = start; i < end; i++) {

            if (this.filter.getLastFilterApplied() == FilterDim3.SOBEL_X || this.filter.getLastFilterApplied() == FilterDim3.SOBEL_Y || this.filter.getLastFilterApplied() == FilterDim3.HIGH_PASS || this.filter.getLastFilterApplied() == FilterDim3.LAPLACIAN) {
                super.grayScale(this.filter.getImatges().get(i).getImage());
            }
            this.filter.getImatges().get(i).setImage(this.convolve(this.filter.getLastFilterApplied().getFilter(), this.filter.getImatges().get(i).getImage(), BORDES_0));
        }
        return null;
    }
    /**
     * Mètode de convolució d'una imatge bufferedImage segons el filtre 2d filtre[][]. El paràmetre bordes configura el seu tractament en la convolució.
     * @param filtre
     * @param imatge
     * @param bordes
     * @return 
     */
    public BufferedImage convolve(float filtre[][], BufferedImage imatge, int bordes)  {
        BufferedImage res;

        if (imatge == null) {
            throw new IllegalArgumentException("La imatge no pot ser nula");
        }
        if (filtre == null || filtre.length == 0) {
            throw new IllegalArgumentException("S'ha de passar algun filtre vàlid");
        }

        if (bordes != SENSE_BORDES || bordes != BORDES_0) {
            bordes = SENSE_BORDES;
        }

        int width = filtre.length;
        int height = filtre[0].length;
        int tam = width * height;
        float filtroK[] = new float[tam];

        //Creem el filtre
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                filtroK[i * width + j] = filtre[i][j];
            }
        }

        //Creem l'operació de convolució
        Kernel kernel = new Kernel(width, height, filtroK);
        ConvolveOp cop = new ConvolveOp(kernel, bordes, null);

        //Creem la imatge nova semblant a l'antiga
        res = new BufferedImage(imatge.getWidth(), imatge.getHeight(), imatge.getType());

        //Apliquem el filtre
        cop.filter(imatge, res);

        return res;
    }
    public Color getMean(BufferedImage imatge)  {
        BufferedImage res;
        int bordes = BORDES_0;
        float[][] filtre = FilterDim3.AVERAGE.getFilter();
        if (imatge == null) {
            throw new IllegalArgumentException("La imatge no pot ser nula");
        }
        


        int width = filtre.length;
        int height = filtre[0].length;
        int tam = width * height;
        float filtroK[] = new float[tam];

        //Creem el filtre
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                filtroK[i * width + j] = filtre[i][j];
            }
        }

        //Creem l'operació de convolució
        Kernel kernel = new Kernel(width, height, filtroK);
        ConvolveOp cop = new ConvolveOp(kernel, bordes, null);

        //Creem la imatge nova semblant a l'antiga
        res = new BufferedImage(imatge.getWidth(), imatge.getHeight(), imatge.getType());

        //Apliquem el filtre
        cop.filter(imatge, res);

        float meanR = 0;
        float meanG = 0;
        float meanB = 0;
        int w =  imatge.getWidth();
        int h = imatge.getHeight();
        int wh = w*h;
        for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    Color c = new Color(imatge.getRGB(i, j));
                    meanR += c.getRed();
                    meanG += c.getGreen();
                    meanB += c.getBlue();
                    
                }
        }
        meanR /= wh;
        meanG /= wh;
        meanB /= wh;

        return new Color(meanR, meanG, meanB);
    }

}
