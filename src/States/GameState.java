
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
import java.awt.Color;

import Constants.Constant;
import GameObjects.Chronometer;
import GameObjects.Message;
import GameObjects.Meteor;
import GameObjects.MovingObject;
import GameObjects.Player;
import GameObjects.Size;
import GameObjects.Ufo;
import Graphics.Animation;
import Graphics.Assets;
import Graphics.Sound;
import Math.Vector2D;


public class GameState extends State //Este cambio se hizo para tener el control                                      
{                                      //de manera directa del juego y no comprometernos con su logica al momento de crear la interfaz
    
    
    public static final Vector2D PLAYER_START_POSITION = new Vector2D(Constant.WIDTH/2 - Assets.players[ChooseShip.avatar].getWidth()/2,
			Constant.HEIGHT/2 - Assets.players[ChooseShip.avatar].getHeight()/2);
    
    private Player player;

    //crear un arreglo del tipo MovingObject
    private ArrayList<MovingObject> movingObjects= new ArrayList<MovingObject>();
    private ArrayList<Animation> explosions = new ArrayList<Animation>();
    private ArrayList<Message> messages = new ArrayList<Message>();
    
    //Variable que representa la cantidad de meteoros iniciales en cada oleada 
    private int meteors;

    //Puntaje del jugador
    private int score=0;
    private int lives = 5;
    
    private int waves = 1;
    
    private Sound backgroundMusic;
    
    private Chronometer ufoSpawner;
    private Chronometer gameOverTimer;
    private boolean gameOver;
    
    
    public GameState()
    {
        
        //player = new Player(new Vector2D(400, 250), new Vector2D(0,0), 7, Assets.player, this);
        player = new Player(new Vector2D(Constant.WIDTH/2 - Assets.players[ChooseShip.avatar].getWidth()/2,
				Constant.HEIGHT/2 - Assets.players[ChooseShip.avatar].getHeight()/2), new Vector2D(),
				Constant.PLAYER_MAX_VEL, Assets.players[ChooseShip.avatar], this);
        
        gameOverTimer = new Chronometer();
	gameOver = false;
                
        //Agregamos al jugador en la clase objetos Moviles
        movingObjects.add(player);

        meteors = 1;
        startWave();
        
        backgroundMusic = new Sound(Assets.backgroundMusic);
        
        //Para la musica de fondo
        backgroundMusic.loop();
        //Para controlar el volumen de la musica de fondo [-80;6.02]
        backgroundMusic.changeVolumeFloat((int) -10f);
        
        //Para crear los Ufo cada 10 segundos
        ufoSpawner = new Chronometer();
        ufoSpawner.run(Constant.UFO_SPAWN_RATE);
    }

    
    public void addScore( int value, Vector2D position ) //Esta posicion es del objeto que se destruyo
    {
        score += value;
        messages.add(new Message(position, true, "+"+value+" score", Color.WHITE, false, Assets.fontMed));
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
            //case SMALL:
               // newSize = Size.TINY;
                //break;
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
        messages.add(new Message( new Vector2D(Constant.WIDTH/2, Constant.HEIGHT/2), true,
                    "WAVE "+ waves, Color.ORANGE, true, Assets.fontBig)); 
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
        //spawnUfo();   //Se quita esto pues ya se establecio de una mejor manera la creacion de enemigos cada 10 segundos
        waves++;
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
        Ufo.enemy = (int)(Math.random()*Assets.ufo.length);
        
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

        movingObjects.add(new Ufo(   //Luego se agrega todo arreglo de objetos moviles 
                new Vector2D(x, y),
                new Vector2D(), //La velocidad puede ser un vector velocidad cero, esto no importa ya que hay aceleracion
                Constant.UFO_MAX_VEL,
                Assets.ufo[Ufo.enemy],
                path, //Camino 
                this  //estado de juego
                ));
    }
    
    @Override
    public void update()
    {
        for ( int i=0; i< movingObjects.size(); i++ )
        {
            
            MovingObject movingObject = movingObjects.get(i);

            movingObject.update();
            
            if(movingObject.isDead()) {
                movingObjects.remove(i); //Y aqui es el incremento de eficiencia cuando 
                                         //ya se esta pasando el índice del objeto a destruir   //CAP-21
                i--; //Se tiene que restar una posicion pues esta ya fue eliminada
            }
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

        if(gameOver && !gameOverTimer.isRunning()) 
        {
            State.changeState(new MenuState());
	}
        
        if(!ufoSpawner.isRunning()) {
            ufoSpawner.run(Constant.UFO_SPAWN_RATE);
            spawnUfo(); //Como para aumentar la dificultad al juego
	}
        
        gameOverTimer.update();
	ufoSpawner.update();
                
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

    @Override
    public void draw(Graphics g)
    {
        //Para evitar que la imagen se vea pixelada
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
        for ( int i=0; i< messages.size(); i++ )
        {
            messages.get(i).draw(g2d);
            if (messages.get(i).isDead())
            {
                messages.remove(i);
            }
        }
        
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
        
        drawScore(g);
        drawLives(g);
        //Text.drawText(g, "WAVE " + waves, new Vector2D( Constant.WIDTH/2 , Constant.HEIGHT/2), 
               // true, Color.yellow, Assets.fontBig);
    }

    //para mostrar el score
    private void drawScore(Graphics g)
    {
        Vector2D pos = new Vector2D(Constant.WIDTH-75, 25);

        String scoreToString = Integer.toString(score);

        for(int i = 0; i < scoreToString.length(); i++) 
        {
            g.drawImage(Assets.numbers[Integer.parseInt(scoreToString.substring(i, i + 1))],
                            (int)pos.getX(), (int)pos.getY(), null);
            pos.setX(pos.getX() + 20);
        }
    }
    
    private void drawLives(Graphics g)
    {
        if ( lives<1)
            return;
        
        Vector2D livePosition = new Vector2D(25, 25);

        g.drawImage(Assets.life, (int)livePosition.getX(), (int)livePosition.getY(), null);

        g.drawImage(Assets.numbers[10], (int)livePosition.getX() + 40,
                        (int)livePosition.getY() + 5, null);

        String livesToString = Integer.toString(lives);

        Vector2D pos = new Vector2D(livePosition.getX(), livePosition.getY());

        for(int i = 0; i < livesToString.length(); i ++)
        {
            int number = lives ; //Integer.parseInt(livesToString.substring(i,i+1));

            if(number <= 0) //Comprobando que el numero de vidas sea mayor que cero 
                break;
            
            g.drawImage(Assets.numbers[number],
                            (int)pos.getX() + 60, (int)pos.getY() + 5, null);
            pos.setX(pos.getX() + 20);
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
    
    public boolean subtractLife()
    {
        lives--;
        return lives>0;
    }

    public void gameOver()
    {
        Message gameOverMsg = new Message(
                PLAYER_START_POSITION,
                true,
                "GAMEOVER",
                Color.GREEN,
                true,
                Assets.fontBig);
        
        this.messages.add(gameOverMsg);
        gameOverTimer.run(Constant.GAME_OVER_TIME);
        gameOver = true;
    }
    
    public ArrayList<Message> getMessages() {
        return messages;
    }
    
}
