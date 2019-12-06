
package Graphics;

/**
 * PERMITE REPRODUCIR LOS SONIDOS
 * @author Willy Bazan
 * @date   30/11/2019
 * @time   12:33 pm
 */

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl; //Para controlar el volumen 

public class Sound 
{
    private Clip clip;
    private FloatControl volume;
    
    public Sound(Clip clip)
    {
        this.clip = clip;
        volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN); //Y ya con esto esta listo el objeto que controla el volumen
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
        clip.setFramePosition(100000);  //La musica de fondo esta retrasada 
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
     
    public void changeVolumeFloat ( int value)
    {
        volume.setValue(value);
    }
}
