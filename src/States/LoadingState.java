
package States;

/**
 *
 * @author Willy Bazan
 * @date   05/12/2019
 * @time   02:29 am
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Font;
import java.awt.image.BufferedImage;

import Constants.Constant;
import Graphics.Assets;
import Graphics.Loader;
import Graphics.Text;
import Math.Vector2D;

public class LoadingState extends State
{
    private Thread loadingThread;
    private Font font;
    private BufferedImage portada;
    public LoadingState( Thread loadingThread)
    {
        this.loadingThread = loadingThread;
        //y luego se inicia
        this.loadingThread.start();
        
        font = Loader.loadFont("/fonts/futureFont.ttf", 38);
        portada= Loader.ImageLoader("/ui/1.jpg");
    }

    @Override
    public void update() 
    {
        //Cuando todos los recursos fueron cargados ya puedo arrancar el estado menu
        if ( Assets.loaded)
        {
            State.changeState(new MenuState());
            
            try {
                //Luego se cierra el hilo
                loadingThread.join();
            } catch (InterruptedException ex) 
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void draw(Graphics g) 
    {
        //Se puede usar una textura (imagen) para dibujar la barra de carga
        
        /**
         * Pero opté por GradientPaint 
         * sirve para interpolar desde un punto hasta otro punto un color a otro color
         * Lo que quiere decir que se puede rellenar ujn rectangulo con un degrado 
         * */
        g.drawImage( portada , 0, 0, Constant.WIDTH, Constant.HEIGHT, null);
        GradientPaint gp = new GradientPaint(//La posicion va estar en el medio 
                //Empieza desde la esquina superiior izquierda
                    Constant.WIDTH/2- Constant.LOADING_BAR_WIDTH/2, 
                    Constant.HEIGHT/2- Constant.LOADING_BAR_HEIGHT/2,
                    Color.WHITE,
                //Termina en la esquina superior derecha
                    Constant.WIDTH/2+ Constant.LOADING_BAR_WIDTH/2,
                    Constant.HEIGHT/2+ Constant.LOADING_BAR_HEIGHT/2,
                    Color.BLUE);
        
        //Luego de esto dibujamos
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setPaint(gp);
        float percentage = Assets.count/Assets.MAX_COUNT; //Para obtener el porcentaje que voy cargando
    
        g2d.fillRect(Constant.WIDTH / 2 - Constant.LOADING_BAR_WIDTH / 2,
				Constant.HEIGHT / 2 - Constant.LOADING_BAR_HEIGHT / 2,
				(int)(Constant.LOADING_BAR_WIDTH * percentage),
				Constant.LOADING_BAR_HEIGHT);
		//Dibuja el marco de la barra
		g2d.drawRect(Constant.WIDTH / 2 - Constant.LOADING_BAR_WIDTH / 2,
				Constant.HEIGHT / 2 - Constant.LOADING_BAR_HEIGHT / 2,
				Constant.LOADING_BAR_WIDTH,
				Constant.LOADING_BAR_HEIGHT);
		
        Text.drawText(g2d, "A KAAL IN SPACE", new Vector2D(Constant.WIDTH / 2, Constant.HEIGHT / 2 - 50),
                        true, Color.WHITE, font);


        Text.drawText(g2d, "LOADING...", new Vector2D(Constant.WIDTH / 2, Constant.HEIGHT / 2 + 40),
                        true, Color.WHITE, font);

    }
}
