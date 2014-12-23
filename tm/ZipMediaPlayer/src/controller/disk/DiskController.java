/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.disk;

import controller.MainController;
import model.Imatge;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
 * Classe controladora de les funcions a disc
 * @author Albert Folch i Xavi Moreno
 */
public class DiskController implements InternalIDisk {
    /**
     * Mètode que obre un zip d'un path i retorna un ZipFile
     * @param path
     * @return 
     */
    @Override
    public ZipFile openZip(String path) {
        ZipFile zFl = null;
        try {
            File file = new File(path);
            zFl = new ZipFile(file);

        } catch (IOException ex) {
        }
        return zFl;

    }
    /**
     * Mètode que obre una imatge d'un path i retorna un Imatge
     * @param path
     * @return 
     */
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
        }
        return image;
    }
    /**
     * Mètode que guarda una imatge en jpg en un path concret
     * @param path
     * @param img 
     */
    @Override
    public void saveImage(String path, Imatge img) {
        while (path.endsWith(".jpg")) {
            path = path.substring(0, path.length() - 4);
        }
        try {
            BufferedImage bi = img.getImage();
            File outputfile = new File(path + ".jpg");
            ImageIO.write(bi, "jpg", outputfile);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Mètode que guarda un conjunt d'imatges en un zip en un path concret
     * @param path
     * @param imatges 
     */
    @Override
    public void saveZip(String path, ArrayList<Imatge> imatges) {
        while (path.endsWith(".zip")) {
            path = path.substring(0, path.length() - 4);
        }
        try {
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(new File(path + ".zip"))));
            for (Imatge imatge : imatges) {
                String nom = imatge.getName();
                while (nom.endsWith(".png")) {
                    nom = nom.substring(0, nom.length() - 4);
                }
                ZipEntry entry = new ZipEntry(nom + ".jpg");
                out.putNextEntry(entry);
                ImageIO.write(imatge.getImage(), "jpg", out);
            }
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(DiskController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * Mètode que guarda un gzip per cada imatge que primer s'haurà guardat com a file
     * @param path
     * @param files 
     */
    @Override
    public void saveGZip(String path, ArrayList<File> files) {
        for (File file : files) {
            String outFilename = file.getName()+ ".gz";
             
            byte[] buffer = new byte[1024];

            try {

                GZIPOutputStream gzos
                        = new GZIPOutputStream(new FileOutputStream(outFilename));

                FileInputStream in
                        = new FileInputStream(file);

                int len;
                while ((len = in.read(buffer)) > 0) {
                    gzos.write(buffer, 0, len);
                }

                in.close();

                gzos.finish();
                gzos.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            file.delete();

        }
    }

    @Override
    public void saveGZip(String path, String content) {
        
    }

    @Override
    public String openGZip(String path) {
        
        return null;
    }

}
