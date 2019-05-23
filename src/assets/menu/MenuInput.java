package assets.menu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import assets.ai.handling.Handler;
import assets.menu.submenu.DifficultySelect;
import assets.window.Game;
import assets.window.Game.STATE;

public class MenuInput extends MouseAdapter{
	
	Handler handler;
	DifficultySelect ds = new DifficultySelect();
	
	private int WIDTH = Game.WIDTH;
	private int HEIGHT = Game.HEIGHT;
	
	public static long timer;
	public static boolean update;
	
	public MenuInput(Handler handler) {
		this.handler = handler;
	}
	
	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		update = false;

		if(Game.gs == STATE.DifficultySelect) {
			//SHOOT game
			if(mouseOver(mx, my, WIDTH/3, HEIGHT/12, WIDTH/3, HEIGHT/6)) {
				Game.gs = STATE.Game;
				timer = System.currentTimeMillis();
				handler.createLevel();
				update = true;
			}
			//AIM training
			if(mouseOver(mx, my, WIDTH/3, (HEIGHT * 5/12), WIDTH/3, HEIGHT/6)) {
				Game.gs = STATE.Game;
				timer = System.currentTimeMillis();
			}
			//RETURN to menu
			if(mouseOver(mx, my, 0, 0, WIDTH/6, HEIGHT/12)) {
				Game.gs = STATE.Menu;
				update = true;
			}
		}
		if(Game.gs == STATE.Menu) {
			//PLAY button
			if(mouseOver(mx, my, WIDTH/12, HEIGHT/3, WIDTH/3, HEIGHT/3)) {
				Game.gs = STATE.DifficultySelect;
			}
			//OPTIONS button
			if(mouseOver(mx, my, WIDTH * 7/12, HEIGHT/3, WIDTH * 3/4, HEIGHT/3)) {
				
			}
			//EXIT button
			if(mouseOver(mx, my, WIDTH/3, HEIGHT * 3/4, WIDTH/3, HEIGHT/6)) {
				if(!update)
					System.exit(0);
			}
		}
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int w, int h) {
		if(mx >= x && mx <= x + w) {
			if(my >= y && my <= y + h) {
				return true;
			}else return false;
		}else return false;
	}

}
