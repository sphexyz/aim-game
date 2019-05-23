package assets.ai.enemy.small;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import assets.ai.handling.GameObject;
import assets.ai.handling.ObjectID;
import assets.detail.image.Texture;
import assets.window.Game;

public class Strafer extends GameObject {
	
	Random rand = new Random();
	Texture tex = Game.getInstance();
	
	private int switcher = rand.nextInt(2);
	
	private int width = Game.width * 3/4;
	private int height = width;
	private int speed = 5;

	public Strafer(int x, int y, ObjectID id) {
		super(x, y, id);

		if(switcher == 0) velX = -speed;
		else velX = speed;
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		
		bounce();
	}

	public void render(Graphics g) {
		g.drawImage(tex.textures[2], x, y, width, height, null);
	}
	
	private void bounce() {
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
