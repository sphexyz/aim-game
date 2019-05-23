package assets.user.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import assets.ai.handling.GameObject;
import assets.ai.handling.Handler;
import assets.ai.handling.ObjectID;
import assets.window.Game;
import assets.window.Game.GAMEMODE;

public class AimAttackSystem extends MouseAdapter{
	
	Handler handler;
	
	public AimAttackSystem(Handler handler) {
		this.handler = handler;
	}

	public void mouseReleased(MouseEvent e) {
		if(Game.gm == GAMEMODE.Aim) {
			int mx = e.getX();
			int my = e.getY();
			
			for(int i = handler.object.size() - 1; i > -1; i--) {
				System.out.println(i);
				GameObject tempObject = handler.object.get(i);
				int tempX = tempObject.getX();
				int tempY = tempObject.getY();
				
				if(tempObject.getId() == ObjectID.Enemy) {
					if(mouseOver(mx, my, tempX, tempY, Game.width, Game.width)) {
						Game.points++;
						handler.removeObject(tempObject);
					}
				}
				if(tempObject.getId() == ObjectID.FastEnemy) {
					if(mouseOver(mx, my, tempX, tempY, Game.width, Game.width)){
						handler.removeObject(tempObject);
						Game.points+= tempObject.getVelX() + tempObject.getVelY();
					}
				}
				if(tempObject.getId() == ObjectID.Friend) {
					if(mouseOver(mx, my, tempX, tempY, Game.width, Game.width)) {
						Game.points--;
						handler.removeObject(tempObject);
					}
				}
				if(tempObject.getId() == ObjectID.SmallEnemy) {
					if(mouseOver(mx, my, tempX, tempY, Game.width * 3/4, Game.width * 3/4)) {
						Game.points++;
						handler.removeObject(tempObject);
					}
				}
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
