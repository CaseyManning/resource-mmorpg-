package engine;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;

import player.PlayerAttributes;

public class Wizard {

	ArrayList<String> responses = new ArrayList<String>();
	
	public Wizard() {
		
	}
	
	public Item startWizard() {
		System.out.println("I am the Wizard! What do you want me to do?");
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		if(str.equalsIgnoreCase("gimme a sword")) {
			System.out.println("I am the Wizard! Take this sword!");
			//sword = true;
		}
		return null;
	}
}
