package player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.GamePanel;
import engine.Item;
import engine.Keys;
import engine.Vec2;

public class Player {

	Vec2 pos;
	PlayerAttributes attributes;
	BufferedImage img;

	public Player(Vec2 pos) {
		attributes = new PlayerAttributes();
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
		
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(8.0f));
		g.drawString("Health = " + Integer.toString(attributes.getHealth()), 2, 10);
		//add(l);
		for(Item t : attributes.getItems())
			t.draw(g, attributes.getItems().indexOf(t)*20 + 10, 5);
	}

	public void update(boolean abovePassable, boolean belowPassable, boolean leftPassable, boolean rightPassable) {
		if(Keys.isDown(Keys.UP) && !(Keys.isDown(Keys.LEFT) || Keys.isDown(Keys.DOWN) || Keys.isDown(Keys.RIGHT))) {
			if(abovePassable) pos.y--;
		//} else if(Keys.isDown(Keys.LEFT) && !(Keys.isDown(Keys.UP) || Keys.isDown(Keys.DOWN) || Keys.isDown(Keys.RIGHT))) {
		} else if(Keys.isDown(Keys.LEFT) && !(Keys.isDown(Keys.UP) || Keys.isDown(Keys.DOWN) || Keys.isDown(Keys.RIGHT))) {
			if(leftPassable) {
				pos.x--;
			} else {
				attributes.removeHealth(2);
			}
		} else if(Keys.isDown(Keys.DOWN) && !(Keys.isDown(Keys.UP) || Keys.isDown(Keys.LEFT) || Keys.isDown(Keys.RIGHT))) {
			if(belowPassable) pos.y++;
		} else if(Keys.isDown(Keys.RIGHT) && !(Keys.isDown(Keys.UP) || Keys.isDown(Keys.LEFT) || Keys.isDown(Keys.DOWN))) {
			if(rightPassable) pos.x++;
		}
		System.out.println(pos.x + ", " + pos.y);
	}
}

