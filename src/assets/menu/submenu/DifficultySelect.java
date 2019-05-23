package assets.menu.submenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import assets.window.Game;

public class DifficultySelect {
	
	public static int WIDTH = Game.WIDTH;
	public static int HEIGHT = Game.HEIGHT;
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setFont(new Font("arial", 1, WIDTH/25));
		
		g.setColor(Color.blue);
		g.fillRect(0, 0, WIDTH * 2, HEIGHT);
		
		//actual difficulty selection area
		g.setColor(Color.black);
		g.drawRect(WIDTH/3, HEIGHT/12, WIDTH/3, HEIGHT/6);
		g.drawString("shoot game", WIDTH/2, HEIGHT/6);
		g.drawRect(WIDTH/3, HEIGHT * 5/12, WIDTH/3, HEIGHT/6);
		g.drawString("aim training", WIDTH/2, HEIGHT/2);
		
		g.drawRect(0, 0, WIDTH/6, HEIGHT/12);
		g.drawString("return to menu", WIDTH/12, HEIGHT/24);
		//actual difficulty selection area
	}

}
