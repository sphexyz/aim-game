package assets.detail.image;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage grabImage(int x, int y, int w, int h) {
		BufferedImage img = image.getSubimage(x, y, w, h); 
	
		return img;
	}

}
