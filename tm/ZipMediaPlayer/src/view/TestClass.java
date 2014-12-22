/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import model.Imatge;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author zenbook
 */
public class TestClass {
    public static void main(String[] args) {
        BufferedImage i = openImage("/home/zenbook/github/pracs4rt/pimproject/girl.jpg");
        grayScale(i);
        BufferedImage j = i.getSubimage(17, 40, 40, 40);
        grayScale(j);
        
        
        
        
        Mat img = bufferedImageToMat(i);
        Mat template = bufferedImageToMat(j);
        Mat result = new Mat();
        Imgproc.matchTemplate(img, template, result, Imgproc.TM_CCORR_NORMED);
        

        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(i)));
        frame.getContentPane().add(new JLabel(new ImageIcon(j)));
        frame.getContentPane().add(new JLabel(new ImageIcon(MatToBufferedImage(result))));
        
        frame.pack();
        frame.setVisible(true);
        
        /*for (int k = 0; k < i.getWidth(); k++) {
            for (int l = 0; l < i.getHeight(); l++) {
                System.out.println(i.getRGB(k, l));
            }
        }*/
    }
    
    public static Mat bufferedImageToMat(BufferedImage img){
        byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        Mat m = new Mat();
        m.put(0, 0, pixels);
        return m;
    }
    
    public static BufferedImage MatToBufferedImage(Mat m) {
        MatOfByte mob = new MatOfByte();
        Highgui.imencode(".jpg", m ,mob); 
        //convert the "matrix of bytes" into a byte array
         byte[] byteArray = mob.toArray();
         BufferedImage bufImage = null;
         try {
                InputStream in = new ByteArrayInputStream(byteArray);
                bufImage = ImageIO.read(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
         return bufImage;
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
