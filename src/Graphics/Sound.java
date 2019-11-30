
package Graphics;

/**
 * PERMITE REPRODUCIR LOS SONIDOS
 * @author Willy Bazan
 * @date   30/11/2019
 * @time   12:33 pm
 */

import javax.sound.sampled.Clip;
public class Sound 
{
    private Clip clip;
    
    public Sound(Clip clip)
    {
        this.clip = clip;
    }
    
    //Para reproducirlo
    public void play()
    {
        clip.setFramePosition(0);
        clip.start();
    }
    
    //Para la musica de fondo
    public void loop()
    {
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);  //Loop requiere de una constante (default)
    }
    
    //para detenerlo
    public void stop()
    {
        clip.stop();
    }
    
    public int getFramePosition()
    {
        return clip.getFramePosition();
    }
}
