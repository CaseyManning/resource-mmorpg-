package engine;

import java.awt.image.BufferedImage;

public class Tile {

	protected BufferedImage img;
	protected boolean passable;
	boolean hasPlayer;
	Player player;
	
	
	public Tile(BufferedImage img, boolean passable) {
		this.img = img;
		this.passable = passable;
	}
	
	public boolean IsPassable() {
		return passable;
	}
	
	public BufferedImage GetImg() {
		return img;
	}
	
	public  void setPassable(boolean passable) {
		this.passable = passable;
	}

}
