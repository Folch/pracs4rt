/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.player;

import model.Imatge;

/**
 * Interfície que implementa la vista per mostrar una imatge i crida el MainController per passar-li-ho
 * @author Albert Folch i Xavi Moreno
 */
public interface OnImageListener {
    
    public void onImage(Imatge i);
    
    
}
