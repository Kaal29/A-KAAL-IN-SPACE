
package GameObjects;

/**
 *
 * @author Willy Bazan
 * @date   20/11/2019 
 * @time   3:20 pm
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

import Constants.Constant;
import Graphics.Assets;
import Input.KeyBoard;
import Math.Vector2D;
import States.GameState;

public class Player extends MovingObject
{
    private Vector2D heading;
    private Vector2D acceleration;
    
    //variables para indicar el estado del jugador(imagenes)
    private boolean accelerating = false;  
    
    //Objeto de la clase Chronometer
    private Chronometer fireRate;
    
    public Player(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
                super(position, velocity, maxVel, texture, gameState);
                heading = new Vector2D(0, 1);
                acceleration = new Vector2D();
                fireRate = new Chronometer();
	}
    
    @Override
    public void update() 
    {
        
        if (KeyBoard.SHOOT && !fireRate.isRunning())
        {
            //Mostrando el laser
            gameState.getMovingObjects().add( 0, new Laser( //el cero indica que este se ubica al fondo
                    getCenter().add((heading.scale(width))),
                    heading,
                    Constant.LASER_VEL, 
                    angle,
                    Assets.blueLaser, 
                    gameState));
            
            fireRate.run(Constant.FIRERATE);
        }
        
        if (KeyBoard.RIGHT)
            angle += Constant.DELTAANGLE;
        if (KeyBoard.LEFT)
            angle -= Constant.DELTAANGLE;
        
        if (KeyBoard.UP)
        {
            acceleration = heading.scale(Constant.ACC);
            accelerating = true;
        } else  
        {
            //Para comprobar si la velocidad no es cero y contiunuar desacelerando
            if ( velocity.getMagnitude() != 0)
            {
                acceleration = (velocity.scale(-1).normalize()).scale(Constant.ACC/2);
            }
            
            accelerating = false;
        } 
         
        velocity = velocity.add(acceleration);
        
        velocity = velocity.limit(maxVel);
        
        heading = heading.setDirection(angle-Math.PI/2);
        
        position = position.add(velocity);
        
        if (position.getX() > Constant.WIDTH-width)
           position.setX(0);
        
        if (position.getY() > Constant.HEIGHT-height)
           position.setY(0);
        
        if (position.getX() < 0)
           position.setX(Constant.WIDTH-width);
        
        if (position.getY() < 0)
           position.setY(Constant.HEIGHT-height);
        
        fireRate.update();
        collidesWith();
    }

    @Override
    public void draw(Graphics g) 
    {
	Graphics2D g2d = (Graphics2D)g;
        
        //Parta indicar cuando debe o no cambiar de imagen
        
        //at es stop
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle, width/2, height/2); 
        if (!accelerating)
        {
            g2d.drawImage(texture, at, null);
        }
            
        //at1 es Vuelo
        AffineTransform at1 = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at1.rotate(angle, width/2, height/2); //Tenemos que pasar el punto de rotacion y el angulo
        if (accelerating)
        {
            g2d.drawImage(Assets.speed, at1, null);
        }
            
    }   
    
     //Devuelve el centro de la imagen 
    @Override
     public Vector2D getCenter()
    {
        return new Vector2D(position.getX()+ width/2, position.getY()+ height/2);
    }
     
    
}
