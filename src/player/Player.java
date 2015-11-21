package player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.GamePanel;
import engine.Item;
import engine.Sprite;
import engine.WizardSprite;

public class Player extends Sprite implements KeyListener {

	public PlayerAttributes attributes;

	private int dx;
	private int dy;
	private int speed;
	
	public boolean grabbing;

	public Player(int x, int y) {
		super(x, y);
		attributes = new PlayerAttributes();
		grabbing = false;
		speed = 4;
		dx = 0;
		dy = 0;
		initPlayer();
	}
	
	private void initPlayer() {
		loadImage("src/assets/player.png");
		getImageDimensions();
	}
	
	public void move() {
		x += dx;
		y += dy;
	}

	public void draw(Graphics2D g) {
		move();
		g.drawImage(getImage(), getX(), getY(), null);

		
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(8.0f));
		g.drawString("Health = " + Integer.toString(attributes.getHealth()), 2, 10);
		//add(l);
		int i=0;
		for(Item t : attributes.getItems()) {
			t.draw(g, i*20 + 10, GamePanel.HEIGHT);
			i++;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("[[KEYPRESS]]");
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) {
			loadImage("src/assets/playerup.png");
			getImageDimensions();
			dy = -speed;
		}
		if(key == KeyEvent.VK_A) {
			loadImage("src/assets/playerleft.png");
			getImageDimensions();
			dx = -speed;
		}
		if(key == KeyEvent.VK_S) {
			loadImage("src/assets/playerdown.png");
			getImageDimensions();
			dy = speed;
		}
		if(key == KeyEvent.VK_D) {
			loadImage("src/assets/playerright.png");
			getImageDimensions();
			dx = speed;
		}
		if(key == KeyEvent.VK_Q) {
			grabbing = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("[[KEYRELEASE]]");
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) {
			dy = 0;
		}
		if(key == KeyEvent.VK_A) {
			dx = 0;
		}
		if(key == KeyEvent.VK_S) {
			dy = 0;
		}
		if(key == KeyEvent.VK_D) {
			dx = 0;
		}
		if(key == KeyEvent.VK_Q) {
			grabbing = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {  }
	
	
	public void addItem(Item i) {
		this.attributes.addItem(i);
	}
}

