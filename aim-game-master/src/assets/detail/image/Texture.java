package assets.detail.image;

import java.awt.image.BufferedImage;

public class Texture {
	
	BufferedImageLoader loader;
	
	SpriteSheet sheet1;
	
	public BufferedImage[] textures = new BufferedImage[20];
	
	public Texture() {
		loader = new BufferedImageLoader();
		try {
			sheet1 = new SpriteSheet(loader.loadImage("res/sprite_sheet.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		getTextures();
	}
	
	private void getTextures() {
		//red, green, pink, black, yellow
		textures[0] = sheet1.grabImage(0, 0, 48, 48);
		textures[1] = sheet1.grabImage(48, 0, 48, 48);
		textures[2] = sheet1.grabImage(48, 48, 48, 48);
		textures[3] = sheet1.grabImage(144, 48, 48, 48);
		textures[4] = sheet1.grabImage(96, 48, 48, 48);
		//bottom and side shooter
		textures[5] = sheet1.grabImage(0, 96, 32, 32);
		textures[6] = sheet1.grabImage(32, 96, 32, 32);
		//vertical and side bullet
		textures[7] = sheet1.grabImage(64, 96, 8, 24);
		textures[8] = sheet1.grabImage(72, 96, 24, 8);
		//play button
		textures[9] = sheet1.grabImage(0, 135, 240, 135);
		
		//toggle button off
		textures[10] = sheet1.grabImage(0, 128, 300, 150);
		//toggle button on
		textures[11] = sheet1.grabImage(0, 278, 300, 150);
		//toggle button
		textures[12] = sheet1.grabImage(300, 128, 140, 140);
	}

}
