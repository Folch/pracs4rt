/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.disk;

/**
 *
 * @author albert
 */
public interface IDisk {
    public boolean openZip (String path);
    public boolean openImage (String path);
    public void saveImage(String path);
    public void saveZip (String path);
    public void saveGZip(String path);
}
