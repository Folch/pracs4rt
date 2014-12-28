/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dialog;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author zenbook
 */
public abstract class BaseDialog extends javax.swing.JDialog {
    
    public BaseDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //((VideoPanel)imagepanel).loading(true);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        this.setBounds( width/2-this.getWidth()/2, height/2-this.getHeight()/2, this.getWidth(), this.getHeight());
    }
    
    public abstract void initComponents();
    
}
