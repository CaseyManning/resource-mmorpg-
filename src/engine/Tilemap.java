package engine;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyStore.Entry;
import java.util.HashMap;

public class Tilemap {

	protected Tile[][] data;
	protected HashMap<Vec2, Item> items;
	protected HashMap<Vec2, Resource> resources;
	
	public Tilemap(Tile[][] data) {
		this.data = data;
	}
	
	public Tilemap(HashMap<Character, Tile> key, InputStream is, Charset encoding) {
		try {
			byte[] encoded = new byte[4096];
			is.read(encoded);
			is.close();
			String contents = new String(encoded, encoding);
			String[] lines = contents.split("\\n");
			data = new Tile[lines.length][lines[0].length()];
			for(int line = 0; line < lines.length; line++) {
				for(int ch = 0; ch < lines[0].length(); ch++) {
					data[line][ch] = key.get(lines[line].charAt(ch));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Item itemAt(Vec2 v) {
		if (items.containsKey(v)) {
			return items.get(v);
		}
		return null;
	}
	
	public Item itemAt(int x, int y) {
		return this.itemAt(new Vec2(x, y));
	}
	
	public Tile getTile(int x, int y) {
		return data[y][x];
	}
	
	public Tile getTile(Vec2 v) {
		return getTile(v.x, v.y);
	}
	
	public void update(int elapsed) {
		for(java.util.Map.Entry<Vec2, Resource> entry : resources.entrySet()) {
			resources.get(entry.getKey()).update(elapsed);
		}
	}
	
	public void draw(Graphics2D g, Vec2 playerPos, Tile outOfBoundsTile) {
		// (GamePanel.HEIGHT / GameManager.TILESIZE)/2;
		
		// WILL BE USED LATER:
//		for(java.util.Map.Entry<Vec2, Resource> entry : resources.entrySet()) {
//			
//		}
		
		System.out.println("TILEMAP DRAWN");
		
		for(int row=0; row < (GamePanel.HEIGHT / GameManager.TILESIZE)+1; row++) {
			for(int col=0; col < (GamePanel.WIDTH / GameManager.TILESIZE)+1; col++) {
				try {
					data[playerPos.y+row-(GamePanel.HEIGHT / GameManager.TILESIZE)/2][playerPos.x+col-(GamePanel.WIDTH / GameManager.TILESIZE)/2].draw(g, col, row);
				} catch(Exception e) {
					outOfBoundsTile.draw(g, col, row);
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
