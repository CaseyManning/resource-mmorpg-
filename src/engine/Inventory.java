package engine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import player.PlayerAttributes;

public class Inventory {

	BufferedImage background;
	PlayerAttributes attributes;

	public Inventory(PlayerAttributes attributes) {
		super();


		this.attributes = attributes;

		try {
			background = ImageIO.read(this.getClass().getResourceAsStream("/assets/Inventory.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g) {
		//Graphics g = getGraphics();
		g.drawImage(background, 0, 10, background.getWidth(), background.getHeight(), null);

		System.out.println("drawing Inventory");

		int b = 0;
		for(int i = 0; i < attributes.getItems().size(); i++) {

			if(i%3 == 0) {
				b++;
			}

			Item item = attributes.getItems().get(i);
			g.drawImage(item.getIcon(), i*10 + 3, 25 + b*10 - 10, item.getIcon().getWidth(), item.getIcon().getHeight(), null);
		}
	}
}

