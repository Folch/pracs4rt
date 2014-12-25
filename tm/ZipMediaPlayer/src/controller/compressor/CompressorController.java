/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compressor;

import controller.MainController;
import controller.filter.threads.ConvolveThread;
import controller.statistics.Statistics;
import model.Imatge;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import model.config.Config;
import java.awt.Color;

/**
 * Classe controladora de totes les accions relacionades amb la compressió
 *
 * @author Albert Folch i Xavi Moreno
 */
public class CompressorController implements ICompressor {

    private int GoP;
    private int size_t;
    private int pc;
    private float fq;

    public CompressorController() {
        this.GoP = Config.DEFAULT_GOP;
        this.size_t = Config.DEFAULT_SIZE_TESELA;
        this.pc = Config.DEFAULT_PC;
        this.fq = Config.DEFAULT_FQ;
    }

    /**
     * Mètode que segons un ZipFile el descomprimeix i et retorna un ArrayList
     * de Imatge
     *
     * @param zFl
     * @return
     */
    @Override
    public ArrayList<Imatge> decompressZip(ZipFile zFl) {
        ArrayList<Imatge> images = new ArrayList<>();
        try {
            Enumeration<? extends ZipEntry> entries = zFl.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                Imatge image = new Imatge();
                String imgName = entry.getName();
                image.setName(imgName);
                InputStream is = zFl.getInputStream(entry);
                ImageInputStream iis = ImageIO.createImageInputStream(is);
                BufferedImage bufImg = ImageIO.read(iis);
                image.setImage(bufImg);
                images.add(image);

            }

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return images;
    }

