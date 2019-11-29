
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
    public static BufferedImage player, speed, attack;

    //Lasers
    public static BufferedImage blueLaser, greenLaser, redLaser;

    //Meteors
    public static BufferedImage[] bigs = new BufferedImage[4];
    public static BufferedImage[] meds = new BufferedImage[2];
    public static BufferedImage[] smalls = new BufferedImage[2];
    public static BufferedImage[] tinies = new BufferedImage[2];
    
    public static void init()
    {

        player = Loader.ImageLoader("/ships/estatico.png");
        speed  = Loader.ImageLoader("/effects/vuelo.png");
        attack = Loader.ImageLoader("/effects/ataque.png");

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
    }
	
}
