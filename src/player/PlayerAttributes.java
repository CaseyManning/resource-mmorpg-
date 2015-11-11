package player;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
			items.add(new Item("wood",ImageIO.read(this.getClass().getResourceAsStream("/assets/wood.png"))));
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

	public Item getItemWithName(String name) {
		for(Item i : items) {
			if(i.getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return null;
	}
	public void removeItem(Item i) {
		items.remove(items.indexOf(i));
	}
	
	public String toString() {
		String s = "";
		for(Item i : items) {
			s = s + "|" + i.toString();
		}
		return s;
	}
	
	public ArrayList<String> asList() {
		ArrayList<String> ret = new ArrayList<String>();
		for(Item i : items) {
			ret.add(i.toString());
		}
		return ret;
		
	}
	
	public PlayerAttributes(String s) {
		StringTokenizer st = new StringTokenizer(s, "|");

		while(st.hasMoreTokens()) {
			String nt = st.nextToken();
			try {
				Item i = new Item(nt, ImageIO.read(this.getClass().getResourceAsStream("/assets/" + nt + ".png")));
				addItem(i);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
