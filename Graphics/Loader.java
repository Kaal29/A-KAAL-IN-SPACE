
package Graphics;

/**
 *
 * @author Willy Bazan
 * @date   20/11/2019
 * @time   10:05 am
 */
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	
	public static BufferedImage ImageLoader(String path)
	{
		try {
			return ImageIO.read(Loader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
