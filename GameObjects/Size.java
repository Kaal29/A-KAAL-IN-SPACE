
package GameObjects;

/**
 *
 * @author Willy Bazan
 * @date   23/11/2019
 * @time   12:05 am
 */

import Graphics.Assets;
import java.awt.image.BufferedImage;

public enum Size 
{
    BIG (2, Assets.bigs),MED(2, Assets.meds), SMALL(2, Assets.smalls), TINY(0, null);
    
    public int quantity;
    public BufferedImage[] textures;
    
    private Size ( int quantity, BufferedImage[] textures)
    {
        this.quantity = quantity;
        this.textures = textures;
    }
}
