package player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.GamePanel;
import engine.Item;
import engine.Keys;
import engine.Resource;
import engine.Vec2;

public class Player {

	Vec2 pos;
	public PlayerAttributes attributes;
	BufferedImage img;
	BufferedImage upImg;
	BufferedImage downImg;
	BufferedImage leftImg;
	BufferedImage rightImg;

	Direction dir;

	public Player(Vec2 pos) {
		attributes = new PlayerAttributes();
		this.pos = pos;
		try {
			img = ImageIO.read(this.getClass().getResourceAsStream("/assets/player.png"));
		} catch (IOException e) {  }
		try {
			upImg = ImageIO.read(this.getClass().getResourceAsStream("/assets/playerup.png"));
		} catch (IOException e) {  }
		try {
			downImg = ImageIO.read(this.getClass().getResourceAsStream("/assets/playerdown.png"));
		} catch (IOException e) {  }
		try {
			leftImg = ImageIO.read(this.getClass().getResourceAsStream("/assets/playerleft.png"));
		} catch (IOException e) {  }
		try {
			rightImg = ImageIO.read(this.getClass().getResourceAsStream("/assets/playerright.png"));
		} catch (IOException e) {  }

		dir = Direction.NULL;
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

		g.drawImage(img, GamePanel.WIDTH/2-img.getWidth()/2, GamePanel.HEIGHT/2-img.getHeight()/2, null);

		// g.drawImage(img, GamePanel.WIDTH/2-img.getWidth()/2, GamePanel.HEIGHT/2-img.getHeight()/2, null);
		BufferedImage drawImg = img;
		switch (dir) {
		case UP:
			drawImg = upImg;
			break;
		case DOWN:
			drawImg = downImg;
			break;
		case LEFT:
			drawImg = leftImg;
			break;
		case RIGHT:
			drawImg = rightImg;
			break;
		default:
			break;
		}
		g.drawImage(drawImg, GamePanel.WIDTH/2-img.getWidth()/2, GamePanel.HEIGHT/2-img.getHeight()/2, null);


		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(8.0f));
		g.drawString("Health = " + Integer.toString(attributes.getHealth()), 2, 10);
		//add(l);
		for(Item t : attributes.getItems()) {
			t.draw(g, attributes.getItems().indexOf(t)*20 + 10, GamePanel.HEIGHT);
		}
		Keys.get.clear();
		
	}

	public void update(boolean abovePassable, boolean belowPassable, boolean leftPassable, boolean rightPassable, Resource aboveResource, Resource belowResource, Resource leftResource, Resource rightResource) {
		if(Keys.isDown(Keys.UP)) {
			if(abovePassable) pos.y--; else attributes.removeHealth(2);
			dir = Direction.UP;
			//} else if(Keys.isDown(Keys.LEFT) && !(Keys.isDown(Keys.UP) || Keys.isDown(Keys.DOWN) || Keys.isDown(Keys.RIGHT))) {
		} else if(Keys.isDown(Keys.LEFT)) {
			if(leftPassable) pos.x--; else attributes.removeHealth(2);
			dir = Direction.LEFT;
		} else if(Keys.isDown(Keys.DOWN)) {
			if(belowPassable) pos.y++; else attributes.removeHealth(2);
			dir = Direction.DOWN;
		} else if(Keys.isDown(Keys.RIGHT) && !(Keys.isDown(Keys.UP) || Keys.isDown(Keys.LEFT) || Keys.isDown(Keys.DOWN))) {
			if(rightPassable) pos.x++; else attributes.removeHealth(2);
			dir = Direction.RIGHT;
		}

		for(Item i : Keys.get) {
			attributes.addItem(i);
		}
		if (Keys.isDown(Keys.PICKUP)) {
			switch(dir) {
			case UP:
				Item aboveItem = aboveResource.grabItem();
				if (aboveItem != null)
					attributes.addItem(aboveItem);
				break;
			case DOWN:
				Item belowItem = belowResource.grabItem();
				if (belowItem != null)
					attributes.addItem(belowItem);
				break;
			case LEFT:
				Item leftItem = leftResource.grabItem();
				if (leftItem != null)
					attributes.addItem(leftItem);
				break;
			case RIGHT:
				Item rightItem = rightResource.grabItem();
				if (rightItem != null)
					attributes.addItem(rightItem);
				break;
			default:

				break;
			}
		}


	}

	public Direction getDir() {
		return dir;
	}
}

