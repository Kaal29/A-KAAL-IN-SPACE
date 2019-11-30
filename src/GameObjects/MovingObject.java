
package GameObjects;



/**
 *
 * @author Willy Bazan
 * @date   18/11/2019
 * @time   05:24 pm
 */

import Graphics.Assets;
import Graphics.Sound;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Math.Vector2D;
import States.GameState;

public abstract class MovingObject extends GameObject
{
    protected Vector2D velocity;
    protected AffineTransform at;
    protected double angle;
    protected double maxVel;
    protected int width;
    protected int height;
    protected GameState gameState; //Para que cada objeto de MovingObjevt tenga acceso a GameState
    
    private Sound explosion;
    
    public MovingObject(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) 
    {
        super(position, texture);
        this.velocity = velocity;
        this.maxVel = maxVel;
        this.gameState = gameState;
        width = texture.getWidth();
        height = texture.getHeight();
        angle = 0;
        explosion = new Sound( Assets.explosion );
        
    }
    
    //Colisiona cuando (comprobar cuando colisiona con otro objeto)
    protected void collidesWith()
    {
       
        //Obtener una referencia de ese arreglo
        ArrayList<MovingObject> movingObjects = gameState.getMovingObjects();
        
        for ( int i=0; i < movingObjects.size(); i++)
        {
            MovingObject m = movingObjects.get(i);
            
            if (m.equals(this))
                continue;
            
            double distance = m.getCenter().subtract(getCenter()).getMagnitude();  //El ultimo getCenter() es del objecto que esta intentado colisionar
        
            //Cuando os objetos colisionen van a ser destruidos
            //con excepcion de los meteoros que colisinen entre si
            if ( distance< m.width/2+width/2 && movingObjects.contains(this))
            {
                objectCollision(m, this);
            }  
        }
    }
    
    private void objectCollision( MovingObject a, MovingObject b)
    {   //Si algunos de estos objetos es el jugador y si la variable spawing es verdadera
        if ( a instanceof Player &&((Player)a).isSpawing())
        {
            return;
        }
        
        if ( b instanceof Player &&((Player)b).isSpawing())
        {
            return;
        }
        
        //Si a no es un meteoro
        if ( !(a instanceof Meteor && b instanceof Meteor))
        {
            gameState.playExplosion(getCenter());
            a.destroy();
            b.destroy();
        }
    }
    
    //Metodo para eliminarlos objetos destruidos
    protected void destroy()
    {
        gameState.getMovingObjects().remove(this);
        if ( !(this instanceof Laser))
        {
            explosion.play();
        }
    }
    
    protected Vector2D getCenter()
    {
        return new Vector2D(position.getX() + width/2, position.getY() + height/2 );
    }

}
