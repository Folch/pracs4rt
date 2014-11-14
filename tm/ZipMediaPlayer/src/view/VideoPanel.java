/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.player.OnImageListener;
import java.awt.BorderLayout;
import model.Imatge;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author zenbook
 */
public class VideoPanel extends JPanel implements OnImageListener{
    
    private BufferedImage currentImage;
    //private boolean isLoading;
    
    //private JLabel image;
    
    public VideoPanel() {
        /*
        image = new JLabel();
        ImageIcon ii = new ImageIcon(this.getClass().getResource(
                    "/view/resource/loading.gif"));
        image.setIcon(ii);
        
        this.setLayout(new BorderLayout());
        */
    }
    
    @Override
    public void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        
        if(currentImage != null) {
            Graphics2D g = (Graphics2D) grphcs;
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            
            double scaleX = getScaleX(this.getWidth(), currentImage.getWidth());
            double scaleY = getScaleY(this.getHeight(), currentImage.getHeight());

            double xPos = ((this.getWidth() - scaleX * currentImage.getWidth())/2.);
            double yPos = ((this.getHeight() - scaleY * currentImage.getHeight())/2.);

            AffineTransform at = AffineTransform.getTranslateInstance(xPos, yPos);

            at.scale(scaleX, scaleY);
            g.drawRenderedImage(currentImage, at);
        }
                
    }
    
    /*
    public void loading(boolean load) {
        if(load) {
            this.add(image, BorderLayout.CENTER);
        } else{
            this.remove(image);
        }
        //repaint();
    }*/
    
    private double getScaleY(int panelHeight, int imageHeight) {
        double yScale = 1;
        
        if (imageHeight > panelHeight) {
            yScale = (double)imageHeight / panelHeight;
        } else if (imageHeight < panelHeight) {
            yScale = (double)panelHeight / imageHeight;
        }
        return yScale;
    }
    
    private double getScaleX(int panelWidth, int imageWidth) {
        double xScale = 1;
        if (imageWidth > panelWidth) {
            xScale = (double)imageWidth  / panelWidth;
        } else if (imageWidth < panelWidth) {
            xScale = (double)panelWidth / imageWidth;
        }
        return xScale;
    }

    @Override
    public void onImage(Imatge i) {
        this.currentImage = i.getImage();
        repaint();
    }
}
