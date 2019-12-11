
package GameObjects;

/**
 *
 * @author Willy Bazan
 * @date   23/11/2019
 * @time   12:05 am
 */

import java.awt.image.BufferedImage;

import Graphics.Assets;

public enum Size 
{
    BIG (1, Assets.meds),MED(1, Assets.smalls), SMALL(1, null);
    
    public int quantity;
    
    public BufferedImage[] textures;
    
    private Size ( int quantity, BufferedImage[] textures)
    {
        this.quantity = quantity;
        this.textures = textures;
    }
}
