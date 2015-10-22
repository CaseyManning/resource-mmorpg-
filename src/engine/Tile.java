package engine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {

	protected BufferedImage img;
	protected boolean passable;
	public int tileWidth;
	public int tileHeight;
	
	
	public Tile(BufferedImage img, boolean passable) {
		this.img = img;
		this.passable = passable;
		tileWidth = img.getWidth();
		tileHeight = img.getHeight();
	}
	
	public boolean isPassable() {
		return passable;
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public void draw(Graphics2D g, int x, int y) {
		g.drawImage(img, x*tileWidth-tileWidth/2, y*tileHeight-tileHeight/2, null);
	}

}
