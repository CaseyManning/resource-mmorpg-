package engine;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import player.PlayerAttributes;

public class Inventory extends JPanel {

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
		this.setSize(new Dimension(background.getWidth(), background.getHeight()));
	}

	public void draw() {
		Graphics g = getGraphics();
		g.drawImage(background, 0, 0, background.getWidth(), background.getHeight(), null);
		
		System.out.println("drawing Inventory");

		for(int i = 0; i < attributes.getItems().size(); i++) {
			Item item = attributes.getItems().get(i);
			g.drawImage(item.getIcon(), i*20, 10, item.getIcon().getWidth(), item.getIcon().getHeight(), null);
		}
	}
}
