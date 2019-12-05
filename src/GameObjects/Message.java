
package GameObjects;

import Graphics.Text;
import Math.Vector2D;
import States.GameState;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author    ***
 * @date      ***
 * @time      ***
 * 
 * @adapted by  Willy Bazán
 * @date       30/11/2019
 * @time       10:30 am
 */
public class Message 
{
    //private GameState gameState;
    private float alpha;
    private String text; //Para el texto
    private Vector2D position;
    private Color color;
    private boolean center;
    private boolean fade; //Efecto de aparecer <- contrario de fade
    private Font font;
    private final float deltaAlpha = 0.01f; //Mientras mas rapido mas rapido de desvanece
    private boolean dead;
        
    public Message(Vector2D position, boolean fade, String text, Color color,
                    boolean center, Font font, GameState gameState) {
            this.font = font;
            //this.gameState = gameState; rfeamplasa la variable dead
            this.text = text;
            this.position = position;
            this.fade = fade;
            this.color = color;
            this.center = center;

            if(fade)
                    alpha = 1;
            else
                    alpha = 0;
    }
	
    public void draw(Graphics2D g2d) 
    {
	//Para hacer la transparencia
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        Text.drawText(g2d, text, position, center, color, font);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        position.setY(position.getY() - 1);

        if(fade)
                alpha -= deltaAlpha;
        else
                alpha += deltaAlpha;

        if(fade && alpha < 0) {
            dead = true; //gameState.getMessages().remove(this); //Para eliminar mensaje
        }

        if(!fade && alpha > 1) {
                fade = true;
                alpha = 1;
        }

    }
    
    public boolean isDead() {return dead;}
    
}
