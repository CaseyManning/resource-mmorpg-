package engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

import player.PlayerAttributes;

public class Wizard {

	ArrayList<String> responses = new ArrayList<String>();
	//HashMap<String, Integer> responses = new HashMap<String, Integer>();

	public Wizard() {

	}

	public ArrayList<Item> startWizard() {
		ArrayList<Item> ret = new ArrayList<Item>();
		Scanner scan = new Scanner(System.in);
		int x = 7;
		while(x < 8) {
			System.out.println("I am the Wizard! What can I do for you?");
			if(scan.hasNext()){
				String str = scan.nextLine();
				switch(str) {
				case "gimme a sword": {
					try {
						ret.add(new Item("Sword", ImageIO.read(this.getClass().getResourceAsStream("/assets/Sword-Knife.png"))));
					} catch (IOException e) { e.printStackTrace(); }

				}
				case "gimme a good sword": {
					try {
						ret.add(new Item("Good Sword", ImageIO.read(this.getClass().getResourceAsStream("/assets/tree.png"))));
					} catch (IOException e) { e.printStackTrace(); }
				}
				case "I'm done": {
					scan.close();
					return ret;
				}
				}
			}
		}
		scan.close();
		return null;
	}

}
