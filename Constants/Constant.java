
package Constants;

/**
 * ALBERGA TODAS LAS CONSTANTES PRIMITIVAS
 * @author Willy Bazan
 * @date   23/11/2019
 * @time   03:26 pm
 */

import javax.swing.filechooser.FileSystemView;

public class Constant 
{
    //Frame dimensiones
    public static final int WIDTH = 1200, HEIGHT = 600;
    
    //Player properties
    public static final int FIRERATE = 50 ; //TIEMPO DE DISPARO
    public static final double ACC = 0.2; //0.2//Velocidad de movimiento
    public static final double DELTAANGLE = 0.035; //Angulo de rotacion
    public static final double PLAYER_MAX_VEL = 10; //Velocidad maxima de desplazamiento
    public static final long SPAWING_TIME = 3000;
    public static final long FLICKER_TIME = 200;
    public static final long GAME_OVER_TIME = 3000;
    
    //Laser properties
    public static final double LASER_VEL = 15;
    
    //Meteors properties
    public static final double METEOR_VEL = 5;
    public static final int METEOR_SCORE = 20;
    
    //Ufo properties
    public static final int NODE_RADIUS = 100;
    public static final double UFO_FORCE_MASS = 0.009;
    public static final int UFO_MAX_VEL = 3;
    public static final double UFO_TURN_ANGLE = 0.08;
    public static final int UFO_FIRE_RATE = 1000;
    public static final double UFO_ANGLE_RANGE = Math.PI/2;
    public static final int UFO_SCORE = 40;
    public static final long UFO_SPAWN_RATE = 10000; //Para que cada 10 segundos cree un nuevo Ufo (10000)
    
    //Para nombre de los botones 
    public static final String PLAY = "PLAY";
    public static final String CHOOSE_SHIP = "CHOOSE SHIP";
    public static final String CHOOSE_STAGE = "CHOOSE STAGE";
    public static final String BACK = "BACK";
    public static final String NEXT = "NEXT";
    public static final String HIGH_SCORES = "HIGHEST SCORES";
    public static final String EXIT = "EXIT";
    public static final String RETURN = "RETURN";
    
    //para el score
    public static final String SCORE = "SCORE";
    public static final String DATE = "DATE";
    
    public static final int LOADING_BAR_WIDTH = 500; //Ancho de la barra de carga
    public static final int LOADING_BAR_HEIGHT = 50; //Altura

    //Para poder usar JSON
    public static final String SCORE_PATH = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() +
			"\\DATOS\\data.json";  // data.xml se se usa XMLParser

   
    // Estas variables son requeridas para usar XMLParse
    //public static final String PLAYER = "PLAYER";
    //public static final String PLAYERS = "PLAYERS";
}
