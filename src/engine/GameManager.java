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
	
	public static final int TILESIZE = 8;
	
	public GameManager() {
		player = new Player(new Vec2(0, 0));
		HashMap<Character, Tile> tileChars = new HashMap<Character, Tile>();
		try {
			tileChars.put('#', new Tile(ImageIO.read(this.getClass().getResourceAsStream("/assets/tree.png")), false));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			tileChars.put('.', new Tile(ImageIO.read(this.getClass().getResourceAsStream("/assets/grass.png")), false));
		} catch (IOException e) {
			e.printStackTrace();
		}
		map = new Tilemap(tileChars, this.getClass().getResourceAsStream("testMap.txt"), Charset.forName("UTF8"));
	}
	
	public void draw(Graphics2D g) {
		//map.draw(g, player.getPos());
		player.draw(g);
	}
	
	public void update() {
		player.update();
	}

}
