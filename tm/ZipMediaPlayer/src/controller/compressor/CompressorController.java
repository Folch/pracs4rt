/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compressor;

import controller.MainController;
import model.Imatge;
import java.awt.image.BufferedImage;
import java.io.File;
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
 * Classe controladora de totes les accions relacionades amb la compressió
 * @author Albert Folch i Xavi Moreno
 */
public class CompressorController implements ICompressor {
    /**
     * Mètode que segons un ZipFile el descomprimeix i et retorna un ArrayList de Imatge
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
     * Aquest mètode fa: Descomprimir Zip,Guardar imatges com a files a disc i retorna l'arraylist de files corresponent a les imatges
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
                outputfile = new File((image.getName().substring(0, image.getName().length() - 4))+".jpg");
                ImageIO.write(image.getImage(), "jpg", outputfile);
                files.add(outputfile);
                
            } catch (IOException ex) {
                Logger.getLogger(CompressorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return files;
    }

}
