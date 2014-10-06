/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zipmediaplayer;

import Controller.OnImageListener;
import Controller.ZipController;
import java.awt.image.BufferedImage;

/**
 *
 * @author zenbook
 */
public class ZipMediaPlayer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ZipController cont = new ZipController("/home/albert/Downloads/imagenes.zip", ZipController.FileType.ZIP, new OnImageListener() {

            @Override
            public void onImage(BufferedImage i) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
    }

}
