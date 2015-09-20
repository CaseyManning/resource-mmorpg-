package engine;

import java.awt.Point;

import player.PlayerAttributes;

public class Player {

	protected Point pos;
	protected PlayerAttributes attributes;
	
	public Player(Point pos) {
		this.pos = pos;
	}
	
	public Point GetPos() {
		return pos;
	}
	
	public Point SetPos(Point p) {
		pos = p;
		return pos;
	}

}
