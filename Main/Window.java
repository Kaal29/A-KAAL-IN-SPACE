
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
import Input.MouseInput;
import States.ChooseStage;
import States.LoadingState;
import States.MenuState;

import States.State;
import java.awt.Image;
import java.awt.Toolkit;

public class Window extends JFrame implements Runnable
{
    //private static final long serialVersionUID = 1L;
    
    private Canvas canvas;
    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g;

    private final int FPS = 60;
    private double TARGETTIME = 1000000000/FPS;
    private double delta = 0;
    private int AVERAGEFPS = FPS;

    //sirvepara seleccionar el fondo de manera aleatoria 
    //private int typeStage;
    
    private KeyBoard keyBoard;
    
    private MouseInput mouseInput;
    
    public Window()
    {
        //typeStage = (int)(Math.random()*Assets.stages.length);
        
        setTitle("A KAAL IN SPACE");
        setSize(Constant.WIDTH, Constant.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(getIconImage());
        
        canvas = new Canvas();
        keyBoard = new KeyBoard();
        mouseInput = new MouseInput();
        
        canvas.setPreferredSize(new Dimension(Constant.WIDTH, Constant.HEIGHT));
        canvas.setMaximumSize(new Dimension(Constant.WIDTH, Constant.HEIGHT));
        canvas.setMinimumSize(new Dimension(Constant.WIDTH, Constant.HEIGHT));
        canvas.setFocusable(true);

        add(canvas);
        canvas.addKeyListener(keyBoard);
        canvas.addMouseListener(mouseInput); //Y con esto nuestra clase ya recibe los eventos del mouse
        canvas.addMouseMotionListener(mouseInput);
        
        //Esta debe de ser la ultima linea 
        //De lo contrario a veces la pantalla carga en blanco
        setVisible(true);
    }

    //Para cambiar el icono de la tasita de cafe
    @Override
    public Image getIconImage() 
    {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("ui/icono.png"));
        return retValue;
    }
    
    public static void main(String[] args) {
            new Window().empieza();
            System.out.println("main");
    }

    private void update()
    {
        System.out.println("update");
        keyBoard.update();
        State.getCurrentState().update();
    }

    private void draw(){
        System.out.println("draw");
        bs = canvas.getBufferStrategy();

        if(bs == null)
        {
                canvas.createBufferStrategy(3);
                return;
        }

        g = bs.getDrawGraphics();

        //-----------------------

        g.setColor(Color.BLACK);

        g.drawImage(Assets.stages[ChooseStage.stage], 0, 0, Constant.WIDTH, Constant.HEIGHT, this); //Para ajustar la imagen al tamaño de la pantalla

        State.getCurrentState().draw(g); //Y ya con esto se esta dibujando siempre el estdo actual

        //g.drawString(""+AVERAGEFPS, 10, 20);

        //---------------------
        g.dispose();
        bs.show();
    }

    private void init()
    {
        System.out.println("init");
        //ya que aquí empieza todo, se agrega el hilo para generar la barra de carga
        Thread loadingThread = new Thread( new Runnable() { //Y este hilo se inicia desde el constructor de la clase LoadingState

            @Override
            public void run()
            {
                Assets.init();
            }
        });
        
        //De la clase State su objeto empieza siendo nulo
        //entonces
        //Sirve para hacer modificaciones sin meternos con la logica del juego
        State.changeState(new LoadingState(loadingThread));
                    
    }
    
    @Override
    public void run() //Este hilo pertenece a la clase Runnable
    {
        System.out.println("run");
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

    public void empieza(){

            thread = new Thread(this);
            thread.start();
            running = true;
            System.out.println("EMPIEZA");
    }
    
    private void stop()
    {
        try {
                thread.join();
                running = false;
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }
}
