package engine;

import java.awt.Graphics2D;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import javax.imageio.ImageIO;

import player.Player;

public class GameManager {

	Player player;
	Tilemap map;
	Tile tree;
	Tile grass;
	Resource woodR;
	Item woodI;
	
	public static final int TILESIZE = 8;
	
	public GameManager() {
		player = new Player(new Vec2(0, 0));
		HashMap<Character, Tile> tileChars = new HashMap<Character, Tile>();
		tree = null;
		try {
			tree = new Tile(ImageIO.read(this.getClass().getResourceAsStream("/assets/tree.png")), false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		tileChars.put('#', tree);
		grass = null;
		try {
			grass = new Tile(ImageIO.read(this.getClass().getResourceAsStream("/assets/grass.png")), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		tileChars.put('.', grass);

		woodI = null;
		try {
			woodI = new Item("Wood", ImageIO.read(this.getClass().getResourceAsStream("/assets/wood.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		woodR = null;
		try {
			woodR = new Resource("Wood", ImageIO.read(this.getClass().getResourceAsStream("/assets/wood.png")), 1000, 5, woodI);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		HashMap<Vec2, Resource> resources = new HashMap<>();
		resources.put(new Vec2(1, 1), woodR);
		
		map = new Tilemap(tileChars, this.getClass().getResourceAsStream("testMap.txt"), Charset.forName("UTF8"), resources);
	}
	
	public void draw(Graphics2D g) {
		map.draw(g, player.getPos(), tree);
		// System.out.println("Relative Rectangle Drawn");
		// g.drawRect(GamePanel.WIDTH/2-player.getPos().x*TILESIZE-TILESIZE/2, GamePanel.HEIGHT/2-player.getPos().y*TILESIZE-TILESIZE/2, TILESIZE, TILESIZE);
		
		player.draw(g);
		
	}
	
	public void update(int elapsed) {
		System.out.println("UPDATE");
		Vec2 current = player.getPos();
		
		boolean abovePassable = false;
		try {
			abovePassable = map.getTile(current.x, current.y-1).isPassable();
		} catch(Exception e) {  }
		if (map.resourceAt(current.x, current.y-1) != null) {
			abovePassable=false;
		}
		
		boolean belowPassable = false;
		try {
			belowPassable = map.getTile(current.x, current.y+1).isPassable();
		} catch(Exception e) {  }
		if (map.resourceAt(current.x, current.y+1) != null) {
			belowPassable=false;
		}

		boolean leftPassable = false;
		try {
			leftPassable = map.getTile(current.x-1, current.y).isPassable();
		} catch(Exception e) {  }
		if (map.resourceAt(current.x-1, current.y) != null) {
			leftPassable=false;
		}

		boolean rightPassable = false;
		try {
			rightPassable = map.getTile(current.x+1, current.y).isPassable();
		} catch(Exception e) {  }
		if (map.resourceAt(current.x+1, current.y) != null) {
			rightPassable=false;
		}
		
		
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

}
