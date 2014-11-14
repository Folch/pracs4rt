/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter.threads;

import static controller.filter.FilterController.BORDES_0;
import static controller.filter.FilterController.SENSE_BORDES;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.concurrent.Callable;
import model.FilterDim3;

/**
 *
 * @author albert
 */
public class ConvolveThread extends FilterThread implements Callable {

    public ConvolveThread() {
        super();
    }

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

    private BufferedImage convolve(float filtre[][], BufferedImage imatge, int tratBordes)  {
        BufferedImage res;

        if (imatge == null) {
            throw new IllegalArgumentException("La imatge no pot ser nula");
        }
        if (filtre == null || filtre.length == 0) {
            throw new IllegalArgumentException("S'ha de passar algun filtre vàlid");
        }

        if (tratBordes != SENSE_BORDES || tratBordes != BORDES_0) {
            tratBordes = SENSE_BORDES;
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
        ConvolveOp cop = new ConvolveOp(kernel, tratBordes, null);

        //Creem la imatge nova semblant a l'antiga
        res = new BufferedImage(imatge.getWidth(), imatge.getHeight(), imatge.getType());

        //Apliquem el filtre
        cop.filter(imatge, res);

        return res;
    }

}
