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
    public static int DEFAULT_FRAME_RATE = 60;
    public static int DEFAULT_THRESHOLD = 128;
    public static FilterDim3 DEFAULT_FILTER = FilterDim3.IDENTITY;
    public static float DEFAULT_HUE = 0;
    public static float DEFAULT_VALUE = 0;
    public static float DEFAULT_SATURATION = 0;
}
