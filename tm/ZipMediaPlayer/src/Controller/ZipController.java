/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author albert
 */
public class ZipController {
    private ArrayList<BufferedImage> images;
    public enum FileType {IMAGE,ZIP};
    
    public ZipController (String path, FileType f){
        if(f == FileType.ZIP){
            
        }else{ //image
            
        }
        
        this.images = new ArrayList<>();
        
    }
    
    public void first(){
        
    }
    
    public void next(){
        
    }
    
    public void previous(){
        
    }
    
    public void auto(int time){
        
    }
    
    public void manual(){
        
    }
    
}
