
package Main;

/**
 *
 * @author Willy Bazan
 * @date   20/11/2019
 * @time   04:20 pm
 */


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Constants.Constant;
import Graphics.Assets;
import Input.KeyBoard;
import States.GameState;

public class Window extends JFrame implements Runnable{
	
    private static final long serialVersionUID = 1L;
    
    private Canvas canvas;
    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g;

    private final int FPS = 60;
    private double TARGETTIME = 1000000000/FPS;
    private double delta = 0;
    private int AVERAGEFPS = FPS;

    private GameState gameState;
    private KeyBoard keyBoard;
    
    public Window()
    {
        setTitle("IRONMAN KAAL");
        setSize(Constant.WIDTH, Constant.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        canvas = new Canvas();
        keyBoard = new KeyBoard();
        
        canvas.setPreferredSize(new Dimension(Constant.WIDTH, Constant.HEIGHT));
        canvas.setMaximumSize(new Dimension(Constant.WIDTH, Constant.HEIGHT));
        canvas.setMinimumSize(new Dimension(Constant.WIDTH, Constant.HEIGHT));
        canvas.setFocusable(true);

        add(canvas);
        canvas.addKeyListener(keyBoard);
        
        //Esta debe de ser la ultima linea 
        //De lo contrario a veces la pantalla carga en blanco
        setVisible(true);
    }



    public static void main(String[] args) {
            new Window().start();

    }


    private void update()
    {
        keyBoard.update();
        gameState.update();
    }

    private void draw(){
            bs = canvas.getBufferStrategy();

            if(bs == null)
            {
                    canvas.createBufferStrategy(3);
                    return;
            }

            g = bs.getDrawGraphics();

            //-----------------------

            g.setColor(Color.DARK_GRAY);

            g.fillRect(0, 0, Constant.WIDTH, Constant.HEIGHT);

            gameState.draw(g);

            g.drawString(""+AVERAGEFPS, 10, 20);

            //---------------------
            g.dispose();
            bs.show();
    }

    private void init()
    {
            Assets.init();
            gameState = new GameState();
    }


    @Override
    public void run() {

            long now = 0;
            long lastTime = System.nanoTime();
            int frames = 0;
            long time = 0;

            init();

            while(running)
            {
                    now = System.nanoTime();
                    delta += (now - lastTime)/TARGETTIME;
                    time += (now - lastTime);
                    lastTime = now;

                    if(delta >= 1)
                    {		
                            update();
                            draw();
                            delta --;
                            frames ++;
                    }
                    if(time >= 1000000000)
                    {
                            AVERAGEFPS = frames;
                            frames = 0;
                            time = 0;
                    }

            }

            stop();
    }

    private void start(){

            thread = new Thread(this);
            thread.start();
            running = true;


    }
    private void stop(){
            try {
                    thread.join();
                    running = false;
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
    }
}
