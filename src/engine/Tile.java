package engine;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Tile {

	protected Image img;
	protected boolean passable;
	
	
	public Tile(ImageIcon img, boolean passable) {
		this.img = img.getImage();
		this.passable = passable;
	}
	
	public boolean isPassable() {
		return passable;
	}
	
	public Image getImg() {
		return img;
	}
	
	public void draw(Graphics2D g, int x, int y, int tilesize) {
		g.drawImage(img, x*tilesize-tilesize/2, y*tilesize-tilesize/2, null);
	}

}
