package engine;

import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JFrame;

public class StateMachine {

	private String[] stackArray;
	private int stackSize;
	
	private int topOfStack = -1;
	private Tilemap tilemap;
	private GamePanel game;
	private JFrame frame;

	public StateMachine(int size) {
		stackSize = size;
		stackArray = new String[size];
		Arrays.fill(stackArray, "-1");
		frame = new JFrame();
		game = new GamePanel();
		tilemap = new Tilemap(null, "testMap.txt", 8);
		frame.add(game);
	}

	public void push(String input) {

		if(topOfStack+1 < stackSize) {
			
			topOfStack++;
			
			stackArray[topOfStack] = input;
		} else {
			System.out.println("Stack is full");
		}
		displayTheStack();
		
		System.out.println("PUSH " + input + " Was Added to the Stack\n");
	}
	
	public String pop() {
		
		if(topOfStack >= 0) {
			
			displayTheStack();
			
			System.out.println("POP " + stackArray[topOfStack] + " was Removed From the Stack \n");
			
			return stackArray[topOfStack--];
			
		} else {
			
			displayTheStack();
			
			System.out.println("STACK EMPTY");
			
			return "-1";
		}
	}
	
	public void displayTheStack() {
		
		for(int n = 0; n < 61; n++) System.out.print("-");
		
		System.out.println();
		
		for(int n = 0; n < stackSize; n++) {
			System.out.format("| %2s " + " ", n);
		}
		
		System.out.println("|");
		
		for(int n = 0; n < 61; n++)System.out.print("-");
		
		System.out.println();
		for(int n = 0; n < stackSize; n++) {
			
			if(stackArray[n].equals("-1")) System.out.print("|     ");
			
			else System.out.print(String.format("| %2s " + " ", stackArray[n]));
		}
		
		System.out.println("|");
		
		for(int n = 0; n < 61; n++)System.out.print("-");
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		StateMachine stack = new StateMachine(10);
		
		stack.push("17");
		
		stack.pop();
	}
	
	public Tilemap prepTileMap() {
		HashMap<Character, Tile> tiles = new HashMap<Character, Tile>();
		tiles.put('#', new Tile(null, false));
		Tilemap tileMap = new Tilemap(null, "testMap.txt", 8);
		return tileMap;
	}
	
	public void normalState() {
		
		
	}
	
	public void inventory() {
		
	}
	
	public void wizardState() {
		//Wizard.startWizard(null, 2,2)
	}
	
	public void menu() {
		
	}
	
	public void updateState() {
		//gp.update(gp.g);
	}
}
