/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.player;

import javax.xml.datatype.Duration;


/**
 *
 * @author albert
 */
public interface OnLoading {
    public void updateProgressBar(short percent, Duration timeleft);
    public void onLoadImages();
}
