package engine;

import java.awt.Graphics2D;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import player.Player;

public class GameManager {

	Player player;
	Tilemap map;
	Tile tree;
	Tile grass;
	Resource woodR;
	Item woodI;
	Wizard w;
	
	public static final int TILESIZE = 8;
	
	public GameManager() {
		w = new Wizard();
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
			woodR = new Resource("Wood", ImageIO.read(this.getClass().getResourceAsStream("/assets/wood.png")), 500, 5, woodI);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		map = new Tilemap(tileChars, this.getClass().getResourceAsStream("testMap.txt"), Charset.forName("UTF8"), new HashMap<Vec2, Resource>());
		
		Random r=new Random();
		for(int i=0; i<5; i++) {
			Vec2 pos = new Vec2(r.nextInt(map.getSize().x), r.nextInt(map.getSize().y));
			while (!(map.resourceAt(pos) == null && map.getTile(pos) == grass)) {
				pos = new Vec2(r.nextInt(map.getSize().x), r.nextInt(map.getSize().y));
			}
			map.addResource(pos, woodR);
		}
		
	}
	
	public void draw(Graphics2D g) {
		map.draw(g, player.getPos(), tree);
		// System.out.println("Relative Rectangle Drawn");
		// g.drawRect(GamePanel.WIDTH/2-player.getPos().x*TILESIZE-TILESIZE/2, GamePanel.HEIGHT/2-player.getPos().y*TILESIZE-TILESIZE/2, TILESIZE, TILESIZE);
		
		player.draw(g);
	}
	
	public void update(int elapsed) {
		map.update(elapsed);
		if (Keys.isDown(Keys.WIZARD)) {
			Object[] items = w.startWizard().toArray();
			for (Object item : items) {
				player.addItem((Item) item);
			}
		}
		
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

}
