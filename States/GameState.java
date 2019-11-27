package States;

/**
 *
 * @author Willy Bazan
 * @date dia
 * @time  hora
 */

import java.awt.Graphics;

import GameObjects.Player;
import Graphics.Assets;
import Math.Vector2D;

public class GameState {
	
	private Player player;
	
	public GameState()
	{
		player = new Player(new Vector2D(400, 250), new Vector2D(0,0), 7, Assets.player);
	}
	
	public void update()
        {
            player.update();
            
	}
	
	public void draw(Graphics g)
	{
		player.draw(g);
	}
}
