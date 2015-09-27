package engine;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;

public class Tilemap {

	protected HashMap<Character, Tile> key;
	protected String[] data;
	
	public Tilemap(HashMap<Character, Tile> key, String[] data) {
		this.key = key;
		this.data = data;
	}
	
	public Tilemap(HashMap<Character, Tile> key, InputStream is, Charset encoding) {
		this.key = key;
		try {
			byte[] encoded = new byte[4096];
			is.read(encoded);
			is.close();
			String contents = new String(encoded, encoding);
			data = contents.split("\\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Tile getTile(int x, int y) {
		return key.get(data[y].charAt(x));
	}
	
	public void draw(Graphics2D g, Vec2 playerPos) {
		// (GamePanel.HEIGHT / GameManager.TILESIZE)/2;
		for(int row=0; row < (GamePanel.HEIGHT / GameManager.TILESIZE)+1; row++) {
			for(int col=0; col < (GamePanel.WIDTH / GameManager.TILESIZE)+1; row++) {
				try {
					g.drawImage(key.get(data[row+playerPos.y-(GamePanel.HEIGHT / GameManager.TILESIZE)/2].charAt(col+playerPos.x-(GamePanel.WIDTH / GameManager.TILESIZE)/2)).getImg(), row*GameManager.TILESIZE-GameManager.TILESIZE/2, col*GameManager.TILESIZE-GameManager.TILESIZE/2, null);
					System.out.println("In array: "+data[row+playerPos.y-(GamePanel.HEIGHT / GameManager.TILESIZE)/2].charAt(col+playerPos.x-(GamePanel.WIDTH / GameManager.TILESIZE)/2));
				} catch(Exception e) {
					g.drawImage(key.get('#').getImg(), row*GameManager.TILESIZE-GameManager.TILESIZE/2, col*GameManager.TILESIZE-GameManager.TILESIZE/2, null);
				}
			}
		}
	}

}
