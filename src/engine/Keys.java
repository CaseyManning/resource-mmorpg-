package engine;

import java.awt.event.KeyEvent;

public class Keys {


	public static final int UP    = 0;
	public static final int LEFT  = 1;
	public static final int DOWN  = 2;
	public static final int RIGHT = 3;

	public static final int PICKUP = 4;
	public static final int WIZARD = 5;

	
	public static final int NUM_KEYS = 6;
	
	public static boolean keyState[]     = new boolean[NUM_KEYS];
	public static boolean prevKeyState[] = new boolean[NUM_KEYS];
	
	public static void keySet(int key, boolean b) {
		System.out.println("keySet(" + Integer.toString(key) + ", " + ((b)?"true":"false") + ")");
		switch(key) {

		case KeyEvent.VK_W: keyState[UP]     = b; break;
		case KeyEvent.VK_A: keyState[LEFT]   = b; break;
		case KeyEvent.VK_S: keyState[DOWN]   = b; break;
		case KeyEvent.VK_D: keyState[RIGHT]  = b; break;
		case KeyEvent.VK_Q: keyState[PICKUP] = b; break;
		case KeyEvent.VK_E: keyState[WIZARD] = b; break;

		default: break;
		}
	}
	
	public static void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}
	
	public static boolean isPressed(int i) {
		return keyState[i] && !prevKeyState[i];
	}
	
	public static boolean isDown(int i) {
		return keyState[i];
	}
	
	public static boolean anyKeyDown() {
		for(int i = 0; i < NUM_KEYS; i++) {
			if(keyState[i]) return true;
		}
		return false;
	}
	
	public static boolean anyKeyPress() {
		for(int i = 0; i < NUM_KEYS; i++) {
			if(keyState[i] && !prevKeyState[i]) return true;
		}
		return false;
	}

}
