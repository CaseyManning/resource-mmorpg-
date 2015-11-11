// The GamePanel is the drawing canvas.
// It contains the game loop which
// keeps the game moving forward.
// This class is also the one that grabs key events.

package engine;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import engine.Keys;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener {

	// dimensions
	// HEIGHT is the playing area size
	// HEIGHT2 includes the bottom window
	public static final int WIDTH = 384;
	public static final int HEIGHT = 256;
	public static final int HEIGHT2 = HEIGHT + 16;
	public static final int SCALE = 2;

	// game loop stuff
	private Thread thread;
	private boolean running;
	private final int FPS = 8;
	private final int TARGET_TIME = 1000 / FPS;

	// drawing stuff
	private BufferedImage image;
	public Graphics2D g;

	//Don't delete my inventory work
	Inventory inv;
	boolean invOpen = false;

	Wizard w;

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
				for(int i = 0; i < 1; i++) {
					gm.update(TARGET_TIME, g);
					gm.draw(g);
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

			update(TARGET_TIME);
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
		w = new Wizard();
		image = new BufferedImage(WIDTH, HEIGHT2, 1);
		g = (Graphics2D) image.getGraphics();
		gm = new GameManager(w);
		inv = new Inventory(gm.player.attributes);
		add(w);
		w.setLocation(100,  1020);
		addMouseListener(this);
	}

	// updates game
	private void update(int elapsed) {
		gm.update(elapsed, g);
		Keys.update();

		if (Keys.isDown(Keys.INVENTORY) && invOpen == false) {
			//add(inv);
			//this.revalidate();
			invOpen = true;
			Keys.keySet(Keys.INVENTORY, false);
		} else if (Keys.isDown(Keys.INVENTORY) && invOpen == true) {
			invOpen = false;
		}

		if (Keys.isDown(Keys.WIZARD)) {

			//w = new Wizard();
			//add(w);

		}

	}





	// draws game
	private void draw() {
		g.setStroke(new BasicStroke(0));
		g.fill(new Rectangle(0, HEIGHT, WIDTH, HEIGHT2 - HEIGHT));
		gm.draw(g);
		w.draw(g);
		//g.setColor(new Color(255, 255, 255));

		if(invOpen) {
			inv.draw(g);
		}
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getX() < 50) {
			System.out.println("Starting wizard");
			ArrayList<String> atboots = gm.player.attributes.asList();
			ArrayList<Item> r = w.startWizard(atboots, e.getX(), e.getY());
			if(r != null) {
				for(Item i : r) {
					gm.player.attributes.addItem(i);
				}
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

//class AttributesPanel extends JPanel {
//	
//	GamePanel gp;
//	JLabel l;
//	public AttributesPanel(GamePanel gp) {
//		super();
//		this.gp = gp;
//		setSize(100, 100);
//		l = new JLabel("Health = " + Integer.toString(gp.pa.getHealth()));
//		l.setFont(l.getFont().deriveFont(18.0f));
//		l.setOpaque(true);
//	}
//
//
//	public void draw(Graphics g) {
//		l.setOpaque(false);
//		l.setText("Health = " + Integer.toString(gp.pa.getHealth()));
//		//add(l);
//		g.setFont(l.getFont().deriveFont(8.0f));
//		g.drawString("Health = " + Integer.toString(gp.pa.getHealth()), 50, 10);
//		for(Item t : gp.pa.getItems()) {
//			t.draw(gp.g, gp.pa.getItems().indexOf(t)*20 + 10, 5);
//		}
//	}
//}
