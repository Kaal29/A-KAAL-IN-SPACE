
package GameObjects;

/**
 *
 * @author Willy Bazan
 * @date   20/11/2019 
 * @time   3:20 pm
 */


import Graphics.Assets;
import Input.KeyBoard;
import Main.Window;
import Math.Vector2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;


public class Player extends MovingObject
{
    private Vector2D heading;
    private Vector2D acceleration;
    private final double ACC = 0.1; //Velocidad de movimiento
    private final double DELTAANGLE = 0.1; //Angulo de rotacion 
    //variables para indicar el estado del jugador(imagenes)
    private boolean accelerating = false;  
    private boolean stop = false;
    
    public Player(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture) {
        super(position, velocity, maxVel, texture);
        heading = new Vector2D(0,1);
        acceleration = new Vector2D();
    }
    
    @Override
    public void update() 
    {
        if (KeyBoard.RIGHT)
        {
            angle += DELTAANGLE;
        }
        
        if (KeyBoard.LEFT)
        {
            angle -= DELTAANGLE;
        }
        
        if (KeyBoard.UP)
        {
            acceleration = heading.scale(ACC);
            accelerating = true;
            stop = false;
        } else 
        {
            //Para comprobar si la velocidad no es cero y contiunuar desacelerando
            if ( velocity.getMagnitude() !=0)
            {
                acceleration = (velocity.scale(-1).normalize()).scale(ACC/2);
            }
            
            accelerating = false;
            stop = true;
        }
         
        
        velocity = velocity.add(acceleration);
        
        velocity = velocity.limit(maxVel);
        
        heading = heading.setDirection(angle-Math.PI/2);
        
        position = position.add(velocity);
        
        if (position.getX() >= Window.WIDTH-width)
           position.setX(Window.WIDTH-width);
        
        if (position.getY() > Window.HEIGHT-height)
           position.setY(Window.HEIGHT-height);
        
        if (position.getX() <= 0)
           position.setX(0);
        
        if (position.getY() <= 0)
           position.setY(0);
        
    }

    @Override
    public void draw(Graphics g) 
    {
	Graphics2D g2d = (Graphics2D)g;
        
        //at es la imagen estatica
        //at1 es vuelo
        AffineTransform at1 = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        
        at1.rotate(angle, width/2, height/2); //Tenemos que pasar el punto de rotacion y el angulo
        
        //Parta indicar cuandoi debe o no cambiar de imagen
        if (accelerating)
        {
            g2d.drawImage(Assets.speed, at1, null);
        }
        
        //at es stop
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle, width/2, height/2);
        if (stop)
        {
            g2d.drawImage(Assets.player, at, null);
            
        }
        
        
    }   
}
