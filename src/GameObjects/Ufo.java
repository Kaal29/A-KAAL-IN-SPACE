package GameObjects;

/**
 *
 * @author Willy Bazan
 * @date   27/11/2019
 * @time   05:16 pm
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Constants.Constant;
import Graphics.Assets;
import Graphics.Sound;
import Math.Vector2D;
import States.GameState;

public class Ufo extends MovingObject
{
    //Contiene los nodos que forman el camino
    private ArrayList<Vector2D> path;

    //referencia al nodo actual que esta persiguiendo el Ufo 
    private Vector2D currentNode;

    //Representa el indice en el arreglo de nodos ( es decir el camino)
    private int index;

    //Para saber si la nave esta reccoriendo el camino
    private boolean following;
    
    //Para saber que enemigo estamos enfrentando
    public static int enemy;

    //Para determinar el tiempo que el Ufo puede disparar
    private Chronometer fireRate;
    
    //Sonido del Ufo
    private Sound shoot;
    
    
    public Ufo(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture,
                    ArrayList<Vector2D> path, GameState gameState) {
            super(position, velocity, maxVel, texture, gameState);
            this.path = path;
            index = 0;
            following = true;
            fireRate = new Chronometer();
            fireRate.run(Constant.UFO_FIRE_RATE);
            shoot = new Sound(Assets.ufoShoot);
    }

    /**
     * Retorna la fuerza seek, solo que en esta gestiona todo el camino
     * @return 
     */
    private Vector2D pathFollowing() 
    {
        currentNode = path.get(index);

        double distanceToNode = currentNode.subtract(getCenter()).getMagnitude();
        
        if(distanceToNode < Constant.NODE_RADIUS) 
        {
            index ++;

            if(index >= path.size()) 
            {
                following = false;
            }
        }

        return seekForce(currentNode);

    }

    /**
     * Vector que permite calcular la fuerza 
     * y pide como parametro un Vector2D de objetivo
     * @param target
     * @return 
     */
    private Vector2D seekForce(Vector2D target) 
    {
        Vector2D desiredVelocity = target.subtract(getCenter());
        
        //Se tiene que ajustar esta fuerza a la velocidad maxima del Ufo
        desiredVelocity = desiredVelocity.normalize().scale(maxVel);
        
        return desiredVelocity.subtract(velocity);
    }

    @Override
    public void update() 
    {
        Vector2D pathFollowing;

        if(following){ //Si estamos siguiendo el camino
            pathFollowing = pathFollowing();
            
        }else{
            pathFollowing = new Vector2D();
            
        }
        
        //Por la segunda ley de Newton, calculamos la aceleracion
        pathFollowing = pathFollowing.scale(Constant.UFO_FORCE_MASS);
        
        velocity = velocity.add(pathFollowing);

        velocity = velocity.limit(maxVel);

        position = position.add(velocity);

        //si termina el camino del jugador
        if(position.getX() > Constant.WIDTH || position.getY() > Constant.HEIGHT
                        || position.getX() < -Assets.ufo[0].getWidth() || position.getY() < -Assets.ufo[0].getHeight())
            destroy();

        // Para obtener posicion y direccion y sentido de disparo dirigido al jugador
        if(!fireRate.isRunning()) 
        {
            Vector2D toPlayer = gameState.getPlayer().getCenter().subtract(getCenter());

            toPlayer = toPlayer.normalize();

            double currentAngle = toPlayer.getAngle();//Obtengo el angulo actual

            //Se obtiene un angulo de 180 grados con respecto al jugador
            currentAngle += Math.random()*Constant.UFO_ANGLE_RANGE - Constant.UFO_ANGLE_RANGE / 2;
            
            //Circulo unitario
            if ( toPlayer.getX() <0)
                currentAngle = -currentAngle + Math.PI;
            
            //Modifico el vector toPlayer
            toPlayer = toPlayer.setDirection(currentAngle);

            //Y ya con estos valores puedo crear el laser 
            Laser laser = new Laser(
                    getCenter().add(toPlayer.scale(width)),
                    toPlayer,
                    Constant.LASER_VEL,
                    currentAngle + Math.PI/2, //Ya que el laser ya viene a 90 grados
                    Assets.redLaser,
                    gameState
                    );

            gameState.getMovingObjects().add(0, laser);//Para que se dibuje antes que el ufo

            fireRate.run(Constant.UFO_FIRE_RATE);
            shoot.play();

        }
        
        //Ya que el sonido demora mas de lo que suena 
        if (shoot.getFramePosition()> 8500)
        {  //La posicion total del sonido es 12000
            shoot.stop();
        }
        
        angle += Constant.UFO_TURN_ANGLE;

        //Ya que esta clase tambien puede colisionar
        collidesWith();
        fireRate.update();
    }

    @Override
    public void destroy()
    {
        gameState.addScore(Constant.UFO_SCORE, position);
        super.destroy();
    }
    
    //Para hacer que el Ufo le dispare al jugador
    @Override
    public void draw(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D)g;

        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());

        at.rotate(angle, width/2, height/2);

        g2d.drawImage(texture, at, null);
        
    }
}
