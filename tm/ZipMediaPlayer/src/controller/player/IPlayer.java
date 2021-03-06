/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.player;

import model.config.DirectionType;


/**
 * Interficie que implementa el MainController per les opcions de reproducció
 * @author Albert Folch
 */
public interface IPlayer {
    public void first();
    public void next();
    public void previous();
    public void setFrameRate(int time);
    public void pause();
    public void play();
    
    public void setDirection(DirectionType type);
    public boolean isPlaying();
    public int getFrameRate();
    public DirectionType getDirection();
    
    /**
     * Cancel open/save if the program is loading.
     * 
     */
    public void cancelLoading();
    
}
