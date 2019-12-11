
package Graphics;

/**
 * INTEGRA TODA LA INFORMACION DE ANIMACIONES QUE HAY EN EL JUEGO
 * @author Willy Bazan
 * @date   27/11/2019
 * @time   12.02 am
 */

import java.awt.image.BufferedImage;

import Math.Vector2D;

public class Animation 
{
    //Número todal de fotogramas
    private BufferedImage[] frames;
    
    private int velocity;
    private int index; //El índice del fotograma actual que estoy dibujando
    
    private boolean running; //Si la animacion esta corriendo
    private Vector2D position ; //Representa la posicion donde sa va a dibujar
    
    /**
     * Para controlar el tiempo necesitamos crear variables locales
     * Se puede usar la clase Chronometer, pero ya que solo lo usaremos una vez
     * se obta por realizarlo de la siguiente manera 
     */
    private long time, lastTime;
    
    public Animation(BufferedImage[] frames, int velocity, Vector2D position)
    {
        this.frames = frames;
        this.velocity = velocity;
        this.position = position;
        
        index = 0;
        running = true;
        time = 0;
        lastTime = System.currentTimeMillis();
        
    }
    
    //Metodo que se encarga de cambiar los fotogramas cada vez que pase la cantidad de tiempo llama como velocidad
    public void update()
    {
        time += System.currentTimeMillis()-lastTime;
        lastTime = System.currentTimeMillis();
        if (time>velocity)
        {
            time+=0;
            index++;
            
            if (index >= frames.length)
                running = false;
        }
    }
    
    public boolean isRunning() 
    {
        return running;
    }
    
    public Vector2D getPosition()
    {
        return position;
    }
    
    //Devuelve el fotograma actual en el arreglo de fotogramas
    public BufferedImage getCurrentFrame()
    {
        return frames[index];
    }
}
