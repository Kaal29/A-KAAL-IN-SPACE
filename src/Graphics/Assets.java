
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

public class Assets {
	
    //Effects
    public static BufferedImage speed;
    public static BufferedImage[] players = new BufferedImage[3];

    //Explosion
    public static BufferedImage[] exp = new BufferedImage[9];
    
    //Lasers
    public static BufferedImage blueLaser, greenLaser, redLaser;

    //Meteors
    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tinies = new BufferedImage[2];
    
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
    
    public static void init()
    {

        speed  = Loader.ImageLoader("/effects/vuelo.png");

        blueLaser = Loader.ImageLoader("/lasers/laserBlue01.png");
        greenLaser = Loader.ImageLoader("/lasers/laserGreen11.png");
        redLaser = Loader.ImageLoader("/lasers/laserRed01.png");

        life = Loader.ImageLoader("/life/life.jpg");
        
        fontBig = Loader.loadFont("/fonts/futureFont.ttf", 42);
        fontMed = Loader.loadFont("/fonts/futureFont.ttf", 20);
        
        for(int i=0; i<players.length;i++)
           players[i] = Loader.ImageLoader("/ships/player"+i+".png");
        
        for(int i=0; i<bigs.length;i++)
           bigs[i] = Loader.ImageLoader("/meteors/big/big"+(i+1)+".png");
           
        for(int i=0; i<meds.length;i++)
            meds[i] = Loader.ImageLoader("/meteors/med/med"+(i+1)+".png");
        
        for(int i=0; i<smalls.length;i++)
            smalls[i] = Loader.ImageLoader("/meteors/small/small"+(i+1)+".png");
       
        for(int i=0; i<tinies.length;i++)
            tinies[i] = Loader.ImageLoader("/meteors/tiny/tiny"+(i+1)+".png");
        
        for(int i=0; i<exp.length;i++)
            exp[i] = Loader.ImageLoader("/explosion/"+i+".png");
        
        for(int i=0; i<ufo.length;i++)
            ufo[i] = Loader.ImageLoader("/enemy/enemy"+i+".png");
            
        for(int i = 0; i < numbers.length; i++)
            numbers[i] = Loader.ImageLoader("/numbers/"+i+".png");
        
        backgroundMusic = Loader.loadSound("/sounds/backGroundMusic.wav");
        explosion = Loader.loadSound("/sounds/explosion.wav");
        playerLoose = Loader.loadSound("/sounds/playerLoose.wav");
        playerShoot = Loader.loadSound("/sounds/playerShoot.wav");
        ufoShoot = Loader.loadSound("/sounds/ufoShoot.wav");

    }
	
}
