package assets.ai.enemy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import assets.ai.handling.GameObject;
import assets.ai.handling.ObjectID;
import assets.detail.image.Texture;
import assets.window.Game;

public class ZigZag extends GameObject {
	
	
	private int width = Game.width;
	private int height = width;
	private int speed = 5;

	Random rand = new Random();
	Texture tex = Game.getInstance();
	private int vertical = rand.nextInt(2);
	private int horizontal = rand.nextInt(2);
	
	public ZigZag(int x, int y, ObjectID id) {
		super(x, y, id);
		
		if(vertical == 0) velY = -speed;
		else velY = speed;
		if(horizontal == 0) velX = -speed;
		else velX = speed;
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		bounce();
	}

	public void render(Graphics g) {
		g.drawImage(tex.textures[3], x, y, width, height, null);
	}
	
	private void bounce() {
		if(y + height >= Game.HEIGHT - 40) {
			y = Game.HEIGHT - 40 - height;
			velY = -speed;
		}
		if(y <= 48) {
			y = 48;
			velY = speed;
		}
		if(x + width >= Game.WIDTH - 48) {
			x = Game.WIDTH - 48 - width;
			velX = -speed;
		}
		if(x <= 64) {
			x = 64;
			velX = speed;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
