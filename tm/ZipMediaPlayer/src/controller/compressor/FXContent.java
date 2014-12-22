/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compressor;

import java.util.ArrayList;
import model.Imatge;

/**
 *
 * @author zenbook
 */
public class FXContent {
    private ArrayList<Imatge> imatges;
    private FXFile fx;

    public FXContent(ArrayList<Imatge> imatges, FXFile fx) {
        this.imatges = imatges;
        this.fx = fx;
    }

    public static void save(String path, FXContent fx) {
        
    }
    
    public static FXContent open(String path) {
        
        return null;
    }
}
