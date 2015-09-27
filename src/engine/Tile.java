package engine;

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

}
