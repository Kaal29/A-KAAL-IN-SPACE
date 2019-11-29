
package GameObjects;

import Math.Vector2D;
import States.GameState;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author Willy Bazan
 * @date   18/11/2019
 * @time   05:24 pm
 */
public abstract class MovingObject extends GameObject
{
    protected Vector2D velocity;
    protected AffineTransform at;
    protected double angle;
    protected double maxVel;
    protected int width;
    protected int height;
    protected GameState gameState; //Para que cada objeto de MovingObjevt tenga acceso a GameState
    
    public MovingObject(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) 
    {
        super(position, texture);
        this.velocity = velocity;
        this.maxVel = maxVel;
        this.gameState = gameState;
        width = texture.getWidth();
        height = texture.getHeight();
        angle = 0;
        
        
    }

}
