/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.disk;

import java.io.File;
import model.Imatge;
import java.util.ArrayList;
import java.util.zip.ZipFile;

/**
 * Interfície que implementa el DiskController per accions relacionades amb obrir i guardar imatges/zip
 * @author Albert Folch i Xavi Moreno
 */
public interface InternalIDisk {
    public ZipFile openZip (String path);
    public Imatge openImage (String path);
    public void saveImage(String path, Imatge img);
    public void saveZip (String path, ArrayList<Imatge> imatges);
    public void saveGZip(String path, ArrayList<File> files);
}
