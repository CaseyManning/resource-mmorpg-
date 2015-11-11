package engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import player.Player;

public class GameManager {

	Player player;
	Tilemap map;
	Tile tree;
	Tile grass;
	Tile water;
	Tile wizard;
	Tile cliffTop;
	Resource woodR;
	Item woodI;
	Wizard w;
	
	HashMap<Character, Tile> tileChars = new HashMap<Character, Tile>();
	
	public static final int TILESIZE = 16;
	
	public GameManager(Wizard w) {
		player = new Player(new Vec2(0, 0));
		this.w = w;
		
		tree = addTile(tree, "/assets/tree2.png", false, '#');
		water = addTile(water, "/assets/water2.png", false, '~');
		grass = addTile(grass, "/assets/grass2.png", true, '.');
		wizard = addTile(wizard, "/assets/wizard2.png", false, 'W');
		cliffTop = addTile(cliffTop, "/assets/cliff-top.png", false, '|');
		
		woodI = null;
		try {
			woodI = new Item("Wood", ImageIO.read(this.getClass().getResourceAsStream("/assets/wood.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		woodR = null;
		try {
			woodR = new Resource("Wood", ImageIO.read(this.getClass().getResourceAsStream("/assets/wood.png")), 500, 5, woodI);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		w = new Wizard();
		
		map = new Tilemap(tileChars, this.getClass().getResourceAsStream("testMap.txt"), Charset.forName("UTF8"), new HashMap<Vec2, Resource>());
		
		int hashCode = 0;
		
		Random r=new Random();
		for(int i=0; i<5; i++) {
			Vec2 pos = new Vec2(r.nextInt(map.getSize().x), r.nextInt(map.getSize().y));
			while (!(map.resourceAt(pos) == null && map.getTile(pos) == grass)) {
				pos = new Vec2(r.nextInt(map.getSize().x), r.nextInt(map.getSize().y));
			}
			map.addResource(pos, woodR);
			hashCode++;
		}
		
	}
	
	public void draw(Graphics2D g) {
		map.draw(g, player.getPos(), tree);
		// System.out.println("Relative Rectangle Drawn");
		// g.drawRect(GamePanel.WIDTH/2-player.getPos().x*TILESIZE-TILESIZE/2, GamePanel.HEIGHT/2-player.getPos().y*TILESIZE-TILESIZE/2, TILESIZE, TILESIZE);

		g.setColor(Color.white);
		g.fillRect(0, GamePanel.HEIGHT, GamePanel.WIDTH, GamePanel.HEIGHT2-GamePanel.HEIGHT);
		
		player.draw(g);
	}
	
	public void update(int elapsed) {
		map.update(elapsed);
		
		
		
		Vec2 current = player.getPos();
		
		boolean abovePassable = false;
		try {
			abovePassable = map.getTile(current.x, current.y-1).isPassable();
		} catch(Exception e) {  }
		
		boolean belowPassable = false;
		try {
			belowPassable = map.getTile(current.x, current.y+1).isPassable();
		} catch(Exception e) {  }

		boolean leftPassable = false;
		try {
			leftPassable = map.getTile(current.x-1, current.y).isPassable();
		} catch(Exception e) {  }

		boolean rightPassable = false;
		try {
			rightPassable = map.getTile(current.x+1, current.y).isPassable();
		} catch(Exception e) {  }
		
		
		player.update(
			abovePassable,
			belowPassable,
			leftPassable,
			rightPassable,
			map.resourceAt(current.x, current.y-1),
			map.resourceAt(current.x, current.y+1),
			map.resourceAt(current.x-1, current.y),
			map.resourceAt(current.x+1, current.y));
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

}
