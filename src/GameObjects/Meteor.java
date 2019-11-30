
package GameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Constants.Constant;
import Math.Vector2D;
import States.GameState;

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
        
        //Para escalonar el vector velocity
        this.velocity = velocity.scale(maxVel);
    }

    @Override
    public void update() 
    {
        position = position.add(velocity);
        
        if(position.getX() > Constant.WIDTH)
            position.setX(-width);
        if(position.getY() > Constant.HEIGHT)
            position.setY(-height);

        if(position.getX() < -width)
            position.setX(Constant.WIDTH);
        if(position.getY() < -height)
            position.setY(Constant.HEIGHT);

        angle += Constant.DELTAANGLE/2;
    }

    @Override
    public void destroy()
    {
        gameState.divideMeteor(this);
        gameState.addScore(Constant.METEOR_SCORE, position);
        super.destroy();
    }
            
            
    @Override
    public void draw(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D)g;
        
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle, width/2, height/2); 
        
        g2d.drawImage(texture, at, null);
        
    }
    
    public Size getSize()
    {
        return size;
    }
}
