/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.ZipController.DirectionType;

/**
 *
 * @author albert
 */
public interface IPlayer {
    public void first();
    public void next();
    public void previous();
    public void setFrameRate(int time);
    public void pause();
    public void play();
    public void setDirection(DirectionType type);
}
