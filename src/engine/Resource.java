package engine;

import java.awt.image.BufferedImage;

public class Resource {

	String name;
	BufferedImage img;
	int waitTime;
	int waitTimeLeft;
	int amount;
	Item item;
	
	public Resource(String name, BufferedImage img, int waitTime, int amount, Item item) {
		this.name = name;
		this.img = img;
		this.waitTime = waitTime;
		this.amount = amount;
		this.item = item;
		this.waitTimeLeft = 0;
	}
	
	public Item grabItem() {
		if (waitTimeLeft == 0) {
			amount -= 1;
			waitTimeLeft = waitTime;
			return item;
		}
		return null;
	}
	
	public void update(int elapsed) {
		waitTimeLeft -= elapsed;
		if (waitTime < 0) {
			waitTimeLeft = 0;
		}
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public String getName() {
		return name;
	}

}
