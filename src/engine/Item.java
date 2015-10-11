package engine;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Item {

	String name;
	BufferedImage icon;
	
	public Item(String name, BufferedImage icon) {
		this.name = name;
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BufferedImage getIcon() {
		return icon;
	}

	public void setIcon(BufferedImage icon) {
		this.icon = icon;
	}
	
	public void draw(Graphics2D g, int x, int y) {
		g.drawImage(icon, x, y, null);
	}
	
	
}
