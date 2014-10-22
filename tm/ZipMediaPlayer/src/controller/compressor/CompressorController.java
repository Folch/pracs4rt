/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compressor;

import controller.MainController;
import model.Imatge;
import java.awt.image.BufferedImage;
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

/**
 *
 * @author albert
 */
public class CompressorController implements ICompressor {

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

}
