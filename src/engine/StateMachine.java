package engine;

import java.util.Arrays;

public class StateMachine {

	private String[] stackArray;
	private int stackSize;
	
	private int topOfStack = -1;

	public StateMachine(int size) {

		stackSize = size;

		stackArray = new String[size];
		Arrays.fill(stackArray, "-1");
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
		
		for(int n = 0; n < stackSize; n++) {
			
			if(stackArray[n].equals("-1")) System.out.print("|	");
			
			else System.out.println(String.format("| %2s " + " ", stackArray[n]));
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
	
	public void normalState() {
		
	}
	
	public void inventory() {
		
	}
	
	public void talking() {
		
	}
	public void menu() {
		
	}
	
	
}
