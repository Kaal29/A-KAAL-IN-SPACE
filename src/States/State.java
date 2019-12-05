
package States;

/**
 *
 * @author Willy Bazan
 * @date   03/12/2019
 * @time   07:47 pm
 */

import java.awt.Graphics;

public abstract class State 
{
    private static State currentState = null; //Esta es una referencia y va ser usada en la clase ventana para saber que dibujar e iterar
    
    public static State getCurrentState(){return currentState;}
    
    public static void changeState( State newState)
    {
        currentState = newState;
    }
    
    public abstract void update();
    public abstract void draw( Graphics g);
    
}
