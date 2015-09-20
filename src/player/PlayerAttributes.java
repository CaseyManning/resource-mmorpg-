package player;

import java.awt.Point;
import java.util.ArrayList;

public class PlayerAttributes {

	public int health;
	int attack;
	ArrayList<Item> items;
	public Point location;
	
	public PlayerAttributes() {
		health = 10;
		items = new ArrayList<Item>();
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void addHealth(int health) {
		this.health += health;
	}
	
	public void removeHealth(int health) {
		this.health -= health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
}
