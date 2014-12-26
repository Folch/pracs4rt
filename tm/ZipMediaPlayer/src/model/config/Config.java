/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.config;

import model.FilterDim3;

/**
 * Classe utilitzada per configurar els valors per defecte d'alguns atributs
 * @author Albert Folch i Xavi Moreno
 */
public class Config {

    public static final DirectionType DEFAULT_DIRECTION = DirectionType.FORWARD;
    public static final int DEFAULT_FRAME_RATE = 60;
    public static final int DEFAULT_THRESHOLD = 128;
    public static final FilterDim3 DEFAULT_FILTER = FilterDim3.IDENTITY;
    public static final float DEFAULT_HUE = 0;
    public static final float DEFAULT_VALUE = 0;
    public static final float DEFAULT_SATURATION = 0;
    
    public static final int DEFAULT_GOP = 10;
    public static final int DEFAULT_SIZE_TESELA = 20;
    public static final int DEFAULT_PC = 3;
    public static final float DEFAULT_FQ = 0.25f;
}
