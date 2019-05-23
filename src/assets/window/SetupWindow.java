package assets.window;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class SetupWindow extends JPanel {
	
	private static final long serialVersionUID = -1L;
	
    @SuppressWarnings("rawtypes")
	private JComboBox selector;
    private JButton play, exit;
    private static JFrame frame;
    private String items[] = new String[24];
    
    public static int width, height;
 
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public SetupWindow() {

        items[0] = "Select a resolution:";
        items[1] = "400x300"; //4:3
		items[2] = "640x480"; //4:3
		items[3] = "800x600"; //4:3
        items[4] = "960x720"; //4:3
		items[5] = "1024x576"; //16:9
		items[6] = "1024x768"; //4:3
		items[7] = "1280x720"; //16:9
		items[8] = "1280x800"; //16:10
		items[9] = "1280x960"; //4:3
        items[10] = "1366x768"; //16:9
        items[11] = "1400x1050"; //4:3
		items[12] = "1440x900"; //16:10
		items[13] = "1440x1080"; //4:3
		items[14] = "1600x900"; //16:9
		items[15] = "1600x1200"; //4:3
		items[16] = "1680x1050"; //16:10
		items[17] = "1920x1080"; //16:9
		items[18] = "1920x1200"; //16:10
        items[19] = "1920x1440"; //4:3
        items[20] = "2048x1536"; //4:3
        items[21] = "2560x1440"; //16:9
		items[22] = "2560x1600"; //16:10
		items[23] = "3840x2160"; //16:9

		selector = new JComboBox(items);
		
		Dimension monitor = Toolkit.getDefaultToolkit().getScreenSize();
		int w = monitor.width;
		int h = monitor.height;
        
		for(int i = 0; i < this.items.length; i++) {
			if(this.items[i].contains(Integer.toString(w))) {
				if(this.items[i].contains(Integer.toString(h))) {
					selector.setSelectedItem(this.items[i]);
				}
			}
		}

		exit = new JButton("Exit");
		exit.setBounds(0, 0, 100, 50);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		play = new JButton("Play");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launch();
			}
		});
		
		add(exit);
		add(selector);
    }
	
	private void launch() {
		String item = (String) selector.getSelectedItem();
		
		if(item != items[0]) {
			if(item.length() % 2 == 1) {
				int beginWidth = 0;
				int endWidth = (item.length() - 1)/2;
				int beginHeight = (item.length() - 1)/2 + 1;
				int endHeight = item.length();
				
				width = Integer.parseInt(item.substring(beginWidth, endWidth));
				height = Integer.parseInt(item.substring(beginHeight, endHeight));
			}else {
				int beginWidth = 0;
				int endWidth = item.length()/2;
				int beginHeight = item.length()/2 + 1;
				int endHeight = item.length();
				
				width = Integer.parseInt(item.substring(beginWidth, endWidth));
				height = Integer.parseInt(item.substring(beginHeight, endHeight));
			}
			new Window(width, height, "AIM", new Game());
			frame.setVisible(false);
			frame.dispose();
		}
	}
	
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public static void createWindow(int w, int h) {
    	
        //Create and set up the window.
        frame = new JFrame("Select Your Resolution");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(w, h));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
 
        //Add contents to the window.
        frame.add(new SetupWindow());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}