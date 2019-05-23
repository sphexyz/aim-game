package assets.menu.options;

import java.awt.Color;
import java.awt.Graphics;

import assets.detail.image.Texture;
import assets.window.Game;

public class Options {
	
	static Texture tex = Game.getInstance();
	
	public static int WIDTH = Game.WIDTH;
	public int HEIGHT = Game.HEIGHT;
	
	public static int width = tex.textures[10].getWidth();
	public static int height = width/2;
	public static float scale = WIDTH/9;
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//button x always on grid ref
		g.drawImage(tex.textures[10], WIDTH/9, HEIGHT/6, (int)(scale), (int)(scale/2), null);
		g.drawImage(tex.textures[10], WIDTH/9, HEIGHT/3, (int)(scale), (int)(scale/2), null);
		g.drawImage(tex.textures[10], WIDTH/9, HEIGHT/2, (int)(scale), (int)(scale/2), null);
		g.drawImage(tex.textures[10], WIDTH/9, HEIGHT * 2/3, (int)(scale), (int)(scale/2), null);
		g.drawImage(tex.textures[10], WIDTH/9, HEIGHT * 5/6, (int)(scale), (int)(scale/2), null);
		
		//button position reference
		g.setColor(Color.black);
		g.drawLine(WIDTH/9, 0, WIDTH/9, HEIGHT);
		g.drawLine(WIDTH * 2/9, 0, WIDTH * 2/9, HEIGHT);
	}

}
