package assets.ai;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import assets.ai.handling.GameObject;
import assets.ai.handling.ObjectID;
import assets.detail.image.Texture;
import assets.window.Game;
import assets.window.Game.GAMEMODE;

public class BlockX extends GameObject {
	
	private int width = 32;
	private int height = width;
	
	Texture tex = Game.getInstance();

	public BlockX(int x, int y, ObjectID id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {
		x = Game.mx;
		
		if(x >= Game.WIDTH - 80)
			x = Game.WIDTH - 80;
		if(x <= 64)
			x = 64;
	}

	public void render(Graphics g) {
		if(Game.gm == GAMEMODE.Shooter || Game.gm == GAMEMODE.ShooterTimed)
			g.drawImage(tex.textures[5], x, y, width, height, null);
	}

	public Rectangle getBounds() {
		return null;
	}

}
