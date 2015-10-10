package engine;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Item {

	String name;
	BufferedImage icon;
	
	public Item(String name) {
		this.name = name;
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
	
	
}
