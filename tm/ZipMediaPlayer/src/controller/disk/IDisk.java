/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.disk;

/**
 * Interfície que implementa el MainController i que farà servir com a crides la vista
 * @author Albert Folch
 */
public interface IDisk {
    public boolean openZip (String path);
    public boolean openImage (String path);
    public void saveImage(String path);
    public void saveZip (String path);
    public void saveGZip(String path);
}
