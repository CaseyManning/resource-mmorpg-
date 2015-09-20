/**
 * Engine.java: the game's engine
 */

// Test

package engine;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import player.PlayerMovement;


public class Engine extends JPanel {

	// The grass tile
	Tile grass;
	// The tree tile
	Tile tree;
	
	BufferedImage playerImg;
	
	// The map
	public static String[] map;
	
	// public HashMap<Player, Point> players;
	
	public static Player[] otherPlayers;
	public static Player userPlayer;
	
	public static PlayerMovement movement;
	
	static HashMap<Character, Tile> tileChars;
	
	// we are not actually going to want a main in this class
	public static void main(String[] args) {
		movement = new PlayerMovement();
		otherPlayers = new Player[1];
		userPlayer = new Player(new Point(0, 0));
		
		Engine engine = new Engine();
		
		JFrame frame = new JFrame();
		frame.setTitle("Resource MMORPG");
		frame.setSize(1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(engine);
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }
			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				engine.paint(engine.getGraphics());
			}
		});
		frame.addKeyListener(new PlayerMovement());
		frame.setVisible(true);
	}
	
	public Engine() {
		tileChars = new HashMap<Character, Tile>();
		
		playerImg = null;
		try {
			playerImg = ImageIO.read(this.getClass().getResourceAsStream("/assets/player.png"));
		} catch (IOException e) {
			
		}
		
		BufferedImage gImg = null;
		try {
			gImg = ImageIO.read(this.getClass().getResourceAsStream("/assets/grass.png"));
		} catch (IOException e) {
			
		}
		grass = new Tile(gImg, true);
		
		BufferedImage tImg = null;
		try {
			tImg = ImageIO.read(this.getClass().getResourceAsStream("/assets/tree.png"));
		} catch (IOException e) {
			
		}
		tree = new Tile(tImg, false);
		
		tileChars.put('#', tree);
		tileChars.put('.', grass);
		
		map = mapFromFile(this.getClass().getResourceAsStream("/engine/testMap.txt"), Charset.defaultCharset());
	}
	
	String[] mapFromFile(InputStream is, Charset encoding) {
		try {
			byte[] encoded = new byte[1024];
			is.read(encoded);
			is.close();
			String contents = new String(encoded, encoding);
			return contents.split("\\n");
		} catch (IOException e) {
			return null;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		BufferedImage[][] imgs = new BufferedImage[25][33];
		
		for(int row=0; row < 25; row++) {
			for(int col=0; col < 33; col++) {
				try {
					imgs[row][col] = tileChars.get(map[row-12-userPlayer.GetPos().y].charAt(col-16-userPlayer.GetPos().x)).GetImg();
				} catch(Exception e) {
					imgs[row][col] = tileChars.get('#').GetImg();
				}
			}
		}
		
		for(int row=0; row < 25; row++) {
			for(int col=0; col < 33; col++) {
				g.drawImage(imgs[row][col], 32*col-16, 32*row-16, null);
			}
		}
		
		g.drawImage(playerImg, 16*32-16, 12*32-16, null);
		
		System.out.println(userPlayer.GetPos());
	}
	
	public static Tile GetTile(int x, int y) {
		try {
			return tileChars.get(map[y].charAt(x));
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Tile GetTile(Point p) {
		return GetTile(p.x, p.y);
	}

}
