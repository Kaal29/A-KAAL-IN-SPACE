
package Graphics;

/**
 *
 * @author Willy Bazan
 * @date   20/11/2019
 * @time   10:02 am
 */

import java.awt.image.BufferedImage;
import java.awt.Font;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Assets {
	
    //Para que indique si ya todos los recursos cargaron
    public static boolean loaded = false;
    //Para saber cuanto lleva de carga y asi poder dibujar la barra
    public static float count = 0;
    //Cantidad total de recursos
    public static float MAX_COUNT = 130; //110 cantidad de archivos, método poco eficaz
    
    //Effects
    public static BufferedImage speed;
    
    public static BufferedImage[] ships = new BufferedImage[20];
    public static BufferedImage[] shipsSeleccion = new BufferedImage[20];

    //Explosion
    public static BufferedImage[] exp = new BufferedImage[9];
    
    //Lasers
    public static BufferedImage[] lasers = new BufferedImage[3];

    //Meteors
    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[4];
    public static BufferedImage[] smalls = new BufferedImage[3];
    //public static BufferedImage[] tinies = new BufferedImage[2];
    
    // ufo
    public static BufferedImage [] ufo = new BufferedImage[15];;
    
    // numbers
    public static BufferedImage[] numbers = new BufferedImage[11];
    
    //life
    public static BufferedImage life;
    
    //fonts
    public static Font fontBig;
    public static Font fontMed;
    
    //sounds
    public static Clip backgroundMusic, explosion, playerLoose, playerShoot, ufoShoot;
    
    //sreen
    public static BufferedImage [] stages = new BufferedImage[10];
    
    // ui
    public static BufferedImage blueBtn;
    public static BufferedImage greyBtn;
    
    //menu
    public static BufferedImage [] stageNormal = new BufferedImage[10];
    public static BufferedImage [] stageSeleccionado = new BufferedImage[10];
    
            
    public static void init()
    {
        
        for (int j =0; j<1; j++)
        {
            speed  = loadImage("/effects/0.png");
            
            for(int i=0; i<lasers.length;i++)
                lasers[i] = loadImage("/lasers/"+i+".png");

            life = loadImage("/life/life.jpg");

            fontBig = loadFont("/fonts/futureFont.ttf", 60);
            fontMed = loadFont("/fonts/futureFont.ttf", 20);

            for(int i=0; i<stages.length;i++)
                stages[i] = loadImage("/fonts/fond"+i+".jpg");
                
            for(int i=0; i<stageNormal.length;i++)
                stageNormal[i] = loadImage("/menu/stage/normal/"+i+".png");
            
            for(int i=0; i<stageSeleccionado.length;i++)
                stageSeleccionado[i] = loadImage("/menu/stage/seleccionar/"+i+".png");
            
            
            for(int i=0; i<ships.length;i++)
               ships[i] = loadImage("/ships/"+i+".png");
            
            for(int i=0; i<shipsSeleccion.length;i++)
               shipsSeleccion[i] = loadImage("/ships/seleccion/"+i+".png");

            for(int i=0; i<bigs.length;i++)
               bigs[i] = loadImage("/meteors/big/big"+(i+1)+".png");

            for(int i=0; i<meds.length;i++)
                meds[i] = loadImage("/meteors/med/"+i+".png");

            for(int i=0; i<smalls.length;i++)
                smalls[i] = loadImage("/meteors/small/"+i+".png");

            //for(int i=0; i<tinies.length;i++)
               //tinies[i] = Loader.ImageLoader("/meteors/tiny/tiny"+(i+1)+".png");

            for(int i=0; i<exp.length;i++)
                exp[i] = loadImage("/explosion/"+i+".png");

            for(int i=0; i<ufo.length;i++)
                ufo[i] = loadImage("/enemy/enemy"+i+".png");

            for(int i = 0; i < numbers.length; i++)
                numbers[i] = loadImage("/numbers/"+i+".png");

            backgroundMusic = loadClip("/sounds/fondo3.wav");
            explosion = loadClip("/sounds/explosion.wav");
            playerLoose = loadClip("/sounds/playerLoose.wav");
            playerShoot = loadClip("/sounds/playerShoot.wav");
            ufoShoot = loadClip("/sounds/ufoShoot.wav");


            greyBtn = loadImage("/ui/grey_button.png");
            blueBtn = loadImage("/ui/blue_button.png");
    
        }
        
        //cargamos los recursos unas 10 veces para que se pueda ver la barra
        loaded = true;
    }
    
    public static BufferedImage loadImage( String path)
    {
        count++;
        return Loader.ImageLoader(path);
    }
	
    public static Font loadFont( String path, int size)
    {
        count++;
        return Loader.loadFont(path, size);
    }
    
    public static Clip loadClip( String path)
    {
        count++;
        return Loader.loadSound(path);
    }
}
