/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compressor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author zenbook
 */
public class FXFile {
    private int GoP;
    private int size_t;
    public ArrayList<HashMap<Integer, Integer[]>> frames;
    
    public FXFile (int GoP, int size_t){
        this.GoP = GoP;
        this.size_t = size_t;
        frames = new ArrayList<>();
    }
}
