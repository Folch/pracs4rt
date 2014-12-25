/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compressor;

import controller.disk.InternalIDisk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public void save(String path, InternalIDisk disk) {
        try {
            JSONObject root = new JSONObject();
            root.put("gop", GoP);
            root.put("size_t", size_t);
            
            JSONArray array = new JSONArray();
            JSONObject element;
            for (HashMap<Integer, Integer[]> frame : frames) {
                element = new JSONObject();
                for (Map.Entry pairs : frame.entrySet()) {
                    Integer tesela = (Integer) pairs.getKey();
                    Integer[] pos = (Integer[]) pairs.getValue();
                    
                    JSONArray a = new JSONArray();
                    a.put(pos[0]);
                    a.put(pos[1]);
                    
                    element.put(tesela+"", a);
                }
                array.put(element);
            }
            root.put("frames", array);
            
            disk.saveGZip(path, root.toString());
        } catch (JSONException ex) {
            Logger.getLogger(FXFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static FXFile open(String path, InternalIDisk disk) {
        
        try {
            String json = disk.openGZip(path);
            if(json == null)
                return null;
            JSONObject root = new JSONObject(json);
            int gop = root.getInt("gop");
            int size_t = root.getInt("size_t");
            
            FXFile fxf = new FXFile(gop, size_t);
            fxf.frames = new ArrayList<>();
            
            JSONArray array = root.getJSONArray("frames");
            for (int i = 0; i < array.length(); i++) {
                fxf.frames.add(new HashMap<Integer, Integer[]>());
                JSONObject element = array.getJSONObject(i);
                Iterator it = element.keys();
                while (it.hasNext()) {
                    Integer tesela = Integer.parseInt((String) it.next());
                    JSONArray arr = element.getJSONArray(tesela+"");
                    Integer[] pos = new Integer[2];
                    pos[0] = arr.getInt(0);
                    pos[1] = arr.getInt(1);
                    fxf.frames.get(i).put(tesela, pos);
                }
            }
            
            return fxf;
            
        } catch (JSONException ex) {
            Logger.getLogger(FXFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
    }
    
}
