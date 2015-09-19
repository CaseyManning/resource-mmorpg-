package player;

import java.util.ArrayList;

import assets.Item;

public class PlayerAttributes {

	int health = 10;
	int attack;
	ArrayList<Item> items = new ArrayList<Item>();
	
	public PlayerAttributes() {
		
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
