 
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
import Graphics.Sound;
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
    
    //Para hacer que el jugador tenga oportunidad al iniciar 
    private boolean spawing, visible;
    //spawing para cuando esta parpadenado y no deveria detectar colisiones
    //visible para saber cuando dibujar y no al jugador
    
    //Para hacer el parpadeo del jugador
    private Chronometer spawnTime, flickerTime;
    //spawnTine para el tiempo en que esta parpadeando
    //flickerTime para el cambio entre visible y no visible
    
    //Para saber con que avatar estamos jugando
    public static int avatar;
    
    //sound
    private Sound shoot, loose;
    
    public Player(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, GameState gameState) {
                super(position, velocity, maxVel, texture, gameState);
                heading = new Vector2D(0, 1);
                acceleration = new Vector2D();
                fireRate = new Chronometer();
                spawnTime = new Chronometer();
                flickerTime =new Chronometer();
                shoot = new Sound(Assets.playerShoot);
                loose = new Sound(Assets.playerLoose);
	}
    
    @Override
    public void update() 
    {
        if ( !spawnTime.isRunning())
        {
            spawing = false;
            visible = true;
        }
        
        if (spawing)
        {
            if (!flickerTime.isRunning())
            {
                flickerTime.run(Constant.FLICKER_TIME);
                visible = !visible; //De esta manera se consigue el efecto de parpadeo
            }
        }
        
        if (KeyBoard.SHOOT && !fireRate.isRunning() && !spawing)
        {
            //Mostrando el laser
            gameState.getMovingObjects().add( 0, new Laser( //el cero indica que este se ubica al fondo
                    getCenter().add((heading.scale(width))),
                    heading,
                    Constant.LASER_VEL, 
                    angle,
                    Assets.lasers[0], 
                    gameState));
            
            fireRate.run(Constant.FIRERATE);
            shoot.play();
        }
        
        //Ya que el sonido demora mas de lo que suena 
        if (shoot.getFramePosition()> 8500)
        {  //La posicion totalñ del sonido es 12000
            shoot.stop();
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
           position.setX(Constant.WIDTH-width);
        
        if (position.getY() > Constant.HEIGHT-height)
           position.setY(Constant.HEIGHT-height);
        
        if (position.getX() < 0)
           position.setX(0);
        
        if (position.getY() < 0)
           position.setY(0);
        
        fireRate.update();
        spawnTime.update();
        flickerTime.update();
        collidesWith();
    }

    //Para hacer que la variable spawing sea verdadera
    @Override
    public void destroy()
    {
        spawing = true;
        spawnTime.run(Constant.SPAWING_TIME);
        loose.play();
        if ( !(gameState.subtractLife()))
        {
            gameState.gameOver();
            
            super.destroy();
        }
            
        resetValues();
    }
    
    private void resetValues() {
		
        angle = 0;
        velocity = new Vector2D();
        position = new Vector2D(Constant.WIDTH/2 - Assets.players[Player.avatar].getWidth()/2,
                        Constant.HEIGHT/2 - Assets.players[Player.avatar].getHeight()/2);
	}
    
    @Override
    public void draw(Graphics g) 
    {
        if (!visible)
            return;
        
	Graphics2D g2d = (Graphics2D)g;
        
        //Parta indicar cuando debe o no cambiar de imagen
        
        //at es stop
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle, width/2, height/2); 
        
        //at1 es efecto del vuelo 
        AffineTransform at1 = AffineTransform.getTranslateInstance(position.getX()+width/2-20, position.getY()+height);
         //Es necesario restar los numeros que agregamos a la posicion para que el giro no se altere
        at1.rotate(angle,20,-height/2); //Tenemos que pasar el punto de rotacion y el angulo
       
        if (accelerating)
        {
            g2d.drawImage(Assets.speed, at1, null);
        }
        
        //at es stop
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle, width/2, height/2); 
        
        g2d.drawImage(texture, at, null);
    }   
    
     //Devuelve el centro de la imagen 
    @Override
     public Vector2D getCenter()
    {
        return new Vector2D(position.getX()+ width/2, position.getY()+ height/2);
    }
     
    public boolean isSpawing()
    {
        return spawing;
    }
}
