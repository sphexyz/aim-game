package assets.ai.enemy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import assets.ai.handling.GameObject;
import assets.ai.handling.Handler;
import assets.ai.handling.ObjectID;
import assets.detail.image.Texture;
import assets.window.Game;

public class Enemy extends GameObject{
	
	Handler handler;
	
	private Timer timer;
	private int countdown = 5;
	
	private int width = Game.width;
	private int height = width;
	
	Texture tex = Game.getInstance();
	
	public Enemy(int x, int y, Handler handler, ObjectID id) {
		super(x, y, id);
		
		this.handler = handler;
		
		timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run() {
				countdown -= 1;
			}
		}, 1000, 1000);
	}

	public void tick(LinkedList<GameObject> object) {
		if(countdown == 0) {
			Game.points -= 5;
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {
		g.drawImage(tex.textures[0], x, y, width, height, null);
		
		g.setColor(Color.black);
		g.setFont(new Font("aller", 1, width/2));
		g.drawString("" + countdown, x + width/3, y + height * 53/81);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
