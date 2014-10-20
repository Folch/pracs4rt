/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Imatge;
import java.util.ArrayList;
import java.util.zip.ZipFile;

/**
 *
 * @author albert
 */
public interface InternalIDisk {
    public ZipFile openZip (String path);
    public Imatge openImage (String path);
    public void saveImage(String path, Imatge img);
    public void saveZip (String path, ArrayList<Imatge> images);
    public void saveGZip(String path, ArrayList<Imatge> images);
}
