package engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import player.Player;

public class GameAssistant implements KeyListener {

	Player player;
	Tilemap map;
	Tile tree;
	Tile grass;
	Tile water;
	Tile cliffTop;
	Resource woodR;
	Item woodI;
	Wizard w;
	WizardSprite wizard;
	
	HashMap<Character, Tile> tileChars = new HashMap<Character, Tile>();
	
	public static final int TILESIZE = 16;
	
	public GameAssistant(Wizard w) {
		wizard = new WizardSprite(700, 50);
		player = new Player(0, 0);
		this.w = w;
		
		
		tree = addTile(tree, "/assets/tree2.png", false, '#');
		water = addTile(water, "/assets/water2.png", false, '~');
		grass = addTile(grass, "/assets/grass2.png", true, '.');
		cliffTop = addTile(cliffTop, "/assets/cliff-top.png", false, '|');
		
		woodI = null;
		try {
			woodI = new Item("wood", ImageIO.read(this.getClass().getResourceAsStream("/assets/wood.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		woodR = null;
		try {
			woodR = new Resource("Wood", ImageIO.read(this.getClass().getResourceAsStream("/assets/wood.png")), 500, 5, woodI, new Vec2(0, 0));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		w = new Wizard();
		
		map = new Tilemap(tileChars, this.getClass().getResourceAsStream("testMap.txt"), Charset.forName("UTF8"));
		
	}
	
	public void draw(Graphics2D g) {
		map.draw(g, player.getX(), player.getY(), tree);
		// System.out.println("Relative Rectangle Drawn");
		// g.drawRect(GamePanel.WIDTH/2-player.getPos().x*TILESIZE-TILESIZE/2, GamePanel.HEIGHT/2-player.getPos().y*TILESIZE-TILESIZE/2, TILESIZE, TILESIZE);

		g.setColor(Color.white);
		g.fillRect(0, GamePanel.HEIGHT, GamePanel.WIDTH, GamePanel.HEIGHT2-GamePanel.HEIGHT);
		g.drawImage(wizard.image, wizard.getX(), wizard.getY(), null);
		player.draw(g);
	}
	

	public void update(int elapsed) {
		map.update(elapsed);
	}
	
	public Tile addTile(Tile t, String img, boolean passable, char c) {
		try {
			t = new Tile(ImageIO.read(this.getClass().getResourceAsStream(img)), passable);
		} catch (IOException e) {
			e.printStackTrace();
		}
		tileChars.put(c, t);
		return t;
	}

	@Override
	public void keyTyped(KeyEvent e) {  }

	@Override
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}

}
