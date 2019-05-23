package assets.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.Random;

import assets.ai.Friend;
import assets.ai.enemy.Enemy;
import assets.ai.enemy.ZigZag;
import assets.ai.enemy.small.Strafer;
import assets.ai.enemy.small.Vertical;
import assets.ai.handling.Handler;
import assets.ai.handling.ObjectID;
import assets.detail.audio.AudioHandler;
import assets.detail.image.Texture;
import assets.menu.Menu;
import assets.menu.MenuInput;
import assets.menu.options.Options;
import assets.menu.submenu.DifficultySelect;
import assets.menu.submenu.LimitSelector;
import assets.user.input.AimAttackSystem;
import assets.user.input.AttackSystem;
import assets.user.input.KeyInput;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	private boolean running = false;
	private Thread thread;
	
	//background/gameplay events
	private Handler handler;
	private AttackSystem attackSystem;
	private AimAttackSystem aimAttackSystem;
	private KeyInput attackSystem2;
	
	Random rand = new Random();
	
	//display guis
	private Menu menu;
	private MenuInput menuInput;
	private Options options;
	private DifficultySelect diff;
	private LimitSelector time;
	public static Texture tex;
	
	public static int WIDTH, HEIGHT;
	public static int mx, my;
	public static int points = 0;
	public static int width = 48;
	
	public static enum STATE {
		Menu(),
		DifficultySelect(),
		TimeSelect(),
		Game(),
		Options(),
		Tips(),
	};
	public static enum GAMEMODE {
		Shooter(),
		Aim(),
	};
	
	public static STATE gs = STATE.Menu;
	public static GAMEMODE gm = GAMEMODE.Shooter; 
	
	public int difficulty = 4;
	//can be 7 max
	//0-3 = slow and relaxing
	//4-5 = speedy area
	//6-7 = NOT recommended, large clusters of objects
	
	public synchronized void start() {
		if(running)
			return;
		
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		//initialise everything
		handler = new Handler();
		tex = new Texture();
		menu = new Menu();
		options = new Options();
		diff = new DifficultySelect();
		time = new LimitSelector();
		
		//input events
		menuInput = new MenuInput(handler);
		aimAttackSystem = new AimAttackSystem(handler);
		attackSystem = new AttackSystem(handler);
		attackSystem2 = new KeyInput(handler);
		this.addMouseListener(menuInput);
		this.addMouseListener(attackSystem);
		this.addMouseListener(aimAttackSystem);
		this.addKeyListener(attackSystem2);
		this.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				int mx = e.getX();
				int my = e.getY();
		
				Game.mx = mx - 16;
				Game.my = my - 16;
			}
			
			public void mouseDragged(MouseEvent e) {}
		});
		
		AudioHandler.load();
	}
	
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
			
			if(gs == STATE.Game) {
				int addTimer = (1050 - (difficulty * 150)) * 3;
				
				if(System.currentTimeMillis() - MenuInput.timer > addTimer) {
					MenuInput.timer += addTimer;
					spawn();
				}
			}
		}
	}
	
	private void tick() {
		if(gs == STATE.Menu) menu.tick();
		if(gs == STATE.DifficultySelect) diff.tick();
		if(gs == STATE.Game) handler.tick();
		if(gs == STATE.Options) options.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		if(gs == STATE.Menu) 
			menu.render(g);
		if(gs == STATE.DifficultySelect)
			diff.render(g);
		if(gs == STATE.TimeSelect)
			time.render(g);
		if(gs == STATE.Game) {
			g.setColor(Color.blue);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			g.setColor(Color.black);
			g.setFont(new Font("arial", 1, 30));
			g.drawString("" + points, WIDTH/2 - 30, 42);
			
			g.drawLine(0, HEIGHT - 40, WIDTH, HEIGHT - 40);
			g.drawLine(0, 48, WIDTH, 48);
			g.drawLine(64, 0, 64, HEIGHT);
			g.drawLine(WIDTH - 48, 0, WIDTH - 48, HEIGHT);
			
			handler.render(g);
		}
		if(gs == STATE.Options)
			options.render(g);
		
		g.dispose();
		bs.show();
	}
	
	private boolean isBetween(int min, int max, int var) {
		if(var >= min && var <= max) {
			return true;
		}
		else return false;
	}
	
	private void spawn() {
		int a = rand.nextInt(100) + 1;
		int posX = rand.nextInt(WIDTH - 80 - (2 * Game.width)) + 64 + 1;
		int posY = rand.nextInt(HEIGHT - 56 - (2 * Game.width)) + 48 + 1;
		if(isBetween(1, 10, a))
			handler.addObject(new Friend(posX, posY, handler, ObjectID.Friend));
		else if (isBetween(11, 84, a))
			handler.addObject(new Enemy(posX, posY, handler, ObjectID.Enemy));
		else if (isBetween(86, 90, a))
			handler.addObject(new ZigZag(posX, posY, ObjectID.Enemy));
		else if (isBetween(91, 95, a))
			handler.addObject(new Strafer(posX, posY, ObjectID.SmallEnemy));
		else if (isBetween(96, 100, a))
			handler.addObject(new Vertical(posX, posY, ObjectID.SmallEnemy));
	}
	
	public static Texture getInstance() {
		return tex;
	}
	
	public static void main(String[] args) {
		SetupWindow.createWindow(300, 225);
		//new Window(1366, 768, "AIM", new Game());
	}

}
