package assets.user.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import assets.ai.handling.GameObject;
import assets.ai.handling.Handler;
import assets.ai.handling.ObjectID;
import assets.user.Bullet;
import assets.window.Game;
import assets.window.Game.STATE;

public class KeyInput extends KeyAdapter {
	
	Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) {
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
					
				if(tempObject.getId() == ObjectID.BlockX) {
					handler.addObject(new Bullet(tempObject.getX() + 12, tempObject.getY() - 24, 1, handler, ObjectID.Bullet));
				}
			}
		}
		if(key == KeyEvent.VK_D) {
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getId() == ObjectID.BlockY) {
					handler.addObject(new Bullet(tempObject.getX() + 32, tempObject.getY() + 12, 0, handler, ObjectID.Bullet));
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
			if(Game.gs == STATE.Game || Game.gs == STATE.DifficultySelect) {
				Game.gs = STATE.Menu;
				Game.points = 0;
				handler.object.clear();
			}
			else if(Game.gs == STATE.Menu)
				System.exit(0);
		}
	}

}
