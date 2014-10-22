/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.disk;

import controller.MainController;
import model.Imatge;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author albert
 */
public class DiskController implements InternalIDisk {

    @Override
    public ZipFile openZip(String path) {
        ZipFile zFl = null;
        try {
            File file = new File(path);
            zFl = new ZipFile(file);

        } catch (IOException ex) {
            Logger.getLogger(DiskController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    @Override
    public void saveImage(String path, Imatge img) {
        try {
            BufferedImage bi = img.getImage();
            File outputfile = new File(path + ".png");
            ImageIO.write(bi, "png", outputfile);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void saveZip(String path, ArrayList<Imatge> imatges) {
        try {
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(new File(path + ".zip"))));
            for (Imatge imatge : imatges) {
                ZipEntry entry = new ZipEntry(imatge.getName());
                out.putNextEntry(entry);
                ImageIO.write(imatge.getImage(), "png", out);
            }
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(DiskController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void saveGZip(String path, ArrayList<Imatge> imatges) {
        try {
            GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(path + ".gzip"));

            for (Imatge imatge : imatges) {
                byte[] imageBytes = ((DataBufferByte) imatge.getImage().getData().getDataBuffer()).getData();
                int len = imageBytes.length;
                out.write(imageBytes, 0, len);
            }

            out.finish();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(DiskController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
