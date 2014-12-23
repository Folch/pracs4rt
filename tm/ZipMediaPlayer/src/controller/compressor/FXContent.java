/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compressor;

import controller.disk.DiskController;
import controller.disk.InternalIDisk;
import java.util.ArrayList;
import java.util.zip.ZipFile;
import model.Imatge;

/**
 *
 * @author zenbook
 */
public class FXContent {
    private final ArrayList<Imatge> imatges;
    private final FXFile fx;

    public FXContent(ArrayList<Imatge> imatges, FXFile fx) {
        this.imatges = imatges;
        this.fx = fx;
    }

    public void save(String path, InternalIDisk disk) {
        disk.saveZip(path+"/images.zip", imatges);
        fx.save(path+"/fxf", disk);
    }
    
    public static FXContent open(String path, InternalIDisk disk, ICompressor compressor) {
        FXFile fxf = FXFile.open(path+"/fxf", disk);
        ZipFile zip = disk.openZip(path+"/images.zip");
        ArrayList<Imatge> imatges;
        if (zip != null && fxf != null) {
            imatges = compressor.decompressZip(zip);
            return new FXContent(imatges, fxf);
        } 
        return null;
    }
}
