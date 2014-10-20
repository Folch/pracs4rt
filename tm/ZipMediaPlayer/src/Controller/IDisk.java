/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author albert
 */
public interface IDisk {
    public void openZip (String path);
    public void openImage (String path);
    public void saveImage(String path);
    public void saveZip (String path);
    public void saveGZip(String path);
}
