package assets.user;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import assets.ai.handling.GameObject;
import assets.ai.handling.Handler;
import assets.ai.handling.ObjectID;
import assets.detail.image.Texture;
import assets.window.Game;

public class Bullet extends GameObject {
	
	private int width, height, direction;
	
	Handler handler;
	Texture tex = Game.getInstance();

	public Bullet(int x, int y, int direction, Handler handler, ObjectID id) {
		super(x, y, id);

		this.direction = direction;
		//0 = right/left
		//1 = up/down
		
		if(direction == 0) {
			width = 24;
			height = 8;
			velX = 11;
		}
		else {
			width = 8;
			height = 24;
			velY = -11;
		}
		
		this.handler = handler;
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		collision(object);
		checkBounds();
	}
	
	private void collision(LinkedList<GameObject> object) {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectID.Enemy || tempObject.getId() == ObjectID.SmallEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					Game.points++;
					handler.removeObject(this);
				}
			}
			if(tempObject.getId() == ObjectID.FastEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					Game.points+= tempObject.getVelX() + tempObject.getVelY();
					handler.removeObject(this);
				}
			}
			if(tempObject.getId() == ObjectID.Friend) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					Game.points--;
					handler.removeObject(this);
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(direction == 1)
			g.drawImage(tex.textures[7], x, y, null);
		else
			g.drawImage(tex.textures[8], x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	
	
	private void checkBounds() {
		if(x > Game.WIDTH || x - width < 0)
			handler.removeObject(this);
		if(y > Game.HEIGHT || y - height < 0) 
			handler.removeObject(this);
	}

}
