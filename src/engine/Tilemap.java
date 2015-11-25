package engine;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Random;

public class Tilemap {

	protected Tile[][] data;
	protected int tilesize;
	
	public Tilemap(Tile[][] data, int tilesize) {
		this.data = data;
		this.tilesize = tilesize;
	}
	
	public Tilemap(HashMap<Character, Tile> key, String filepath, int tilesize) {
		this.tilesize = tilesize;
		try {
			InputStream is = this.getClass().getResourceAsStream(filepath);
			byte[] encoded = new byte[4096];
			is.read(encoded);
			is.close();
			String contents = new String(encoded, Charset.availableCharsets().get("UTF-8"));
			String[] lines = contents.split("\\n");
			data = new Tile[lines.length][lines[0].length()];
			for(int line = 0; line < lines.length; line++) {
				for(int ch = 0; ch < lines[0].length(); ch++) {
					data[line]
							[ch] = 
							key.get
							(lines[line]
									.charAt(ch));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Tile getTile(int x, int y) {
		return data[y][x];
	}
	
	public Tile getTile(Vec2 v) {
		return getTile(v.x, v.y);
	}
	
	public Vec2 getSize() {
		return new Vec2(this.data[0].length, this.data.length);
	}
	
	public Vec2 randomEmptyPosition() {
		Random r=new Random();
		Vec2 pos = new Vec2(r.nextInt(this.getSize().x), r.nextInt(this.getSize().y));
		while (this.getTile(pos).isPassable()) {
			pos = new Vec2(r.nextInt(this.getSize().x), r.nextInt(this.getSize().y));
		}
		return pos;
	}
	
	public void update(int elapsed) {
		
	}
	
	public void draw(Graphics2D g, int playerX, int playerY, Tile outOfBoundsTile) {
		// (GamePanel.HEIGHT / GameManager.TILESIZE)/2;
		
		for(int row=0; row < (GamePanel.HEIGHT / tilesize)+1; row++) {
			for(int col=0; col < (GamePanel.WIDTH / tilesize)+1; col++) {
				try {
					data[playerY+row-(GamePanel.HEIGHT / tilesize)/2][playerX+col-(GamePanel.WIDTH / tilesize)/2].draw(g, col, row, tilesize);
				} catch(Exception e) {
					outOfBoundsTile.draw(g, col, row, tilesize);
				}
//				try {
//					g.drawImage(data[row+playerPos.y-(GamePanel.HEIGHT / GameManager.TILESIZE)/2][col+playerPos.x-(GamePanel.WIDTH / GameManager.TILESIZE)/2].getImg(), row*GameManager.TILESIZE-GameManager.TILESIZE/2, col*GameManager.TILESIZE-GameManager.TILESIZE/2, null);
//					System.out.println("In array: "+data[row+playerPos.y-(GamePanel.HEIGHT / GameManager.TILESIZE)/2][col+playerPos.x-(GamePanel.WIDTH / GameManager.TILESIZE)/2]);
//				} catch(Exception e) {
//					g.drawImage(outOfBoundsTile.getImg(), row*GameManager.TILESIZE-GameManager.TILESIZE/2, col*GameManager.TILESIZE-GameManager.TILESIZE/2, null);
//				}
			}
		}
	}

}
