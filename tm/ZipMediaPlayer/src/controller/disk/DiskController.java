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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
    public void saveGZip(String path, ArrayList<File> files) {
        for (File file : files) {
            String outFilename = file.getName() + ".gz";
            String inFilename = file.getName();
            BufferedWriter bufferedWriter = null;
            BufferedReader bufferedReader = null;
            try {

                //Construct the BufferedWriter object
                bufferedWriter = new BufferedWriter(
                        new OutputStreamWriter(
                                new GZIPOutputStream(new FileOutputStream(outFilename))
                        ));

                //Construct the BufferedReader object
                bufferedReader = new BufferedReader(new FileReader(inFilename));

                String line = null;

                // from the input file to the GZIP output file
                while ((line = bufferedReader.readLine()) != null) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //Close the BufferedWrter
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //Close the BufferedReader
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
                
            }
        }

        //mirar http://rauschig.org/jarchivelib/apidocs/
        //File archive = new File("/home/thrau/archive.tar.gz");
        //File destination = new File(path + "tar.gz");
        //Archiver archiver = ArchiverFactory.createArchiver("tar", "gz");
        //archiver.create(path + "tar.gz", destination, files);

        /*try {
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
         }*/
    }

    
}
