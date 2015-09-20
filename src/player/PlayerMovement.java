package player;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.Engine;
import engine.Tile;

public class PlayerMovement implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("keypressed");

		int newX = Engine.userPlayer.GetPos().x;
		int newY = Engine.userPlayer.GetPos().y;
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			Tile t = Engine.GetTile(newX, newY-1);
			if(t != null && t.IsPassable()) {
				newY--;
			}
			break;
			
		case KeyEvent.VK_A:
			t = Engine.GetTile(newX-1, newY);
			if(t != null && t.IsPassable()) {
				newX--;
			}
			break;
		
		case KeyEvent.VK_S:
			t = Engine.GetTile(newX, newY-1);
			if(t != null && t.IsPassable()) {
				newY++;
			}
			break;
			
		case KeyEvent.VK_D:
			t = Engine.GetTile(newX+1, newY);
			if(t != null && t.IsPassable()) {
				newX++;
			}
			break;
		
		default:
			break;
		}
		
		Engine.userPlayer.SetPos(new Point(newX, newY));

	}

	@Override
	public void keyReleased(KeyEvent e) {


	}

	@Override
	public void keyTyped(KeyEvent e) {


	}

}
