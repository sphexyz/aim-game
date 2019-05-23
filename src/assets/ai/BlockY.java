package assets.ai;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import assets.ai.handling.GameObject;
import assets.ai.handling.ObjectID;
import assets.detail.image.Texture;
import assets.window.Game;
import assets.window.Game.GAMEMODE;

public class BlockY extends GameObject {
	
	private int width = 32;
	private int height = width;
	
	Texture tex = Game.getInstance();

	public BlockY(int x, int y, ObjectID id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {
		y = Game.my;
		
		if(y >= Game.HEIGHT - 72)
			y = Game.HEIGHT - 72;
		if(y <= 48)
			y = 48;
	}

	public void render(Graphics g) {
		if(Game.gm == GAMEMODE.Shooter || Game.gm == GAMEMODE.ShooterTimed)
			g.drawImage(tex.textures[6], x, y, width, height, null);
	}
	
	public Rectangle getBounds() {
		return null;
	}

}
