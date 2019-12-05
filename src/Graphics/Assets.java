
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
	
    //Effects
    public static BufferedImage speed;
    
    public static BufferedImage[] players = new BufferedImage[18];

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
    public static BufferedImage [] screens = new BufferedImage[10];;
    public static ImageIcon imag1 ;
    
    // ui
	
    public static BufferedImage blueBtn;
    public static BufferedImage greyBtn;
            
    public static void init()
    {
        
        speed  = Loader.ImageLoader("/effects/0.png");
        for(int i=0; i<lasers.length;i++)
            lasers[i] = Loader.ImageLoader("/lasers/"+i+".png");
        
        life = Loader.ImageLoader("/life/life.jpg");
        
        fontBig = Loader.loadFont("/fonts/futureFont.ttf", 42);
        fontMed = Loader.loadFont("/fonts/futureFont.ttf", 20);
        
        for(int i=0; i<screens.length;i++)
            screens[i] = Loader.ImageLoader("/fonts/fond"+i+".jpg");
        
        for(int i=0; i<players.length;i++)
           players[i] = Loader.ImageLoader("/ships/"+i+".png");
        
        for(int i=0; i<bigs.length;i++)
           bigs[i] = Loader.ImageLoader("/meteors/big/big"+(i+1)+".png");
           
        for(int i=0; i<meds.length;i++)
            meds[i] = Loader.ImageLoader("/meteors/med/"+i+".png");
        
        for(int i=0; i<smalls.length;i++)
            smalls[i] = Loader.ImageLoader("/meteors/small/"+i+".png");
       
        //for(int i=0; i<tinies.length;i++)
           //tinies[i] = Loader.ImageLoader("/meteors/tiny/tiny"+(i+1)+".png");
        
        for(int i=0; i<exp.length;i++)
            exp[i] = Loader.ImageLoader("/explosion/"+i+".png");
        
        for(int i=0; i<ufo.length;i++)
            ufo[i] = Loader.ImageLoader("/enemy/enemy"+i+".png");
            
        for(int i = 0; i < numbers.length; i++)
            numbers[i] = Loader.ImageLoader("/numbers/"+i+".png");
        
        backgroundMusic = Loader.loadSound("/sounds/fondo3.wav");
        explosion = Loader.loadSound("/sounds/explosion.wav");
        playerLoose = Loader.loadSound("/sounds/playerLoose.wav");
        playerShoot = Loader.loadSound("/sounds/playerShoot.wav");
        ufoShoot = Loader.loadSound("/sounds/ufoShoot.wav");
        
       
        greyBtn = Loader.ImageLoader("/ui/grey_button.png");
	blueBtn = Loader.ImageLoader("/ui/blue_button.png");
    }
	
}
