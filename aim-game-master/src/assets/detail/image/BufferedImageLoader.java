package assets.detail.image;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	
	private BufferedImage image;
	
	public BufferedImage loadImage(String file) {
		try {
			image = ImageIO.read(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}

}
