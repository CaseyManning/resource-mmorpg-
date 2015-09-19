/**
 * Engine.java: the game's engine
 */

// Test

package engine;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
	public static Tile[][] map;
	
	public HashMap<Player, Point> players = new HashMap<Player, Point>();
	
	public static Point characterLocation;
	
	
	//we are not actually going to want a main in this class
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Resource MMORPG");
		frame.setSize(1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Engine());
		frame.setVisible(true);
	}
	
	public Engine() {
		BufferedImage gImg = null;
		try {
			gImg = ImageIO.read(new File("/assets/grass.png"));
		} catch (IOException e) {
			
		}
		grass = new Tile(gImg, true);
		System.out.println(grass.GetImg());
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		System.out.println(g.drawImage(this.grass.GetImg(), 0, 0, null));
	}

}
