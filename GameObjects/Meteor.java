
package GameObjects;

import Constants.Constant;
import Math.Vector2D;
import States.GameState;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author Willy Bazan
 * @date   22/11/2019
 * @time   11:21 pm
 */
public class Meteor extends MovingObject
{
    private Size size;
    public Meteor(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState, Size size) {
        super(position, velocity, maxVel, texture, gameState);
        this.size= size;
    }

    @Override
    public void update() 
    {
        position = position.add(velocity);
        
        if (position.getX() >= Constant.WIDTH)
           position.setX(0);
        
        if (position.getY() > Constant.HEIGHT)
           position.setY(0);
        
        if (position.getX() <= Constant.WIDTH)
           position.setX(0);
        
        if (position.getY() <= 0)
           position.setY(Constant.HEIGHT);
        
        angle += Constant.DELTAANGLE/2;
    }

    @Override
    public void draw(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D)g;
        
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle, width/2, height/2); 
        
        g2d.drawImage(texture, at, null);
        
    }
    
    public Size getSise()
    {
        return size;
    }
}
