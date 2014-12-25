/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author zenbook
 */
public class TestJson {
    
    
    public static void main(String[] args) {
        try {
            String json = "{\"frames\":20, \"a\":\"30\"}";
            JSONObject root = new  JSONObject(json);
            System.out.println(root.getInt("frames"));
            System.out.println(root.getString("a"));
            System.out.println(root.toString());
            
            JSONObject obj = new JSONObject();
            obj.put("frames", 20);
            obj.put("a", "30");
            System.out.println(obj.toString());
            
            
        } catch (JSONException ex) {
            Logger.getLogger(TestJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