    /**
     * Aquest mètode fa: Descomprimir Zip,Guardar imatges com a files a disc i
     * retorna l'arraylist de files corresponent a les imatges
     *
     * @param path
     * @param zipFile
     * @return
     */
    @Override
    public ArrayList<File> getFilesFromZip(String path, ZipFile zipFile) {
        ArrayList<File> files = new ArrayList<>();
        File outputfile;
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {

            try {
                ZipEntry entry = entries.nextElement();
                Imatge image = new Imatge();
                String imgName = entry.getName();
                image.setName(imgName);
                InputStream is = zipFile.getInputStream(entry);
                ImageInputStream iis = ImageIO.createImageInputStream(is);
                BufferedImage bufImg = ImageIO.read(iis);
                image.setImage(bufImg);
                outputfile = new File((image.getName().substring(0, image.getName().length() - 4)) + ".jpg");
                ImageIO.write(image.getImage(), "jpg", outputfile);
                files.add(outputfile);

            } catch (IOException ex) {
                Logger.getLogger(CompressorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return files;
    }

    @Override
    public FXContent compressFX(ArrayList<Imatge> imatges, int GoP, int size_t, int pc, float fq) {

        int refs = imatges.size() / GoP;
        Imatge ref = imatges.get(0);

        FXFile fxf = new FXFile(GoP, size_t);

        for (int i = 1; i < imatges.size(); i++) {
            Imatge img = imatges.get(i);
            fxf.frames.add(new HashMap<Integer, Integer[]>());
            //System.out.println("Imatge " + i);
            if (i % refs == 0) {
                ref = img;
                continue;
            }
            HashMap hm = fxf.frames.get(i - 1);
            //int suma = 0;//borrar
            for (int j = 0; j < img.getNumTeseles(size_t); j++) {
                //System.out.println("Tesela "+j);
                Integer[] pos = searchTesela(ref, img, j, size_t, pc, fq);
                if (pos != null) {
                    //suma ++;
                    //System.out.println("elimina la tesela " + j + " imatge " + img.getName());
                    deleteTesela(img, pos, size_t);
                    hm.put(j, pos);
                }

            }
            //System.out.println("No eliminades = "+(img.getNumTeseles(size_t)-suma));//borrar
        }

        return new FXContent(imatges, fxf);
    }

    @Override
    public ArrayList<Imatge> decompressFX(FXContent content) {
        ArrayList<Imatge> imgs = content.getImatges(); // imatges amb forats
        FXFile fx = content.getFx();
        int size = imgs.size();
        int refs = size / GoP;
        Imatge ref = imgs.get(0);

        for (int i = 1; i < size; i++) {
            Imatge img = imgs.get(i);
            if (i % refs == 0) {
                ref = img;
                continue;
            }
            HashMap posicionsTeseles = fx.frames.get(i);
            for (int tesela = 0; tesela < ref.getNumTeseles(size_t); tesela++) {
                Integer[] posTesela = (Integer[]) posicionsTeseles.get(tesela);
                Integer[] refPosTesela = ref.getPosTesela(tesela, size_t);

                BufferedImage imgToFill = img.getImage();
                BufferedImage imgRef = ref.getImage();

                //pos[0]=x,columnes   pos[1]=y,files
                for (int col = 0; col < size_t; col++) {
                    for (int fila = 0; fila < size_t; fila++) {
                        int rgb = imgRef.getRGB(col + refPosTesela[0], fila + refPosTesela[1]);
                        imgToFill.setRGB(col + posTesela[0], fila + posTesela[1], rgb);

                    }

                }
            }

        }

        return imgs;
    }

    private Integer[] searchTesela(Imatge src, Imatge dest, int tesela, int size_t, int pc, float fq) {
        int width = src.getImage().getWidth();
        int height = src.getImage().getHeight();

        Integer[] pos = src.getPosTesela(tesela, size_t);//pos[0]=x,columnes   pos[1]=y,files
        BufferedImage subimatge = src.getImage().getSubimage(pos[0].intValue(), pos[1].intValue(), size_t, size_t);

        for (int l = 0; l < pc; l++) {
            int lastRow = pos[1] + l + size_t;
            int lastColumn = pos[0] + l + size_t;
            /*
            System.out.println("Inici "+(pos[1]-l)+"  "+(pos[0]-l));
            System.out.println("Fi "+lastRow+"  "+lastColumn);
                    */
            for (int fila = pos[1] - l; fila < lastRow; fila++) {
                if (fila >= 0 && fila < height - size_t) {

                    for (int col = pos[0] - l; col < lastColumn;) {
                        //System.out.println("fila = "+fila+" columna = "+col);
                        if (col >= 0 && col < width - size_t) {
                            BufferedImage desti = dest.getImage().getSubimage(col, fila, size_t, size_t);
                            double diff = Statistics.normalizedCrossCorrelation(subimatge, desti);
                            if (diff < fq) {
                                Integer[] posD = new Integer[2];
                                posD[0] = pos[0];
                                posD[1] = pos[1];
                                return posD;
                            } else {
                                if (fila == pos[1] - l || fila == lastRow) {
                                    col++;
                                } else {
                                    col = lastColumn;
                                }
                            }
                        } else if (col < 0) {
                            col = 0;
                        } else {
                            break;
                        }

                    }
                } else if (fila < 0) {
                    fila = 0;
                } else {
                    break;
                }
            }

        }
        return null;
    }

    //el valor que es posa en el moment d'eliminar es la mitja de TOTA la imatge
    private void deleteTesela(Imatge imatge, Integer[] pos, int size_t) {
        BufferedImage img = imatge.getImage();
        Statistics s = new Statistics(img);
        int mean = (int) s.getMean();
        Color colorMig = new Color(mean, mean, mean);

        for (int i = pos[0]; i < pos[0] + size_t; i++) {
            for (int j = pos[1]; j < pos[1] + size_t; j++) {
                img.setRGB(i, j, colorMig.getRGB());
            }

        }
    }

    public int getGoP() {
        return GoP;
    }

    public void setGoP(int GoP) {
        this.GoP = GoP;
    }

    public int getSize_t() {
        return size_t;
    }

    public void setSize_t(int size_t) {
        this.size_t = size_t;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public float getFq() {
        return fq;
    }

    public void setFq(float fq) {
        this.fq = fq;
    }

}
