/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Imatge;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;

/**
 *
 * @author albert
 */
public class DiscController implements InternalIDisk {

    @Override
    public ZipFile openZip(String path) {
        ZipFile zFl = null;
        try {
            File file = new File(path);
            zFl = new ZipFile(file);
            
        } catch (IOException ex) {
            Logger.getLogger(DiscController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zFl;
        
    }

    @Override
    public Imatge openImage(String path) {
        File file = new File(path);
        Imatge image = null;
        try {

            BufferedImage bufImg = ImageIO.read(file);
            image = new Imatge();
            image.setImage(bufImg);
            image.setName(file.getName());

        } catch (IOException ex) {
            Logger.getLogger(ZipController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    @Override
    public void saveImage(String path, Imatge img) {
        
    }

    @Override
    public void saveZip(String path, ArrayList<Imatge> images) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveGZip(String path, ArrayList<Imatge> images) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
