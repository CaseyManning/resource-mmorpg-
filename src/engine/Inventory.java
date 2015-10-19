package engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

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
		if(attributes.getItems().size() > 5) {
			for(int i = 0; i < 4; i++) {
				Item item = attributes.getItems().get(i);

				g.drawImage(item.getIcon(), i*10 + 3, 25, item.getIcon().getWidth(), item.getIcon().getHeight(), null);

			}
			if(attributes.getItems().size() < 9) {
				for(int i = 5; i < attributes.getItems().size(); i++) {
					Item item = attributes.getItems().get(i);

					g.drawImage(item.getIcon(), i*10 + 3 - 50, 35, item.getIcon().getWidth(), item.getIcon().getHeight(), null);
				}
			} else {
				for(int i = 5; i < 8; i++) {
					Item item = attributes.getItems().get(i);

					g.drawImage(item.getIcon(), i*10 + 3 - 50, 35, item.getIcon().getWidth(), item.getIcon().getHeight(), null);
				}
				for(int i = 8; i < attributes.getItems().size(); i++) {
					Item item = attributes.getItems().get(i);

					g.drawImage(item.getIcon(), i*10 + 3, 45, item.getIcon().getWidth(), item.getIcon().getHeight(), null);
				}
			}
		} else {
			for(int i = 0; i < attributes.getItems().size(); i++) {
				Item item = attributes.getItems().get(i);

				g.drawImage(item.getIcon(), i*10 + 3, 25, item.getIcon().getWidth(), item.getIcon().getHeight(), null);
			}
		}
	}
}
