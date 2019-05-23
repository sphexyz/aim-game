package assets.ai.handling;

import java.awt.Graphics;
import java.util.LinkedList;

import assets.ai.BlockX;
import assets.ai.BlockY;
import assets.window.Game;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void createLevel() {
		addObject(new BlockX(64, Game.HEIGHT - 40, ObjectID.BlockX));
		addObject(new BlockY(32, Game.HEIGHT - 72, ObjectID.BlockY));
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}

}
