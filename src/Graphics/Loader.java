
package Graphics;

/**
 * CARGA TODOS LOS RECURSOS
 * @author Willy Bazan
 * @date   20/11/2019
 * @time   10:05 am
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Font;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

import java.io.IOException;
import java.awt.FontFormatException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Loader 
{
    //Para cargar las imagenes principales
    public static BufferedImage ImageLoader(String path)
    {
            try {
                    return ImageIO.read(Loader.class.getResource(path));
            } catch (IOException e) {
                    e.printStackTrace();
            }
            return null;
    }
    
    
    //Para cargar los puntages 
    public static Font loadFont( String path, int size)
    {
        try {
            return Font.createFont( Font.TRUETYPE_FONT, Loader.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size );
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
            return null;
        } 
    }
    
    //
    public static Clip loadSound( String path)
    {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Loader.class.getResource(path)));
            return clip;
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
	
}
