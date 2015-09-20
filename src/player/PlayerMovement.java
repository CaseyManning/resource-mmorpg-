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

		Point pos = Engine.userPlayer.GetPos();
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			Tile t = Engine.GetTile(pos.x, pos.y-1);
			if(t != null && t.IsPassable()) {
				pos.y--;
			}
			break;
			
		case KeyEvent.VK_A:
			t = Engine.GetTile(pos.x-1, pos.y);
			if(t != null && t.IsPassable()) {
				pos.x--;
			}
			break;
		
		case KeyEvent.VK_S:
			t = Engine.GetTile(pos.x, pos.y-1);
			if(t != null && t.IsPassable()) {
				pos.y++;
			}
			break;
			
		case KeyEvent.VK_D:
			t = Engine.GetTile(pos.x-1, pos.y);
			if(t != null && t.IsPassable()) {
				pos.x++;
			}
			break;
		
		default:
			break;
		}
		
		Engine.userPlayer.SetPos(pos);
		System.out.println(pos);
		System.out.println(Engine.userPlayer.GetPos());
		System.out.println();

	}

	@Override
	public void keyReleased(KeyEvent e) {


	}

	@Override
	public void keyTyped(KeyEvent e) {


	}

}
