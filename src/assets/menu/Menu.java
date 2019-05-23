package assets.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import assets.window.Game;
import assets.window.Game.STATE;

public class Menu{
	
	private int WIDTH = Game.WIDTH;
	private int HEIGHT = Game.HEIGHT;
	
	private int x = 0, y = 0;
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(Game.gs == STATE.Menu) {
			g.setFont(new Font("arial", 1, WIDTH/15));
			
			g.setColor(Color.blue);
			g.fillRect(x, y, WIDTH * 2, HEIGHT);
			
			g.setColor(Color.black);
			g.drawRect(WIDTH/12, HEIGHT/3, WIDTH/3, HEIGHT * 1/3);
			g.drawString("play", WIDTH/4, HEIGHT/2);

			g.drawRect(WIDTH * 7/12, HEIGHT/3, WIDTH/3, HEIGHT * 1/3);
			g.drawString("options", WIDTH * 3/4, HEIGHT/2);

			g.drawRect( WIDTH/3, HEIGHT * 3/4, WIDTH/3, HEIGHT/6);
			g.drawString("exit", WIDTH/2, HEIGHT * 5/6);
		}
	}

}
