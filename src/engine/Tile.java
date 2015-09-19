package com.tripplyons.tmp.engine;

import java.awt.image.BufferedImage;

public class Tile {

	protected BufferedImage img;
	protected boolean passable;
	
	public Tile(BufferedImage img, boolean passable) {
		this.img = img;
		this.passable = passable;
	}
	
	public boolean CanPass() {
		return passable;
	}
	
	public BufferedImage GetImg() {
		return img;
	}

}
