/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compressor;

import java.io.File;
import model.Imatge;
import java.util.ArrayList;
import java.util.zip.ZipFile;

/**
 * Interfície interna que implementa el CompressorController
 * @author Albert Folch
 */
public interface ICompressor {
    public ArrayList<Imatge> decompressZip (ZipFile zip);
    public ArrayList<File> getFilesFromZip(String path,ZipFile zip);
}
