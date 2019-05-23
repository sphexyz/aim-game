package assets.window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Window {
	
	//SetUndecorated needs to be called before setVisible and before adding the game
	
	public Window(int w, int h, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setMaximumSize(new Dimension(w, h));
		frame.setMinimumSize(new Dimension(w, h));
		frame.setPreferredSize(new Dimension(w, h));
		
		Dimension monitor = Toolkit.getDefaultToolkit().getScreenSize();
		int ww = monitor.width;
		int hh = monitor.height;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		if(w >= ww && h >= hh) frame.setUndecorated(true);
		ImageIcon image = new ImageIcon("res/target_template.png");
		frame.setIconImage(image.getImage());
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		
		
		game.start();
	}

}
