// The GamePanel is the drawing canvas.
// It contains the game loop which
// keeps the game moving forward.
// This class is also the one that grabs key events.

package engine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Keys;
import player.PlayerAttributes;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	// dimensions
	// HEIGHT is the playing area size
	// HEIGHT2 includes the bottom window
	public static final int WIDTH = 192;
	public static final int HEIGHT = 128;
	public static final int HEIGHT2 = HEIGHT + 16;
	public static final int SCALE = 4;
	
	// game loop stuff
	private Thread thread;
	private boolean running;
	private final int FPS = 30;
	private final int TARGET_TIME = 1000 / FPS;
	
	// drawing stuff
	private BufferedImage image;
	private Graphics2D g;
	
	public static PlayerAttributes pa = new PlayerAttributes();
	AttributesPanel ap;
	
	// game state manager
	private GameManager gm;
	
	// constructor
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT2 * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	// ready to display
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			addKeyListener(this);
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void action() {
		new Thread(new Runnable() {
			public void run() {
				for(int i = 0; i < 3; i++) {
					update();
					draw();
				}
			}
		}).start();
	}
	
	// run new thread
	public void run() {
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			action();
			
			wait = TARGET_TIME - elapsed / 1000000;
			if(wait < 0) wait = TARGET_TIME;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	// initializes fields
	private void init() {
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT2, 1);
		g = (Graphics2D) image.getGraphics();
		gm = new GameManager();
		ap = new AttributesPanel();
		add(ap);
		
	}
	
	// updates game
	private void update() {
		gm.update();
		Keys.update();
	}
	
	// draws game
	private void draw() {
		gm.draw(g);
		//g.setColor(new Color(255, 255, 255));
		g.setStroke(new BasicStroke(0));
		g.fill(new Rectangle(0, HEIGHT, WIDTH, HEIGHT2 - HEIGHT));
		ap.repaint();
		
	}
	
	// copy buffer to screen
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT2 * SCALE, null);
		g2.dispose();
	}
	
	// key event
	public void keyTyped(KeyEvent key) {
		
	}
	
	public void keyPressed(KeyEvent key) {
		Keys.keySet(key.getKeyCode(), true);
	}
	public void keyReleased(KeyEvent key) {
		Keys.keySet(key.getKeyCode(), false);
	}
	
}

class AttributesPanel extends JPanel {
	
	
	JLabel l;
	public AttributesPanel() {
		super();
		setSize(100, 100);
		l = new JLabel("Health = " + Integer.toString(GamePanel.pa.getHealth()));
		l.setFont(l.getFont().deriveFont(18.0f));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.RED);
		g.drawString(Integer.toString(GamePanel.pa.getHealth()), 0, 0);
		l.setFont(l.getFont().deriveFont(18.0f));
		l.setBackground(Color.GREEN);
		add(l);
	}
}
