package assets.ai.enemy.small;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import assets.ai.handling.GameObject;
import assets.ai.handling.ObjectID;
import assets.detail.image.Texture;
import assets.window.Game;

public class Vertical extends GameObject{
	
	Random rand = new Random();
	Texture tex = Game.getInstance();
	
	private int switcher = rand.nextInt(2);
	
	private int speed = 5;
	
	private int width = Game.width * 3/4;
	private int height = width;

	public Vertical(int x, int y, ObjectID id) {
		super(x, y, id);
		
		if(switcher == 0) velY = -speed;
		else velY = speed;
	}

	public void tick(LinkedList<GameObject> object) {
		y += velY;
		
		bounce();
	}

	public void render(Graphics g) {
		g.drawImage(tex.textures[2], x, y, width, height, null);
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
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
