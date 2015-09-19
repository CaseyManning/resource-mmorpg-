package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.Engine;

public class PlayerMovement implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {

		if(e.getKeyCode() == KeyEvent.VK_W) {
			if(Engine.map[Engine.characterLocation.x][Engine.characterLocation.y - 1].IsPassable()) {

			}

		} else if(e.getKeyCode() == KeyEvent.VK_A) {
			if(Engine.map[Engine.characterLocation.x - 1][Engine.characterLocation.y].IsPassable()) {

			}

		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			if(Engine.map[Engine.characterLocation.x][Engine.characterLocation.y + 1].IsPassable()) {

			}

		} else if(e.getKeyCode() == KeyEvent.VK_D) {
			if(Engine.map[Engine.characterLocation.x + 1][Engine.characterLocation.y].IsPassable()) {

			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {


	}

	@Override
	public void keyTyped(KeyEvent e) {


	}

}
