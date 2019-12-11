
package Input;

/**
 *
 * @author Willy Bazan
 * @date   14/11/2019
 * @time   03:56 am
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener{
	
    private boolean[] keys = new boolean[256];

    //Para el movimiento
    public static boolean UP, LEFT, RIGHT;

    //Para disparar
    public static boolean SHOOT;
    
    public KeyBoard()
    {
            UP    = false;
            LEFT  = false;
            RIGHT = false;
            SHOOT = false;
    }

    public void update()
    {
            UP    = keys[KeyEvent.VK_UP];
            LEFT  = keys[KeyEvent.VK_LEFT];
            RIGHT = keys[KeyEvent.VK_RIGHT];
            SHOOT = keys[KeyEvent.VK_SPACE];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

	
}
