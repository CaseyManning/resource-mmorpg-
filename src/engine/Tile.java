package engine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {

	protected BufferedImage img;
	protected boolean passable;
	
	
	public Tile(BufferedImage img, boolean passable) {
		this.img = img;
		this.passable = passable;
	}
	
	public boolean isPassable() {
		return passable;
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public void draw(Graphics2D g, int x, int y) {
		g.drawImage(img, x*GameManager.TILESIZE-GameManager.TILESIZE/2, y*GameManager.TILESIZE-GameManager.TILESIZE/2, null);
	}

}
