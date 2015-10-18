package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import player.PlayerAttributes;

public class Wizard {

	ArrayList<String> responses = new ArrayList<String>();
	//HashMap<String, Integer> responses = new HashMap<String, Integer>();

	public Wizard() {

	}

	public ArrayList<Item> startWizard(PlayerAttributes attributes) {
		ArrayList<Item> ret = new ArrayList<Item>();
		Scanner scan = new Scanner(System.in);
		int x = 7;
		while(x < 8) {
			System.out.println("I am the Wizard! What can I do for you?");
			String str = scan.nextLine();
			switch(str) {
			case "gimme a sword": {
				if(attributes.getItems().contains(new Item("wood", null))) {
					System.out.println("Take this sword");
					try {
						ret.add(new Item("Sword", ImageIO.read(this.getClass().getResourceAsStream("/assets/Sword-Knife.png"))));
					} catch (IOException e) { e.printStackTrace(); }
				}
				break;
			}
			case "gimme a good sword": {
				try {
					ret.add(new Item("Good Sword", ImageIO.read(this.getClass().getResourceAsStream("/assets/Sword-Knife.png"))));
				} catch (IOException e) { e.printStackTrace(); }
				break;
			}
			case "I'm done": {
				scan.close();
				System.out.println("Thank you.");
				return ret;
			}
			}
		}
		scan.close();
		return null;
	}

}
