package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.Engine;

public class PlayerMovement implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			if(Engine.map[Engine.characterLocation.x][Engine.characterLocation.y - 1].isPassable()) {
				
			}
			
		} else if(e.getKeyCode() == KeyEvent.VK_A) {
			
			
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			
			
		} else if(e.getKeyCode() == KeyEvent.VK_D) {
			
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

}
