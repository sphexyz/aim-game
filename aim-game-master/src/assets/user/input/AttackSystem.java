package assets.user.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import assets.ai.handling.GameObject;
import assets.ai.handling.Handler;
import assets.ai.handling.ObjectID;
import assets.menu.MenuInput;
import assets.user.Bullet;
import assets.window.Game;
import assets.window.Game.GAMEMODE;
import assets.window.Game.STATE;

public class AttackSystem extends MouseAdapter{
	
	Handler handler;
	
	public AttackSystem(Handler handler) {
		this.handler = handler;
	}
	
	public void mouseReleased(MouseEvent e){
		if(Game.gs == STATE.Game && !MenuInput.update) {
			if(Game.gm == GAMEMODE.Shooter) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					for(int i = 0; i < handler.object.size(); i++) {
						GameObject tempObject = handler.object.get(i);
						
						if(tempObject.getId() == ObjectID.BlockX) {
							handler.addObject(new Bullet(tempObject.getX() + 12, tempObject.getY() - 24, 1, handler, ObjectID.Bullet));
						}
					}
				}
				if(e.getButton() == MouseEvent.BUTTON3) {
					for(int i = 0; i < handler.object.size(); i++) {
						GameObject tempObject = handler.object.get(i);
						
						if(tempObject.getId() == ObjectID.BlockY) {
							handler.addObject(new Bullet(tempObject.getX() + 32, tempObject.getY() + 12, 0, handler, ObjectID.Bullet));
						}
					}
				}
			}
		}
	}
	
	
	
}
