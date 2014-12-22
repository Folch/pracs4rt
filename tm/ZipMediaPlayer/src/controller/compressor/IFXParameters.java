/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.compressor;

/**
 *
 * @author zenbook
 */
public interface IFXParameters {
    public void setGoP(int GoP);
    public int getGoP();
    
    public void setSizeTesela(int size_t);
    public int getSizeTesela();
    
    public void setPC(int pc);
    public int getPC();
    
    public void setFQ(int fq);
    public int getFQ();
}
