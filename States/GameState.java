
package States;

/**
 *
 * @author Willy Bazan
 * @date   20/11/2019
 * @time   02:30 am
 */

import Constants.Constant;
import GameObjects.Meteor;
import GameObjects.MovingObject;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints; 
import java.util.ArrayList;
import GameObjects.Player;
import GameObjects.Size;
import Graphics.Assets;
import Math.Vector2D;
import java.awt.image.BufferedImage;

public class GameState {
	
	private Player player;
        
        //crear un arreglo del tipo MovingObject
        private ArrayList<MovingObject> movingObjects= new ArrayList<MovingObject>();
	
        //Variable que representa la cantidad de meteoros iniciales en cada oleada 
        private int meteors;
	public GameState()
	{
            player = new Player(new Vector2D(400, 250), new Vector2D(0,0), 7, Assets.player, this);
            movingObjects.add(player);
            
            meteors = 1;
            startWave();
        }
        
        //Inicia cada olida de meteoros
        private void startWave()
        {
            double x, y;
            for ( int i=0; i < meteors; i++)
            { //Posiciones en las que apareceran los meteoros
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
        }
	
	public void update()
        {
            for ( int i=0; i< movingObjects.size(); i++ )
            {
                movingObjects.get(i).update();
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
	}

    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }
   
}
