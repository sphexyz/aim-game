package assets.ai.handling;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	
	protected int x, y;
	protected float velX, velY;
	protected ObjectID id;
	
	public GameObject(int x, int y, ObjectID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public ObjectID getId() {
		return id;
	}

	public void setId(ObjectID id) {
		this.id = id;
	}

}
