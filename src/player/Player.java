package player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.GamePanel;
import engine.Keys;
import engine.Tile;
import engine.Vec2;

public class Player {

	Vec2 pos;
	PlayerAttributes attributes;
	int health;
	BufferedImage img;
	
	public Player(Vec2 pos) {
		this.pos = pos;
		try {
			img = ImageIO.read(this.getClass().getResourceAsStream("/assets/player.png"));
		} catch (IOException e) {
			
		}
	}
	
	public Vec2 getPos() {
		return pos;
	}
	
	public Vec2 setPos(Vec2 p) {
		pos = p;
		return pos;
	}
	
	public Player(Vec2 startPos, BufferedImage img) {
		pos = startPos;
		this.img = img;
	}
	
	public void draw(Graphics2D g) {
		System.out.println("PLAYER DRAWN");
		g.drawImage(img, GamePanel.WIDTH/2-img.getWidth()/2, GamePanel.HEIGHT/2-img.getHeight()/2, null);
	}
	
	public void update(Tile above, Tile below, Tile left, Tile right) {
		if(Keys.isDown(Keys.UP) && !(Keys.isDown(Keys.LEFT) || Keys.isDown(Keys.DOWN) || Keys.isDown(Keys.RIGHT))) {
			if(above.isPassable()) pos.y--;
		} else if(Keys.isDown(Keys.LEFT) && !(Keys.isDown(Keys.UP) || Keys.isDown(Keys.DOWN) || Keys.isDown(Keys.RIGHT))) {
			if(left.isPassable()) pos.x--;
		} else if(Keys.isDown(Keys.DOWN) && !(Keys.isDown(Keys.UP) || Keys.isDown(Keys.LEFT) || Keys.isDown(Keys.RIGHT))) {
			if(below.isPassable()) pos.y++;
		} else if(Keys.isDown(Keys.RIGHT) && !(Keys.isDown(Keys.UP) || Keys.isDown(Keys.LEFT) || Keys.isDown(Keys.DOWN))) {
			if(right.isPassable()) pos.x++;
		}
		System.out.println(pos.x + ", " + pos.y);
	}

}
