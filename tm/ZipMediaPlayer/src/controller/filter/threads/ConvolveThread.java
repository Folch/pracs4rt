/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter.threads;

import static controller.filter.FilterController.BORDES_0;
import model.FilterDim3;

/**
 *
 * @author albert
 */
public class ConvolveThread  extends FilterThread  implements Runnable {

    public ConvolveThread(){
        super();
    }
    @Override
    public void run() {
        for (int i = start; i < end; i++) {

            if (this.filter.getLastFilterApplied() == FilterDim3.SOBEL_X || this.filter.getLastFilterApplied() == FilterDim3.SOBEL_Y || this.filter.getLastFilterApplied() == FilterDim3.HIGH_PASS || this.filter.getLastFilterApplied() == FilterDim3.LAPLACIAN) {
                this.filter.grayScale(this.filter.getImatges().get(i).getImage());
            }
            this.filter.getImatges().get(i).setImage(this.filter.convolve(this.filter.getLastFilterApplied().getFilter(), this.filter.getImatges().get(i).getImage(), BORDES_0));
        }

    }
}
