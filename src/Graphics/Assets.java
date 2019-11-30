
package Graphics;

/**
 *
 * @author Willy Bazan
 * @date   20/11/2019
 * @time   10:02 am
 */

import java.awt.image.BufferedImage;

public class Assets {
	
    //Effects
    public static BufferedImage player, speed;

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
    public static BufferedImage [] ufo = new BufferedImage[16];;
    
    
    public static void init()
    {

        player = Loader.ImageLoader("/ships/player01.png");
        speed  = Loader.ImageLoader("/effects/vuelo.png");

        blueLaser = Loader.ImageLoader("/lasers/laserBlue01.png");
        greenLaser = Loader.ImageLoader("/lasers/laserGreen11.png");
        redLaser = Loader.ImageLoader("/lasers/laserRed01.png");

        for(int i=0; i<bigs.length;i++)
        {
           bigs[i] = Loader.ImageLoader("/meteors/big/big"+(i+1)+".png");
        }
            
        for(int i=0; i<meds.length;i++)
        {
            meds[i] = Loader.ImageLoader("/meteors/med/med"+(i+1)+".png");
        }
            
        for(int i=0; i<smalls.length;i++)
        {
            smalls[i] = Loader.ImageLoader("/meteors/small/small"+(i+1)+".png");
        }
            
        for(int i=0; i<tinies.length;i++)
        {
            tinies[i] = Loader.ImageLoader("/meteors/tiny/tiny"+(i+1)+".png");
        }
        
        for(int i=0; i<exp.length;i++)
        {
            exp[i] = Loader.ImageLoader("/explosion/"+i+".png");
        }
        
        for(int i=0; i<ufo.length;i++)
        {
            ufo[i] = Loader.ImageLoader("/enemy/enemy"+i+".png");
        }
        
        
    }
	
}
