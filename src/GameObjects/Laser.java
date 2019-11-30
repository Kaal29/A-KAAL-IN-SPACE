
package GameObjects;

/**
 *
 * @author Willy Bazan
 * @date   22/11/2019
 * @time   10:24 am
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Constants.Constant;
import Math.Vector2D;
import States.GameState;

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
            destroy();
        }
        
        collidesWith();
        
    }

    @Override
    public void draw(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D)g;
        
        at = AffineTransform.getTranslateInstance(position.getX()-width/2, position.getY());
        at.rotate(angle, width/2,0);
        
        g2d.drawImage(texture, at, null);
    
    }
    
    //sobrescribir el método de MovingObject ya que el laser se calcula la colision de manera diferente
    @Override
    protected Vector2D getCenter()
    {
        return new Vector2D(position.getX() + width/2, position.getY() + width/2 );
    }

}
