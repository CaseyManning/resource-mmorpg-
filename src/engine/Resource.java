package engine;

import java.awt.image.BufferedImage;

public class Resource {

	String name;
	BufferedImage img;
	int waitTime;
	int waitTimeLeft;
	int amount;
	Item item;
	Vec2 pos;
	
	public Resource(String name, BufferedImage img, int waitTime, int amount, Item item, Vec2 pos) {
		this.name = name;
		this.img = img;
		this.waitTime = waitTime;
		this.amount = amount;
		this.item = item;
		this.waitTimeLeft = 0;
		this.pos = pos;
	}
	
	public Item grabItem() {
		if (waitTimeLeft == 0) {
			amount -= 1;
			waitTimeLeft = waitTime;
			return item;
		}
		return null;
	}
	
	public boolean shouldDelete() {
		if (amount <= 0) {
			return true;
		}
		return false;
	}
	
	public void update(int elapsed) {
		waitTimeLeft -= elapsed;
		if (waitTimeLeft < 0) {
			waitTimeLeft = 0;
		}
	}
	
	public BufferedImage getImg() {
		return img;
	}
	
	public String getName() {
		return name;
	}
	
	public Vec2 getPos() {
		return pos;
	}
	
	public Vec2 setPos(Vec2 pos) {
		return (this.pos = pos);
	}

}
