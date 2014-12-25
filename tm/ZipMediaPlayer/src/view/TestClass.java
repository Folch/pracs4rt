/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.statistics.Statistics;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author zenbook
 */
public class TestClass {
    public static void main(String[] args) {
        BufferedImage i = openImage("/home/zenbook/github/pracs4rt/pimproject/girl.jpg");
        grayScale(i);
        BufferedImage j = i.getSubimage(17, 40, 50, 40);
        grayScale(j);
        
        BufferedImage k = i.getSubimage(90, 28, 50, 40);
        grayScale(k);
        
        System.out.println(normalizedCrossCorrelation(j, k));
        System.out.println(normalizedCrossCorrelation(j, j));
        System.out.println(normalizedCrossCorrelation(k, k));
       
        

        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(i)));
        frame.getContentPane().add(new JLabel(new ImageIcon(j)));
        frame.getContentPane().add(new JLabel(new ImageIcon(k)));
        
        frame.pack();
        frame.setVisible(true);
        
        /*for (int k = 0; k < i.getWidth(); k++) {
            for (int l = 0; l < i.getHeight(); l++) {
                System.out.println(i.getRGB(k, l));
            }
        }*/
    }
    
    public static double normalizedCrossCorrelation(BufferedImage img1, BufferedImage img2) {
        assert img1.getWidth() == img2.getWidth();
        assert img1.getHeight() == img2.getHeight();
        
        double out = 0;
        int size = img1.getWidth()*img1.getHeight();

        double mean12;
        Statistics s1;
        Statistics s2;
        
        s1 = new Statistics(img1);
        s2 = new Statistics(img2);
        
        mean12 = s1.getMean() * s2.getMean();
        for (int i = 0; i < size; i++) {
            out += s1.data[i]*s2.data[i] - mean12;
        }
        
        out /= (size*s1.getStdDev() * s2.getStdDev());
        
        return out;
    }
    
    public static BufferedImage openImage(String path) {
        File file = new File(path);
        BufferedImage bufImg = null;
        try {
            bufImg = ImageIO.read(file);

        } catch (IOException ex) { }
        return bufImg;
    }
    
    public static void grayScale(BufferedImage img) {
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                Color c = new Color(img.getRGB(i, j));
                int g, b, r;
                r = c.getRed();
                g = c.getGreen();
                b = c.getBlue();
                int mean = (int) (((float) r) + g + b) / 3;
                r = mean;
                g = mean;
                b = mean;

                int rgb = new Color(r, g, b).getRGB();
                img.setRGB(i, j, rgb);
            }

        }
    }
    
 
}
