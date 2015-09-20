/**
 * Engine.java: the game's engine
 */

// Test

package engine;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Engine extends JPanel {

	// The grass tile
	Tile grass;
	// The tree tile
	Tile tree;
	
	// The map
	public static String[] map;
	
	public HashMap<Player, Point> players;
	
	public static Point characterLocation;
	
	HashMap<Character, Tile> tileChars;
	
	// we are not actually going to want a main in this class
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Resource MMORPG");
		frame.setSize(1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Engine());
		frame.setVisible(true);
	}
	
	public Engine() {
		tileChars = new HashMap<Character, Tile>();
		players = new HashMap<Player, Point>();
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
		
		for(int row=0; row < map.length; row++) {
			for(int col=0; col < map[0].length(); col++) {
				g.drawImage(tileChars.get(map[row].charAt(col)).GetImg(), 32*row, 32*col, null);
			}
		}
	}

}
