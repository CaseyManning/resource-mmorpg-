package player;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import engine.Item;


public class PlayerAttributes {

	public int health;
	int attack;
	ArrayList<Item> items;
	public Point location;
	
	
	
	public PlayerAttributes() {
		health = 100;
		items = new ArrayList<Item>();
		attack = 1;
		try {
			items.add(new Item("Test",ImageIO.read(this.getClass().getResourceAsStream("/assets/wood.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public int getHealth() {
		return health;
	}

	public int setHealth(int health) {
		this.health = health;
		return health;
	}
	
	public int addHealth(int health) {
		this.health += health;
		return this.health;
	}
	
	public int removeHealth(int health) {
		this.health -= health;
		return this.health;
	}

	public int getAttack() {
		return attack;
	}

	public int setAttack(int attack) {
		this.attack = attack;
		return attack;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public ArrayList<Item> setItems(ArrayList<Item> items) {
		this.items = items;
		return items;
	}
	
	public Item addItem(Item item) {
		items.add(item);
		return item;
	}
}
