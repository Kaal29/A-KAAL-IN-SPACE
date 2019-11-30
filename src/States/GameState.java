
package States;

/**
 *
 * @author Willy Bazan
 * @date   21/11/2019
 * @time   02:43 am
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints; 
import java.util.ArrayList;
import java.awt.image.BufferedImage;

import Constants.Constant;
import GameObjects.Meteor;
import GameObjects.MovingObject;
import GameObjects.Player;
import GameObjects.Size;
import GameObjects.Ufo;
import Graphics.Animation;
import Graphics.Assets;
import Math.Vector2D;


public class GameState 
{
	
    private Player player;

    //crear un arreglo del tipo MovingObject
    private ArrayList<MovingObject> movingObjects= new ArrayList<MovingObject>();
    private ArrayList<Animation> explosions = new ArrayList<Animation>();

    //Variable que representa la cantidad de meteoros iniciales en cada oleada 
    private int meteors;


    public GameState()
    {
        //player = new Player(new Vector2D(400, 250), new Vector2D(0,0), 7, Assets.player, this);
        player = new Player(new Vector2D(Constant.WIDTH/2 - Assets.player.getWidth()/2,
				Constant.HEIGHT/2 - Assets.player.getHeight()/2), new Vector2D(),
				Constant.PLAYER_MAX_VEL, Assets.player, this);
        
        //Agregamos al jugador en la clase objetos Moviles
        movingObjects.add(player);

        meteors = 1;

        startWave();
    }

    //Para dividir los meteoros
    public void divideMeteor( Meteor meteor)
    {
        //Obtener el tamño de este meteoro ( el tipo de numerador que tiene
        Size size = meteor.getSize();

        //Crear un objeto para las texturas
        BufferedImage[] textures = size.textures;

        //Para el nuevo tamaño
        Size newSize = null;

        switch ( size)
        {
            case BIG:
                newSize = Size.MED;
                break;
            case MED:
                newSize = Size.SMALL;
                break;
            case SMALL:
                newSize = Size.TINY;
                break;
            default:
                return;
        }

        //Crear los meteoros
        for ( int i=0; i <size.quantity ; i++)
        { //De aqui se procede examente igual que cuando se crea un meteoro
            movingObjects.add(new Meteor(
                        meteor.getPosition(),
                        new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                        Constant.METEOR_VEL*Math.random()+1,
                        textures[(int)(Math.random()*textures.length)],
                        this,
                        newSize));

        }
    }

    //Inicia cada ronda de meteoros
    private void startWave()
    {
        double x, y;
        
        for ( int i=0; i < meteors; i++)
        { 
            //Posiciones en las que apareceran los meteoros
            x = i%2 == 0 ? Math.random()*Constant.WIDTH: 0; //Forma de generar condicinales en una sola linea
            y = i%2 == 0 ? 0: Math.random()*Constant.HEIGHT; 

            BufferedImage texture = Assets.bigs[(int)(Math.random()*Assets.bigs.length)];

            movingObjects.add(new Meteor(
                        new Vector2D(x,y),
                        new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                        Constant.METEOR_VEL*Math.random()+1,
                        texture,
                        this,
                        Size.BIG));
        }

        meteors ++;
        spawnUfo();
    }
    //Agregar animaciones a un arreglo
    public void playExplosion(Vector2D position)
    {

        explosions.add(new Animation(
                    Assets.exp,
                    50, //Tiempo que dura una explosion
                    position.subtract(new Vector2D(Assets.exp[0].getWidth()/2, Assets.exp[0].getHeight()/2))
                    ));
    }

    //Nos permite crear un Ufo
    private void spawnUfo()
    {
        int rand = (int)(Math.random()*2);

        //Le damos una posicion inicial
        double x = rand == 0 ? (Math.random()*(Constant.WIDTH-100)): 0;
        double y = rand == 0 ? 0 : (Math.random()*(Constant.HEIGHT-100));

        //Representa al camino
        ArrayList<Vector2D> path = new ArrayList<Vector2D>();

        //Para las posiciones X y Y que forma el camino
        double posX, posY;

        //Valor random en el sector superior izquierdo
        posX = Math.random()*Constant.WIDTH/2;
        posY = Math.random()*Constant.HEIGHT/2;	
        path.add(new Vector2D(posX, posY)); //Se agrega al camino

        //Valor random en el sector superior derecho
        posX = Math.random()*(Constant.WIDTH/2) + Constant.WIDTH/2;
        posY = Math.random()*Constant.HEIGHT/2;	
        path.add(new Vector2D(posX, posY));

        //Valor random en el sector inferior izquierdo
        posX = Math.random()*Constant.WIDTH/2;
        posY = Math.random()*(Constant.HEIGHT/2) + Constant.HEIGHT/2;	
        path.add(new Vector2D(posX, posY));

        //Valor random en el sector inferior derecho
        posX = Math.random()*(Constant.WIDTH/2) + Constant.WIDTH/2;
        posY = Math.random()*(Constant.HEIGHT/2) + Constant.HEIGHT/2;	
        path.add(new Vector2D(posX, posY));

        Ufo.enemy = (int)(Math.random()*Assets.ufo.length);
        
        movingObjects.add(new Ufo(   //Luego se agrega todo arreglo de objetos moviles 
                new Vector2D(x, y),
                new Vector2D(), //La velocidad puede ser un vector velocidad cero, esto no importa ya que hay aceleracion
                Constant.UFO_MAX_VEL,
                Assets.ufo[Ufo.enemy],
                path, //Camino 
                this  //estado de juego
                ));
            
        
        
            
    }
    
    
    public void update()
    {

        for ( int i=0; i< movingObjects.size(); i++ )
        {
            movingObjects.get(i).update();
        }

        //Para actualizar las animaciones
        for ( int i=0; i < explosions.size(); i++ )
        {
            Animation anim = explosions.get(i);
            anim.update();

            if (!anim.isRunning())
            {
                explosions.remove(i);
            }
        }

        for ( int i=0; i< movingObjects.size(); i++ )
        {
            if (movingObjects.get(i) instanceof Meteor) //Con esto se pregunta si el objeto movible es un objeto de la clase meteoro
            {
                return ;
            } 
        }

        //Si no hay ningun meteoro iniciamos la nueva oleada
        startWave();

    }

    public void draw(Graphics g)
    {
        //Para evitar que la imagen se vea pixelada
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        for ( int i=0; i< movingObjects.size(); i++ )
        {
            movingObjects.get(i).draw(g);
        }

        //Para dibujar 
        for ( int i=0; i< explosions.size(); i++ )
        {
            Animation anim = explosions.get(i);
            g2d.drawImage(anim.getCurrentFrame(),(int)(anim.getPosition().getX()), (int)(anim.getPosition().getY()), null);
        }

    }

    public ArrayList<MovingObject> getMovingObjects() 
    {
        return movingObjects;
    }
    
    public Player getPlayer() 
    {
        return player;
    }
}
