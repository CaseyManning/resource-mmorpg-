package engine;

import java.awt.image.BufferedImage;

public class Resource {

	String name;
	BufferedImage img;
	
	public Item(String name, BufferedImage img) {
		this.name = name;
		this.img = img;
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public String getName() {
		return name;
	}

}
