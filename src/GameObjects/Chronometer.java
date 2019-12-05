
package GameObjects;

/**
 *
 * @author Willy Bazan
 * @date   23/11/2019 
 * @time   05:15 pm
 */
public class Chronometer 
{
    private long delta, lastTime;
    private long time;
    private boolean running;

    public Chronometer() 
    {
        delta = 0;
        lastTime = System.currentTimeMillis(); //Estaba en 0 pero deveria ser de esta forma para leer el timpo actual 
        running = false;
    }
    
    public void run ( long time)
    {
        running = true;
        this.time = time;
    }
    
    public void update()
    {
        if (running)
            delta += System.currentTimeMillis()-lastTime;
        
        if(delta >= time)
        {
            running = false;
            delta = 0;
        }
          
        lastTime = System.currentTimeMillis();
        
    }
    
    //Para saber si el cronometro esta corriendo
    public boolean isRunning ()
    {
        return running;
    }
}
