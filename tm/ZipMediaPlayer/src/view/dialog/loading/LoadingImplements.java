/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dialog.loading;

import controller.player.OnLoading;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import view.MainFrame;

/**
 *
 * @author zenbook
 */
public class LoadingImplements implements OnLoading {

    public short progress;
    public Duration timeleft;
    private IGuiLoading gui;
    
    public LoadingImplements() {
        progress = 0;
    }
    
    @Override
    public void updateProgressBar(short percent, Duration timeleft) {
        this.progress = percent;
        this.timeleft = timeleft;
        if(gui != null)
            gui.loading(this);
    }

    public void setIGuiLoading(IGuiLoading gui) {
        this.gui = gui;
    }
    
    
    
    
    public static void updateLoading(JProgressBar p, JLabel stat, LoadingImplements l) {
        try {
            p.setValue(l.progress);
            if(l.timeleft == null) return;
            DatatypeFactory datafactory = DatatypeFactory.newInstance();
            Duration minute = datafactory.newDuration(60*1000);
            if(l.timeleft.isShorterThan(minute))
                stat.setText(" "+l.timeleft.getSeconds()+" seconds");
            else{
                DecimalFormat df = new DecimalFormat("00");
                stat.setText(" "+l.timeleft.getMinutes()+":"+df.format(l.timeleft.getSeconds())+" minutes");
            }
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onLoadImages() {
        if(gui != null)
            gui.finishLoading();
    }
    
}
