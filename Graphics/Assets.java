
package Graphics;

/**
 *
 * @author Willy Bazan
 * @date   20/11/2019
 * @time   10/02 am
 */


import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage player;
	
        //Effects
        public static BufferedImage speed;
	public static void init()
	{
          
            
            player = Loader.ImageLoader("/ships/estatico.png");
            
            speed = Loader.ImageLoader("/effects/vuelo.png");
           
		  
        }
	
}
