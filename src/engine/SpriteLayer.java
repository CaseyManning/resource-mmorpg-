package engine;

import java.awt.Graphics2D;

import player.Player;

public class SpriteLayer {

	WizardSprite wizard;
	Player player;
	
	public SpriteLayer(WizardSprite wizard, Player player) {
		this.wizard = wizard;
		this.player = player;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(wizard.getImage(), wizard.getX(), wizard.getY(), null);
		g.drawImage(player.getImage(), player.getX(), player.getY(), null);
	}

}
