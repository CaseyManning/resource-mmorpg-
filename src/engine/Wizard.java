package engine;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import player.PlayerAttributes;

public class Wizard {

	HashMap<String, Integer> responds = new HashMap<String, Integer>();
	
	public Wizard() {
		
	}
	
	public Item startWizard() {
		System.out.println("I am the Wizard! What do you want me to do?");
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		
		return null;
	}
}
