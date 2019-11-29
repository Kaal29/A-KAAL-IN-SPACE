
package GameObjects;

/**
 *
 * @author Willy Bazan
 * @date   22/11/2019
 * @time   10:24 am
 */

import Constants.Constant;
import Math.Vector2D;
import States.GameState;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Laser extends MovingObject
{
    //Debido a que la direccion del laser es la misma que la del jugador es necesario pasar el angulo
    public Laser(Vector2D position, Vector2D velocity, double maxVel, double angle, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVel, texture, gameState);
        this.angle = angle;
        this.velocity = velocity.scale(maxVel);
    }

    @Override
    public void update() 
    {
        position = position.add(velocity);
        if (position.getX()<0 || position.getX()>Constant.WIDTH || 
                position.getY()<0 || position.getY() >Constant.HEIGHT)
        {
            gameState.getMovingObjects().remove(this);
        }
    }

    @Override
    public void draw(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D)g;
        
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle);
        
        g2d.drawImage(texture, at, null);
    
    }

}
