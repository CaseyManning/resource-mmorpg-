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
		map = new Tilemap(tileChars, this.getClass().getResourceAsStream("testMap.txt"), Charset.forName("UTF8"));
	}
	
	public void draw(Graphics2D g) {
		map.draw(g, player.getPos(), tree);
		System.out.println("Relative Rectangle Drawn");
		g.drawRect(GamePanel.WIDTH/2-player.getPos().x*TILESIZE-TILESIZE/2, GamePanel.HEIGHT/2-player.getPos().y*TILESIZE-TILESIZE/2, TILESIZE, TILESIZE);
		
		player.draw(g);
	}
	
	public void update() {
		Vec2 current = player.getPos();
		Vec2 above = current;
		above.y--;
		Vec2 below = current;
		below.y++;
		Vec2 left = current;
		left.x--;
		Vec2 right = current;
		right.x++;
		
		Tile aboveT = null;
		try {
			aboveT = map.getTile(above);
		} catch(Exception e) { aboveT = tree; }
		Tile belowT = null;
		try {
			belowT = map.getTile(below);
		} catch(Exception e) { belowT = tree; }
		Tile leftT = null;
		try {
			leftT = map.getTile(left);
		} catch(Exception e) { leftT = tree; }
		Tile rightT = null;
		try {
			rightT = map.getTile(right);
		} catch(Exception e) { rightT = tree; }
		
		player.update(aboveT, belowT, leftT, rightT);
	}

}
